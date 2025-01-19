package com.jacob.micro.note.biz.rpc;

import com.jacob.micro.framework.common.response.Response;
import com.jacob.micro.user.api.UserFeignApi;
import com.jacob.micro.user.dto.req.FindUserByIdReqDTO;
import com.jacob.micro.user.dto.rsp.FindUserByIdRspDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: Jacob
 * @Description: TODO
 * @Date: 2025/1/19 14:38
 * @Version: 1.0
 */
@Component
public class UserRpcService {

    @Resource
    private UserFeignApi userFeignApi;

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    public FindUserByIdRspDTO findById(Long userId) {
        FindUserByIdReqDTO findUserByIdReqDTO = new FindUserByIdReqDTO();
        findUserByIdReqDTO.setId(userId);

        Response<FindUserByIdRspDTO> response = userFeignApi.findById(findUserByIdReqDTO);

        if (Objects.isNull(response) || !response.isSuccess()) {
            return null;
        }
        return response.getData();
    }
}
