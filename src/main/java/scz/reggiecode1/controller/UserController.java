package scz.reggiecode1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import scz.reggiecode1.common.Result;
import scz.reggiecode1.entity.User;
import scz.reggiecode1.service.UserService;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 用户管理控制器
 * 负责用户的登录、登出、发送验证码等操作
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Tag(name = "用户管理")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 发送短信验证码
     */
    @PostMapping("/sendMsg")
    @Operation(summary = "发送短信验证码")
    public Result<String> sendMsg(@RequestBody User user) {
        log.info("发送短信验证码：{}", user.getPhone());
        String phone = user.getPhone();
        if (phone == null || phone.isEmpty()) {
            return Result.error("验证码发送失败：手机号不能为空");
        }
        if (!phone.matches("(13|15|17|18|19)[0-9]{9}")) {
            return Result.error("验证码发送失败：手机号错误");
        }
        String code = userService.sendMsg(phone);
        redisTemplate.opsForValue().set(phone, code, 60, TimeUnit.SECONDS);
        return Result.success("验证码发送成功");
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    @Operation(summary = "用户登录")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Parameters({
            @Parameter(name = "map", description = "手机号和验证码的键值对", required = true)
    })
    public Result<User> login(HttpServletRequest httpServletRequest, @RequestBody Map<String, String> map) {
        log.info("用户登录：{}", map.get("phone"));
        String phone = map.get("phone");
        String code = (String) redisTemplate.opsForValue().get(phone);
        if (code != null && code.equals(map.get("code"))) {
            User user = userService.login(phone);
            if (user != null) {
                httpServletRequest.getSession().setAttribute("userId", user.getId());
                redisTemplate.delete(phone);
                return Result.success(user);
            }
            return Result.error("该用户已被禁用");
        }
        return Result.error("手机号码或验证码错误");
    }

    /**
     * 用户登出
     */
    @PostMapping("/loginout")
    @Operation(summary = "用户登出")
    public Result<String> logout(HttpServletRequest request) {
        log.info("用户登出");
        request.getSession().removeAttribute("userId");
        return Result.success("退出登录");
    }
}
