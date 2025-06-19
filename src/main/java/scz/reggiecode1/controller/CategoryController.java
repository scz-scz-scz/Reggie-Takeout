package scz.reggiecode1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import scz.reggiecode1.common.Result;
import scz.reggiecode1.entity.Category;
import scz.reggiecode1.entity.PageBean;
import scz.reggiecode1.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //新增分类
    @PostMapping
    public Result<String> save(@RequestBody Category category){
        categoryService.save(category);
        if (category.getType()==1)
            return Result.success("新增菜品分类成功");
        return Result.success("新增套餐分类成功");
    }

    //查询分类（分页展示）
    @GetMapping("/page")
    public Result<PageBean<Category>> list(@RequestParam Integer page,@RequestParam Integer pageSize){
        PageBean<Category> categoryList=categoryService.list(page,pageSize);
        return Result.success(categoryList);
    }

    //删除分类
    @DeleteMapping
    public Result<String> delete(@RequestParam Long id){
        categoryService.delete(id);
        return Result.success("删除成功");
    }

    //修改分类
    @PutMapping
    public Result<String> updateCategory(@RequestBody Category category){
        categoryService.updateCategory(category);
        return Result.success("修改分类成功");
    }

    //查询分类（新增菜品或套餐时展示）
    @GetMapping("/list")
    public Result<List<Category>> list(Category category){
        List<Category> categoryList=categoryService.list(category);
        return Result.success(categoryList);
    }
}
