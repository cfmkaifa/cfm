package org.forbes.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @ClassName
 * @Description 添加角色请求参数模型
 * @Author
 * @Date 2019/11/20 14:56
 * @Version 1.0
 **/
@ApiModel
@Data
public class AddRoleModel {

    @ApiModelProperty(value = "角色名称",required = true)
    @NotEmpty(message = "名称不能为空")
    private String roleName;

    @ApiModelProperty(value = "角色编码",required = true)
    @NotEmpty(message = "角色编码不能为空")
    private String roleCode;

    @ApiModelProperty(value = "角色描述",required = true)
    private String description;


}
