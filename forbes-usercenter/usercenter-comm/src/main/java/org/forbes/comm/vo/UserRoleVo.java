package org.forbes.comm.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName
 * @Description 用户角色中间表返回集合对象
 * @Author
 * @Date 2019/11/22 13:05
 * @Version 1.0
 **/
@ApiModel(description="用户角色中间表返回集合")
@Data
public class UserRoleVo implements Serializable{

    private static final long serialVersionUID = -3783418862274521217L;

    /**
     * 用户ID
     * Table:     f_sys_user_role
     * Column:    user_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * 角色ID
     * Table:     f_sys_user_role
     * Column:    role_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "角色ID")
    private Long roleId;
}
