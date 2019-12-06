package org.forbes.comm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * @ClassName
 * @Description TODO
 * @Author xfx
 * @Date 2019/11/20 14:05
 * @Version 1.0
 **/
@ApiModel(description = "查询角色参数")
@Data
public class RoleDto implements Serializable{

    private static final long serialVersionUID = -7360307154626516168L;


    @ApiModelProperty(value="用户id",required=true)
    private Long userId;

    @ApiModelProperty(value="用户名称",required=true)
    private String username;
}
