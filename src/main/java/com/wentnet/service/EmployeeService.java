package com.wentnet.service;


import com.wentnet.dao.master.entity.Employee;
import com.wentnet.dao.master.mapper.EmployeeMapper;
import com.wentnet.dao.master.service.EmployeeDaoService;
import com.wentnet.exception.BusinessException;
import com.wentnet.model.R;
import com.wentnet.model.vo.LoginVo;
import com.wentnet.model.vo.RegisterVo;
import com.wentnet.model.vo.SmsLoginVo;
import com.wentnet.model.vo.SmsReturnVo;
import com.wentnet.utils.SmsUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Random;

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

    private final EmployeeMapper employeeMapper;

    public Employee login(LoginVo loginVo) {
        String password = loginVo.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        Employee employee = employeeDaoService.findByUsername(loginVo.getUsername());
        if (ObjectUtils.isEmpty(employee)) {
            throw new BusinessException("没有账号");
        }

        if (!employee.getPassword().equals(password)) {
            throw new BusinessException("密码错误");
        }

        if (employee.getStatus() == 0) {
            throw new BusinessException("该账号已禁用");
        }

        log.info("登录成功！ 用户信息:"+employee);

        return employee;
    }

    public SmsReturnVo smsLogin(SmsLoginVo smsLoginVo){
        Employee employee = employeeDaoService.findByPhone(smsLoginVo.getPhone());
        if(ObjectUtils.isEmpty(employee)){
            throw new BusinessException("该手机号没有注册用户");
        }

        //随机生成验证码
        Random rand = new Random();
        int code =rand.nextInt(900000)+100000;
        Boolean smsFlag = SmsUtil.sendSms(employee.getPhone(), code);

        SmsReturnVo smsReturnVo = new SmsReturnVo(code,employee);

        log.info("短信发送结果："+smsReturnVo);

        return smsReturnVo;
    }

    public Employee register(RegisterVo registerVo){
        String password = registerVo.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        Employee employee=new Employee();
        employee.setUsername(registerVo.getUsername());
        employee.setPassword(password);
        employee.setPhone(registerVo.getPhone());

        employee.setName("temp");
        employee.setSex("1");
        employee.setIdNumber("000000000000000000");
        employee.setStatus(1);
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        employee.setCreateUser((long)1);
        employee.setUpdateUser((long)1);

        log.info("新增员工,{}",employee.toString());

        employeeDaoService.saveEmployee(employee);

        return employee;
    }
}
