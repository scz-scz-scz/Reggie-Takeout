package scz.reggiecode1.service;

import scz.reggiecode1.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    void save(ShoppingCart shoppingCart);

    List<ShoppingCart> list();

    void clean();

    void delete(ShoppingCart shoppingCart);
}
