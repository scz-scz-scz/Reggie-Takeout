package scz.reggiecode1.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    //发送验证码
    @PostMapping("/sendMsg")
    public Result<String> sendMsg(@RequestBody User user){
        String phone=user.getPhone();
        if (phone==null||phone.isEmpty())
            return Result.error("验证码发送失败：手机号不能为空");
        if (!phone.matches("(13|15|17|18|19)[0-9]{9}"))
            return Result.error("验证码发送失败：手机号错误");
        String code=userService.sendMsg(phone);
        redisTemplate.opsForValue().set(phone,code,60, TimeUnit.SECONDS);
        return Result.success("验证码发送成功");
    }

    //用户登录
    @PostMapping("/login")
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public Result<User> login(HttpServletRequest httpServletRequest, @RequestBody Map<String,String> map){
        String phone=map.get("phone");
        String code=(String) redisTemplate.opsForValue().get(phone);
        if (code!=null&&code.equals(map.get("code"))){
            User user=userService.login(phone);
            if (user!=null){
                httpServletRequest.getSession().setAttribute("userId",user.getId());
                redisTemplate.delete(phone);
                return Result.success(user);
            }
            return Result.error("该用户已被禁用");
        }
        return Result.error("手机号码或验证码错误");
    }

    //退出登录
    @PostMapping("/loginout")
    public Result<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("userId");
        return Result.success("退出登录");
    }
}
