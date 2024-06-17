package com.jacob.micro.auth.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.jacob.micro.auth.constant.RedisKeyConstants;
import com.jacob.micro.auth.enums.ResponseCodeEnum;
import com.jacob.micro.auth.model.vo.verificationcode.SendVerificationCodeReqVO;
import com.jacob.micro.auth.service.VerificationCodeService;
import com.jacob.micro.framework.common.exception.BizException;
import com.jacob.micro.framework.common.response.Response;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Jacob
 * @Description: 验证码业务接口实现类
 * @Date: 2024/6/16 18:34
 * @Version: 1.0
 */
@Service
@Slf4j
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 发送验证码
     * @param sendVerificationCodeReqVO
     * @return
     */
    @Override
    public Response<?> send(SendVerificationCodeReqVO sendVerificationCodeReqVO) {
        // 获取手机号
        String phone = sendVerificationCodeReqVO.getPhone();

        // 构建验证码 redis key
        String key = RedisKeyConstants.buildVerificationCodeKey(phone);

        // 判断是否已发送验证码
        boolean isSent = redisTemplate.hasKey(key);
        if (isSent) {
            // 若之前发送的验证码未过期，则提示发送频繁
            throw new BizException(ResponseCodeEnum.VERIFICATION_CODE_SEND_FREQUENTLY);
        }

        // 生成6位数字的随机验证码
        String verificationCode = RandomUtil.randomNumbers(6);

        // todo：调用第三方短信发送服务

        log.info("==》 手机号：{}， 已发送验证码：【{}】", phone, verificationCode);

        // 存储验证码到redis中，并设置过期时间3分钟
        redisTemplate.opsForValue().set(key, verificationCode, 3, TimeUnit.MINUTES);

        return Response.success();
    }
}
