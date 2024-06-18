package com.jacob.micro.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Jacob
 * @Description: 逻辑删除枚举类
 * @Date: 2024/6/17 16:46
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum DeletedEnum {

    YES(true),
    NO(false);

    private final Boolean value;
}
