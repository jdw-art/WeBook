package com.jacob.micro.auth.alarm;

/**
 * @Author: Jacob
 * @Description: 告警接口
 * @Date: 2024/6/24 20:01
 * @Version: 1.0
 */
public interface AlarmInterface {

    /**
     * 发送告警信息
     * @param message
     * @return
     */
    boolean send(String message);
}
