package scz.reggiecode1.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scz.reggiecode1.common.CustomException;
import scz.reggiecode1.common.MyMetaObjectHandler;
import scz.reggiecode1.dto.DishDto;
import scz.reggiecode1.entity.Dish;
import scz.reggiecode1.entity.DishFlavor;
import scz.reggiecode1.entity.PageBean;
import scz.reggiecode1.mapper.CategoryMapper;
import scz.reggiecode1.mapper.DishMapper;
import scz.reggiecode1.service.DishService;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜品服务实现类
 */
@Slf4j
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private DishMapper dishMapper;

    /**
     * 新增菜品
     */
    @Override
    public void save(DishDto dishDto) {
        Dish dish = dishDto;
        Long maxId = dishMapper.getMaxId();
        if (maxId == null) {
            maxId = (long) 0;
        }
        Long dishId = maxId + 1;
        dish.setId(dishId);
        for (DishFlavor flavor : dishDto.getFlavors()) {
            flavor.setDishId(dishId);
        }
        MyMetaObjectHandler.save(dish);
        dishMapper.save(dish);
    }

    /**
     * 查询菜品（分页展示）
     */
    @Override
    public PageBean<DishDto> list(Integer page, Integer pageSize, String name) {
        Integer index = (page - 1) * pageSize;
        Integer total = dishMapper.selectAll(name);
        List<DishDto> dishDtoList = dishMapper.list(index, pageSize, name);
        for (DishDto dishDto : dishDtoList) {
            dishDto.setCategoryName(categoryMapper.selectById(dishDto.getCategoryId()));
        }
        return new PageBean<>(total, dishDtoList, dishDtoList.size());
    }

    /**
     * 查询菜品（修改菜品时展示）
     */
    @Override
    public DishDto list(Long dishId) {
        DishDto dishDto = dishMapper.listById(dishId);
        dishDto.setCategoryName(categoryMapper.selectById(dishDto.getCategoryId()));
        return dishDto;
    }

    /**
     * 修改菜品
     */
    @Override
    public void update(DishDto dishDto) {
        Dish dish = dishDto;
        MyMetaObjectHandler.update(dish);
        dishMapper.update(dish);
    }

    /**
     * 启停售菜品
     */
    @Override
    public List<DishDto> updateStatus(Integer status, Long[] ids) {
        List<DishDto> dishDtoList = new ArrayList<>();
        for (Long id : ids) {
            Dish dish = new Dish();
            dish.setId(id);
            dish.setStatus(status);
            MyMetaObjectHandler.update(dish);
            dishMapper.updateStatus(dish);
            dishDtoList.add(dishMapper.listById(id));
        }
        return dishDtoList;
    }

    /**
     * 删除菜品并获取图片名
     */
    @Override
    public List<DishDto> delete(Long[] ids) {
        List<DishDto> dishDtoList = new ArrayList<>();
        for (Long id : ids) {
            dishDtoList.add(dishMapper.listById(id));
        }
        Integer count = dishMapper.delete(ids);
        if (count != ids.length) {
            if (ids.length == 1) {
                throw new CustomException("删除失败：当前菜品状态为启售");
            }
            throw new CustomException("删除失败：当前批量选中的菜品中有菜品状态为启售");
        }
        return dishDtoList;
    }

    /**
     * 查询菜品（新建和修改套餐时展示，以及用户展示）
     */
    @Override
    public List<DishDto> list(Dish dish) {
        List<DishDto> dishDtoList = dishMapper.listByCategoryId(dish.getCategoryId());
        for (DishDto dishDto : dishDtoList) {
            dishDto.setCategoryName(categoryMapper.selectById(dishDto.getCategoryId()));
        }
        return dishDtoList;
    }
}
