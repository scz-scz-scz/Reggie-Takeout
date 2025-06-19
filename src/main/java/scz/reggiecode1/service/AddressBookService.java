package scz.reggiecode1.service;

import scz.reggiecode1.entity.AddressBook;

import java.util.List;

public interface AddressBookService {
    void save(AddressBook addressBook);

    void updateDefault(AddressBook addressBook);

    List<AddressBook> list();

    AddressBook list(Long id);

    void update(AddressBook addressBook);

    AddressBook getDefault();

    void delete(Long[] ids);
}
