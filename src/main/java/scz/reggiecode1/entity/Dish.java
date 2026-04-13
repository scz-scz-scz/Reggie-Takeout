package scz.reggiecode1.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 菜品实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "菜品", description = "包含菜品的各种属性")
public class Dish {
    
    /**
     * 主键ID
     */
    @Schema(description = "主键id", example = "1397849739276890114")
    private Long id;
    
    /**
     * 菜品名称
     */
    @Schema(description = "名称", example = "辣子鸡")
    private String name;
    
    /**
     * 所属分类ID
     */
    @Schema(description = "所属分类的id", example = "1397844263642378242")
    private Long categoryId;
    
    /**
     * 价格（单位：分）
     */
    @Schema(description = "价格/分", example = "7800.00")
    private BigDecimal price;
    
    /**
     * 商品码
     */
    @Schema(description = "商品码", example = "222222222")
    private String code;
    
    /**
     * 图片路径
     */
    @Schema(description = "图片", example = "222222222")
    private String image;
    
    /**
     * 描述信息
     */
    @Schema(description = "描述信息", example = "来自鲜嫩美味的小鸡，值得一尝")
    private String description;
    
    /**
     * 售卖状态（1为启售，0为停售）
     */
    @Schema(description = "售卖状态（1为启售，0为停售）", example = "1")
    private Integer status;
    
    /**
     * 排序字段
     */
    @Schema(description = "顺序", example = "0")
    private Integer sort;
    
    /**
     * 创建时间
     */
    @Schema(description = "创建时间", example = "2021-05-27 09:38:43")
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    @Schema(description = "更新时间", example = "2021-05-27 09:38:43")
    private LocalDateTime updateTime;
    
    /**
     * 创建人ID
     */
    @Schema(description = "创建人", example = "1")
    private Long createUser;
    
    /**
     * 修改人ID
     */
    @Schema(description = "修改人", example = "1")
    private Long updateUser;
    
    /**
     * 是否删除（0为未删除，1为已删除）
     */
    @Schema(description = "是否删除", example = "0")
    private Integer isDeleted;
}
