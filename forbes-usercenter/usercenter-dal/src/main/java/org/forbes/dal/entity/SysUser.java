package org.forbes.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Table: f_sys_user
 */
@Data
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
}