package com.wentnet.dao.master.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wentnet.dao.master.entity.Employee;
import com.wentnet.dao.master.mapper.EmployeeMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工信息 服务实现类
 * </p>
 *
 * @author 稳拓
 * @since 2023-07-07 19:24:44
 */
@DS("master")
@Service
public class EmployeeDaoService extends ServiceImpl<EmployeeMapper, Employee> {

    public Employee findByUsername(String username) {
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, username);
        return getOne(queryWrapper);
    }
}
