package com.jacob.micro.user.biz.rpc;

import com.jacob.micro.distributed.id.generator.api.DistributedIdGeneratorFeignApi;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @Author: Jacob
 * @Description: 分布式 ID 生成服务
 * @Date: 2024/8/11 20:52
 * @Version: 1.0
 */
@Component
public class DistributedIdGeneratorRpcService {

    @Resource
    private DistributedIdGeneratorFeignApi distributedIdGeneratorFeignApi;

    /**
     * Leaf 号段模式：微书 ID 业务逻辑
     */
    private static final String BIZ_TAG_WEBOOK_ID = "leaf-segment-webook-id";

    /**
     * Leaf 号段模式：用户 ID 业务逻辑
     */
    private static final String BIZ_TAG_USER_ID = "leaf-segment-user-id";


    /**
     * 调用分布式 ID 生成服务生成微书 ID
     * @return
     */
    public String getWebookId() {
        return distributedIdGeneratorFeignApi.getSegmentId(BIZ_TAG_WEBOOK_ID);
    }

    /**
     * 调用分布式 ID 生成服务生成用户 ID
     * @return
     */
    public String getUserId() {
        return distributedIdGeneratorFeignApi.getSegmentId(BIZ_TAG_USER_ID);
    }


}
