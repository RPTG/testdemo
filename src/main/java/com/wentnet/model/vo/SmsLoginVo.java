package com.wentnet.model.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Api("短信登录实体")
public class SmsLoginVo {
    @ApiModelProperty("登录的手机号")
    @NotBlank(message = "手机号不能为空")
    private String phone;
}
