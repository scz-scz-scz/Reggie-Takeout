package scz.reggiecode1.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import scz.reggiecode1.common.MyClientException;

@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.sms")
//阿里巴巴短信服务工具类
public class SMSUtils {
    private String regionId;
    private String accessKeyId;
    private String accessKeySecret;
    private String signName;
    private String templateCode;

    //发送短信
    public void sendMessage(String phoneNumber,String code){
        DefaultProfile profile=DefaultProfile.getProfile(regionId,accessKeyId,accessKeySecret);
        IAcsClient client=new DefaultAcsClient(profile);
        SendSmsRequest request=new SendSmsRequest();
        request.setPhoneNumbers(phoneNumber);
        request.setSignName(signName);
        request.setTemplateCode(templateCode);
        request.setTemplateParam("{\"code\":\""+code+"\"}");
        /*try {
            client.getAcsResponse(request);
        } catch (ClientException e) {
            throw new MyClientException(e.toString());
        }*/
        log.info("短信发送成功，验证码为{}",code);
        client.shutdown();
    }
}
