package com.wentnet.model.vo;

import com.wentnet.dao.master.entity.Employee;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("短信验证码和数据实体")
public class SmsReturnVo {

    @ApiModelProperty("验证码")
    private int code;

    @ApiModelProperty("登录人员的账号信息")
    private Employee employee;
}
