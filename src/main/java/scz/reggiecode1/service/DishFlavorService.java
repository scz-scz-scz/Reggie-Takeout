package scz.reggiecode1.service;

import scz.reggiecode1.dto.DishDto;
import scz.reggiecode1.entity.DishFlavor;

import java.util.List;

/**
 * 菜品口味服务接口
 */
public interface DishFlavorService {

    /**
     * 新增菜品口味
     *
     * @param dishDto 菜品DTO
     */
    void save(DishDto dishDto);

    /**
     * 根据菜品ID查询口味列表
     *
     * @param dishId 菜品ID
     * @return 口味列表
     */
    List<DishFlavor> list(Long dishId);

    /**
     * 修改菜品口味
     *
     * @param dishDto 菜品DTO
     */
    void update(DishDto dishDto);

    /**
     * 删除菜品口味
     *
     * @param ids 菜品ID数组
     */
    void delete(Long[] ids);
}
