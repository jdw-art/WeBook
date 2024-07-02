package com.jacob.micro.oss.biz.factory;

import com.jacob.micro.oss.biz.strategy.FileStrategy;
import com.jacob.micro.oss.biz.strategy.impl.AliyunOSSFileStrategy;
import com.jacob.micro.oss.biz.strategy.impl.MinioFileStrategy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Jacob
 * @Description: 文件上传工厂
 * @Date: 2024/7/2 20:12
 * @Version: 1.0
 */
@Configuration
public class FileStrategyFactory {

    @Value("${storage.type}")
    private String strategyType;

    @Bean
    public FileStrategy getFileStrategy() {
        if (StringUtils.equals(strategyType, "minio")) {
            return new MinioFileStrategy();
        } else if (StringUtils.equals(strategyType, "aliyun")) {
            return new AliyunOSSFileStrategy();
        }
        throw new IllegalArgumentException("不可用存储类型");
    }
}
