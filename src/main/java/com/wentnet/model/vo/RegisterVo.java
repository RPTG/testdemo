package com.wentnet.model.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Api("注册实体")
public class RegisterVo {

    @ApiModelProperty("注册用户名")
    @NotBlank(message = "注册名不能为空")
    private String username;

    @ApiModelProperty("注册用户密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty("注册用户手机号")
    @NotBlank(message = "手机号不能为空")
    private String phone;
}
