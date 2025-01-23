package com.jacob.micro.data.align.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author: Jacob
 * @Description: 点赞、取消点赞笔记
 * @Date: 2025/1/23 15:37
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeUnlikeNoteMqDTO {

    private Long userId;

    private Long noteId;

    /**
     * 0: 取消点赞， 1：点赞
     */
    private Integer type;

    /**
     * 笔记发布者 ID
     */
    private Long noteCreatorId;

    private LocalDateTime createTime;
}
