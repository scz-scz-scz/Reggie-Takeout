package scz.reggiecode1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import scz.reggiecode1.common.Result;
import scz.reggiecode1.entity.AddressBook;
import scz.reggiecode1.service.AddressBookService;

import java.util.List;

/**
 * 地址簿管理控制器
 */
@Slf4j
@RestController
@RequestMapping("/addressBook")
@Tag(name = "地址簿管理")
public class AddressBookController {
    
    @Autowired
    private AddressBookService addressBookService;

    /**
     * 新增地址
     */
    @PostMapping
    @Operation(summary = "新增地址")
    public Result<String> save(@RequestBody AddressBook addressBook) {
        log.info("新增地址：{}", addressBook);
        addressBookService.save(addressBook);
        return Result.success("新增地址成功");
    }

    /**
     * 设置默认地址
     */
    @PutMapping("/default")
    @Operation(summary = "设置默认地址")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Result<String> updateDefault(@RequestBody AddressBook addressBook) {
        log.info("设置默认地址：{}", addressBook);
        addressBookService.updateDefault(addressBook);
        return Result.success("设置默认地址成功");
    }

    /**
     * 查询地址列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询地址列表")
    public Result<List<AddressBook>> list() {
        log.info("查询地址列表");
        List<AddressBook> addressBookList = addressBookService.list();
        return Result.success(addressBookList);
    }

    /**
     * 根据ID查询地址
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询地址")
    public Result<AddressBook> getById(@PathVariable Long id) {
        log.info("根据ID查询地址：{}", id);
        AddressBook addressBook = addressBookService.list(id);
        return Result.success(addressBook);
    }

    /**
     * 修改地址
     */
    @PutMapping
    @Operation(summary = "修改地址")
    public Result<String> update(@RequestBody AddressBook addressBook) {
        log.info("修改地址：{}", addressBook);
        if (addressBook.getPhone() == null || addressBook.getPhone().isEmpty()) {
            return Result.error("修改地址失败：手机号不能为空");
        }
        if (!addressBook.getPhone().matches("(13|15|17|18|19)[0-9]{9}")) {
            return Result.error("修改地址失败：手机号错误");
        }
        addressBookService.update(addressBook);
        return Result.success("修改地址成功");
    }

    /**
     * 查询默认地址
     */
    @GetMapping("/default")
    @Operation(summary = "查询默认地址")
    public Result<AddressBook> getDefault() {
        log.info("查询默认地址");
        AddressBook addressBook = addressBookService.getDefault();
        if (addressBook == null) {
            return Result.error("用户未设置默认地址");
        }
        return Result.success(addressBook);
    }

    /**
     * 删除地址
     */
    @DeleteMapping
    @Operation(summary = "删除地址")
    public Result<String> delete(@RequestParam Long[] ids) {
        log.info("删除地址：{}", (Object) ids);
        addressBookService.delete(ids);
        return Result.success("删除地址成功");
    }
}
