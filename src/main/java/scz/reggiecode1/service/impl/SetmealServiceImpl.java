package scz.reggiecode1.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scz.reggiecode1.common.CustomException;
import scz.reggiecode1.common.MyMetaObjectHandler;
import scz.reggiecode1.dto.SetmealDto;
import scz.reggiecode1.entity.PageBean;
import scz.reggiecode1.entity.Setmeal;
import scz.reggiecode1.mapper.CategoryMapper;
import scz.reggiecode1.mapper.SetmealMapper;
import scz.reggiecode1.service.SetmealService;

import java.util.List;

/**
 * 套餐服务实现类
 */
@Slf4j
@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 新增套餐
     */
    @Override
    public void save(SetmealDto setmealDto) {
        Long maxId = setmealMapper.getMaxId();
        if (maxId == null) {
            maxId = (long) 0;
        }
        setmealDto.setId(maxId + 1);
        MyMetaObjectHandler.save(setmealDto);
        setmealMapper.save(setmealDto);
    }

    /**
     * 查询套餐
     */
    @Override
    public PageBean<SetmealDto> list(Integer page, Integer pageSize, String name) {
        Integer total = setmealMapper.selectAll(name);
        int index = (page - 1) * pageSize;
        List<SetmealDto> setmealList = setmealMapper.list(index, pageSize, name);
        for (SetmealDto setmealDto : setmealList) {
            setmealDto.setCategoryName(categoryMapper.selectById(setmealDto.getCategoryId()));
        }
        PageBean<SetmealDto> result = new PageBean<>(total, setmealList, setmealList.size());
        return result;
    }

    /**
     * 删除套餐并获取图片名
     */
    @Override
    public String[] delete(Long[] ids) {
        String[] images = setmealMapper.selectImage(ids);
        Integer count = setmealMapper.delete(ids);
        if (count != ids.length) {
            if (ids.length == 1) {
                throw new CustomException("删除失败：当前套餐状态为启售");
            }
            throw new CustomException("删除失败：当前批量选中的套餐中有套餐状态为启售");
        }
        return images;
    }

    /**
     * 启停售套餐
     */
    @Override
    public void updateStatus(Integer status, Long[] ids) {
        for (Long id : ids) {
            Setmeal setmeal = new Setmeal();
            setmeal.setId(id);
            setmeal.setStatus(status);
            MyMetaObjectHandler.update(setmeal);
            setmealMapper.updateStatus(setmeal);
        }
    }

    /**
     * 查询套餐（修改套餐时展示）
     */
    @Override
    public SetmealDto list(Long id) {
        return setmealMapper.listById(id);
    }

    /**
     * 修改套餐
     */
    @Override
    public void update(SetmealDto setmealDto) {
        MyMetaObjectHandler.update(setmealDto);
        setmealMapper.update(setmealDto);
    }

    /**
     * 查询套餐（用户展示）
     */
    @Override
    public List<Setmeal> list(Setmeal setmeal) {
        List<Setmeal> setmealList = setmealMapper.listByCategoryId(setmeal);
        return setmealList;
    }
}
