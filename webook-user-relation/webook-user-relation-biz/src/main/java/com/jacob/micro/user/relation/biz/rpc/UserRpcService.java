package com.jacob.micro.user.relation.biz.rpc;

import com.jacob.micro.framework.common.response.Response;
import com.jacob.micro.user.api.UserFeignApi;
import com.jacob.micro.user.dto.req.FindUserByIdReqDTO;
import com.jacob.micro.user.dto.rsp.FindUserByIdRspDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: Jacob
 * @Description: 用户服务
 * @Date: 2025/1/19 20:18
 * @Version: 1.0
 */
@Component
public class UserRpcService {

    @Resource
    private UserFeignApi userFeignApi;

    /**
     * 根据用户 ID 查询用户信息
     * @param userId
     * @return
     */
    public FindUserByIdRspDTO findById(Long userId) {
        FindUserByIdReqDTO findUserByIdReqDTO = new FindUserByIdReqDTO();
        findUserByIdReqDTO.setId(userId);

        Response<FindUserByIdRspDTO> response = userFeignApi.findById(findUserByIdReqDTO);

        if (!response.isSuccess() || Objects.isNull(response.getData())) {
            return null;
        }

        return response.getData();
    }
}
