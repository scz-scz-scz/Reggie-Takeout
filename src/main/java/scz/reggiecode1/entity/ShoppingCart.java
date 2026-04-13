package scz.reggiecode1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 购物车实体类
 * 用于存储用户的购物车信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart implements Serializable {
    
    private static final long serialVersionUID = 3L;
    
    /**
     * 主键ID
     */
    private Long id;
    
    /**
     * 商品名称
     */
    private String name;
    
    /**
     * 商品图片
     */
    private String image;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 菜品ID
     */
    private Long dishId;
    
    /**
     * 套餐ID
     */
    private Long setmealId;
    
    /**
     * 菜品口味
     */
    private String dishFlavor;
    
    /**
     * 数量
     */
    private Integer number;
    
    /**
     * 金额
     */
    private BigDecimal amount;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
