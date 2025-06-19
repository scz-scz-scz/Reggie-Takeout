package scz.reggiecode1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import scz.reggiecode1.utils.SMSUtils;
import scz.reggiecode1.utils.ValidateCodeUtils;

@SpringBootTest
class ReggieCode1ApplicationTests {
    @Autowired
    private SMSUtils smsUtils;

    @Test
    public void SMSTest(){
        String code= ValidateCodeUtils.generateValidateCode(2);
        smsUtils.sendMessage("13187839614",code);
        System.out.println(code);
    }
}
