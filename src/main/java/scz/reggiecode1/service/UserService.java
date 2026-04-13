package scz.reggiecode1.service;

import scz.reggiecode1.entity.User;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 发送短信验证码
     *
     * @param phone 手机号
     * @return 验证码
     */
    String sendMsg(String phone);

    /**
     * 用户登录
     *
     * @param phone 手机号
     * @return 用户信息
     */
    User login(String phone);

    /**
     * 查询用户信息
     *
     * @return 用户信息
     */
    User list();
}
