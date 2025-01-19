package com.jacob.micro.note.biz.enums;

import com.jacob.micro.framework.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Jacob
 * @Description: 响应异常码
 * @Date: 2025/1/15 17:57
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum ResponseCodeEnum implements BaseExceptionInterface {

    // ----------- 通用异常状态码 -----------
    SYSTEM_ERROR("NOTE-10000", "出错啦，后台小哥正在努力修复中..."),
    PARAM_NOT_VALID("NOTE-10001", "参数错误"),
    NOTE_TYPE_ERROR("NOTE-20000", "未知的笔记类型"),
    NOTE_PUBLISH_FAIL("NOTE-20001", "笔记发布失败"),
    NOTE_NOT_FOUND("NOTE-20002", "笔记不存在"),
    NOTE_PRIVATE("NOTE-20003", "作者已将该笔记设置为仅自己可见"),

    // ----------- 业务异常状态码 -----------
    ;

    // 异常码
    private final String errorCode;
    // 错误信息
    private final String errorMessage;

}
