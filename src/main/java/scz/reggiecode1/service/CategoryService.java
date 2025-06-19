package scz.reggiecode1.service;

import scz.reggiecode1.entity.Category;
import scz.reggiecode1.entity.PageBean;

import java.util.List;

public interface CategoryService {
    void save(Category category);

    PageBean<Category> list(Integer page, Integer pageSize);

    void delete(Long id);

    void updateCategory(Category category);

    List<Category> list(Category category);
}
