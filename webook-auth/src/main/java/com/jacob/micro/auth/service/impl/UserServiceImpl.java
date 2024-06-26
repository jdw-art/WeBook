package com.jacob.micro.auth.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.jacob.micro.auth.constant.RedisKeyConstants;
import com.jacob.micro.auth.constant.RoleConstants;
import com.jacob.micro.auth.domain.dataobject.RoleDO;
import com.jacob.micro.auth.domain.dataobject.UserDO;
import com.jacob.micro.auth.domain.dataobject.UserRoleDO;
import com.jacob.micro.auth.domain.mapper.RoleDOMapper;
import com.jacob.micro.auth.domain.mapper.UserDOMapper;
import com.jacob.micro.auth.domain.mapper.UserRoleDOMapper;
import com.jacob.micro.auth.enums.LoginTypeEnum;
import com.jacob.micro.auth.enums.ResponseCodeEnum;
import com.jacob.micro.auth.model.vo.user.UpdatePasswordReqVO;
import com.jacob.micro.auth.model.vo.user.UserLoginRepVO;
import com.jacob.micro.auth.service.UserService;
import com.jacob.micro.framework.biz.context.holder.LoginUserContextHolder;
import com.jacob.micro.framework.common.enums.DeletedEnum;
import com.jacob.micro.framework.common.enums.StatusEnum;
import com.jacob.micro.framework.common.exception.BizException;
import com.jacob.micro.framework.common.response.Response;
import com.jacob.micro.framework.common.util.JsonUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: Jacob
 * @Description: 用户服务实现类
 * @Date: 2024/6/17 14:42
 * @Version: 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserDOMapper userDOMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private UserRoleDOMapper userRoleDOMapper;
    @Resource
    private TransactionTemplate transactionTemplate;
    @Resource
    private RoleDOMapper roleDOMapper;
    @Resource(name = "taskExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Response<String> loginAndRegister(UserLoginRepVO userLoginRepVO) {

        String phone = userLoginRepVO.getPhone();
        Integer type = userLoginRepVO.getType();

        LoginTypeEnum loginTypeEnum = LoginTypeEnum.valueOf(type);

        // 登录类型错误
        if (Objects.isNull(loginTypeEnum)) {
            throw new BizException(ResponseCodeEnum.LOGIN_TYPE_ERROR);
        }

        Long userId = null;

        // 判断登录类型
        switch (loginTypeEnum) {
            case VERIFICATION_CODE:
                // 验证码登录
                String verificationCode = userLoginRepVO.getCode();

                // 校验验证码
                Preconditions.checkArgument(StringUtils.isNotBlank(verificationCode), "验证码不能为空");

                // 构建验证码 Redis key
                String key = RedisKeyConstants.buildVerificationCodeKey(phone);
                String sentCode = (String) redisTemplate.opsForValue().get(key);

                // 判断用户提交的验证码，与redis中的验证码是否匹配
                if (!StringUtils.equals(verificationCode, sentCode)) {
                    throw new BizException(ResponseCodeEnum.VERIFICATION_CODE_SEND_FREQUENTLY);
                }

                // 通过手机号查询记录
                UserDO userDO = userDOMapper.selectByPhone(phone);

                log.info("==> 用户是否注册，phone：{}，userDo：{}", phone, JsonUtils.toJsonString(userDO));

                // 判断用户是否注册
                if (Objects.isNull(userDO)) {
                    // 若此用户还没有注册，系统自动注册该用户
                    userId = registerUser(phone);
                } else {
                    // 已注册用户，则获取用户ID
                    userId = userDO.getId();
                }
                break;
            case PASSWORD:
                // 密码登录
                String password = userLoginRepVO.getPassword();
                // 根据手机号查询
                UserDO userDO1 = userDOMapper.selectByPhone(phone);

                // 判断该手机号是否注册
                if (Objects.isNull(userDO1)) {
                    throw new BizException(ResponseCodeEnum.USER_NOT_FOUND);
                }

                // 拿到密文密码
                String encodedPassword = userDO1.getPassword();

                // 匹配密码是否一致
                boolean isPasswordCorrect = passwordEncoder.matches(password, encodedPassword);

                // 如果不正确，则抛出业务异常，提示用户名或密码不正确
                if (!isPasswordCorrect) {
                    throw new BizException(ResponseCodeEnum.PHONE_OR_PASSWORD_ERROR);
                }

                userId = userDO1.getId();
                break;
            default:
                break;
        }

        // SaToken 登录用户，入参为用户ID
        StpUtil.login(userId);

        // 获取Token
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

        // 返回token令牌
        return Response.success(tokenInfo.tokenValue);
    }

    /**
     * 系统自动注册用户
     * @param phone
     * @return
     */
    private Long registerUser(String phone) {
        return transactionTemplate.execute(status -> {
           try {
               // 获取全局自增的微书 ID
               Long webookId = redisTemplate.opsForValue().increment(RedisKeyConstants.WEBOOK_ID_GENERATOR_KEY);

               UserDO userDO = UserDO.builder()
                       .phone(phone)
                       .xiaohashuId(String.valueOf(webookId))  // 自动生成小红书号 ID
                       .nickname("微书" + webookId)             // 自动生成昵称, 如：小红薯10000
                       .status(StatusEnum.ENABLE.getValue())   // 状态为启用
                       .createTime(LocalDateTime.now())
                       .updateTime(LocalDateTime.now())
                       .isDeleted(DeletedEnum.NO.getValue())   // 逻辑删除
                       .build();

               // 添加入库
               userDOMapper.insert(userDO);

               // 获取刚刚添加入库的用户ID
               Long userId = userDO.getId();

               // 验证用户分配一个默认角色
               UserRoleDO userRoleDO = UserRoleDO.builder()
                       .userId(userId)
                       .roleId(RoleConstants.COMMON_USER_ROLE_ID)
                       .createTime(LocalDateTime.now())
                       .updateTime(LocalDateTime.now())
                       .isDeleted(DeletedEnum.NO.getValue())
                       .build();
               userRoleDOMapper.insert(userRoleDO);

               RoleDO roleDO = roleDOMapper.selectByPrimaryKey(RoleConstants.COMMON_USER_ROLE_ID);

               // 将改用户的角色id存入redis中，指定初始容量为 1，这样可以减少在扩容时的性能开销
               List<String> roles = new ArrayList<>();
               roles.add(roleDO.getRoleKey());

               String userRolesKey = RedisKeyConstants.buildUserRoleKey(userId);
               redisTemplate.opsForValue().set(userRolesKey, JsonUtils.toJsonString(roles));

               return userId;
           } catch (Exception e) {
               status.setRollbackOnly();
               log.error("==> 系统注册用户异常：", e);
               return null;
           }
        });
    }

    /**
     * 退出登录
     * @return
     */
    @Override
    public Response<?> logout() {

        Long userId = LoginUserContextHolder.getUserId();

        log.info("==> 用户退出登录, userId: {}", userId);

        threadPoolTaskExecutor.submit(() -> {
            Long userId2 = LoginUserContextHolder.getUserId();
            log.info("==> 异步线程中获取 userId: {}", userId2);
        });

        // 退出登录 (指定用户 ID)
        StpUtil.logout(userId);

        return Response.success();
    }

    /**
     * 修改面膜
     * @param updatePasswordReqVO
     * @return
     */
    @Override
    public Response<?> updatePassword(UpdatePasswordReqVO updatePasswordReqVO) {
        // 新密码
        String newPassword = updatePasswordReqVO.getNewPassword();
        // 加密后的密码
        String encodePassword = passwordEncoder.encode(newPassword);

        // 获取当前请求对应的用户ID
        Long userId = LoginUserContextHolder.getUserId();

        UserDO userDO = UserDO.builder()
                .id(userId)
                .password(encodePassword)
                .updateTime(LocalDateTime.now())
                .build();

        // 更新密码
        userDOMapper.updateByPrimaryKeySelective(userDO);

        return Response.success();
    }
}
