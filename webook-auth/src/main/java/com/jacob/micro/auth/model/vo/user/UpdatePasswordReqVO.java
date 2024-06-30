package com.jacob.micro.auth.model.vo.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Jacob
 * @Description: 用户修改密码请求参数
 * @Date: 2024/6/30 16:32
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UpdatePasswordReqVO {
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
}
