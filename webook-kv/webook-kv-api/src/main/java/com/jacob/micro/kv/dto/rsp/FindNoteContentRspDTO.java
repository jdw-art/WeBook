package com.jacob.micro.kv.dto.rsp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @Author: Jacob
 * @Description: 新增笔记内容响应体
 * @Date: 2025/1/15 14:21
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindNoteContentRspDTO {

    /**
     * 笔记 UUID
     */
    private UUID uuid;

    /**
     * 笔记内容
     */
    private String content;
}
