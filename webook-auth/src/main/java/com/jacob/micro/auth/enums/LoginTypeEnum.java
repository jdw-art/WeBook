package com.jacob.micro.auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @Author: Jacob
 * @Description: 登录类型枚举类
 * @Date: 2024/6/17 14:30
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum LoginTypeEnum {

    // 验证码
    VERIFICATION_CODE(1),
    // 密码
    PASSWORD(2);

    private final Integer value;

    public static LoginTypeEnum valueOf(Integer code) {
        for (LoginTypeEnum loginTypeEnum : LoginTypeEnum.values()) {
            if (Objects.equals(code, loginTypeEnum.getValue())) {
                return loginTypeEnum;
            }
        }
        return null;
    }
}
