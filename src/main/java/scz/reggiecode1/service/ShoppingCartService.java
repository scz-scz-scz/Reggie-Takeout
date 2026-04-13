package scz.reggiecode1.service;

import scz.reggiecode1.entity.ShoppingCart;

import java.util.List;

/**
 * 购物车服务接口
 */
public interface ShoppingCartService {

    /**
     * 添加购物车
     *
     * @param shoppingCart 购物车信息
     */
    void save(ShoppingCart shoppingCart);

    /**
     * 查询购物车列表
     *
     * @return 购物车列表
     */
    List<ShoppingCart> list();

    /**
     * 清空购物车
     */
    void clean();

    /**
     * 删除购物车项
     *
     * @param shoppingCart 购物车信息
     */
    void delete(ShoppingCart shoppingCart);
}
