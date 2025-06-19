package scz.reggiecode1.service;

import scz.reggiecode1.entity.Employee;
import scz.reggiecode1.entity.PageBean;

public interface EmployeeService {
    Employee login(Employee employee);

    void save(Employee employee);

    PageBean<Employee> list(Integer page, Integer pageSize,String name);

    void updateEmployee(Employee employee);

    Employee getById(Long id);
}
