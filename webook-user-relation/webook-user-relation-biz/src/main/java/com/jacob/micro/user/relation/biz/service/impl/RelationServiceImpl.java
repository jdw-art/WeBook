package com.jacob.micro.user.relation.biz.service.impl;

import com.jacob.micro.framework.biz.context.holder.LoginUserContextHolder;
import com.jacob.micro.framework.common.exception.BizException;
import com.jacob.micro.framework.common.response.Response;
import com.jacob.micro.user.dto.rsp.FindUserByIdRspDTO;
import com.jacob.micro.user.relation.biz.enums.ResponseCodeEnum;
import com.jacob.micro.user.relation.biz.model.vo.FollowUserReqVO;
import com.jacob.micro.user.relation.biz.rpc.UserRpcService;
import com.jacob.micro.user.relation.biz.service.RelationService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author: Jacob
 * @Description: 关注业务
 * @Date: 2025/1/19 20:13
 * @Version: 1.0
 */
@Service
@Slf4j
public class RelationServiceImpl implements RelationService {

    @Resource
    private UserRpcService userRpcService;

    @Override
    public Response<?> follow(FollowUserReqVO followUserReqVO) {
        // 关注的用户 ID
        Long followUserId = followUserReqVO.getFollowUserId();

        // 当前登录的用户 ID
        Long userId = LoginUserContextHolder.getUserId();

        // 校验：无法关注自己
        if (Objects.equals(userId, followUserId)) {
            throw new BizException(ResponseCodeEnum.CANT_FOLLOW_YOUR_SELF);
        }

        // TODO: 校验关注的用户是否存在
        FindUserByIdRspDTO findUserByIdRspDTO = userRpcService.findById(followUserId);
        if (Objects.isNull(findUserByIdRspDTO)) {
            throw new BizException(ResponseCodeEnum.FOLLOW_USER_NOT_EXISTED);
        }

        // TODO: 校验关注的数量是否达到上限

        // TODO: 写入 Redis ZSET 关注列表

        // TODO: 发送 MQ

        return Response.success();
    }
}
