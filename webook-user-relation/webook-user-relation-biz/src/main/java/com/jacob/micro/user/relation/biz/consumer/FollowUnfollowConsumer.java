package com.jacob.micro.user.relation.biz.consumer;

import com.google.common.util.concurrent.RateLimiter;
import com.jacob.micro.framework.common.util.DateUtils;
import com.jacob.micro.framework.common.util.JsonUtils;
import com.jacob.micro.user.relation.biz.constant.MQConstants;
import com.jacob.micro.user.relation.biz.constant.RedisKeyConstants;
import com.jacob.micro.user.relation.biz.domain.dataobject.FansDO;
import com.jacob.micro.user.relation.biz.domain.dataobject.FollowingDO;
import com.jacob.micro.user.relation.biz.domain.mapper.FansDOMapper;
import com.jacob.micro.user.relation.biz.domain.mapper.FollowingDOMapper;
import com.jacob.micro.user.relation.biz.model.dto.FollowUserMqDTO;
import com.jacob.micro.user.relation.biz.model.dto.UnfollowUserMqDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;

/**
 * @Author: Jacob
 * @Description: 关注、取关 MQ 消费者
 * @Date: 2025/1/19 21:34
 * @Version: 1.0
 */
@Component
@RocketMQMessageListener(consumerGroup = "webook_group", // Group 组
        topic = MQConstants.TOPIC_FOLLOW_OR_UNFOLLOW // 消费的主题 Topic
)
@Slf4j
public class FollowUnfollowConsumer implements RocketMQListener<Message> {

    @Resource
    private FollowingDOMapper followingDOMapper;

    @Resource
    private FansDOMapper fansDOMapper;

    @Resource
    private TransactionTemplate transactionTemplate;

    // 每秒创建5000个令牌
    @Resource
    private RateLimiter rateLimiter;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void onMessage(Message message) {

        // 流量削峰，通过获取令牌，如果没有令牌可用，将阻塞，直接获得
        rateLimiter.acquire();

        // 消息体
        String bodyJsonStr = new String(message.getBody());
        // 标签
        String tags = message.getTags();

        log.info("===> FollowUnfollowConsumer 消费了消息 {}，tags：{}", bodyJsonStr, tags);

        // 根据 MQ 的tag标签，判断操作类型
        if (Objects.equals(tags, MQConstants.TAG_FOLLOW)) {
            // 关注
            handleFollowTagMessage(bodyJsonStr);
        } else if (Objects.equals(tags, MQConstants.TAG_UNFOLLOW)) {
            // 取关
            handleUnfollowTagMessage(bodyJsonStr);
        }
    }

    /**
     * 关注
     * @param bodyJsonStr
     */
    private void handleFollowTagMessage(String bodyJsonStr) {
        // 将消息体 Json 字符串转为 DTO 对象
        FollowUserMqDTO followUserMqDTO = JsonUtils.parseObject(bodyJsonStr, FollowUserMqDTO.class);

        // 判空
        if (Objects.isNull(followUserMqDTO)) return;

        // 幂等性：通过联合唯一索引保证

        Long userId = followUserMqDTO.getUserId();
        Long followUserId = followUserMqDTO.getFollowUserId();
        LocalDateTime createTime = followUserMqDTO.getCreateTime();

        // 编程式提交事务
        boolean isSuccess = Boolean.TRUE.equals(transactionTemplate.execute(status -> {
            try {
                // 关注成功需往数据库添加两条记录
                // 关注表：一条记录
                int count = followingDOMapper.insert(FollowingDO.builder()
                        .userId(userId)
                        .followingUserId(followUserId)
                        .createTime(createTime)
                        .build());

                // 粉丝表：一条记录
                if (count > 0) {
                    fansDOMapper.insert(FansDO.builder()
                            .userId(followUserId)
                            .fansUserId(userId)
                            .createTime(createTime)
                            .build());
                }
                return true;
            } catch (Exception ex) {
                status.setRollbackOnly(); // 标记事务为回滚
                log.error("", ex);
            }
            return false;
        }));

        log.info("## 数据库添加记录结果：{}", isSuccess);

        // 若数据库操作成功，更新 Redis 中被关注用户的 ZSet 粉丝列表
        if (isSuccess) {
            // Lua 脚本
            DefaultRedisScript<Long> script = new DefaultRedisScript<>();
            script.setScriptSource(new ResourceScriptSource(new ClassPathResource("/lua/follow_check_and_update_fans_zset.lua")));
            script.setResultType(Long.class);

            // 时间戳
            long timestamp = DateUtils.localDateTime2Timestamp(createTime);

            // 构建被关注用户的粉丝列表 Redis Key
            String fansRedisKey = RedisKeyConstants.buildUserFansKey(followUserId);
            // 执行脚本
            Long isS = redisTemplate.execute(script, Collections.singletonList(fansRedisKey), userId, timestamp);
        }

    }

    /**
     * 取消关注
     * @param bodyJsonStr
     */
    private void handleUnfollowTagMessage(String bodyJsonStr) {
        // 将消息体 Json 字符串转为 DTO 对象
        UnfollowUserMqDTO unfollowUserMqDTO = JsonUtils.parseObject(bodyJsonStr, UnfollowUserMqDTO.class);

        // 判空
        if (Objects.isNull(unfollowUserMqDTO)) return;

        Long userId = unfollowUserMqDTO.getUserId();
        Long unfollowUserId = unfollowUserMqDTO.getUnfollowUserId();
        LocalDateTime createTime = unfollowUserMqDTO.getCreateTime();

        // 编程式事务提交
        boolean isSuccess = Boolean.TRUE.equals(transactionTemplate.execute(status -> {
            try {
                // 取关成功需要删除两条数据库记录
                // 关注表：一条记录
                int count = followingDOMapper.deleteByUserIdAndFollowingUserId(userId, unfollowUserId);

                // 粉丝表：一条记录
                if (count > 0) {
                    fansDOMapper.deleteByUserIdAndFansUserId(unfollowUserId, userId);
                }
                return true;
            } catch (Exception ex) {
                status.setRollbackOnly(); // 标记事务为回滚
                log.error("", ex);
            }
            return false;
        }));

        // TODO: 若数据库删除成功，更新 Redis，将自己从取关的用户的 ZSet 粉丝列表中移除
        if (isSuccess) {
            // 被取关用户的粉丝列表 Redis Key
            String fansRedisKey = RedisKeyConstants.buildUserFansKey(unfollowUserId);
            // 删除指定粉丝
            redisTemplate.opsForZSet().remove(fansRedisKey, userId);
        }
    }
}
