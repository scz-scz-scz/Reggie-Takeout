package scz.reggiecode1.controller;

import io.swagger.v3.oas.annotations.Operation;
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

/**
 * 员工管理控制器
 * 负责员工的登录、登出、增删改查等操作
 */
@Slf4j
@RestController
@RequestMapping("/employee")
@Tag(name = "员工管理")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     */
    @PostMapping("/login")
    @Operation(summary = "员工登录")
    public Result<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("员工登录：{}", employee.getUsername());
        Employee emp = employeeService.login(employee);
        if (emp == null) {
            return Result.error("登录失败");
        }
        request.getSession().setAttribute("employeeId", emp.getId());
        return Result.success(emp);
    }

    /**
     * 员工登出
     */
    @PostMapping("/logout")
    @Operation(summary = "员工登出")
    public Result<String> logout(HttpServletRequest request) {
        log.info("员工登出");
        request.getSession().removeAttribute("employeeId");
        return Result.success("退出登录");
    }

    /**
     * 新增员工
     */
    @PostMapping
    @Operation(summary = "新增员工")
    public Result<String> save(@RequestBody Employee employee) {
        log.info("新增员工：{}", employee);
        employeeService.save(employee);
        return Result.success("新增员工成功");
    }

    /**
     * 分页查询员工
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询员工")
    public Result<PageBean<Employee>> list(@RequestParam Integer page, @RequestParam Integer pageSize, String name) {
        log.info("分页查询员工，页码：{}，每页数量：{}，员工姓名：{}", page, pageSize, name);
        PageBean<Employee> employeeList = employeeService.list(page, pageSize, name);
        return Result.success(employeeList);
    }

    /**
     * 修改员工信息
     */
    @PutMapping
    @Operation(summary = "修改员工信息")
    public Result<String> updateEmployee(@RequestBody Employee employee) {
        log.info("修改员工信息：{}", employee);
        Long updateUser = BaseContext.getCurrentId();
        Integer status = getById(employee.getId()).getData().getStatus();
        if (updateUser.equals(employee.getId()) && !employee.getStatus().equals(status)) {
            return Result.error("不能禁用自己");
        }
        employeeService.updateEmployee(employee);
        if (employee.getStatus().equals(status)) {
            return Result.success("编辑员工成功");
        }
        return Result.success("修改员工状态成功");
    }

    /**
     * 根据ID查询员工
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询员工")
    public Result<Employee> getById(@PathVariable Long id) {
        log.info("根据ID查询员工：{}", id);
        Employee employee = employeeService.getById(id);
        if (employee != null) {
            return Result.success(employee);
        }
        return Result.error("没有查询到对应的员工信息");
    }
}
