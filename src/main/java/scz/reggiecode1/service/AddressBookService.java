package scz.reggiecode1.service;

import scz.reggiecode1.entity.AddressBook;

import java.util.List;

/**
 * 地址簿服务接口
 */
public interface AddressBookService {

    /**
     * 新增地址
     *
     * @param addressBook 地址信息
     */
    void save(AddressBook addressBook);

    /**
     * 设置默认地址
     *
     * @param addressBook 地址信息
     */
    void updateDefault(AddressBook addressBook);

    /**
     * 查询地址列表
     *
     * @return 地址列表
     */
    List<AddressBook> list();

    /**
     * 根据ID查询地址
     *
     * @param id 地址ID
     * @return 地址信息
     */
    AddressBook list(Long id);

    /**
     * 修改地址
     *
     * @param addressBook 地址信息
     */
    void update(AddressBook addressBook);

    /**
     * 查询默认地址
     *
     * @return 默认地址信息
     */
    AddressBook getDefault();

    /**
     * 删除地址
     *
     * @param ids 地址ID数组
     */
    void delete(Long[] ids);
}
