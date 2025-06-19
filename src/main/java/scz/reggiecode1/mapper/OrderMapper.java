package scz.reggiecode1.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
import scz.reggiecode1.dto.OrderDto;
import scz.reggiecode1.entity.Orders;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Transactional(rollbackFor = Exception.class)
public interface OrderMapper {
    Long getMaxId();

    void save(Orders order);

    Integer selectAllByUserId(Long userId);

    List<OrderDto> list4User(int index, Integer pageSize, Long userId);

    Orders listById(Orders order);

    Integer selectAll(String number, LocalDateTime beginTime, LocalDateTime endTime);

    List<Orders> list(Integer index, Integer pageSize, String number, LocalDateTime beginTime, LocalDateTime endTime);

    void updateStatus(Orders order);
}
