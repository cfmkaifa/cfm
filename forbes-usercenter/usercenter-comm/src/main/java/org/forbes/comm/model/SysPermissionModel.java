package org.forbes.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.forbes.dal.entity.SysPermission;
import org.forbes.dal.entity.SysRole;
import org.forbes.dal.entity.SysUser;

/**
 * 权限管理
 *
 * @author scott
 * @since  2019-11-20
 */
@ApiModel(description="权限管理传入对象")
public class SysPermissionModel {
    @ApiModelProperty("传入用户对象")
    private SysUser sysUser;
    @ApiModelProperty("传入角色对象")
    private SysRole sysRole;
    @ApiModelProperty("传入权限对象")
    private SysPermission sysPermission;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    public SysPermission getSysPermission() {
        return sysPermission;
    }

    public void setSysPermission(SysPermission sysPermission) {
        this.sysPermission = sysPermission;
    }
}
