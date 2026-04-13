package scz.reggiecode1.common;

/**
 * 短信发送异常
 * 当短信发送失败时抛出此异常
 */
public class MyClientException extends RuntimeException {

    /**
     * 无参构造函数
     */
    public MyClientException() {
    }

    /**
     * 带消息的构造函数
     *
     * @param message 异常消息
     */
    public MyClientException(String message) {
        super(message);
    }
}
