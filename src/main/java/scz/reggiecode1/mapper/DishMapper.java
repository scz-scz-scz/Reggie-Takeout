package scz.reggiecode1.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
import scz.reggiecode1.dto.DishDto;
import scz.reggiecode1.entity.Dish;

import java.util.List;

@Mapper
@Transactional(rollbackFor = Exception.class)
public interface DishMapper {
    //查询菜品是否关联分类
    Integer selectByCategoryId(Long categoryId);

    Long getMaxId();

    void save(Dish dish);

    Integer selectAll(String name);

    List<DishDto> list(Integer index, Integer pageSize, String name);

    DishDto listById(Long dishId);

    void update(Dish dish);

    void updateStatus(Dish dish);

    String[] selectImage(Long[] ids);

    Integer delete(Long[] ids);

    List<DishDto> listByCategoryId(Long categoryId);
}