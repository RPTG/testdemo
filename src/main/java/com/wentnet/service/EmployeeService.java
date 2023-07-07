package com.wentnet.service;


import com.wentnet.dao.master.entity.Employee;
import com.wentnet.dao.master.service.EmployeeDaoService;
import com.wentnet.model.R;
import com.wentnet.model.vo.LoginVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 员工信 服务功能类
 * </p>
 *
 * @author 稳拓
 * @since 2023-07-07 19:24:44
 */
@Service
@AllArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeDaoService employeeDaoService;

    public R<Employee> login(LoginVo loginVo) {
        String password = loginVo.getPassword();
        Employee employee = employeeDaoService.findByUsername(loginVo.getUsername());
        if (ObjectUtils.isEmpty(employee)) {
            return R.error("没有账号");
        }

        if (!employee.getPassword().equals(password)) {
            return R.error("密码错误");
        }

        if (employee.getStatus() == 0) {
            return R.error("该账号已禁用");
        }
        return R.success(employee);
    }

}
