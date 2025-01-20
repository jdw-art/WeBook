package com.jacob.micro.user.dto.req;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Jacob
 * @Description: TODO
 * @Date: 2025/1/20 13:32
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindUsersByIdsReqDTO {

    @NotNull(message = "用户 ID 集合不能为空")
    @Size(min = 1, max = 10, message = "用户 ID 集合大小必须大于等于 1, 小于等于 10")
    private List<Long> ids;

}
