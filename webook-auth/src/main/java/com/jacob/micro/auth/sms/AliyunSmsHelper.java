package com.jacob.micro.auth.sms;

import com.alibaba.druid.filter.config.ConfigTools;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teautil.models.RuntimeOptions;
import com.jacob.micro.framework.common.util.JsonUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @Author: Jacob
 * @Description: 短信发送工具类
 * @Date: 2024/6/17 13:44
 * @Version: 1.0
 */
@Component
@Slf4j
public class AliyunSmsHelper {

    @Resource
    private Client client;

    /**
     * 发送短信
     * @param signName
     * @param templateCode
     * @param phone
     * @param templateParam
     * @return
     */
    public boolean sendMessage(String signName, String templateCode, String phone, String templateParam) {
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName(signName)
                .setTemplateCode(templateCode)
                .setPhoneNumbers(phone)
                .setTemplateParam(templateParam);
        RuntimeOptions runtimeOptions = new RuntimeOptions();

        try {
            log.info("==> 开始短信发送，phone: {}, signName: {}, templateCode: {}, templateParam: {}", phone, signName, templateCode, templateParam);

            // 发送短信
            SendSmsResponse response = client.sendSmsWithOptions(sendSmsRequest, runtimeOptions);

            log.info("==> 短信发送成功，response: {}", JsonUtils.toJsonString(response));
            return true;
        } catch (Exception error) {
            log.error("==> 短信发送错误：", error);
            return false;
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {
        // 你的密码
        String password = "jdw112233";
        String[] arr = ConfigTools.genKeyPair(512);

        // 私钥
        log.info("privateKey: {}", arr[0]);
        // 公钥
        log.info("publicKey: {}", arr[1]);

        String encodePassword = null;

        // 通过私钥加密密码
        try {
            encodePassword = ConfigTools.encrypt(arr[0], password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("password: {}", encodePassword);
    }
}
