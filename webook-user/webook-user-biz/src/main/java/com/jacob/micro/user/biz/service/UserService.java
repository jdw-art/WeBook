package com.jacob.micro.user.biz.service;

import com.jacob.micro.framework.common.response.Response;
import com.jacob.micro.user.biz.model.vo.UpdateUserInfoReqVO;
import com.jacob.micro.user.dto.req.FindUserByIdReqDTO;
import com.jacob.micro.user.dto.req.FindUsersByIdsReqDTO;
import com.jacob.micro.user.dto.rsp.FindUserByIdRspDTO;

import java.util.List;

/**
 * @Author: Jacob
 * @Description: 更新用户接口
 * @Date: 2024/7/12 22:12
 * @Version: 1.0
 */
public interface UserService {

    /**
     * 更新用户信息
     * @param updateUserInfoReqVO
     * @return
     */
    Response<?> updateUserInfo(UpdateUserInfoReqVO updateUserInfoReqVO);

    /**
     * 根据用户 ID 查询用户信息
     * @param findUserByIdReqDTO
     * @return
     */
    Response<FindUserByIdRspDTO> findByUserId(FindUserByIdReqDTO findUserByIdReqDTO);

    /**
     * 批量根据用户 ID 查询用户信息
     *
     * @param findUsersByIdsReqDTO
     * @return
     */
    Response<List<FindUserByIdRspDTO>> findByIds(FindUsersByIdsReqDTO findUsersByIdsReqDTO);
}
