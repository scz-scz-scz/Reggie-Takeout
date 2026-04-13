package scz.reggiecode1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 地址簿实体类
 * 用于存储用户的收货地址信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressBook implements Serializable {
    
    private static final long serialVersionUID = 2L;
    
    /**
     * 主键ID
     */
    private Long id;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 收货人
     */
    private String consignee;
    
    /**
     * 手机号
     */
    private String phone;
    
    /**
     * 性别
     */
    private String sex;
    
    /**
     * 省级区划编码
     */
    private String provinceCode;
    
    /**
     * 省级名称
     */
    private String provinceName;
    
    /**
     * 市级区划编码
     */
    private String cityCode;
    
    /**
     * 市级名称
     */
    private String cityName;
    
    /**
     * 区级区划编码
     */
    private String districtCode;
    
    /**
     * 区级名称
     */
    private String districtName;
    
    /**
     * 详细地址
     */
    private String detail;
    
    /**
     * 地址标签（如：家、公司等）
     */
    private String label;
    
    /**
     * 是否默认地址（1为默认，0为非默认）
     */
    private Integer isDefault;
    
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
