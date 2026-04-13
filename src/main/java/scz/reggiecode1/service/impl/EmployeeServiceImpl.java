package scz.reggiecode1.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import scz.reggiecode1.entity.Employee;
import scz.reggiecode1.entity.PageBean;
import scz.reggiecode1.mapper.EmployeeMapper;
import scz.reggiecode1.service.EmployeeService;
import scz.reggiecode1.common.MyMetaObjectHandler;

import java.util.List;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    // 登录
    @Override
    public Employee login(Employee employee) {
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());  // md5加密
        employee.setPassword(password);
        Employee emp = employeeMapper.login(employee);
        // 检查用户名、密码和状态
        if (emp == null) {
            return null;
        } else if (!emp.getPassword().equals(password)) {
            return null;
        } else if (emp.getStatus() != 1) {
            return null;
        } else {
            return emp;
        }
    }

    // 新增员工
    @Override
    public void save(Employee employee) {
        // 设置初始密码并进行md5加密
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        MyMetaObjectHandler.save(employee);
        // 获取并设置当前最大id
        Long maxId = employeeMapper.getMaxId();
        if (maxId == null) {
            employee.setId((long) 1);
        } else {
            employee.setId(maxId + 1);
        }
        log.info("新增的员工信息为{}", employee.toString());
        employeeMapper.save(employee);
    }

    // 查询员工
    @Override
    public PageBean<Employee> list(Integer page, Integer pageSize, String name) {
        Integer total = employeeMapper.selectAll(name);
        int index = (page - 1) * pageSize;
        List<Employee> employeelist = employeeMapper.list(index, pageSize, name);
        PageBean<Employee> result = new PageBean<>(total, employeelist, employeelist.size());
        return result;
    }

    //修改员工状态和编辑员工
    @Override
    public void updateEmployee(Employee employee) {
        MyMetaObjectHandler.update(employee);
        employeeMapper.updateEmployee(employee);
    }

    // 编辑员工时的数据回显
    @Override
    public Employee getById(Long id) {
        Employee employee = employeeMapper.selectById(id);
        return employee;
    }
}
