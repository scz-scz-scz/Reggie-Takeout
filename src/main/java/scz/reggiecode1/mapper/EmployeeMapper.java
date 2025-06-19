package scz.reggiecode1.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
import scz.reggiecode1.entity.Employee;

import java.util.List;

@Mapper
@Transactional(rollbackFor = Exception.class)
public interface EmployeeMapper {
    Employee login(Employee employee);

    Long getMaxId();

    void save(Employee employee);

    Integer selectAll(String name);

    List<Employee> list(Integer index, Integer pageSize,String name);

    void updateEmployee(Employee employee);

    Employee selectById(Long id);
}
