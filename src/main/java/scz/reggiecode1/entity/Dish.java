package scz.reggiecode1.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "菜品", description = "包含菜品的各种属性")
public class Dish {
    @Schema(description = "主键id",example = "1397849739276890114")
    private Long id;
    @Schema(description = "名称",example = "辣子鸡")
    private String name;
    @Schema(description = "所属分类的id",example = "1397844263642378242")
    private Long categoryId;
    @Schema(description = "价格/分",example = "7800.00")
    private BigDecimal price;
    @Schema(description = "商品码",example = "222222222")
    private String code;
    @Schema(description = "图片",example = "222222222")
    private String image;
    @Schema(description = "描述信息",example = "来自鲜嫩美味的小鸡，值得一尝")
    private String description;
    @Schema(description = "售卖状态（1为启售，0为停售）",example = "1")
    private Integer status;
    @Schema(description = "顺序",example = "0")
    private Integer sort;
    @Schema(description = "创建时间",example = "2021-05-27 09:38:43")
    private LocalDateTime createTime;
    @Schema(description = "更新时间",example = "2021-05-27 09:38:43")
    private LocalDateTime updateTime;
    @Schema(description = "创建人",example = "1")
    private Long createUser;
    @Schema(description = "修改人",example = "1")
    private Long updateUser;
    @Schema(description = "是否删除",example = "0")
    private Integer isDeleted;
}
