package scz.reggiecode1.common;

//当短信发送失败时，抛出此异常
public class MyClientException extends RuntimeException{
    public MyClientException(){}

    public MyClientException(String message){
        super(message);
    }
}
