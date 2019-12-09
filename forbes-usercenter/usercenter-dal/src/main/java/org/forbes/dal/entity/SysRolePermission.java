package org.forbes.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Table: f_sys_role_permission
 */
@Data
@TableName("f_sys_role_permission")
public class SysRolePermission extends BaseEntity {
   
	private static final long serialVersionUID = 2058286503703627828L;

	/**
     * 角色ID
     * Table:     f_sys_role_permission
     * Column:    role_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "角色ID")
    @NotEmpty(message = "角色id为空")
    private Long roleId;

    /**
     * 权限ID
     * Table:     f_sys_role_permission
     * Column:    permission_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "权限ID")
    @NotEmpty(message = "权限id为空")
    private Long permissionId;

}