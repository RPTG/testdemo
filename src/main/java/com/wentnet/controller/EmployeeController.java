package com.wentnet.controller;

import com.wentnet.dao.master.entity.Employee;
import com.wentnet.model.R;
import com.wentnet.model.vo.LoginVo;
import com.wentnet.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 员工信 接口
 * </p>
 *
 * @author 稳拓
 * @since 2023-07-07 19:24:44
 */
@Validated
@Api(tags = "员工信")
@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> login(@RequestBody LoginVo loginVo) {
        return employeeService.login(loginVo);
    }
}
