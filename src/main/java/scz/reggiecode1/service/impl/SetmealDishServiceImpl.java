package scz.reggiecode1.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scz.reggiecode1.common.MyMetaObjectHandler;
import scz.reggiecode1.dto.DishDto;
import scz.reggiecode1.dto.SetmealDto;
import scz.reggiecode1.entity.SetmealDish;
import scz.reggiecode1.mapper.SetmealDishMapper;
import scz.reggiecode1.mapper.SetmealMapper;
import scz.reggiecode1.service.SetmealDishService;

import java.util.List;

@Slf4j
@Service
public class SetmealDishServiceImpl implements SetmealDishService {
    @Autowired
    private SetmealDishMapper setmealDishMapper;

    //修改套餐中的菜品
    @Override
    public void update(DishDto dishDto) {
        MyMetaObjectHandler.update(dishDto);
        setmealDishMapper.update(dishDto);
    }

    //新增套餐中的菜品
    @Override
    public void save(SetmealDto setmealDto) {
        Long maxId=setmealDishMapper.getMaxId();
        if (maxId==null)
            maxId=(long) 0;
        Long setmealId=setmealDto.getId();
        List<SetmealDish> setmealDishList=setmealDto.getSetmealDishes();
        for (int i = 0; i < setmealDishList.size(); i++) {
            setmealDishList.get(i).setId(maxId+i+1);
            setmealDishList.get(i).setSetmealId(setmealId);
            setmealDishList.get(i).setSort(i);
            MyMetaObjectHandler.save(setmealDishList.get(i));
            setmealDishMapper.save(setmealDishList.get(i));
        }
    }

    //删除菜品
    @Override
    public void delete(Long[] ids) {
        setmealDishMapper.delete(ids);
    }

    //查询菜品
    @Override
    public List<SetmealDish> list(Long setmealId) {
        return setmealDishMapper.list(setmealId);
    }

    //修改套餐时修改其中的菜品
    @Override
    public void update(SetmealDto setmealDto) {
        Long setmealId=setmealDto.getId();
        //先将isDeleted全设为1
        setmealDishMapper.preDelete(setmealId);
        //修改更新，修改过的菜品的is_deleted会重新变为0
        List<SetmealDish> setmealDishList=setmealDto.getSetmealDishes();
        Long maxId=setmealDishMapper.getMaxId();
        Integer maxSort=setmealDishMapper.getMaxSortBySetmealId(setmealId);
        if (maxId==null)
            maxId=(long) 0;
        if (maxSort==null)
            maxSort=-1;
        int count=1;
        for (SetmealDish setmealDish : setmealDishList) {
            setmealDish.setSetmealId(setmealId);
            Integer sort=setmealDishMapper.getSortBySetmealIdAndDishId(setmealId,setmealDish.getDishId());
            //要更新的菜品
            if (sort!=null){
                MyMetaObjectHandler.update(setmealDish);
                setmealDish.setIsDeleted(0);
                setmealDishMapper.updateBySetmealIdAndDishId(setmealDish);
            }
            //要新增的菜品
            else {
                setmealDish.setId(maxId+count);
                setmealDish.setSort(maxSort+count);
                count++;
                MyMetaObjectHandler.save(setmealDish);
                setmealDishMapper.save(setmealDish);
            }
        }
        //将is_deleted仍然为1的菜品删除
        setmealDishMapper.deleteByIsDeleted(setmealId);
    }

    //通过菜品id删除菜品
    @Override
    public void deleteByDishId(Long[] dishIds) {
        setmealDishMapper.deleteByDishId(dishIds);
    }
}
