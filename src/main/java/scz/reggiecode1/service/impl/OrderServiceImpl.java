package scz.reggiecode1.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scz.reggiecode1.common.BaseContext;
import scz.reggiecode1.common.MyMetaObjectHandler;
import scz.reggiecode1.dto.OrderDto;
import scz.reggiecode1.entity.AddressBook;
import scz.reggiecode1.entity.Orders;
import scz.reggiecode1.entity.PageBean;
import scz.reggiecode1.entity.ShoppingCart;
import scz.reggiecode1.mapper.OrderMapper;
import scz.reggiecode1.service.OrderService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    //下单
    @Override
    public void save(Orders order, AddressBook addressBook,List<ShoppingCart> shoppingCartList) {
        Long maxId=orderMapper.getMaxId();
        if (maxId==null)
            maxId=0L;
        order.setId(maxId+1);
        //随机生成订单号
        order.setNumber(String.valueOf(new Random().nextLong(1000000000000L)));
        order.setStatus(2);
        MyMetaObjectHandler.save(order);
        order.setPhone(addressBook.getPhone());
        String address="";
        if (addressBook.getProvinceName()!=null)
            address+=addressBook.getProvinceName()+" ";
        if (addressBook.getCityName()!=null)
            address+=addressBook.getCityName()+" ";
        if (addressBook.getDistrictName()!=null)
            address+=addressBook.getDistrictName()+" ";
        order.setAddress(address+addressBook.getDetail());
        order.setConsignee(addressBook.getConsignee());
        BigDecimal amount= new BigDecimal(0);
        for (ShoppingCart shoppingCart : shoppingCartList) {
            amount=amount.add(shoppingCart.getAmount().multiply(BigDecimal.valueOf(shoppingCart.getNumber())));
        }
        order.setAmount(amount);
        orderMapper.save(order);
    }

    //用户端查询订单
    @Override
    public PageBean<OrderDto> list4User(Integer page, Integer pageSize) {
        Integer total=orderMapper.selectAllByUserId(BaseContext.getCurrentId());
        int index=(page-1)*pageSize;
        List<OrderDto> orderDtoList=orderMapper.list4User(index,pageSize,BaseContext.getCurrentId());
        PageBean<OrderDto> result=new PageBean<>(total,orderDtoList,orderDtoList.size());
        return result;
    }

    //再下一单
    @Override
    public Orders again(Orders order) {
        order=orderMapper.listById(order);
        Long maxId=orderMapper.getMaxId();
        if (maxId==null)
            maxId=0L;
        order.setId(maxId+1);
        //随机生成订单号
        order.setNumber(String.valueOf(new Random().nextLong(1000000000000L)));
        order.setStatus(2);
        MyMetaObjectHandler.save(order);
        orderMapper.save(order);
        return order;
    }

    @Override
    public PageBean<Orders> list4Employee(Integer page, Integer pageSize, String number, LocalDateTime beginTime, LocalDateTime endTime) {
        Integer total=orderMapper.selectAll(number,beginTime,endTime);
        int index=(page-1)*pageSize;
        List<Orders> orderList=orderMapper.list(index,pageSize,number,beginTime,endTime);
        PageBean<Orders> result=new PageBean<>(total,orderList,orderList.size());
        return result;
    }

    //员工端派送、完成订单
    @Override
    public void updateStatus(Orders order) {
        orderMapper.updateStatus(order);
    }
}
