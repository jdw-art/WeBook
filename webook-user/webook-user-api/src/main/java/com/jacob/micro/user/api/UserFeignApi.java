package com.jacob.micro.user.api;

import com.jacob.micro.framework.common.response.Response;
import com.jacob.micro.user.constant.ApiConstants;
import com.jacob.micro.user.dto.req.FindUserByIdReqDTO;
import com.jacob.micro.user.dto.rsp.FindUserByIdRspDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: Jacob
 * @Description: TODO
 * @Date: 2025/1/19 12:41
 * @Version: 1.0
 */
@FeignClient(name = ApiConstants.SERVICE_NAME)
public interface UserFeignApi {

    String PREFIX = "/user";

    @PostMapping(value = PREFIX + "/findById")
    Response<FindUserByIdRspDTO> findById(@RequestBody FindUserByIdReqDTO findUserByIdReqDTO);
}
