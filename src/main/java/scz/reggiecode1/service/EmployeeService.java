package scz.reggiecode1.service;

import scz.reggiecode1.entity.Employee;
import scz.reggiecode1.entity.PageBean;

/**
 * 员工服务接口
 */
public interface EmployeeService {

    /**
     * 员工登录
     *
     * @param employee 员工信息
     * @return 员工信息
     */
    Employee login(Employee employee);

    /**
     * 新增员工
     *
     * @param employee 员工信息
     */
    void save(Employee employee);

    /**
     * 分页查询员工
     *
     * @param page     页码
     * @param pageSize 每页记录数
     * @param name     员工姓名
     * @return 分页结果
     */
    PageBean<Employee> list(Integer page, Integer pageSize, String name);

    /**
     * 修改员工信息
     *
     * @param employee 员工信息
     */
    void updateEmployee(Employee employee);

    /**
     * 根据ID查询员工
     *
     * @param id 员工ID
     * @return 员工信息
     */
    Employee getById(Long id);
}
