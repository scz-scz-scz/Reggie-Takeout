package scz.reggiecode1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单明细实体类
 * 用于存储订单中的具体商品信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail implements Serializable {
    
    private static final long serialVersionUID = 5L;
    
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
     * 订单ID
     */
    private Long orderId;
    
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
}
