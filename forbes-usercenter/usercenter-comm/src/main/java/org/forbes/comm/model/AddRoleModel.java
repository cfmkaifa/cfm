package org.forbes.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * @ClassName
 * @Description 添加角色请求参数模型
 * @Author
 * @Date 2019/11/20 14:56
 * @Version 1.0
 **/
@ApiModel
public class AddRoleModel {

    @ApiModelProperty(value = "角色名称",required = true)
    @NotEmpty(message = "名称不能为空")
    private String roleName;

    @ApiModelProperty(value = "角色编码",required = true)
    @NotEmpty(message = "角色编码不能为空")
    private String roleCode;

    @ApiModelProperty(value = "角色描述",required = true)
    private String description;

    @ApiModelProperty(value = "创建人",required = true)
    private String createBy;

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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
