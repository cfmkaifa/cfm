package org.forbes.dal.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * Table: f_sys_user
 */
@Data
public class SysUser implements Serializable {

	/**
     * 登录账号
     * Table:     f_sys_user
     * Column:    username
     * Nullable:  false
     */
    private String username;

    /**
     * 状态
     * Table:     f_sys_user
     * Column:    status
     * Nullable:  false
     */
    private String status;

    /**
     * 密码
     * Table:     f_sys_user
     * Column:    password
     * Nullable:  true
     */
    private String password;

    /**
     * md5密码盐
     * Table:     f_sys_user
     * Column:    salt
     * Nullable:  true
     */
    private String salt;

    /**
     * 头像
     * Table:     f_sys_user
     * Column:    avatar
     * Nullable:  true
     */
    private String avatar;

    /**
     * 邮件
     * Table:     f_sys_user
     * Column:    email
     * Nullable:  true
     */
    private String email;

    /**
     * 电话
     * Table:     f_sys_user
     * Column:    phone
     * Nullable:  true
     */
    private String phone;

    /**
     * 姓名
     * Table:     f_sys_user
     * Column:    realname
     * Nullable:  true
     */
    private String realname;
}