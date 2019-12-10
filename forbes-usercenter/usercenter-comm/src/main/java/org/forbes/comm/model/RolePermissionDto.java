package org.forbes.comm.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @author lzw
 * @date 2019/12/5 14:48
 */
@Data
@ApiModel(description="修改角色授权参数")
public class RolePermissionDto implements Serializable{

    private static final long serialVersionUID = 7501657572606521470L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @JSONField(format="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "主键id",example = "0")
    private Long id;

    /**
     * 权限ID
     * Table:     f_sys_role_permission
     * Column:    permission_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "权限ID集合(必传)")
    @NotEmpty(message="权限ID集合为空")
    private List<PermissionIdRoleDto> permissionIdRoleDtos;

    /**
     * 角色ID
     * Table:     f_sys_role_permission
     * Column:    role_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "角色ID(必传)",example = "0")
    @NotEmpty(message="角色ID为空")
    private Long roleId;

//    /**
//     * 父级id
//     * Table:     f_sys_permission
//     * Column:    parent_id
//     * Nullable:  true
//     */
//    @ApiModelProperty(value = "父级id")
//    private Integer parentId;
//
//    /**
//     * 资源名称
//     * Table:     f_sys_permission
//     * Column:    name
//     * Nullable:  true
//     */
//    @ApiModelProperty(value = "资源名称")
//    private String name;
//
//    /**
//     * 描述
//     * Table:     f_sys_permission
//     * Column:    description
//     * Nullable:  true
//     */
//    @ApiModelProperty(value = "描述")
//    private String description;
//
//    /**
//     * 菜单权限编码
//     * Table:     f_sys_permission
//     * Column:    perms
//     * Nullable:  true
//     */
//    @ApiModelProperty(value = "菜单权限编码")
//    private String perms;
//
//    /**
//     * 后台请求地址
//     * Table:     f_sys_permission
//     * Column:    url
//     * Nullable:  true
//     */
//    @ApiModelProperty(value = "后台请求地址")
//    private String url;
//
//    /**
//     * 菜单图标
//     * Table:     f_sys_permission
//     * Column:    icon
//     * Nullable:  true
//     */
//    @ApiModelProperty(value = "菜单图标")
//    private byte[] icon;
}
