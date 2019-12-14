package org.forbes.comm.model;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @ClassName
 * @Description TODO
 * @Author lzw
 * @Date 2019/11/20 14:05
 * @Version 1.0
 **/
@ApiModel(description = "角色对象")
@Data
public class RoleDto implements Serializable{

    private static final long serialVersionUID = -7360307154626516168L;

    @ApiModelProperty(value="角色权限对象",required = true)
    List<RolePermissionDto> permissionDtos;
}
