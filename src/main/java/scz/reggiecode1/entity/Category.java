package scz.reggiecode1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 分类实体类
 * 用于菜品和套餐的分类管理
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    
    /**
     * 主键ID
     */
    private Long id;
    
    /**
     * 类型（1为菜品分类，2为套餐分类）
     */
    private Integer type;
    
    /**
     * 分类名称
     */
    private String name;
    
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
}
