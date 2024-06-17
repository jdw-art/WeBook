package com.jacob.micro.auth.controller;

import com.jacob.micro.auth.model.vo.verificationcode.SendVerificationCodeReqVO;
import com.jacob.micro.auth.service.VerificationCodeService;
import com.jacob.micro.framework.biz.operationlog.aspect.ApiOperationLog;
import com.jacob.micro.framework.common.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Jacob
 * @Description: 短信验证服务
 * @Date: 2024/6/16 18:44
 * @Version: 1.0
 */
@RestController
@Slf4j
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @RequestMapping("/verification/code/send")
    @ApiOperationLog(description = "发送短信验证码")
    public Response<?> send(@RequestBody @Validated SendVerificationCodeReqVO sendVerificationCodeReqVO) {
        return verificationCodeService.send(sendVerificationCodeReqVO);
    }
}
