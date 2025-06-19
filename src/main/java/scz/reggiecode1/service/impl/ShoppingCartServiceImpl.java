package scz.reggiecode1.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scz.reggiecode1.common.BaseContext;
import scz.reggiecode1.common.MyMetaObjectHandler;
import scz.reggiecode1.entity.ShoppingCart;
import scz.reggiecode1.mapper.ShoppingCartMapper;
import scz.reggiecode1.service.ShoppingCartService;

import java.util.List;

@Slf4j
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Override
    public void save(ShoppingCart shoppingCart) {
        MyMetaObjectHandler.save(shoppingCart);
        //先查询该菜品或套餐是否已经在购物车中
        Integer number=shoppingCartMapper.getNumber(shoppingCart);
        //不在购物车中
        if (number==null||number==0){
            Long maxId=shoppingCartMapper.getMaxId();
            if (maxId==null)
                maxId=0L;
            shoppingCart.setId(maxId+1);
            shoppingCart.setNumber(1);
            shoppingCartMapper.save(shoppingCart);
            return;
        }
        //已经在购物车中
        shoppingCart.setNumber(number+1);
        shoppingCartMapper.update(shoppingCart);
    }

    //查询购物车
    @Override
    public List<ShoppingCart> list() {
        List<ShoppingCart> shoppingCartList=shoppingCartMapper.list(BaseContext.getCurrentId());
        return shoppingCartList;
    }

    //清空购物车
    @Override
    public void clean() {
        shoppingCartMapper.clean(BaseContext.getCurrentId());
    }

    //修改购物车（减少数量）
    @Override
    public void delete(ShoppingCart shoppingCart) {
        MyMetaObjectHandler.save(shoppingCart);
        //先查看数量是否为1
        Integer number=shoppingCartMapper.getNumber(shoppingCart);
        //数量为1，删除
        if (number==1){
            shoppingCartMapper.delete(shoppingCart);
            return;
        }
        //数量不为1，减少
        shoppingCart.setNumber(number-1);
        shoppingCartMapper.update(shoppingCart);
    }
}
