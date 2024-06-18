package com.jacob.micro.auth.sms;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Jacob
 * @Description: 阿里云SMS配置类
 * @Date: 2024/6/17 13:39
 * @Version: 1.0
 */
@ConfigurationProperties(prefix = "aliyun")
@Component
@Data
public class AliyunAccessKeyProperties {

    private String accessKeyId;
    private String accessKeySecret;
}
