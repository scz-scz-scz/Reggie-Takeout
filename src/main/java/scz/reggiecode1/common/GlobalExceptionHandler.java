package scz.reggiecode1.common;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器
 * 统一处理系统中的各类异常
 */
@Slf4j
@Hidden
@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    /**
     * 处理SQL完整性约束违反异常
     * 主要用于捕获唯一键冲突等数据库约束异常
     *
     * @param e SQL完整性约束违反异常
     * @return 错误结果
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result<String> sqlexhandler(SQLIntegrityConstraintViolationException e) {
        log.error("SQL完整性约束违反异常: {}", e.getMessage(), e);
        
        String message = e.getMessage();
        
        // 处理唯一键冲突：Duplicate entry 'xxx' for key 'xxx'
        if (message != null && message.contains("Duplicate entry")) {
            String duplicateValue = extractDuplicateValue(message);
            String errorMessage = buildDuplicateErrorMessage(e, duplicateValue);
            return Result.error(errorMessage);
        }
        
        // 其他SQL约束异常
        return Result.error("数据操作失败，请检查数据完整性");
    }

    /**
     * 从异常信息中提取重复的值
     *
     * @param message 异常信息
     * @return 重复的值
     */
    private String extractDuplicateValue(String message) {
        try {
            // 提取 Duplicate entry '值' for key 中的值
            int startIndex = message.indexOf("'") + 1;
            int endIndex = message.indexOf("'", startIndex);
            if (startIndex > 0 && endIndex > startIndex) {
                return message.substring(startIndex, endIndex);
            }
        } catch (Exception e) {
            log.warn("提取重复值失败: {}", e.getMessage());
        }
        return "该数据";
    }

    /**
     * 根据堆栈信息构建友好的错误提示
     *
     * @param e 异常对象
     * @param duplicateValue 重复的值
     * @return 错误提示信息
     */
    private String buildDuplicateErrorMessage(Exception e, String duplicateValue) {
        String stackTrace = getStackTraceString(e);
        
        // 员工相关
        if (stackTrace.contains("EmployeeServiceImpl.save")) {
            return String.format("新增员工失败：账号%s已存在", duplicateValue);
        }
        if (stackTrace.contains("EmployeeServiceImpl.update")) {
            return String.format("编辑员工失败：账号%s已存在", duplicateValue);
        }
        
        // 分类相关
        if (stackTrace.contains("CategoryServiceImpl.save")) {
            return String.format("分类添加失败：分类%s已存在", duplicateValue);
        }
        if (stackTrace.contains("CategoryServiceImpl.update")) {
            return String.format("分类修改失败：分类%s已存在", duplicateValue);
        }
        
        // 菜品相关
        if (stackTrace.contains("DishServiceImpl.save")) {
            return String.format("新增菜品失败：菜品%s已存在", duplicateValue);
        }
        if (stackTrace.contains("DishServiceImpl.update")) {
            return String.format("修改菜品失败：菜品%s已存在", duplicateValue);
        }
        
        // 套餐相关
        if (stackTrace.contains("SetmealServiceImpl.save")) {
            return String.format("新增套餐失败：套餐%s已存在", duplicateValue);
        }
        if (stackTrace.contains("SetmealServiceImpl.update")) {
            return String.format("修改套餐失败：套餐%s已存在", duplicateValue);
        }
        
        // 默认提示
        return String.format("操作失败：%s已存在", duplicateValue);
    }

    /**
     * 获取堆栈跟踪字符串
     *
     * @param e 异常对象
     * @return 堆栈跟踪字符串
     */
    private String getStackTraceString(Exception e) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace()) {
            sb.append(element.toString());
        }
        return sb.toString();
    }

    /**
     * 处理反射相关异常
     * 主要用于捕获自动填充字段时的动态代理方法异常
     *
     * @param e 异常对象
     * @return 错误结果
     */
    @ExceptionHandler({IllegalAccessException.class, InvocationTargetException.class, NoSuchMethodException.class})
    public Result<String> metaexhandler(Exception e) {
        log.error("反射操作异常: {}", e.getMessage(), e);
        return Result.error("设置信息错误");
    }

    /**
     * 处理自定义业务异常
     * 用于以下场景：
     * 1. 删除的分类关联了菜品或套餐
     * 2. 删除的菜品或套餐状态为启售
     * 3. 下单时购物车为空
     *
     * @param e 自定义异常
     * @return 错误结果
     */
    @ExceptionHandler(CustomException.class)
    public Result<String> customexhandler(CustomException e) {
        log.error("业务异常: {}", e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    /**
     * 处理IO异常
     * 主要用于文件上传和下载时的异常处理
     *
     * @param e IO异常
     * @return 错误结果
     */
    @ExceptionHandler(IOException.class)
    public Result<String> ioexhandler(IOException e) {
        log.error("IO异常: {}", e.getMessage(), e);
        return Result.error("文件操作失败");
    }

    /**
     * 处理短信发送异常
     *
     * @param e 短信发送异常
     * @return 错误结果
     */
    @ExceptionHandler(MyClientException.class)
    public Result<String> clientexhandler(MyClientException e) {
        log.error("短信发送异常: {}", e.getMessage(), e);
        return Result.error("验证码发送失败");
    }
}
