package scz.reggiecode1.common;

//当要删除的分类关联了菜品或套餐时，抛出此异常
//当要删除的菜品或套餐状态为启售时，抛出此异常
public class CustomException extends RuntimeException{
    public CustomException(){}

    public CustomException(String message){
        super(message);
    }
}
