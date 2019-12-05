package org.forbes.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Table: f_sys_user_role
 */
@Data
@TableName("f_sys_user_role")
public class SysUserRole extends BaseEntity {
    /** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */ 
	private static final long serialVersionUID = 5282561886588562544L;

    /**
     * 用户ID
     * Table:     f_sys_user_role
     * Column:    user_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * 角色ID
     * Table:     f_sys_user_role
     * Column:    role_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "角色ID")
    private Long roleId;
}