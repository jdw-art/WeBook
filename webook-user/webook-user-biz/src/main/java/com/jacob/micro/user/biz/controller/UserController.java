package com.jacob.micro.user.biz.controller;

import com.jacob.micro.framework.biz.operationlog.aspect.ApiOperationLog;
import com.jacob.micro.framework.common.response.Response;
import com.jacob.micro.user.biz.model.vo.UpdateUserInfoReqVO;
import com.jacob.micro.user.biz.service.UserService;
import com.jacob.micro.user.dto.req.FindUserByIdReqDTO;
import com.jacob.micro.user.dto.req.FindUsersByIdsReqDTO;
import com.jacob.micro.user.dto.rsp.FindUserByIdRspDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Jacob
 * @Description: 用户服务控制器
 * @Date: 2024-07-12 22:24
 * @Version: 1.0
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperationLog(description = "修改用户信息")
    public Response<?> updateUserInfo(@Validated UpdateUserInfoReqVO updateUserInfoReqVO) {
        return userService.updateUserInfo(updateUserInfoReqVO);
    }

    @PostMapping(value = "/findById")
    @ApiOperationLog(description = "查询用户信息")
    public Response<FindUserByIdRspDTO> findByUserId(@Validated @RequestBody FindUserByIdReqDTO findUserByIdReqDTO) {
        return userService.findByUserId(findUserByIdReqDTO);
    }

    @PostMapping("/findByIds")
    @ApiOperationLog(description = "批量查询用户信息")
    public Response<List<FindUserByIdRspDTO>> findByIds(@Validated @RequestBody FindUsersByIdsReqDTO findUsersByIdsReqDTO) {
        return userService.findByIds(findUsersByIdsReqDTO);
    }
}
