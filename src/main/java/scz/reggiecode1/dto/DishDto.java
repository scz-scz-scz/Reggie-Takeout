package scz.reggiecode1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import scz.reggiecode1.entity.Dish;
import scz.reggiecode1.entity.DishFlavor;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜品数据传输对象
 * 用于封装菜品及其关联的口味信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishDto extends Dish {

    // 菜品口味列表
    private List<DishFlavor> flavors = new ArrayList<>();

    // 菜品分类名称
    private String categoryName;

    // 菜品份数
    private Integer copies;
}
