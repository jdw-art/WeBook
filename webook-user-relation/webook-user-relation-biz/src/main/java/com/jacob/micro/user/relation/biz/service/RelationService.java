package com.jacob.micro.user.relation.biz.service;

import com.jacob.micro.framework.common.response.Response;
import com.jacob.micro.user.relation.biz.model.vo.FollowUserReqVO;
import com.jacob.micro.user.relation.biz.model.vo.UnfollowUserReqVO;

/**
 * @Author: Jacob
 * @Description: 关注业务
 * @Date: 2025/1/19 20:13
 * @Version: 1.0
 */
public interface RelationService {

    /**
     * 关注用户
     * @param followUserReqVO
     * @return
     */
    Response<?> follow(FollowUserReqVO followUserReqVO);

    /**
     * 取关用户
     * @param unfollowUserReqVO
     * @return
     */
    Response<?> unfollow(UnfollowUserReqVO unfollowUserReqVO);
}
