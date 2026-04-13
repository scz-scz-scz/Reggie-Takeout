package scz.reggiecode1.service;

import scz.reggiecode1.dto.DishDto;
import scz.reggiecode1.dto.SetmealDto;
import scz.reggiecode1.entity.SetmealDish;

import java.util.List;

/**
 * 套餐菜品服务接口
 */
public interface SetmealDishService {

    /**
     * 修改套餐中的菜品
     *
     * @param dishDto 菜品DTO
     */
    void update(DishDto dishDto);

    /**
     * 新增套餐中的菜品
     *
     * @param setmealDto 套餐DTO
     */
    void save(SetmealDto setmealDto);

    /**
     * 删除套餐中的菜品
     *
     * @param ids 套餐ID数组
     */
    void delete(Long[] ids);

    /**
     * 根据套餐ID查询菜品列表
     *
     * @param setmealId 套餐ID
     * @return 套餐菜品列表
     */
    List<SetmealDish> list(Long setmealId);

    /**
     * 修改套餐中的菜品
     *
     * @param setmealDto 套餐DTO
     */
    void update(SetmealDto setmealDto);

    /**
     * 根据菜品ID删除套餐中的菜品
     *
     * @param dishIds 菜品ID数组
     */
    void deleteByDishId(Long[] dishIds);
}
