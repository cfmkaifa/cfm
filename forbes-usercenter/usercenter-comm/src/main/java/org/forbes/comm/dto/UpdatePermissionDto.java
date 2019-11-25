package org.forbes.comm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.dal.entity.SysPermission;

import java.io.Serializable;
@ApiModel(description="修改权限内容传入参数")
@Data
public class UpdatePermissionDto implements Serializable{

    @ApiModelProperty(value = "权限实体")
    private SysPermission sysPermission;

    @ApiModelProperty(value = "权限id")
    private Long permissionId;

    public SysPermission getSysPermission() {
        return sysPermission;
    }

    public void setSysPermission(SysPermission sysPermission) {
        this.sysPermission = sysPermission;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}
