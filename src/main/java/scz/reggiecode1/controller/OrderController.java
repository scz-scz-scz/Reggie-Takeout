package scz.reggiecode1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
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

/**
 * 订单管理控制器
 * 负责订单的提交、查询、再来一单等操作
 */
@Slf4j
@RestController
@RequestMapping("/order")
@Tag(name = "订单管理")
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

    /**
     * 提交订单
     */
    @PostMapping("/submit")
    @Operation(summary = "提交订单")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Result<String> save(@RequestBody Orders order) {
        log.info("提交订单：{}", order);
        if (order == null) {
            return Result.error("支付失败");
        }
        
        AddressBook addressBook = addressBookService.list(order.getAddressBookId());
        if (addressBook == null) {
            throw new CustomException("地址有误，无法下单");
        }
        
        List<ShoppingCart> shoppingCartList = shoppingCartService.list();
        if (shoppingCartList == null || shoppingCartList.isEmpty()) {
            throw new CustomException("购物车为空，无法下单");
        }
        
        User user = userService.list();
        order.setUserName(user.getName());
        orderService.save(order, addressBook, shoppingCartList);
        orderDetailService.save(order, shoppingCartList);
        shoppingCartService.clean();
        return Result.success("支付成功");
    }

    /**
     * 用户端分页查询订单
     */
    @GetMapping("/userPage")
    @Operation(summary = "用户端分页查询订单")
    public Result<PageBean<OrderDto>> list4User(@RequestParam Integer page, @RequestParam Integer pageSize) {
        log.info("用户端分页查询订单，页码：{}，每页数量：{}", page, pageSize);
        PageBean<OrderDto> orderDtoList = orderService.list4User(page, pageSize);
        orderDetailService.list(orderDtoList.getRecords());
        return Result.success(orderDtoList);
    }

    /**
     * 再来一单
     */
    @PostMapping("/again")
    @Operation(summary = "再来一单")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Result<String> again(@RequestBody Orders order) {
        log.info("再来一单：{}", order);
        if (order == null) {
            throw new CustomException("无历史订单，无法再来一单");
        }
        Long oldOrderId = order.getId();
        order = orderService.again(order);
        orderDetailService.again(oldOrderId, order.getId());
        return Result.success("再来一单成功");
    }

    /**
     * 员工端分页查询订单
     */
    @GetMapping("/page")
    @Operation(summary = "员工端分页查询订单")
    public Result<PageBean<Orders>> list4Employee(@RequestParam Integer page, @RequestParam Integer pageSize, String number, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime beginTime, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        log.info("员工端分页查询订单，页码：{}，每页数量：{}，订单号：{}，开始时间：{}，结束时间：{}", page, pageSize, number, beginTime, endTime);
        PageBean<Orders> orderList = orderService.list4Employee(page, pageSize, number, beginTime, endTime);
        return Result.success(orderList);
    }

    /**
     * 修改订单状态
     */
    @PutMapping
    @Operation(summary = "修改订单状态")
    public Result<String> updateStatus(@RequestBody Orders order) {
        log.info("修改订单状态：{}", order);
        if (order == null) {
            return Result.error("未指定订单");
        }
        orderService.updateStatus(order);
        if (order.getStatus() == 3) {
            return Result.success("派送成功");
        }
        return Result.success("订单已完成");
    }
}
