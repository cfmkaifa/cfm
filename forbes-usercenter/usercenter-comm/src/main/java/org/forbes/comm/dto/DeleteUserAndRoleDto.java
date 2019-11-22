package org.forbes.comm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/11/22 11:33
 * @Version 1.0
 **/
@ApiModel("用户角色中间表删除所需参数")
@Data
public class DeleteUserAndRoleDto implements Serializable{

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "角色id")
    private Long roleId;

}
