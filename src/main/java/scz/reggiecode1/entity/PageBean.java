package scz.reggiecode1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页结果封装类
 * 用于封装分页查询的结果数据
 *
 * @param <T> 数据类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {
    
    /**
     * 总记录数
     */
    private Integer total;
    
    /**
     * 当前页数据列表
     */
    private List<T> records;
    
    /**
     * 总页数（冗余变量，用于客户端查询订单时的终止条件）
     */
    private Integer pages;
}
