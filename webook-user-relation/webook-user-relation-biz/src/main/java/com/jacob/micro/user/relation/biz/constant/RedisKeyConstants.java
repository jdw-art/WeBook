package com.jacob.micro.user.relation.biz.constant;

/**
 * @Author: Jacob
 * @Description: TODO
 * @Date: 2025/1/19 20:39
 * @Version: 1.0
 */
public class RedisKeyConstants {

    /**
     * 关注列表 KEY 前缀
     */
    private static final String USER_FOLLOWING_KEY_PREFIX = "following:";

    /**
     * 构建关注列表完整的 KEY
     * @param userId
     * @return
     */
    public static String buildUserFollowingKey(Long userId) {
        return USER_FOLLOWING_KEY_PREFIX + userId;
    }
}
