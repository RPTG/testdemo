package com.wentnet.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.teaopenapi.models.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;


@Slf4j
public class SmsUtil {

    /**
     * 发送短信入口
     *
     * @param phone
     * @return
     * @throws Exception
     */
    public static boolean sendSms(String phone, int code) {
        Client client;
        try {
            client = createClient("LTAI4Fy3fyp6f8i18i3d6NcZ", "kxi0dVDF8ctIclhcPxXeEJW5UPWBzI");
        } catch (Exception e) {
            log.error("发送短信,使用初始化账号Client异常, {}", e.getMessage());
            return false;
        }
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                // 手机号
                .setPhoneNumbers(phone)
                // 签名,比如 "xxx公司",问运营要
                .setSignName("温拓网络")
                // 模板编号,比如 SMS_123456,问运营要
                .setTemplateCode("SMS_205360407")
                // json格式String类型模板,比如 "{"k1", v1, "k2", v2}",问运营要
                .setTemplateParam("{\"code\":" + code + "}");
        SendSmsResponse resp;
        try {
            resp = client.sendSms(sendSmsRequest);
        } catch (Exception e) {
            log.error("发送短信失败, {}", e.getMessage());
            return false;
        }
        log.debug("发送短信【{}】, 验证码【{}】, 结果【{}】", phone, code, JSONObject.toJSONString(resp));
        if (resp == null || !Objects.equals(resp.getBody().getCode(), "OK")) {
            log.error("发送短信失败【{}】, 验证码【{}】, 结果【{}】", phone, code, JSONObject.toJSONString(resp));
            return false;
        }
        return true;
    }


    /**
     *  初始化账号Client
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    private static Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                .setAccessKeyId(accessKeyId)
                .setAccessKeySecret(accessKeySecret);
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new Client(config);
    }

}
