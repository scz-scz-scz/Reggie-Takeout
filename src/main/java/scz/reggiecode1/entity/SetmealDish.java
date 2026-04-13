package scz.reggiecode1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 套餐菜品关系实体类
 * 用于存储套餐与菜品的关联关系
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetmealDish {
    
    /**
     * 主键ID
     */
    private Long id;
    
    /**
     * 套餐ID
     */
    private Long setmealId;
    
    /**
     * 菜品ID
     */
    private Long dishId;
    
    /**
     * 菜品名称
     */
    private String name;
    
    /**
     * 菜品价格
     */
    private BigDecimal price;
    
    /**
     * 份数
     */
    private Integer copies;
    
    /**
     * 排序字段
     */
    private Integer sort;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    
    /**
     * 创建人ID
     */
    private Long createUser;
    
    /**
     * 更新人ID
     */
    private Long updateUser;
    
    /**
     * 是否删除（0为未删除，1为已删除）
     */
    private Integer isDeleted;
    
    /**
     * 菜品图片（冗余变量，用于在给客户端获取菜品图片的文件名）
     */
    private String image;
}
