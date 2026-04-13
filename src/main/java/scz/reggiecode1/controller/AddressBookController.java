package scz.reggiecode1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import scz.reggiecode1.common.Result;
import scz.reggiecode1.entity.AddressBook;
import scz.reggiecode1.service.AddressBookService;

import java.util.List;

@RestController
@RequestMapping("/addressBook")
public class AddressBookController {
    @Autowired
    private AddressBookService addressBookService;

    // 新增地址
    @PostMapping
    public Result<String> save(@RequestBody AddressBook addressBook) {
        addressBookService.save(addressBook);
        return Result.success("新增地址成功");
    }

    // 设置默认地址
    @PutMapping("/default")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Result<String> updateDefault(@RequestBody AddressBook addressBook) {
        addressBookService.updateDefault(addressBook);
        return Result.success("设置默认地址成功");
    }

    // 查询地址（分页展示）
    @GetMapping("/list")
    public Result<List<AddressBook>> list() {
        List<AddressBook> addressBookList = addressBookService.list();
        return Result.success(addressBookList);
    }

    // 查询地址（修改地址时显示）
    @GetMapping("/{id}")
    public Result<AddressBook> list(@PathVariable Long id) {
        AddressBook addressBook = addressBookService.list(id);
        return Result.success(addressBook);
    }

    // 修改地址
    @PutMapping
    public Result<String> update(@RequestBody AddressBook addressBook) {
        if (addressBook.getPhone() == null || addressBook.getPhone().isEmpty())
            return Result.error("修改地址失败：手机号不能为空");
        if (!addressBook.getPhone().matches("(13|15|17|18|19)[0-9]{9}"))
            return Result.error("修改地址失败：手机号错误");
        addressBookService.update(addressBook);
        return Result.success("修改地址成功");
    }

    //查询默认地址（下单时展示）
    @GetMapping("/default")
    public Result<AddressBook> getDefault() {
        AddressBook addressBook = addressBookService.getDefault();
        if (addressBook == null)
            return Result.error("用户未设置默认地址");
        return Result.success(addressBook);
    }

    //删除地址
    @DeleteMapping
    public Result<String> delete(@RequestParam Long[] ids) {
        addressBookService.delete(ids);
        return Result.success("删除地址成功");
    }
}
