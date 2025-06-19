package scz.reggiecode1.service;

import scz.reggiecode1.dto.DishDto;
import scz.reggiecode1.entity.DishFlavor;

import java.util.List;

public interface DishFlavorService {
    void save(DishDto dishDto);

    List<DishFlavor> list(Long dishId);

    void update(DishDto dishDto);

    void delete(Long[] ids);
}
