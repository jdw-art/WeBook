package com.jacob.micro.framework.common.exception;

/**
 * @Author: Jacob
 * @Description: 基础异常接口
 * @Date: 2024/6/13 21:53
 * @Version: 1.0
 */
public interface BaseExceptionInterface {

    // 获取异常码
    String getErrorCode();

    // 获取异常信息
    String getErrorMessage();
}
