package org.forbes.dal.entity;

import lombok.Data;

/**
 * Table: f_sys_role_permission
 */
@Data
public class SysRolePermission extends BaseEntity {
   
	private static final long serialVersionUID = 2058286503703627828L;

	/**
     * 角色ID
     * Table:     f_sys_role_permission
     * Column:    role_id
     * Nullable:  true
     */
    private Long roleId;

    /**
     * 权限ID
     * Table:     f_sys_role_permission
     * Column:    permission_id
     * Nullable:  true
     */
    private Long permissionId;
}