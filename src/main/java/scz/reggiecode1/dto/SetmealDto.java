package scz.reggiecode1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import scz.reggiecode1.entity.Setmeal;
import scz.reggiecode1.entity.SetmealDish;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetmealDto extends Setmeal {
    List<SetmealDish> setmealDishes;
    private String categoryName;
}
