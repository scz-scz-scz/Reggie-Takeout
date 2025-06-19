package scz.reggiecode1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import scz.reggiecode1.common.CustomException;
import scz.reggiecode1.common.Result;
import scz.reggiecode1.dto.OrderDto;
import scz.reggiecode1.entity.*;
import scz.reggiecode1.service.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private AddressBookService addressBookService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UserService userService;

    //下单
    @PostMapping("/submit")
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public Result<String> save(@RequestBody Orders order){
        if (order==null)
            return Result.error("支付失败");
        //先查询用户地址
        AddressBook addressBook=addressBookService.list(order.getAddressBookId());
        if (addressBook==null)
            throw new CustomException("地址有误，无法下单");
        //再查询用户点了哪些菜品或套餐
        List<ShoppingCart> shoppingCartList=shoppingCartService.list();
        if (shoppingCartList==null||shoppingCartList.size()==0)
            throw new CustomException("购物车为空，无法下单");
        //查询用户信息
        User user=userService.list();
        order.setUserName(user.getName());
        //新增订单
        orderService.save(order,addressBook,shoppingCartList);
        //新增订单明细
        orderDetailService.save(order,shoppingCartList);
        //最后清空购物车
        shoppingCartService.clean();
        return Result.success("支付成功");
    }

    //用户端查询订单
    @GetMapping("/userPage")
    public Result<PageBean<OrderDto>> list4User(@RequestParam Integer page, @RequestParam Integer pageSize){
        PageBean<OrderDto> orderDtoList=orderService.list4User(page,pageSize);
        orderDetailService.list(orderDtoList.getRecords());
        return Result.success(orderDtoList);
    }

    //再下一单
    @PostMapping("/again")
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public Result<String> again(@RequestBody Orders order){
        if (order==null)
            throw new CustomException("无历史订单，无法再来一单");
        Long oldOrderId=order.getId();
        order=orderService.again(order);
        orderDetailService.again(oldOrderId,order.getId());
        return Result.success("再来一单成功");
    }

    //员工端查询订单
    @GetMapping("/page")
    public Result<PageBean<Orders>> list4Employee(@RequestParam Integer page, @RequestParam Integer pageSize, String number, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime beginTime, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime){
        PageBean<Orders> orderList=orderService.list4Employee(page,pageSize,number,beginTime,endTime);
        return Result.success(orderList);
    }

    //员工端派送、完成订单
    @PutMapping
    public Result<String> updateStatus(@RequestBody Orders order){
        if (order==null)
            return Result.error("未指定订单");
        orderService.updateStatus(order);
        if (order.getStatus()==3)
            return Result.success("派送成功");
        return Result.success("订单已完成");
    }
}
