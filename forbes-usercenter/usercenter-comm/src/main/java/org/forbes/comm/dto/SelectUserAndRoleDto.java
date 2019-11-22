package org.forbes.comm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/11/22 13:03
 * @Version 1.0
 **/
@Data
@ApiModel("用户角色中间表查询传参")
public class SelectUserAndRoleDto {

    @ApiModelProperty("用户id")
    @NotEmpty(message = "用户id不能为空")
    private Long userId;
}
