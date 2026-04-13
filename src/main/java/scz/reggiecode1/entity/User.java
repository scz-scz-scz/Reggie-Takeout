package scz.reggiecode1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户实体类
 * 用于存储C端用户信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键ID
     */
    private Long id;
    
    /**
     * 用户姓名
     */
    private String name;
    
    /**
     * 手机号
     */
    private String phone;
    
    /**
     * 性别
     */
    private String sex;
    
    /**
     * 身份证号
     */
    private String idNumber;
    
    /**
     * 头像
     */
    private String avatar;
    
    /**
     * 状态（1为正常，0为禁用）
     */
    private Integer status;
}
