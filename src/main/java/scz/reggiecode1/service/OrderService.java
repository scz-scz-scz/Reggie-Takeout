package scz.reggiecode1.service;

import scz.reggiecode1.dto.OrderDto;
import scz.reggiecode1.entity.AddressBook;
import scz.reggiecode1.entity.Orders;
import scz.reggiecode1.entity.PageBean;
import scz.reggiecode1.entity.ShoppingCart;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    void save(Orders order, AddressBook addressBook,List<ShoppingCart> shoppingCartList);

    PageBean<OrderDto> list4User(Integer page, Integer pageSize);

    Orders again(Orders order);

    PageBean<Orders> list4Employee(Integer page, Integer pageSize, String number, LocalDateTime beginTime, LocalDateTime endTime);

    void updateStatus(Orders order);
}
