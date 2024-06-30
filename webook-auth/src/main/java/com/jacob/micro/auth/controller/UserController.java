package com.jacob.micro.auth.controller;

import com.jacob.micro.auth.model.vo.user.UpdatePasswordReqVO;
import com.jacob.micro.auth.model.vo.user.UserLoginRepVO;
import com.jacob.micro.auth.service.UserService;
import com.jacob.micro.framework.biz.operationlog.aspect.ApiOperationLog;
import com.jacob.micro.framework.common.response.Response;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Jacob
 * @Description: 用户服务控制器
 * @Date: 2024/6/17 15:04
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    @ApiOperationLog(description = "用户登录或注册")
    public Response<String> loginAndRegister(@RequestBody @Validated UserLoginRepVO userLoginRepVO) {
        return userService.loginAndRegister(userLoginRepVO);
    }

    @PostMapping("/logout")
    @ApiOperationLog(description = "账号登出")
    public Response<?> logout() {

        return userService.logout();
    }

    @PostMapping("/password/update")
    @ApiOperationLog(description = "修改密码")
    public Response<?> updatePassword(@RequestBody @Validated UpdatePasswordReqVO updatePasswordReqVO) {
        return userService.updatePassword(updatePasswordReqVO);
    }
}
