package com.jacob.micro.auth.constant;

/**
 * @Author: Jacob
 * @Description: Redis常量类
 * @Date: 2024/6/16 18:30
 * @Version: 1.0
 */
public class RedisKeyConstants {

    /**
     * 验证码KEY前缀
     */
    private static final String VERIFICATION_CODE_KEY_PREFIX = "verification_code:";

    /**
     * 构建验证码KEY
     * @param phone
     * @return
     */
    public static String buildVerificationCodeKey(String phone) {
        return VERIFICATION_CODE_KEY_PREFIX + phone;
    }
}
