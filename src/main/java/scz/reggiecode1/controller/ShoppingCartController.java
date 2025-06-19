package scz.reggiecode1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import scz.reggiecode1.common.Result;
import scz.reggiecode1.entity.ShoppingCart;
import scz.reggiecode1.service.ShoppingCartService;

import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    //添加菜品或套餐
    @PostMapping("/add")
    public Result<ShoppingCart> save(@RequestBody ShoppingCart shoppingCart){
        if (shoppingCart==null)
            return Result.error("添加失败");
        shoppingCartService.save(shoppingCart);
        return Result.success(shoppingCart);
    }

    //查询购物车
    @GetMapping("/list")
    public Result<List<ShoppingCart>> list(){
        List<ShoppingCart> shoppingCartList=shoppingCartService.list();
        return Result.success(shoppingCartList);
    }

    //清空购物车
    @DeleteMapping("/clean")
    public Result<String> clean(){
        shoppingCartService.clean();
        return Result.success("清空购物车成功");
    }

    //修改购物车（减少数量）
    @PostMapping("/sub")
    public Result<ShoppingCart> delete(@RequestBody ShoppingCart shoppingCart){
        if (shoppingCart==null)
            return Result.error("修改失败");
        shoppingCartService.delete(shoppingCart);
        return Result.success(shoppingCart);
    }
}
