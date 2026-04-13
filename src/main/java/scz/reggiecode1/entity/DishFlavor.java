package scz.reggiecode1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 菜品口味实体类
 * 用于存储菜品的口味信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishFlavor {
    
    /**
     * 主键ID
     */
    private Long id;
    
    /**
     * 菜品ID
     */
    private Long dishId;
    
    /**
     * 口味名称
     */
    private String name;
    
    /**
     * 口味值（JSON格式）
     */
    private String value;
    
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
}
