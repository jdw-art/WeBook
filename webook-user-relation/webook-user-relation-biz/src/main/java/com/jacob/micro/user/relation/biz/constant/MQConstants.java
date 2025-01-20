package com.jacob.micro.user.relation.biz.constant;

/**
 * @Author: Jacob
 * @Description: MQ 常量
 * @Date: 2025/1/19 21:26
 * @Version: 1.0
 */
public interface MQConstants {

    /**
     * Topic: 关注、取关共用一个
     */
    String TOPIC_FOLLOW_OR_UNFOLLOW = "FollowUnfollowTopic";

    /**
     * 关注标签
     */
    String TAG_FOLLOW = "Follow";

    /**
     * 取关标签
     */
    String TAG_UNFOLLOW = "Unfollow";
}
