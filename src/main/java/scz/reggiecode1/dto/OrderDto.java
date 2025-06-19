package scz.reggiecode1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import scz.reggiecode1.entity.OrderDetail;
import scz.reggiecode1.entity.Orders;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto extends Orders {
    private List<OrderDetail> orderDetails=new ArrayList<>();
}
