package scz.reggiecode1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
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

@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;
    @Autowired
    private SetmealDishService setmealDishService;
    @Autowired
    private DishService dishService;
    @Autowired
    private CommonController commonController;

    //新增套餐
    @PostMapping
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    @CacheEvict(value = "setmealCache",allEntries = true)   //allEntries指删除当前setmealCache下的所有缓存数据
    public Result<String> save(@RequestBody SetmealDto setmealDto){
        setmealService.save(setmealDto);
        setmealDishService.save(setmealDto);
        return Result.success("新增套餐成功");
    }

    //查询套餐（分页展示）
    @GetMapping("/page")
    public Result<PageBean<SetmealDto>> list(@RequestParam Integer page,@RequestParam Integer pageSize,String name){
        PageBean<SetmealDto> setmealList=setmealService.list(page,pageSize,name);
        return Result.success(setmealList);
    }

    //删除套餐
    @DeleteMapping
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    @CacheEvict(value = "setmealCache",allEntries = true)   //allEntries指删除当前setmealCache下的所有缓存数据
    public Result<String> delete(@RequestParam Long[] ids){
        //删除菜品
        setmealDishService.delete(ids);
        //删除套餐并获取图片名
        String[] name=setmealService.delete(ids);
        //删除图片
        commonController.delete(name);
        return Result.success("删除套餐成功");
    }

    //启停售套餐
    @PostMapping("/status/{status}")
    @CacheEvict(value = "setmealCache",allEntries = true)   //allEntries指删除当前setmealCache下的所有缓存数据
    public Result<String> updateStatus(@PathVariable Integer status,@RequestParam Long[] ids){
        setmealService.updateStatus(status,ids);
        if (status==0)
            return Result.success("停售套餐成功");
        return Result.success("启售套餐成功");
    }

    //查询套餐（修改套餐时展示）
    @GetMapping("/{setmealId}")
    public Result<SetmealDto> list(@PathVariable Long setmealId){
        SetmealDto setmealDto=setmealService.list(setmealId);
        setmealDto.setSetmealDishes(setmealDishService.list(setmealId));
        return Result.success(setmealDto);
    }

    //修改套餐
    @PutMapping
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    @CacheEvict(value = "setmealCache",allEntries = true)   //allEntries指删除当前setmealCache下的所有缓存数据
    public Result<String> update(@RequestBody SetmealDto setmealDto){
        setmealService.update(setmealDto);
        setmealDishService.update(setmealDto);
        return Result.success("修改套餐成功");
    }

    //查询套餐（用户展示）
    @GetMapping("/list")
    //unless指满足条件时不缓存，它只能用result，condition指满足条件时缓存，它可以用root和p
    @Cacheable(value = "setmealCache",key = "'setmeal_category_'+#setmeal.categoryId",unless = "#result==null")
    public Result<List<Setmeal>> list(Setmeal setmeal){
        if (setmeal.getCategoryId()==null||setmeal.getStatus()==null)
            return Result.error("查询套餐失败");
        List<Setmeal> setmealList=setmealService.list(setmeal);
        return Result.success(setmealList);
    }

    //查询套餐中菜品
    @GetMapping("/dish/{setmealId}")
    public Result<List<SetmealDish>> listDish(@PathVariable Long setmealId){
        if (setmealId==null)
            return Result.error("查询菜品失败");
        List<SetmealDish> setmealDishList=setmealDishService.list(setmealId);
        for (SetmealDish setmealDish : setmealDishList) {
            setmealDish.setImage(dishService.list(setmealDish.getDishId()).getImage());
        }
        return Result.success(setmealDishList);
    }
}