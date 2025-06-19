package scz.reggiecode1.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
import scz.reggiecode1.entity.User;

@Mapper
@Transactional(rollbackFor = Exception.class)
public interface UserMapper {
    User selectByPhone(String phone);

    Long getMaxId();

    void save(User user);

    User list(Long id);
}
