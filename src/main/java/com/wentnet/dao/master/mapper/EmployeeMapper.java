package com.wentnet.dao.master.mapper;

import com.wentnet.dao.master.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 员工信息 Mapper 接口
 * </p>
 *
 * @author 稳拓
 * @since 2023-07-07 19:24:44
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
