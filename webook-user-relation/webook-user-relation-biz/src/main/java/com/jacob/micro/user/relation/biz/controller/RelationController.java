package com.jacob.micro.user.relation.biz.controller;

import com.jacob.micro.framework.biz.operationlog.aspect.ApiOperationLog;
import com.jacob.micro.framework.common.response.Response;
import com.jacob.micro.user.relation.biz.model.vo.FollowUserReqVO;
import com.jacob.micro.user.relation.biz.model.vo.UnfollowUserReqVO;
import com.jacob.micro.user.relation.biz.service.RelationService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Jacob
 * @Description: 用户关系服务
 * @Date: 2025/1/19 20:23
 * @Version: 1.0
 */
@RestController
@RequestMapping("/relation")
@Slf4j
public class RelationController {

    @Resource
    private RelationService relationService;

    @PostMapping("/follow")
    @ApiOperationLog(description = "关注用户")
    public Response<?> follow(@Validated @RequestBody FollowUserReqVO followUserReqVO) {
        return relationService.follow(followUserReqVO);
    }

    @PostMapping("/unfollow")
    @ApiOperationLog(description = "取关用户")
    public Response<?> unfollow(@Validated @RequestBody UnfollowUserReqVO unfollowUserReqVO) {
        return relationService.unfollow(unfollowUserReqVO);
    }
}
