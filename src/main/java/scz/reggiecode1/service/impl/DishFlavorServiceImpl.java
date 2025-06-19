package scz.reggiecode1.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scz.reggiecode1.common.MyMetaObjectHandler;
import scz.reggiecode1.dto.DishDto;
import scz.reggiecode1.entity.DishFlavor;
import scz.reggiecode1.mapper.DishFlavorMapper;
import scz.reggiecode1.service.DishFlavorService;

import java.util.List;

@Slf4j
@Service
public class DishFlavorServiceImpl implements DishFlavorService {
    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    //新增口味
    @Override
    public void save(DishDto dishDto) {
        List<DishFlavor> dishFlavorList=dishDto.getFlavors();
        Long maxId=dishFlavorMapper.getMaxId();
        if (maxId==null)
            maxId=(long) 0;
        int count=1;
        for (DishFlavor dishFlavor : dishFlavorList) {
            if (dishFlavor.getName() == null || dishFlavor.getName().isEmpty())
                continue;
            dishFlavor.setId(maxId + count);
            count++;
            MyMetaObjectHandler.save(dishFlavor);
            dishFlavorMapper.save(dishFlavor);
        }
    }

    //查询口味
    @Override
    public List<DishFlavor> list(Long dishId) {
        List<DishFlavor> dishFlavorList=dishFlavorMapper.list(dishId);
        return dishFlavorList;
    }

    //修改口味
    @Override
    public void update(DishDto dishDto) {
        Long dishId=dishDto.getId();
        //先默认删除口味，将is_deleted设为1
        DishFlavor Flavor=new DishFlavor();
        Flavor.setDishId(dishId);
        MyMetaObjectHandler.update(Flavor);
        Flavor.setIsDeleted(1);
        dishFlavorMapper.predelete(Flavor);
        //修改更新，修改过的口味的is_deleted会重新变为0
        List<DishFlavor> dishFlavorList=dishDto.getFlavors();
        Long maxId=dishFlavorMapper.getMaxId();
        if (maxId==null)
            maxId=(long) 0;
        int count=1;
        for (DishFlavor dishFlavor : dishFlavorList) {
            if (dishFlavor.getName() == null || dishFlavor.getName().isEmpty())
                continue;
            //要更新的口味
            if (dishFlavor.getId()!=null){
                MyMetaObjectHandler.update(dishFlavor);
                dishFlavorMapper.update(dishFlavor);
            }
            //要新增的口味
            else {
                dishFlavor.setId(maxId + count);
                count++;
                dishFlavor.setDishId(dishId);
                MyMetaObjectHandler.save(dishFlavor);
                dishFlavorMapper.save(dishFlavor);
            }
        }
        //将is_deleted仍然为1的口味删除
        dishFlavorMapper.delete(dishId);
    }

    //删除口味
    @Override
    public void delete(Long[] dishIds) {
        for (Long dishId : dishIds) {
            DishFlavor dishFlavor=new DishFlavor();
            dishFlavor.setDishId(dishId);
            MyMetaObjectHandler.update(dishFlavor);
            dishFlavor.setIsDeleted(1);
            dishFlavorMapper.predelete(dishFlavor);
            dishFlavorMapper.delete(dishId);
        }
    }
}
