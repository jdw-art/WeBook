package com.jacob.micro.note.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Jacob
 * @Description: 笔记操作
 * @Date: 2025/1/21 17:11
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum NoteOperateEnum {
    // 笔记发布
    PUBLISH(1),
    // 笔记删除
    DELETE(0),
    ;

    private final Integer code;

}
