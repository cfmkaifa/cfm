package org.forbes.dal.entity;

import java.util.Date;

import lombok.Data;

/**
 * Table: f_sys_role
 */
@Data
public class SysRole extends BaseEntity {
    
	
	private static final long serialVersionUID = -1667826254533837671L;


    /**
     * 角色名称
     * Table:     f_sys_role
     * Column:    role_name
     * Nullable:  true
     */
    private String roleName;

    /**
     * 角色编码
     * Table:     f_sys_role
     * Column:    role_code
     * Nullable:  true
     */
    private String roleCode;

    /**
     * 角色描述
     * Table:     f_sys_role
     * Column:    description
     * Nullable:  true
     */
    private String description;
}