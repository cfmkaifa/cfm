package org.forbes.comm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.dal.entity.SysPermission;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
@ApiModel(description="修改权限内容传入参数")
@Data
public class UpdatePermissionDto implements Serializable{

    /**
     * 父级id
     * Table:     f_sys_permission
     * Column:    parent_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "父级id")
    private Integer parentId;

    /**
     * 资源名称
     * Table:     f_sys_permission
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "资源名称")
    @NotEmpty(message = "资源名称不能为空")
    private String name;

    /**
     * 描述
     * Table:     f_sys_permission
     * Column:    description
     * Nullable:  true
     */
    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "权限id")
    @NotNull(message = "权限id不能为空")
    private Long permissionId;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}
