package scz.reggiecode1.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scz.reggiecode1.common.BaseContext;
import scz.reggiecode1.entity.User;
import scz.reggiecode1.mapper.UserMapper;
import scz.reggiecode1.service.UserService;
import scz.reggiecode1.utils.SMSUtils;
import scz.reggiecode1.utils.ValidateCodeUtils;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SMSUtils smsUtils;

    //发送验证码
    @Override
    public String sendMsg(String phone) {
        String code= ValidateCodeUtils.generateValidateCode(4);
        smsUtils.sendMessage(phone,code);
        return code;
    }

    @Override
    public User login(String phone) {
        //查找当前手机号是否已注册
        User user=userMapper.selectByPhone(phone);
        //不存在当前用户就将其注册
        if (user==null){
            Long maxId=userMapper.getMaxId();
            if (maxId==null)
                maxId=(long) 0;
            user=new User();
            user.setId(maxId+1);
            user.setPhone(phone);
            user.setStatus(1);
            userMapper.save(user);
            return user;
        }
        //存在当前用户时继续查看其状态
        if (user.getStatus()==1)
            return user;
        return null;
    }

    //查询用户信息
    @Override
    public User list() {
        User user=userMapper.list(BaseContext.getCurrentId());
        return user;
    }
}
