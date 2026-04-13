package scz.reggiecode1.common;

/**
 * 自定义业务异常
 * 用于以下场景：
 * 1. 删除的分类关联了菜品或套餐
 * 2. 删除的菜品或套餐状态为启售
 * 3. 其他业务逻辑异常
 */
public class CustomException extends RuntimeException {

    /**
     * 无参构造函数
     */
    public CustomException() {
    }

    /**
     * 带消息的构造函数
     *
     * @param message 异常消息
     */
    public CustomException(String message) {
        super(message);
    }
}
