package scz.reggiecode1.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.profile.DefaultProfile;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 阿里云短信服务工具类
 * 用于发送短信验证码
 */
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.sms")
public class SMSUtils {

    /**
     * 地域ID
     */
    private String regionId;

    /**
     * 访问密钥ID
     */
    private String accessKeyId;

    /**
     * 访问密钥
     */
    private String accessKeySecret;

    /**
     * 短信签名
     */
    private String signName;

    /**
     * 短信模板代码
     */
    private String templateCode;

    /**
     * 发送短信验证码
     *
     * @param phoneNumber 手机号码
     * @param code        验证码
     */
    public void sendMessage(String phoneNumber, String code) {
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(phoneNumber);
        request.setSignName(signName);
        request.setTemplateCode(templateCode);
        request.setTemplateParam("{\"code\":\"" + code + "\"}");
        /*try {
            client.getAcsResponse(request);
        } catch (ClientException e) {
            throw new MyClientException(e.toString());
        }*/
        log.info("短信发送成功，验证码为{}", code);
        client.shutdown();
    }
}
