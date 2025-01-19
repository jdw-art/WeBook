package com.jacob.micro.note.biz.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Jacob
 * @Description: 笔记删除
 * @Date: 2025/1/19 19:03
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteNoteReqVO {

    @NotNull(message = "笔记 ID 不能为空")
    private Long id;

}
