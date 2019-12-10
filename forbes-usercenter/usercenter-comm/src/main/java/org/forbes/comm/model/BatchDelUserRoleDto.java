package org.forbes.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName
 * @Description 用户角色中间表批量删除传参
 * @Author xfx
 * @Date 2019/12/5 18:54
 * @Version 1.0
 **/
@Data
@ApiModel(description = "用户角色中间表批量删除传参")
public class BatchDelUserRoleDto implements Serializable {

    private static final long serialVersionUID = -7562147645896988719L;

    @ApiModelProperty(value = "用户id(必传)",example = "0")
    private Long userId;

    @ApiModelProperty(value = "角色id数组",example = "-1")
    private Long[] roleIdArray;

}
