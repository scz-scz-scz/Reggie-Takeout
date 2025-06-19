package scz.reggiecode1.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
import scz.reggiecode1.entity.AddressBook;

import java.util.List;

@Mapper
@Transactional(rollbackFor = Exception.class)
public interface AddressBookMapper {
    Long getMaxId();

    void save(AddressBook addressBook);

    void updateDefault2false(Long userId);

    void updateDefault(AddressBook addressBook);

    List<AddressBook> list(Long userId);

    AddressBook listById(Long id);

    void update(AddressBook addressBook);

    AddressBook getDefault(Long userId);

    void delete(Long[] ids);
}
