package com.jacob.micro.user.relation.biz;

import com.jacob.micro.framework.common.util.JsonUtils;
import com.jacob.micro.user.relation.biz.constant.MQConstants;
import com.jacob.micro.user.relation.biz.model.dto.FollowUserMqDTO;
import com.jacob.micro.user.relation.biz.model.dto.UnfollowUserMqDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.time.LocalDateTime;

/**
 * @Author: Jacob
 * @Description: TODO
 * @Date: 2025/1/20 9:41
 * @Version: 1.0
 */
@SpringBootTest
@Slf4j
public class MQTests {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Test
    void testBatchSendMQ() {
        for (long i = 0; i < 10000; i++) {
            // 构建消息体 DTO
            FollowUserMqDTO followUserMqDTO = FollowUserMqDTO.builder()
                    .userId(i)
                    .followUserId(i)
                    .createTime(LocalDateTime.now())
                    .build();

            // 构建消息对象，并将 DTO 转成 Json 字符串设置到消息体中
            Message<String> message = MessageBuilder.withPayload(JsonUtils.toJsonString(followUserMqDTO))
                    .build();

            // 通过冒号连接, 可让 MQ 发送给主题 Topic 时，携带上标签 Tag
            String destination = MQConstants.TOPIC_FOLLOW_OR_UNFOLLOW + ":" + MQConstants.TAG_FOLLOW;

            log.info("==> 开始发送关注操作 MQ, 消息体: {}", followUserMqDTO);

            // 异步发送 MQ 消息，提升接口响应速度
            rocketMQTemplate.asyncSend(destination, message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("==> MQ 发送成功，SendResult: {}", sendResult);
                }

                @Override
                public void onException(Throwable throwable) {
                    log.error("==> MQ 发送异常: ", throwable);
                }
            });
        }
    }

    /**
     * 测试：发送对同一个用户关注、取关 MQ
     */
    @Test
    void testSendFollowUnfollowMQ() {
        // 操作者用户ID
        Long userId = 12L;
        // 目标用户ID
        Long targetUserId = 11L;

        for (long i = 0; i < 10; i++) {
            if (i % 2 == 0) { // 偶数发送关注 MQ
                log.info("{} 是偶数", i);

                // 发送 MQ
                // 构建消息体 DTO
                FollowUserMqDTO followUserMqDTO = FollowUserMqDTO.builder()
                        .userId(userId)
                        .followUserId(targetUserId)
                        .createTime(LocalDateTime.now())
                        .build();

                // 构建消息对象，并将 DTO 转成 Json 字符串设置到消息体中
                Message<String> message = MessageBuilder.withPayload(JsonUtils.toJsonString(followUserMqDTO))
                        .build();

                // 通过冒号连接, 可让 MQ 发送给主题 Topic 时，携带上标签 Tag
                String destination = MQConstants.TOPIC_FOLLOW_OR_UNFOLLOW + ":" + MQConstants.TAG_FOLLOW;

                String hashKey = String.valueOf(userId);

                // 发送 MQ 消息
                SendResult sendResult = rocketMQTemplate.syncSendOrderly(destination, message, hashKey);

                log.info("==> MQ 发送结果，SendResult: {}", sendResult);
            } else { // 取关发送取关 MQ
                log.info("{} 是奇数", i);

                // 发送 MQ
                // 构建消息体 DTO
                UnfollowUserMqDTO unfollowUserMqDTO = UnfollowUserMqDTO.builder()
                        .userId(userId)
                        .unfollowUserId(targetUserId)
                        .createTime(LocalDateTime.now())
                        .build();

                // 构建消息对象，并将 DTO 转成 Json 字符串设置到消息体中
                Message<String> message = MessageBuilder.withPayload(JsonUtils.toJsonString(unfollowUserMqDTO))
                        .build();

                // 通过冒号连接, 可让 MQ 发送给主题 Topic 时，携带上标签 Tag
                String destination = MQConstants.TOPIC_FOLLOW_OR_UNFOLLOW + ":" + MQConstants.TAG_UNFOLLOW;

                String hashKey = String.valueOf(userId);

                // 发送 MQ 消息
                SendResult sendResult = rocketMQTemplate.syncSendOrderly(destination, message, hashKey);

                log.info("==> MQ 发送结果，SendResult: {}", sendResult);
            }
        }
    }
}