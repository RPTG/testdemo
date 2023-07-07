package com.wentnet.dao.master.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 员工信息
 * </p>
 *
 * @author 稳拓
 * @since 2023-07-07 19:24:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Employee", description = "员工信息")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", example = "")
    private Long id;

    @ApiModelProperty(value = "姓名", example = "")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "用户名", example = "")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "密码", example = "")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "手机号", example = "")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "性别", example = "")
    @TableField("sex")
    private String sex;

    @ApiModelProperty(value = "身份证号", example = "")
    @TableField("id_number")
    private String idNumber;

    @ApiModelProperty(value = "状态 0:禁用，1:正常", example = "")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "创建时间", example = "")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间", example = "")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人", example = "")
    @TableField("create_user")
    private Long createUser;

    @ApiModelProperty(value = "修改人", example = "")
    @TableField("update_user")
    private Long updateUser;


}
