package scz.reggiecode1.service;

import scz.reggiecode1.entity.Category;
import scz.reggiecode1.entity.PageBean;

import java.util.List;

/**
 * 分类服务接口
 */
public interface CategoryService {

    /**
     * 新增分类
     *
     * @param category 分类信息
     */
    void save(Category category);

    /**
     * 分页查询分类
     *
     * @param page     页码
     * @param pageSize 每页记录数
     * @return 分页结果
     */
    PageBean<Category> list(Integer page, Integer pageSize);

    /**
     * 删除分类
     *
     * @param id 分类ID
     */
    void delete(Long id);

    /**
     * 修改分类
     *
     * @param category 分类信息
     */
    void updateCategory(Category category);

    /**
     * 根据条件查询分类列表
     *
     * @param category 查询条件
     * @return 分类列表
     */
    List<Category> list(Category category);
}
