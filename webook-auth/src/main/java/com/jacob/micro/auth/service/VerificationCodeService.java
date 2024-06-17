package com.jacob.micro.auth.service;

import com.jacob.micro.auth.model.vo.verificationcode.SendVerificationCodeReqVO;
import com.jacob.micro.framework.common.response.Response;

/**
 * @Author: Jacob
 * @Description: 验证码业务接口
 * @Date: 2024/6/16 18:33
 * @Version: 1.0
 */
public interface VerificationCodeService {

    /**
     * 发送短信验证码
     * @param sendVerificationCodeReqVO
     * @return
     */
    Response<?> send(SendVerificationCodeReqVO sendVerificationCodeReqVO);
}
