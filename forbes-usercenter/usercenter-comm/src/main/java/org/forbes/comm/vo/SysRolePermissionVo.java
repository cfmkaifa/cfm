package org.forbes.comm.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/***
 * @创建人 niehy(Frunk)
 * @创建时间 2019/11/26
 * @修改人 (修改了该文件，请填上修改人的名字)
 * @修改日期 (请填上修改该文件时的日期)
 */
@ApiModel(description="角色-权限返回对象")
@Data
public class SysRolePermissionVo implements Serializable {
    private static final long serialVersionUID = 4783400975725108257L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @JSONField(format="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty("权限id")
    private Long id;


    /**
     * 创建人
     */
    @ApiModelProperty("权限创建人")
    private String createBy;

    /**
     * 创建时间
     */
    @JSONField(format="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty("权限创建时间")
    private Date createTime;

    /**
     * 更新人
     */
    @ApiModelProperty("权限id更新人")
    private String updateBy;

    /**
     * 更新时间
     */
    @ApiModelProperty("权限更新时间")
    private Date updateTime;

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
    private String name;

    /**
     * 菜单权限编码
     * Table:     f_sys_permission
     * Column:    perms
     * Nullable:  true
     */
    @ApiModelProperty(value = "菜单权限编码")
    private String perms;

    /**
     * 组件
     * Table:     f_sys_permission
     * Column:    component
     * Nullable:  true
     */
    @ApiModelProperty(value = "组件")
    private String component;

    /**
     * 组件名称
     * Table:     f_sys_permission
     * Column:    component_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "组件名称")
    private String componentName;

    /**
     * 是否聚合子路由
     * Table:     f_sys_permission
     * Column:    always_show
     * Nullable:  true
     */
    @ApiModelProperty(value = "是否聚合子路由")
    private String alwaysShow;

    /**
     * 是否路由
     * Table:     f_sys_permission
     * Column:    is_route
     * Nullable:  true
     */
    @ApiModelProperty(value = "是否路由")
    private String isRoute;

    /**
     * Table:     f_sys_permission
     * Column:    is_leaf
     * Nullable:  true
     */
    @ApiModelProperty()
    private String isLeaf;

    /**
     * 是否隐藏
     * Table:     f_sys_permission
     * Column:    is_hidden
     * Nullable:  true
     */
    @ApiModelProperty(value = "是否隐藏")
    private String isHidden;

    /**
     * Table:     f_sys_permission
     * Column:    sort_no
     * Nullable:  true
     */
    @ApiModelProperty()
    private Integer sortNo;

    /**
     * 描述
     * Table:     f_sys_permission
     * Column:    description
     * Nullable:  true
     */
    @ApiModelProperty(value = "描述")
    private String description;

    /**
     * 路由请求地址
     * Table:     f_sys_permission
     * Column:    redirect
     * Nullable:  true
     */
    @ApiModelProperty(value = "路由请求地址")
    private String redirect;

    /**
     * 后台请求地址
     * Table:     f_sys_permission
     * Column:    url
     * Nullable:  true
     */
    @ApiModelProperty(value = "后台请求地址")
    private String url;

    /**
     * 菜单图标
     * Table:     f_sys_permission
     * Column:    icon
     * Nullable:  true
     */
    @ApiModelProperty(value = "菜单图标")
    private byte[] icon;









    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @JSONField(format="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty("角色id")
    private Long role_id;


    /**
     * 创建人
     */
    @ApiModelProperty("角色创建人")
    private String role_createBy;

    /**
     * 更新人
     */
    @JSONField(format="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty("角色更新人")
    private Date role_createTime;

    /**
     * 更新人
     */
    @ApiModelProperty("角色更新人")
    private String role_updateBy;

    /**
     * 更新时间
     */
    @ApiModelProperty("角色更新时间")
    private Date role_updateTime;

    /**
     * 角色名称
     * Table:     f_sys_role
     * Column:    role_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    /**
     * 角色编码
     * Table:     f_sys_role
     * Column:    role_code
     * Nullable:  true
     */
    @ApiModelProperty(value = "角色编码")
    private String roleCode;

    /**
     * 角色描述
     * Table:     f_sys_role
     * Column:    description
     * Nullable:  true
     */
    @ApiModelProperty(value = "角色描述")
    private String role_description;
}
