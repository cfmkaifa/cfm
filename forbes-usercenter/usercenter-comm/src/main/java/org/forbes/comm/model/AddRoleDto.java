package org.forbes.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @ClassName
 * @Description 添加角色请求参数模型
 * @Author lzw
 * @Date 2019/11/20 14:56
 * @Version 1.0
 **/
@ApiModel(description = "添加角色")
@Data
public class AddRoleDto implements Serializable{

    private static final long serialVersionUID = -5312771729107023541L;


    @ApiModelProperty(value = "角色名称",required = true)
    @NotEmpty(message = "名称不能为空")
    private String roleName;

    @ApiModelProperty(value = "角色描述",required = true)
    private String description;


}
