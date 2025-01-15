package com.jacob.micro.kv.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Jacob
 * @Description: 新增笔记内容实体
 * @Date: 2025/1/15 13:53
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddNoteContentReqDTO {

    @NotNull(message = "笔记 ID 不能为空")
    private Long noteId;

    @NotNull(message = "笔记内容不能为空")
    private String content;
}
