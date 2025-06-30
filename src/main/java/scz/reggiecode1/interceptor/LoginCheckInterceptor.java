package scz.reggiecode1.interceptor;

import com.alibaba.fastjson.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import scz.reggiecode1.common.BaseContext;
import scz.reggiecode1.common.Result;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("拦截到请求:{}",request.getRequestURL());   //log中可以用{}代表占位符，类似于printf

        HttpSession session = request.getSession();
        Long id;
        String url=request.getRequestURL().toString();
        if ((id=(Long) session.getAttribute("employeeId"))!=null&&(url.contains("/backend")||url.contains("/employee")||url.contains("/category")||url.contains("/dish")||url.contains("/setmeal")||url.contains("/order")||url.contains("/.well-known"))){
            log.info("员工已登录，id为{}",id);
            //在线程局部变量中设置登录员工id
            BaseContext.setCurrentId(id);
            return true;
        }
        if ((id=(Long) session.getAttribute("userId"))!=null&&(url.contains("/front")||url.contains("/user")||url.contains("/addressBook")||url.contains("/shoppingCart")||url.contains("/order")||url.contains("/list")||url.contains("/setmeal/dish")||url.contains("/.well-known"))){
            log.info("用户已登录，id为{}",id);
            //在线程局部变量中设置登录用户id
            BaseContext.setCurrentId(id);
            return true;
        }
        if (url.contains("/common")||url.contains("/error")){
            return true;
        }
        String error= JSON.toJSONString(Result.error("NOTLOGIN"));
        response.getWriter().write(error);
        log.info("未登录或禁止访问");
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        //本次调用结束了，就可以释放threadLocal资源避免内存泄漏（当线程属于线程池时，就会一直持有threadLocal）
        BaseContext.remove();
    }
}
