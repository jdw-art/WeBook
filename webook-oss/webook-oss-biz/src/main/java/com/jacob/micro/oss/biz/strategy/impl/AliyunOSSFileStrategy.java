package com.jacob.micro.oss.biz.strategy.impl;

import com.jacob.micro.oss.biz.strategy.FileStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Jacob
 * @Description: 阿里云 OSS 策略类
 * @Date: 2024/7/2 20:10
 * @Version: 1.0
 */
@Slf4j
public class AliyunOSSFileStrategy implements FileStrategy {
    @Override
    public String uploadFile(MultipartFile file, String bucketName) {
        log.info("## 上传文件至阿里云 OSS...");
        return null;
    }
}
