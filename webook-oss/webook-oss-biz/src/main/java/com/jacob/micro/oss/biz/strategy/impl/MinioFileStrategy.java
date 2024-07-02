package com.jacob.micro.oss.biz.strategy.impl;

import com.jacob.micro.oss.biz.strategy.FileStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Jacob
 * @Description: Minio 策略类
 * @Date: 2024/7/2 20:09
 * @Version: 1.0
 */
@Slf4j
public class MinioFileStrategy implements FileStrategy {

    @Override
    public String uploadFile(MultipartFile file, String bucketName) {
        log.info("## 上传文件至 Minio...");
        return null;
    }
}
