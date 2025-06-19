package scz.reggiecode1.service;

import scz.reggiecode1.dto.OrderDto;
import scz.reggiecode1.entity.Orders;
import scz.reggiecode1.entity.ShoppingCart;

import java.util.List;

public interface OrderDetailService {
    void save(Orders order, List<ShoppingCart> shoppingCartList);

    void list(List<OrderDto> orderDtoList);

    void again(Long oldOrderId,Long newOrderId);
}
