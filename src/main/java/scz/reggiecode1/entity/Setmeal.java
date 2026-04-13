package scz.reggiecode1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 套餐实体类
 * 用于存储套餐信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Setmeal {
    
    /**
     * 主键ID
     */
    private Long id;
    
    /**
     * 分类ID
     */
    private Long categoryId;
    
    /**
     * 套餐名称
     */
    private String name;
    
    /**
     * 套餐价格
     */
    private BigDecimal price;
    
    /**
     * 状态（1为启售，0为停售）
     */
    private Integer status;
    
    /**
     * 套餐编码
     */
    private String code;
    
    /**
     * 描述信息
     */
    private String description;
    
    /**
     * 图片路径
     */
    private String image;
    
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
    private Integer idDeleted;
}
