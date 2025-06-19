package scz.reggiecode1.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
import scz.reggiecode1.entity.Category;

import java.util.List;

@Mapper
@Transactional(rollbackFor = Exception.class)
public interface CategoryMapper {
    Long getMaxId();

    void save(Category category);

    Integer selectAll();

    List<Category> list(Integer index, Integer pageSize);

    void delete(Long id);

    void updateCategory(Category category);

    List<Category> listByType(Category category);

    String selectById(Long id);
}
