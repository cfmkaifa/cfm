package org.forbes.comm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lzw
 * @date 2019/12/5 10:58
 */
@ApiModel(description="角色授权参数")
@Data
public class RoleAuthorizationVo implements Serializable {

    private static final long serialVersionUID = -6025993817470217104L;

    @ApiModelProperty(value = "角色所拥有权限")
    private List<PermissionInRoleVo> permissionInRoleVoInfo;

    @ApiModelProperty(value = "查询所有权限")
    private List<PermissionVo> PermissionVoInfo;
}
