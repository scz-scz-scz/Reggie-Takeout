package scz.reggiecode1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import scz.reggiecode1.entity.Setmeal;
import scz.reggiecode1.entity.SetmealDish;

import java.util.List;

/**
 * 套餐数据传输对象
 * 用于封装套餐及其关联的菜品信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetmealDto extends Setmeal {

    // 套餐关联的菜品列表
    private List<SetmealDish> setmealDishes;

    // 套餐分类名称
    private String categoryName;
}
