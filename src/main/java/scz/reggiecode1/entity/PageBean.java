package scz.reggiecode1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {
    private Integer total;
    private List<T> records;
    //冗余变量，用于客户端查询订单时的终止条件
    private Integer pages;
}
