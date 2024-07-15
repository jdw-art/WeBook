package com.jacob.micro.user.biz.rpc;

import com.jacob.micro.framework.common.response.Response;
import com.jacob.micro.oss.api.FileFeignApi;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Jacob
 * @Description: 服务间调用RPC类
 * @Date: 2024-07-14 17:02
 * @Version: 1.0
 */
@Component
public class OssRpcService {

    @Resource
    private FileFeignApi fileFeignApi;

    public String uploadFile(MultipartFile file) {
        // 调用对象存储服务上传文件
        Response<?> response = fileFeignApi.uploadFile(file);

        if (!response.isSuccess()) {
            return null;
        }

        // 返回图片访问链接
        return (String) response.getData();
    }
}
