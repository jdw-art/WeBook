package com.jacob.micro.framework.common.response;

import com.jacob.micro.framework.common.exception.BaseExceptionInterface;
import com.jacob.micro.framework.common.exception.BizException;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Jacob
 * @Description: 响参工具类
 * @Date: 2024/6/13 21:55
 * @Version: 1.0
 */
@Data
public class Response<T> implements Serializable {
    // 是否成功，默认为 true
    private boolean success = true;
    // 响应消息
    private String message;
    // 异常码
    private String errorCode;
    // 响应数据
    private T data;

    // =================================== 成功响应 ===================================
    public static <T> Response<T> success() {
        Response<T> response = new Response<>();
        return response;
    }

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setData(data);
        return response;
    }

    // =================================== 失败响应 ===================================
    public static <T> Response<T> fail() {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        return response;
    }

    public static <T> Response<T> fail(String errorMessage) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setMessage(errorMessage);
        return response;
    }

    public static <T> Response<T> fail(String errorCode, String errorMessage) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(errorCode);
        response.setMessage(errorMessage);
        return response;
    }

    public static <T> Response<T> fail(BizException bizException) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(bizException.getErrorCode());
        response.setMessage(bizException.getErrorMessage());
        return response;
    }

    public static <T> Response<T> fail(BaseExceptionInterface baseExceptionInterface) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(baseExceptionInterface.getErrorCode());
        response.setMessage(baseExceptionInterface.getErrorMessage());
        return response;
    }
}
