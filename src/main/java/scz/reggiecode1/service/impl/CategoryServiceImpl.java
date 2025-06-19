package scz.reggiecode1.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scz.reggiecode1.common.CustomException;
import scz.reggiecode1.common.MyMetaObjectHandler;
import scz.reggiecode1.entity.Category;
import scz.reggiecode1.entity.PageBean;
import scz.reggiecode1.mapper.CategoryMapper;
import scz.reggiecode1.mapper.DishMapper;
import scz.reggiecode1.mapper.SetmealMapper;
import scz.reggiecode1.service.CategoryService;

import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealMapper setmealMapper;

    //新增分类
    @Override
    public void save(Category category) {
        //获取并设置当前最大id
        Long maxId=categoryMapper.getMaxId();
        if (maxId==null)
            category.setId((long) 1);
        else
            category.setId(maxId+1);
        MyMetaObjectHandler.save(category);
        if (category.getType()==1)
            log.info("新增的菜品分类为{}",category.toString());
        else
            log.info("新增的套餐分类为{}",category.toString());
        categoryMapper.save(category);
    }

    //查询分类（分页展示）
    @Override
    public PageBean<Category> list(Integer page, Integer pageSize) {
        Integer index=(page-1)*pageSize;
        Integer total=categoryMapper.selectAll();
        List<Category> categoryList=categoryMapper.list(index,pageSize);
        return new PageBean<>(total,categoryList,categoryList.size());
    }

    //删除分类
    @Override
    public void delete(Long id) {
        //判断分类是否关联菜品
        if (dishMapper.selectByCategoryId(id)>0)
            throw new CustomException("删除失败：当前分类关联了菜品");
        //判断分类是否关联套餐
        if (setmealMapper.selectByCategoryId(id)>0)
            throw new CustomException("删除失败：当前分类关联了套餐");
        categoryMapper.delete(id);
    }

    //修改分类
    @Override
    public void updateCategory(Category category) {
        MyMetaObjectHandler.update(category);
        categoryMapper.updateCategory(category);
    }

    //查询分类（新增菜品或套餐时展示）
    @Override
    public List<Category> list(Category category) {
        List<Category> categoryList=categoryMapper.listByType(category);
        return categoryList;
    }
}
