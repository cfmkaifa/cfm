package org.forbes.dal.entity;
import javax.validation.constraints.NotEmpty;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Table: f_sys_user
 */
@Data
@ApiModel(description="用户信息")
@EqualsAndHashCode(callSuper = false)
@TableName("f_sys_user")
public class SysUser  extends BaseEntity {

	private static final long serialVersionUID = 6199785596106991966L;

	/**
     * 登录账号
     * Table:     f_sys_user
     * Column:    username
     * Nullable:  false
     */
    @ApiModelProperty(value = "登录账号")
    private String username;

    /**
     * 状态
     * Table:     f_sys_user
     * Column:    status
     * Nullable:  false
     */
    @ApiModelProperty(value = "状态",required = true)
    private String status;

    /**
     * 密码
     * Table:     f_sys_user
     * Column:    password
     * Nullable:  true
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * md5密码盐
     * Table:     f_sys_user
     * Column:    salt
     * Nullable:  true
     */
    @ApiModelProperty(value = "md5密码盐")
    private String salt;

    /**
     * 头像
     * Table:     f_sys_user
     * Column:    avatar
     * Nullable:  true
     */
    @ApiModelProperty(value = "头像")
    private String avatar;

    /**
     * 邮件
     * Table:     f_sys_user
     * Column:    email
     * Nullable:  true
     */
    @ApiModelProperty(value = "邮件")
    private String email;

    /**
     * 电话
     * Table:     f_sys_user
     * Column:    phone
     * Nullable:  true
     */
    @ApiModelProperty(value = "电话")
    private String phone;

    /**
     * 姓名
     * Table:     f_sys_user
     * Column:    realname
     * Nullable:  true
     */
    @ApiModelProperty(value = "姓名",required = true)
    private String realname;
    
    
    @ApiModelProperty(value="管理员标识(-1-普通人员,0-超级管理员,1-企业管理员,2-不限制企业号)")
	@NotEmpty(message="管理员标识不能为空")
    private String adminFlag;

}