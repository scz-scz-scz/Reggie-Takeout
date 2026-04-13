package scz.reggiecode1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import scz.reggiecode1.common.Result;
import scz.reggiecode1.entity.Category;
import scz.reggiecode1.entity.PageBean;
import scz.reggiecode1.service.CategoryService;

import java.util.List;

/**
 * 分类管理控制器
 */
@Slf4j
@RestController
@RequestMapping("/category")
@Tag(name = "分类管理")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     */
    @PostMapping
    @Operation(summary = "新增分类")
    public Result<String> save(@RequestBody Category category) {
        log.info("新增分类：{}", category);
        categoryService.save(category);
        if (category.getType() == 1) {
            return Result.success("新增菜品分类成功");
        }
        return Result.success("新增套餐分类成功");
    }

    // 查询分类（分页展示）
    @GetMapping("/page")
    public Result<PageBean<Category>> list(@RequestParam Integer page, @RequestParam Integer pageSize) {
        PageBean<Category> categoryList = categoryService.list(page, pageSize);
        return Result.success(categoryList);
    }

    /**
     * 删除分类
     */
    @DeleteMapping
    @Operation(summary = "删除分类")
    public Result<String> delete(@RequestParam Long id) {
        log.info("删除分类：{}", id);
        categoryService.delete(id);
        return Result.success("删除成功");
    }

    /**
     * 修改分类
     */
    @PutMapping
    @Operation(summary = "修改分类")
    public Result<String> update(@RequestBody Category category) {
        log.info("修改分类：{}", category);
        categoryService.updateCategory(category);
        return Result.success("修改分类成功");
    }

    /**
     * 查询分类列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询分类列表")
    public Result<List<Category>> list(Category category) {
        log.info("查询分类列表：{}", category);
        List<Category> categoryList = categoryService.list(category);
        return Result.success(categoryList);
    }
}
