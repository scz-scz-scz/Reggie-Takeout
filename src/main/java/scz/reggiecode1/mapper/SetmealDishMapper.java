package scz.reggiecode1.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
import scz.reggiecode1.dto.DishDto;
import scz.reggiecode1.entity.SetmealDish;

import java.util.List;

@Mapper
@Transactional(rollbackFor = Exception.class)
public interface SetmealDishMapper {
    void update(DishDto dishDto);

    Long getMaxId();

    void save(SetmealDish setmealDish);

    void delete(Long[] ids);

    List<SetmealDish> list(Long setmealId);

    void preDelete(Long setmealId);

    Integer getMaxSortBySetmealId(Long setmealId);

    Integer getSortBySetmealIdAndDishId(Long setmealId,Long dishId);

    void updateBySetmealIdAndDishId(SetmealDish setmealDish);

    void deleteByIsDeleted(Long setmealId);

    void deleteByDishId(Long[] dishIds);
}
