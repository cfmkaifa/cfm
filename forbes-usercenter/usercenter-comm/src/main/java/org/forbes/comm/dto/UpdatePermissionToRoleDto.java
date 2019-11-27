package org.forbes.comm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/***
 * @创建人 niehy(Frunk)
 * @创建时间 2019/11/22
 * @修改人 (修改了该文件，请填上修改人的名字)
 * @修改日期 (请填上修改该文件时的日期)
 */
@ApiModel("修改角色的一个权限传入参数")
@Data
public class UpdatePermissionToRoleDto  implements Serializable {

    private static final long serialVersionUID = -1361652913559943696L;
    /**
     * 中间表id
     */
    @ApiModelProperty("主键Id")
    private Long id;

    /**
     * 角色ID
     * Table:     f_sys_role_permission
     * Column:    role_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    /**
     * 权限ID
     * Table:     f_sys_role_permission
     * Column:    permission_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "权限ID")
    private Long permissionId;

}
