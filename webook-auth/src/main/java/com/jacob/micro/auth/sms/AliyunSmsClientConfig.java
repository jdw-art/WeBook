package com.jacob.micro.auth.sms;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Jacob
 * @Description: 短信发送客户端
 * @Date: 2024/6/17 13:40
 * @Version: 1.0
 */
@Configuration
@Slf4j
public class AliyunSmsClientConfig {

    @Resource
    private AliyunAccessKeyProperties aliyunAccessKeyProperties;

    @Bean
    public Client smsClient() {
        try {
            Config config = new Config()
                    .setAccessKeyId(aliyunAccessKeyProperties.getAccessKeyId())
                    .setAccessKeySecret(aliyunAccessKeyProperties.getAccessKeySecret());
            // Enable HTTPS
            config.endpoint = "dysmsapi.aliyuncs.com";
            return new Client(config);
        } catch (Exception e) {
            log.error("初始化阿里云短信发送客户端错误：", e);
            return null;
        }
    }
}
