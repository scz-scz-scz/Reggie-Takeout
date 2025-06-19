package scz.reggiecode1.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
import scz.reggiecode1.entity.ShoppingCart;

import java.util.List;

@Mapper
@Transactional(rollbackFor = Exception.class)
public interface ShoppingCartMapper {
    Integer getNumber(ShoppingCart shoppingCart);

    Long getMaxId();

    void save(ShoppingCart shoppingCart);

    void update(ShoppingCart shoppingCart);

    List<ShoppingCart> list(Long userId);

    void clean(Long userId);

    void delete(ShoppingCart shoppingCart);
}
