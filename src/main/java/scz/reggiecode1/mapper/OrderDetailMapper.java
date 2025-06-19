package scz.reggiecode1.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
import scz.reggiecode1.entity.OrderDetail;

import java.util.List;

@Mapper
@Transactional(rollbackFor = Exception.class)
public interface OrderDetailMapper {
    Long getMaxId();

    void save(OrderDetail orderDetail);

    List<OrderDetail> list(Long orderId);
}
