package scz.reggiecode1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import scz.reggiecode1.common.Result;
import scz.reggiecode1.dto.SetmealDto;
import scz.reggiecode1.entity.PageBean;
import scz.reggiecode1.entity.Setmeal;
import scz.reggiecode1.entity.SetmealDish;
import scz.reggiecode1.service.DishService;
import scz.reggiecode1.service.SetmealDishService;
import scz.reggiecode1.service.SetmealService;

import java.util.List;

/**
 * 套餐管理控制器
 * 负责套餐的增删改查、启停售等操作
 */
@Slf4j
@RestController
@RequestMapping("/setmeal")
@Tag(name = "套餐管理")
public class SetmealController {
    
    @Autowired
    private SetmealService setmealService;
    
    @Autowired
    private SetmealDishService setmealDishService;
    
    @Autowired
    private DishService dishService;
    
    @Autowired
    private CommonController commonController;

    /**
     * 新增套餐
     */
    @PostMapping
    @Operation(summary = "新增套餐")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @CacheEvict(value = "setmealCache", allEntries = true)
    public Result<String> save(@RequestBody SetmealDto setmealDto) {
        log.info("新增套餐：{}", setmealDto);
        setmealService.save(setmealDto);
        setmealDishService.save(setmealDto);
        return Result.success("新增套餐成功");
    }

    /**
     * 分页查询套餐
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询套餐")
    public Result<PageBean<SetmealDto>> list(@RequestParam Integer page, @RequestParam Integer pageSize, String name) {
        log.info("分页查询套餐，页码：{}，每页数量：{}，套餐名称：{}", page, pageSize, name);
        PageBean<SetmealDto> setmealList = setmealService.list(page, pageSize, name);
        return Result.success(setmealList);
    }

    /**
     * 删除套餐
     */
    @DeleteMapping
    @Operation(summary = "删除套餐")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @CacheEvict(value = "setmealCache", allEntries = true)
    public Result<String> delete(@RequestParam Long[] ids) {
        log.info("删除套餐：{}", (Object) ids);
        setmealDishService.delete(ids);
        String[] name = setmealService.delete(ids);
        commonController.delete(name);
        return Result.success("删除套餐成功");
    }

    /**
     * 启停售套餐
     */
    @PostMapping("/status/{status}")
    @Operation(summary = "启停售套餐")
    @CacheEvict(value = "setmealCache", allEntries = true)
    public Result<String> updateStatus(@PathVariable Integer status, @RequestParam Long[] ids) {
        log.info("启停售套餐，状态：{}，套餐ID：{}", status, ids);
        setmealService.updateStatus(status, ids);
        if (status == 0) {
            return Result.success("停售套餐成功");
        }
        return Result.success("启售套餐成功");
    }

    /**
     * 根据ID查询套餐
     */
    @GetMapping("/{setmealId}")
    @Operation(summary = "根据ID查询套餐")
    public Result<SetmealDto> list(@PathVariable Long setmealId) {
        log.info("根据ID查询套餐：{}", setmealId);
        SetmealDto setmealDto = setmealService.list(setmealId);
        setmealDto.setSetmealDishes(setmealDishService.list(setmealId));
        return Result.success(setmealDto);
    }

    /**
     * 修改套餐
     */
    @PutMapping
    @Operation(summary = "修改套餐")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @CacheEvict(value = "setmealCache", allEntries = true)
    public Result<String> update(@RequestBody SetmealDto setmealDto) {
        log.info("修改套餐：{}", setmealDto);
        setmealService.update(setmealDto);
        setmealDishService.update(setmealDto);
        return Result.success("修改套餐成功");
    }

    /**
     * 查询套餐列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询套餐列表")
    @Cacheable(value = "setmealCache", key = "'setmeal_category_'+#setmeal.categoryId", unless = "#result==null")
    public Result<List<Setmeal>> list(Setmeal setmeal) {
        log.info("查询套餐列表：{}", setmeal);
        if (setmeal.getCategoryId() == null || setmeal.getStatus() == null) {
            return Result.error("查询套餐失败");
        }
        List<Setmeal> setmealList = setmealService.list(setmeal);
        return Result.success(setmealList);
    }

    /**
     * 查询套餐中的菜品
     */
    @GetMapping("/dish/{setmealId}")
    @Operation(summary = "查询套餐中的菜品")
    public Result<List<SetmealDish>> listDish(@PathVariable Long setmealId) {
        log.info("查询套餐中的菜品：{}", setmealId);
        if (setmealId == null) {
            return Result.error("查询菜品失败");
        }
        List<SetmealDish> setmealDishList = setmealDishService.list(setmealId);
        for (SetmealDish setmealDish : setmealDishList) {
            setmealDish.setImage(dishService.list(setmealDish.getDishId()).getImage());
        }
        return Result.success(setmealDishList);
    }
}