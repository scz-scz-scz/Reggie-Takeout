package scz.reggiecode1.service;

import scz.reggiecode1.dto.SetmealDto;
import scz.reggiecode1.entity.PageBean;
import scz.reggiecode1.entity.Setmeal;

import java.util.List;

/**
 * 套餐服务接口
 */
public interface SetmealService {

    /**
     * 新增套餐
     *
     * @param setmealDto 套餐DTO
     */
    void save(SetmealDto setmealDto);

    /**
     * 分页查询套餐
     *
     * @param page     页码
     * @param pageSize 每页记录数
     * @param name     套餐名称
     * @return 分页结果
     */
    PageBean<SetmealDto> list(Integer page, Integer pageSize, String name);

    /**
     * 批量删除套餐
     *
     * @param ids 套餐ID数组
     * @return 图片名称数组
     */
    String[] delete(Long[] ids);

    /**
     * 批量修改套餐状态
     *
     * @param status 状态
     * @param ids    套餐ID数组
     */
    void updateStatus(Integer status, Long[] ids);

    /**
     * 根据ID查询套餐
     *
     * @param id 套餐ID
     * @return 套餐DTO
     */
    SetmealDto list(Long id);

    /**
     * 修改套餐
     *
     * @param setmealDto 套餐DTO
     */
    void update(SetmealDto setmealDto);

    /**
     * 根据条件查询套餐列表
     *
     * @param setmeal 查询条件
     * @return 套餐列表
     */
    List<Setmeal> list(Setmeal setmeal);
}
