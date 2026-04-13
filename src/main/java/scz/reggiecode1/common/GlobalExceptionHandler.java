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
        String error = e.toString();
        log.error(error);
        // 判断是新增员工还是编辑员工
        int flag = 0;
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (int i = 0; i < stackTrace.length; i++) {
            if (stackTrace[i].toString().contains("scz.reggiecode1.service.impl.EmployeeServiceImpl.save")) {
                flag = 1;
            }
            if (stackTrace[i].toString().contains("scz.reggiecode1.service.impl.EmployeeServiceImpl.updateEmployee")) {
                flag = 2;
            }
            if (stackTrace[i].toString().contains("scz.reggiecode1.service.impl.CategoryServiceImpl.save")) {
                flag = 3;
            }
            if (stackTrace[i].toString().contains("scz.reggiecode1.service.impl.CategoryServiceImpl.updateCategory")) {
                flag = 4;
            }
            if (stackTrace[i].toString().contains("scz.reggiecode1.service.impl.DishServiceImpl.save")) {
                flag = 5;
            }
            if (stackTrace[i].toString().contains("scz.reggiecode1.service.impl.DishServiceImpl.update")) {
                flag = 6;
            }
            if (stackTrace[i].toString().contains("scz.reggiecode1.service.impl.SetmealServiceImpl.save")) {
                flag = 7;
            }
            if (stackTrace[i].toString().contains("scz.reggiecode1.service.impl.SetmealServiceImpl.update")) {
                flag = 8;
            }
        }
        if (flag == 1)
            return Result.error("新增员工失败：账号“" + error.split("'")[1] + "”已存在");
        if (flag == 2)
            return Result.error("编辑员工失败：账号“" + error.split("'")[1] + "”已存在");
        if (flag == 3)
            return Result.error("分类添加失败：分类“" + error.split("'")[1] + "”已存在");
        if (flag == 4)
            return Result.error("分类修改失败：分类“" + error.split("'")[1] + "”已存在");
        if (flag == 5)
            return Result.error("新增菜品失败：菜品“" + error.split("'")[1] + "”已存在");
        if (flag == 6)
            return Result.error("修改菜品失败：菜品“" + error.split("'")[1] + "”已存在");
        if (flag == 7)
            return Result.error("新增套餐失败：套餐“" + error.split("'")[1] + "”已存在");
        if (flag == 8)
            return Result.error("修改套餐失败：套餐“" + error.split("'")[1] + "”已存在");
        return Result.error("未知错误");
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
        log.error(e.toString());
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
    public Result<String> customexhandler(Exception e) {
        log.error(e.toString());
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
    public Result<String> ioexhandler(Exception e) {
        log.error(e.toString());
        return Result.error("文件上传失败");
    }

    /**
     * 处理短信发送异常
     *
     * @param e 短信发送异常
     * @return 错误结果
     */
    @ExceptionHandler(MyClientException.class)
    public Result<String> clientexhandler(Exception e) {
        log.error(e.toString());
        return Result.error("验证码发送失败");
    }
}
