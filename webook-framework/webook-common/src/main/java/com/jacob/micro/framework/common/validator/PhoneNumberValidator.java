package com.jacob.micro.framework.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @Author: Jacob
 * @Description: 手机号自定义校验类
 * @Date: 2024/6/17 14:00
 * @Version: 1.0
 */
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        // 初始化操作
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        // 校验逻辑，正则表达式判断手机号是否为11位数字
        return phoneNumber != null && phoneNumber.matches("\\d{11}");
    }


}
