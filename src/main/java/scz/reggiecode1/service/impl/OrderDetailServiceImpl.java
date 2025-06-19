package scz.reggiecode1.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scz.reggiecode1.dto.OrderDto;
import scz.reggiecode1.entity.OrderDetail;
import scz.reggiecode1.entity.Orders;
import scz.reggiecode1.entity.ShoppingCart;
import scz.reggiecode1.mapper.OrderDetailMapper;
import scz.reggiecode1.service.OrderDetailService;

import java.util.List;

@Slf4j
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    //下单
    @Override
    public void save(Orders order, List<ShoppingCart> shoppingCartList) {
        Long maxId=orderDetailMapper.getMaxId();
        if (maxId==null)
            maxId=0L;
        for (ShoppingCart shoppingCart : shoppingCartList) {
            OrderDetail orderDetail=new OrderDetail();
            orderDetail.setId(maxId+1);
            maxId++;
            orderDetail.setOrderId(order.getId());
            //beanutils是一个springboot工具类，可以在类之间进行属性复制（要求属性名相同）
            BeanUtils.copyProperties(shoppingCart,orderDetail,"id","userId","createTime");
            orderDetailMapper.save(orderDetail);
        }

    }

    //查询订单明细
    @Override
    public void list(List<OrderDto> orderDtoList) {
        for (OrderDto orderDto : orderDtoList) {
            orderDto.setOrderDetails(orderDetailMapper.list(orderDto.getId()));
        }
    }

    //再下一单
    @Override
    public void again(Long oldOrderId,Long newOrderId) {
        List<OrderDetail> orderDetailList = orderDetailMapper.list(oldOrderId);
        Long maxId=orderDetailMapper.getMaxId();
        if (maxId==null)
            maxId=0L;
        for (OrderDetail orderDetail : orderDetailList) {
            orderDetail.setId(maxId+1);
            maxId++;
            orderDetail.setOrderId(newOrderId);
            orderDetailMapper.save(orderDetail);
        }
    }
}
