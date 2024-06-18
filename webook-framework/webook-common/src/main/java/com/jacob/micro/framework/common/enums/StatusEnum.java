package com.jacob.micro.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Jacob
 * @Description: 状态枚举类
 * @Date: 2024/6/17 16:46
 * @Version: 1.0
 */
@AllArgsConstructor
@Getter
public enum StatusEnum {

    // 启用
    ENABLE(0),
    // 禁用
    DISABLED(1);

    private final Integer value;

}
