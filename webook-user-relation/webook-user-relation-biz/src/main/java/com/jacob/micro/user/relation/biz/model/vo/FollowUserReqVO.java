package com.jacob.micro.user.relation.biz.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Jacob
 * @Description: 关注用户
 * @Date: 2025/1/19 20:12
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FollowUserReqVO {

    @NotNull(message = "被关注用户 ID 不能为空")
    private Long followUserId;
}
