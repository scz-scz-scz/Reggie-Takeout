package scz.reggiecode1.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
import scz.reggiecode1.dto.SetmealDto;
import scz.reggiecode1.entity.Setmeal;

import java.util.List;

@Mapper
@Transactional(rollbackFor = Exception.class)
public interface SetmealMapper {
    //查询套餐是否关联分类
    Integer selectByCategoryId(Long categoryId);

    Long getMaxId();

    void save(SetmealDto setmealDto);

    Integer selectAll(String name);

    List<SetmealDto> list(int index, Integer pageSize, String name);

    String[] selectImage(Long[] ids);

    Integer delete(Long[] ids);

    void updateStatus(Setmeal setmeal);

    SetmealDto listById(Long id);

    void update(SetmealDto setmealDto);

    List<Setmeal> listByCategoryId(Setmeal setmeal);
}
