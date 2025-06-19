package scz.reggiecode1.service;

import scz.reggiecode1.dto.SetmealDto;
import scz.reggiecode1.entity.PageBean;
import scz.reggiecode1.entity.Setmeal;

import java.util.List;

public interface SetmealService {
    void save(SetmealDto setmealDto);

    PageBean<SetmealDto> list(Integer page, Integer pageSize, String name);

    String[] delete(Long[] ids);

    void updateStatus(Integer status, Long[] ids);

    SetmealDto list(Long id);

    void update(SetmealDto setmealDto);

    List<Setmeal> list(Setmeal setmeal);
}
