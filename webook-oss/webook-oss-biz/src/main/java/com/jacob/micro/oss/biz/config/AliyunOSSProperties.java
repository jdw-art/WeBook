package com.jacob.micro.oss.biz.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Jacob
 * @Description: Aliyun 配置类
 * @Date: 2024/7/2 21:15
 * @Version: 1.0
 */
@ConfigurationProperties(prefix = "storage.aliyun-oss")
@Component
@Data
public class AliyunOSSProperties {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
