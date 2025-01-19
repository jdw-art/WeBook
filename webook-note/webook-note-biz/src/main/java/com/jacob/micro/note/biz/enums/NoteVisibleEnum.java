package com.jacob.micro.note.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Jacob
 * @Description: 笔记可见性
 * @Date: 2025/1/15 17:57
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum NoteVisibleEnum {

    PUBLIC(0), // 公开，所有人可见
    PRIVATE(1); // 仅自己可见

    private final Integer code;

}