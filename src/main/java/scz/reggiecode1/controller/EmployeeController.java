package scz.reggiecode1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import scz.reggiecode1.common.BaseContext;
import scz.reggiecode1.common.Result;
import scz.reggiecode1.entity.Employee;
import scz.reggiecode1.entity.PageBean;
import scz.reggiecode1.service.EmployeeService;

@Slf4j
@RestController
@RequestMapping("/employee")
@Tag(name = "1.员工管理模块")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // 登录功能
    @PostMapping("/login")
    public Result<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        Employee emp = employeeService.login(employee);
        if (emp == null) {
            return Result.error("登录失败");
        } else {
            request.getSession().setAttribute("employeeId", emp.getId());
            return Result.success(emp);
        }
    }

    // 登出功能
    @PostMapping("/logout")
    public Result<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employeeId");
        return Result.success("退出登录");
    }

    // 新增员工
    @PostMapping
    public Result<String> save(HttpServletRequest request, @RequestBody Employee employee) {
        // 获得当前登录用户的id（已用BaseContext取代）
        // Long createUser = (Long) request.getSession().getAttribute("employeeId");
        employeeService.save(employee);
        return Result.success("新增员工成功");
    }

    // 查询员工
    @GetMapping("/page")
    public Result<PageBean<Employee>> list(@RequestParam Integer page, @RequestParam Integer pageSize, String name) { // 不加RequestParam指改形参可以不传，加了RequestParam就必须传
        PageBean<Employee> employeeList = employeeService.list(page, pageSize, name);
        return Result.success(employeeList);
    }

    // 修改员工状态和编辑员工
    @PutMapping
    public Result<String> updateEmployee(@RequestBody Employee employee) {
        Long updateUser = BaseContext.getCurrentId();
        // 获取原有状态
        Integer status = getById(employee.getId()).getData().getStatus();
        // 不能禁用自己
        if (updateUser == employee.getId() && employee.getStatus() != status) {
            return Result.error("不能禁用自己");
        }
        employeeService.updateEmployee(employee);
        if (employee.getStatus() == status) {
            return Result.success("编辑员工成功");
        }
        return Result.success("修改员工状态成功");
    }

    // 编辑员工时的数据回显
    @GetMapping("/{id}")
    public Result<Employee> getById(@PathVariable Long id) {
        Employee employee = employeeService.getById(id);
        if (employee != null) {
            return Result.success(employee);
        }
        return Result.error("没有查询到对应的员工信息");
    }
}
