package org.forbes.comm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;
@ApiModel("修改角色权限传入对象")
@Data
public class RolePermissionDto implements Serializable{

    @ApiModelProperty(value="角色id")
    private Long roleId;

    @ApiModelProperty(value="权限id集合")
    private List<Long> PermissionId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<Long> getPermissionId() {
        return PermissionId;
    }

    public void setPermissionId(List<Long> permissionId) {
        PermissionId = permissionId;
    }
}
