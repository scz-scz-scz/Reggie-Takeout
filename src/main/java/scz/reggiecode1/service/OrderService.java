package scz.reggiecode1.service;

import scz.reggiecode1.dto.OrderDto;
import scz.reggiecode1.entity.AddressBook;
import scz.reggiecode1.entity.Orders;
import scz.reggiecode1.entity.PageBean;
import scz.reggiecode1.entity.ShoppingCart;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单服务接口
 */
public interface OrderService {

    /**
     * 保存订单
     *
     * @param order            订单信息
     * @param addressBook      地址信息
     * @param shoppingCartList 购物车列表
     */
    void save(Orders order, AddressBook addressBook, List<ShoppingCart> shoppingCartList);

    /**
     * 用户端分页查询订单
     *
     * @param page     页码
     * @param pageSize 每页记录数
     * @return 分页结果
     */
    PageBean<OrderDto> list4User(Integer page, Integer pageSize);

    /**
     * 再来一单
     *
     * @param order 订单信息
     * @return 新订单信息
     */
    Orders again(Orders order);

    /**
     * 员工端分页查询订单
     *
     * @param page      页码
     * @param pageSize  每页记录数
     * @param number    订单号
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 分页结果
     */
    PageBean<Orders> list4Employee(Integer page, Integer pageSize, String number, LocalDateTime beginTime, LocalDateTime endTime);

    /**
     * 修改订单状态
     *
     * @param order 订单信息
     */
    void updateStatus(Orders order);
}
