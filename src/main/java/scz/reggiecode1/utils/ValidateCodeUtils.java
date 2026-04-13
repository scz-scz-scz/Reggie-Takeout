package scz.reggiecode1.utils;

import org.apache.commons.lang.RandomStringUtils;

/**
 * 随机生成验证码工具类
 */
public class ValidateCodeUtils {

    /**
     * 私有构造函数，防止实例化
     */
    private ValidateCodeUtils() {
    }

    /**
     * 随机生成数字验证码
     *
     * @param length 验证码长度（仅支持4位或6位）
     * @return 生成的数字验证码
     * @throws RuntimeException 当长度不为4或6时抛出异常
     */
    public static String generateValidateCode(int length) {
        if (length == 4 || length == 6) {
            return RandomStringUtils.randomNumeric(length);
        } else {
            throw new RuntimeException("只能生成4位或6位数字验证码");
        }
    }

    /**
     * 随机生成指定长度的字母数字混合验证码
     *
     * @param length 验证码长度
     * @return 生成的字母数字混合验证码
     */
    public static String generateValidateCode4String(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }
}
