package org.forbes.comm.model;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lzw
 * @date 2019/12/5 14:48
 */
@Data
@ApiModel(description="修改角色授权参数")
public class RolePermissionDto implements Serializable{

    private static final long serialVersionUID = 7501657572606521470L;

    
    /**
     * 角色ID
     * Table:     f_sys_role_permission
     * Column:    role_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "角色ID",example = "0")
    private Long roleId;


    @ApiModelProperty(value = "权限ID",example = "0",required=true)
    @NotEmpty(message="权限ID")
    private Long permissionId;
}
