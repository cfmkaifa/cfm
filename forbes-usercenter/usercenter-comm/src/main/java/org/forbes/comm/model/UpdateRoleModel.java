package org.forbes.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * @ClassName
 * @Description TODO
 * @Author xfx
 * @Date 2019/11/21 9:45
 * @Version 1.0
 **/
@ApiModel("角色修改传参")
public class UpdateRoleModel {

    @ApiModelProperty(value = "角色名称",required = true)
    @NotEmpty(message = "角色名称不能为空")
    private String roleName;

    @ApiModelProperty(value = "角色编码",required = true)
    @NotEmpty(message = "角色编码不能为空")
    private String roleCode;

    @ApiModelProperty(value = "描述",required = true)
    private String description;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
