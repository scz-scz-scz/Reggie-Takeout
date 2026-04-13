package scz.reggiecode1.service;

import scz.reggiecode1.dto.OrderDto;
import scz.reggiecode1.entity.Orders;
import scz.reggiecode1.entity.ShoppingCart;

import java.util.List;

/**
 * 订单明细服务接口
 */
public interface OrderDetailService {

    /**
     * 保存订单明细
     *
     * @param order            订单信息
     * @param shoppingCartList 购物车列表
     */
    void save(Orders order, List<ShoppingCart> shoppingCartList);

    /**
     * 查询订单明细列表
     *
     * @param orderDtoList 订单DTO列表
     */
    void list(List<OrderDto> orderDtoList);

    /**
     * 再来一单
     *
     * @param oldOrderId 原订单ID
     * @param newOrderId 新订单ID
     */
    void again(Long oldOrderId, Long newOrderId);
}
