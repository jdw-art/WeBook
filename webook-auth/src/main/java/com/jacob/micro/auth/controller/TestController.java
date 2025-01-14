package com.jacob.micro.auth.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.jacob.micro.auth.alarm.AlarmInterface;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Jacob
 * @Description: nacos测试类
 * @Date: 2024/6/24 19:50
 * @Version: 1.0
 */
@RestController
@Slf4j
public class TestController {

    @NacosValue(value = "${rate-limit.api.limit}", autoRefreshed = true)
    // @Value(value = "${rate-limit.api.limit}")
    private Integer limit;

    @Resource
    private AlarmInterface alarmInterface;

    @GetMapping("/test")
    public String test() {
        return "当前限流阈值为：" + limit;
    }

    @GetMapping("/alarm")
    public String sendAlarm() {
        alarmInterface.send("系统出错！！！");
        return "alarm success";
    }
}
