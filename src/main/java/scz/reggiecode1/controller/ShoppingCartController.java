package scz.reggiecode1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import scz.reggiecode1.common.Result;
import scz.reggiecode1.entity.ShoppingCart;
import scz.reggiecode1.service.ShoppingCartService;

import java.util.List;

/**
 * 购物车管理控制器
 * 负责购物车的增删改查操作
 */
@Slf4j
@RestController
@RequestMapping("/shoppingCart")
@Tag(name = "购物车管理")
public class ShoppingCartController {
    
    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 添加菜品或套餐到购物车
     */
    @PostMapping("/add")
    @Operation(summary = "添加菜品或套餐到购物车")
    public Result<ShoppingCart> save(@RequestBody ShoppingCart shoppingCart) {
        log.info("添加菜品或套餐到购物车：{}", shoppingCart);
        if (shoppingCart == null) {
            return Result.error("添加失败");
        }
        shoppingCartService.save(shoppingCart);
        return Result.success(shoppingCart);
    }

    /**
     * 查询购物车
     */
    @GetMapping("/list")
    @Operation(summary = "查询购物车")
    public Result<List<ShoppingCart>> list() {
        log.info("查询购物车");
        List<ShoppingCart> shoppingCartList = shoppingCartService.list();
        return Result.success(shoppingCartList);
    }

    /**
     * 清空购物车
     */
    @DeleteMapping("/clean")
    @Operation(summary = "清空购物车")
    public Result<String> clean() {
        log.info("清空购物车");
        shoppingCartService.clean();
        return Result.success("清空购物车成功");
    }

    /**
     * 减少购物车中商品数量
     */
    @PostMapping("/sub")
    @Operation(summary = "减少购物车中商品数量")
    public Result<ShoppingCart> delete(@RequestBody ShoppingCart shoppingCart) {
        log.info("减少购物车中商品数量：{}", shoppingCart);
        if (shoppingCart == null) {
            return Result.error("修改失败");
        }
        shoppingCartService.delete(shoppingCart);
        return Result.success(shoppingCart);
    }
}
