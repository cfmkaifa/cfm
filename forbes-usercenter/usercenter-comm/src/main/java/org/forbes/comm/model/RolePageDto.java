package org.forbes.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lzw
 * @date 2019/12/6 9:59
 */
@ApiModel(description="角色多条件分页查询传入参数")
@Data
public class RolePageDto implements Serializable{

    private static final long serialVersionUID = -7838080919618622872L;

    @ApiModelProperty("当前页码(必填)")
    private Long current;

    @ApiModelProperty("当前页显示条数(必填)")
    private Long size;

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
    private String description;
}
