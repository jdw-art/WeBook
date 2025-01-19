package com.jacob.micro.user.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Jacob
 * @Description: 根据用户 ID 查询用户信息
 * @Date: 2025/1/19 11:58
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindUserByIdReqDTO {

    /**
     * 手机号
     */
    @NotNull(message = "用户 ID 不能为空")
    private Long id;

}
