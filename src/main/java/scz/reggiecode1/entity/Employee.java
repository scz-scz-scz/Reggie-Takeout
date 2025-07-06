package scz.reggiecode1.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "员工",description = "包含员工的各种属性")
public class Employee {
    private static final long serialVersionUID=1L;
    @Schema(description = "主键id")
    private Long id;
    @Schema(description = "姓名")
    private String name;
    @Schema(description = "用户名",example = "admin")
    private String username;
    @Schema(description = "密码",example = "123456")
    private String password;
    @Schema(description = "手机号")
    private String phone;
    @Schema(description = "性别（1为男，2为女）")
    private String sex;
    @Schema(description = "身份证号")
    private String idNumber;
    @Schema(description = "状态（1为正常，0为禁用）")
    private Integer status;
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
    @Schema(description = "创建人")
    private Long createUser;
    @Schema(description = "修改人")
    private Long updateUser;
}
