package scz.reggiecode1.utils;

import org.apache.commons.lang.RandomStringUtils;

/**
 * 随机生成验证码工具类
 */
public class ValidateCodeUtils {
    /**
     * 随机生成验证码
     * @param length 长度为4位或者6位
     * @return
     */
    public static String generateValidateCode(int length){
        String code;
        if (length==4||length==6)
            code=RandomStringUtils.randomNumeric(length);
        else
            throw new RuntimeException("只能生成4位或6位数字验证码");
        return code;
    }

    /**
     * 随机生成指定长度字符串验证码
     * @param length 长度
     * @return
     */
    public static String generateValidateCode4String(int length){
        String code= RandomStringUtils.randomAlphanumeric(length);
        return code;
    }
}
