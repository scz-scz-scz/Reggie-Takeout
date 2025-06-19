package scz.reggiecode1.service;

import scz.reggiecode1.dto.DishDto;
import scz.reggiecode1.dto.SetmealDto;
import scz.reggiecode1.entity.SetmealDish;

import java.util.List;

public interface SetmealDishService {
    void update(DishDto dishDto);

    void save(SetmealDto setmealDto);

    void delete(Long[] ids);

    List<SetmealDish> list(Long setmealId);

    void update(SetmealDto setmealDto);

    void deleteByDishId(Long[] dishIds);
}
