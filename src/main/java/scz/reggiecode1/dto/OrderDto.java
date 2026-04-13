package scz.reggiecode1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import scz.reggiecode1.entity.OrderDetail;
import scz.reggiecode1.entity.Orders;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单数据传输对象
 * 用于封装订单及其关联的订单明细信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto extends Orders {

    // 订单明细列表
    private List<OrderDetail> orderDetails = new ArrayList<>();
}
