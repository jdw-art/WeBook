package com.jacob.micro.auth.model.vo.verificationcode;

import com.jacob.micro.framework.common.validator.PhoneNumber;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Jacob
 * @Description: 短信验证码入参实体类
 * @Date: 2024/6/16 18:27
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendVerificationCodeReqVO {

    @NotBlank(message = "手机号不能为空")
    @PhoneNumber
    private String phone;
}
