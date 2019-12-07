package org.forbes.comm.model;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName
 * @Description 用户角色中间表
 * @Author
 * @Date 2019/11/21 18:10
 * @Version 1.0
 **/
@Data
@ApiModel("用户角色中间表")
public class UserRoleDto implements Serializable{

    private static final long serialVersionUID = 5601288102119484469L;

    @ApiModelProperty(value = "用户id")
    @NotEmpty(message = "用户id不能为空")
    private Long userId;


    @ApiModelProperty(value = "角色id")
    @NotEmpty(message = "角色id不能为空")
    private Long roleId;

}
