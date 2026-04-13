package scz.reggiecode1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import scz.reggiecode1.common.Result;
import scz.reggiecode1.dto.DishDto;
import scz.reggiecode1.entity.Dish;
import scz.reggiecode1.entity.PageBean;
import scz.reggiecode1.service.DishFlavorService;
import scz.reggiecode1.service.DishService;
import scz.reggiecode1.service.SetmealDishService;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 菜品管理控制器
 * 负责菜品和菜品口味的管理
 */
@Slf4j
@RestController
@RequestMapping("/dish")
@Tag(name = "菜品管理")
public class DishController {
    
    private static final String CACHE_KEY_PREFIX = "dish_category_";
    
    @Autowired
    private DishService dishService;
    
    @Autowired
    private DishFlavorService dishFlavorService;
    
    @Autowired
    private SetmealDishService setmealDishService;
    
    @Autowired
    private CommonController commonController;
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 新增菜品
     */
    @PostMapping
    @Operation(summary = "新增菜品")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Result<String> save(@RequestBody DishDto dishDto) {
        log.info("新增菜品：{}", dishDto);
        dishService.save(dishDto);
        dishFlavorService.save(dishDto);
        delete(dishDto.getCategoryId());
        return Result.success("新增菜品成功");
    }

    /**
     * 分页查询菜品
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询菜品")
    public Result<PageBean<DishDto>> page(@RequestParam Integer page, @RequestParam Integer pageSize, String name) {
        log.info("分页查询菜品，页码：{}，每页数量：{}，菜品名称：{}", page, pageSize, name);
        PageBean<DishDto> dishDtoList = dishService.list(page, pageSize, name);
        return Result.success(dishDtoList);
    }

    /**
     * 根据ID查询菜品
     */
    @GetMapping("/{dishId}")
    @Operation(summary = "根据ID查询菜品")
    public Result<DishDto> getById(@PathVariable Long dishId) {
        log.info("根据ID查询菜品：{}", dishId);
        DishDto dishDto = dishService.list(dishId);
        dishDto.setFlavors(dishFlavorService.list(dishId));
        return Result.success(dishDto);
    }

    /**
     * 修改菜品
     */
    @PutMapping
    @Operation(summary = "修改菜品")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Result<String> update(@RequestBody DishDto dishDto) {
        log.info("修改菜品：{}", dishDto);
        dishService.update(dishDto);
        setmealDishService.update(dishDto);
        dishFlavorService.update(dishDto);
        delete(dishDto.getCategoryId());
        return Result.success("修改菜品成功");
    }

    /**
     * 启停售菜品
     */
    @PostMapping("/status/{status}")
    @Operation(summary = "启停售菜品")
    public Result<String> updateStatus(@PathVariable Integer status, @RequestParam Long[] ids) {
        log.info("启停售菜品，状态：{}，菜品ID：{}", status, ids);
        List<DishDto> dishDtoList = dishService.updateStatus(status, ids);
        for (DishDto dishDto : dishDtoList) {
            delete(dishDto.getCategoryId());
        }
        if (status == 0) {
            return Result.success("停售菜品成功");
        }
        return Result.success("启售菜品成功");
    }

    // 删除菜品
    @DeleteMapping
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Result<String> delete(@RequestParam Long[] ids) {
        //删除口味
        dishFlavorService.delete(ids);
        //删除关联套餐中的对应菜品
        setmealDishService.deleteByDishId(ids);
        //删除菜品并获取图片名
        List<DishDto> dishDtoList = dishService.delete(ids);
        //删除图片
        commonController.delete(dishDtoList.stream().map(Dish::getImage).toArray(String[]::new));
        for (DishDto dishDto : dishDtoList) {
            delete(dishDto.getCategoryId());
        }
        return Result.success("删除菜品成功");
    }

    // 查询菜品（新增和修改套餐时展示，以及用户展示）
    @GetMapping("/list")
    public Result<List<DishDto>> list(Dish dish) {
        if (dish.getCategoryId() == null)
            return Result.error("查询菜品失败");
        //先查缓存
        String key = CACHE_KEY_PREFIX + dish.getCategoryId();
        List<DishDto> dishDtoList = (List<DishDto>) redisTemplate.opsForValue().get(key);
        //缓存中存在就直接返回
        if (dishDtoList != null)
            return Result.success(dishDtoList);
        //缓存中不存在就查数据库，同时将结果加入缓存
        dishDtoList = dishService.list(dish);
        for (DishDto dishDto : dishDtoList) {
            dishDto.setFlavors(dishFlavorService.list(dishDto.getId()));
        }
        redisTemplate.opsForValue().set(key, dishDtoList, 60, TimeUnit.MINUTES);
        return Result.success(dishDtoList);
    }

    // 清理缓存
    private void delete(Long categoryId) {
        String key = CACHE_KEY_PREFIX + categoryId;
        redisTemplate.delete(key);
    }
}
