package scz.reggiecode1.service;

import scz.reggiecode1.dto.DishDto;
import scz.reggiecode1.entity.Dish;
import scz.reggiecode1.entity.PageBean;

import java.util.List;

/**
 * 菜品服务接口
 */
public interface DishService {

    /**
     * 新增菜品
     *
     * @param dishDto 菜品DTO
     */
    void save(DishDto dishDto);

    /**
     * 分页查询菜品
     *
     * @param page     页码
     * @param pageSize 每页记录数
     * @param name     菜品名称
     * @return 分页结果
     */
    PageBean<DishDto> list(Integer page, Integer pageSize, String name);

    /**
     * 根据ID查询菜品
     *
     * @param dishId 菜品ID
     * @return 菜品DTO
     */
    DishDto list(Long dishId);

    /**
     * 修改菜品
     *
     * @param dishDto 菜品DTO
     */
    void update(DishDto dishDto);

    /**
     * 批量修改菜品状态
     *
     * @param status 状态
     * @param ids    菜品ID数组
     * @return 菜品DTO列表
     */
    List<DishDto> updateStatus(Integer status, Long[] ids);

    /**
     * 批量删除菜品
     *
     * @param ids 菜品ID数组
     * @return 菜品DTO列表
     */
    List<DishDto> delete(Long[] ids);

    /**
     * 根据条件查询菜品列表
     *
     * @param dish 查询条件
     * @return 菜品DTO列表
     */
    List<DishDto> list(Dish dish);
}
