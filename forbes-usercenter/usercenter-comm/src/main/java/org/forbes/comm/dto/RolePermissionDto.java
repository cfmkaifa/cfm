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
    private Integer RoleId;

    @ApiModelProperty(value="权限id集合")
    private List<Integer> PermissionId;

    public Integer getRoleId() {
        return RoleId;
    }

    public void setRoleId(Integer roleId) {
        RoleId = roleId;
    }

    public List<Integer> getPermissionId() {
        return PermissionId;
    }

    public void setPermissionId(List<Integer> permissionId) {
        PermissionId = permissionId;
    }
}
