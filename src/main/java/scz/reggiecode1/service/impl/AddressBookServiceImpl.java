package scz.reggiecode1.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scz.reggiecode1.common.BaseContext;
import scz.reggiecode1.common.MyMetaObjectHandler;
import scz.reggiecode1.entity.AddressBook;
import scz.reggiecode1.mapper.AddressBookMapper;
import scz.reggiecode1.service.AddressBookService;

import java.util.List;

/**
 * 地址簿服务实现类
 */
@Slf4j
@Service
public class AddressBookServiceImpl implements AddressBookService {

    @Autowired
    private AddressBookMapper addressBookMapper;

    /**
     * 新增地址
     */
    @Override
    public void save(AddressBook addressBook) {
        Long maxId = addressBookMapper.getMaxId();
        if (maxId == null) {
            maxId = 0L;
        }
        addressBook.setId(maxId + 1);
        addressBook.setUserId(BaseContext.getCurrentId());
        MyMetaObjectHandler.save(addressBook);
        addressBookMapper.save(addressBook);
    }

    /**
     * 设置默认地址
     */
    @Override
    public void updateDefault(AddressBook addressBook) {
        // 先将该用户的所有地址都设为不默认
        addressBookMapper.updateDefault2false(BaseContext.getCurrentId());
        // 再将地址设置为默认地址
        addressBook.setIsDefault(1);
        MyMetaObjectHandler.update(addressBook);
        addressBookMapper.updateDefault(addressBook);
    }

    /**
     * 查询地址（分页展示）
     */
    @Override
    public List<AddressBook> list() {
        List<AddressBook> addressBookList = addressBookMapper.list(BaseContext.getCurrentId());
        return addressBookList;
    }

    /**
     * 查询地址（修改地址时显示）
     */
    @Override
    public AddressBook list(Long id) {
        AddressBook addressBook = addressBookMapper.listById(id);
        return addressBook;
    }

    /**
     * 修改地址
     */
    @Override
    public void update(AddressBook addressBook) {
        MyMetaObjectHandler.update(addressBook);
        addressBookMapper.update(addressBook);
    }

    /**
     * 查询默认地址（下单时展示）
     */
    @Override
    public AddressBook getDefault() {
        AddressBook addressBook = addressBookMapper.getDefault(BaseContext.getCurrentId());
        return addressBook;
    }

    /**
     * 删除地址
     */
    @Override
    public void delete(Long[] ids) {
        addressBookMapper.delete(ids);
    }
}
