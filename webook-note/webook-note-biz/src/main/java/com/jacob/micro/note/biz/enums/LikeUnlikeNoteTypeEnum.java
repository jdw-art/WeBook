package com.jacob.micro.note.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Jacob
 * @Description: 笔记点赞、取消点赞 Type
 * @Date: 2025/1/21 15:52
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum LikeUnlikeNoteTypeEnum {
    // 点赞
    LIKE(1),
    // 取消点赞
    UNLIKE(0),
    ;

    private final Integer code;

}

