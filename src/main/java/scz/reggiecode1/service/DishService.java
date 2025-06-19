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

    void updateStatus(Integer status, Long[] ids);

    String[] delete(Long[] ids);

    List<DishDto> list(Dish dish);
}
