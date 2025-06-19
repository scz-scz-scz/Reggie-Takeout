package scz.reggiecode1.service;

import scz.reggiecode1.entity.User;

public interface UserService {
    String sendMsg(String phone);

    User login(String phone);

    User list();
}
