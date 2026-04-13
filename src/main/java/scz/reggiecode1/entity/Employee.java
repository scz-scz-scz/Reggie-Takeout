package scz.reggiecode1.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 员工实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "员工", description = "包含员工的各种属性")
public class Employee {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键ID
     */
    @Schema(description = "主键id")
    private Long id;
    
    /**
     * 员工姓名
     */
    @Schema(description = "姓名")
    private String name;
    
    /**
     * 用户名
     */
    @Schema(description = "用户名", example = "admin")
    private String username;
    
    /**
     * 密码
     */
    @Schema(description = "密码", example = "123456")
    private String password;
    
    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String phone;
    
    /**
     * 性别（1为男，2为女）
     */
    @Schema(description = "性别（1为男，2为女）")
    private String sex;
    
    /**
     * 身份证号
     */
    @Schema(description = "身份证号")
    private String idNumber;
    
    /**
     * 状态（1为正常，0为禁用）
     */
    @Schema(description = "状态（1为正常，0为禁用）")
    private Integer status;
    
    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
    
    /**
     * 创建人ID
     */
    @Schema(description = "创建人")
    private Long createUser;
    
    /**
     * 修改人ID
     */
    @Schema(description = "修改人")
    private Long updateUser;
}
