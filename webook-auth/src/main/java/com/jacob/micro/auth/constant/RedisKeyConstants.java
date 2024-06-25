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
     * 微书全局 ID 生成器 KEY
     */
    public static final String WEBOOK_ID_GENERATOR_KEY = "webook.id.generator";

    /**
     * 构建验证码KEY
     * @param phone
     * @return
     */
    public static String buildVerificationCodeKey(String phone) {
        return VERIFICATION_CODE_KEY_PREFIX + phone;
    }

    /**
     * 用户角色数据 KEY 前缀
     */
    private static final String USER_ROLE_KEY_PREFIX = "user:roles:";

    /**
     * 构建验证码 KEY
     * @param userId
     * @return
     */
    public static String buildUserRoleKey(Long userId) {
        return USER_ROLE_KEY_PREFIX + userId;
    }

    /**
     * 角色对应的权限集合 KEY 前缀
     */
    private static final String ROLE_PERMISSION_KEY_PREFIX = "role:permissions:";

    /**
     * 构建角色对应的权限集合 KEY
     */
    public static String buildRolePermissionsKey(String roleKey) {
        return ROLE_PERMISSION_KEY_PREFIX + roleKey;
    }
}
