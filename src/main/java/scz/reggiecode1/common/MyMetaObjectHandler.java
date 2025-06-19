package scz.reggiecode1.common;

import lombok.extern.slf4j.Slf4j;
import scz.reggiecode1.entity.Orders;
import scz.reggiecode1.entity.ShoppingCart;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

//自动填充字段
@Slf4j
public class MyMetaObjectHandler {
    //新建
    public static <T> void save(T t){
        autoSetTime(t,"setCreateTime");
        autoSetTime(t,"setUpdateTime");
        autoSetUser(t,"setCreateUser");
        autoSetUser(t,"setUpdateUser");
    }

    //修改
    public static <T> void update(T t){
        autoSetTime(t,"setUpdateTime");
        autoSetUser(t,"setUpdateUser");
    }

    //新建（仅对于将菜品或套餐加入购物车）
    public static void save(ShoppingCart shoppingCart){
        autoSetUser(shoppingCart,"setUserId");
        autoSetTime(shoppingCart,"setCreateTime");
    }

    //新建订单
    public static void save(Orders order){
        autoSetUser(order,"setUserId");
        autoSetTime(order,"setOrderTime");
        //本项目将下单时间和结账时间设为一个值
        autoSetTime(order,"setCheckoutTime");
    }

    //设置时间
    private static <T> void autoSetTime(T t,String methodName){
        try {
            t.getClass().getMethod(methodName,LocalDateTime.class).invoke(t,LocalDateTime.now());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    //设置人
    private static <T> void autoSetUser(T t,String methodName){
        Long user=BaseContext.getCurrentId();
        try {
            t.getClass().getMethod(methodName,Long.class).invoke(t,user);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
