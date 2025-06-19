package scz.reggiecode1.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
import scz.reggiecode1.entity.DishFlavor;

import java.util.List;

@Mapper
@Transactional(rollbackFor = Exception.class)
public interface DishFlavorMapper {
    Long getMaxId();

    void save(DishFlavor dishFlavor);

    List<DishFlavor> list(Long dishId);

    void predelete(DishFlavor dishFlavor);

    void update(DishFlavor dishFlavor);

    void delete(Long dishId);
}
