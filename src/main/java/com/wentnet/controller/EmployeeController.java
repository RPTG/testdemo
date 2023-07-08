package com.wentnet.controller;

import com.wentnet.dao.master.entity.Employee;
import com.wentnet.model.vo.*;
import com.wentnet.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;



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
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/login")
    public Employee login(@RequestBody LoginVo loginVo) {
        log.info("执行登录接口");
        return employeeService.login(loginVo);
    }

    @PostMapping("/phoneLogin")
    public SmsReturnVo login(@RequestBody SmsLoginVo smsLoginVo){
        log.info("执行短信登录接口");
        return employeeService.smsLogin(smsLoginVo);
    }

    @PostMapping("/register")
    public Employee register(@RequestBody RegisterVo registerVo){
        log.info("执行注册接口");
        return employeeService.register(registerVo);
    }
}
