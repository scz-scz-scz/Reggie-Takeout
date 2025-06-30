package scz.reggiecode1.service;

import scz.reggiecode1.dto.DishDto;
import scz.reggiecode1.entity.Dish;
import scz.reggiecode1.entity.PageBean;

import java.util.List;

public interface DishService {
    void save(DishDto dishDto);

    PageBean<DishDto> list(Integer page, Integer pageSize,String name);

    DishDto list(Long dishId);

    void update(DishDto dishDto);

    List<DishDto> updateStatus(Integer status, Long[] ids);

    List<DishDto> delete(Long[] ids);

    List<DishDto> list(Dish dish);
}
