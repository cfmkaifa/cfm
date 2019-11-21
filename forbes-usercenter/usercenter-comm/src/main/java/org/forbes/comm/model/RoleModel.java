package org.forbes.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;


/**
 * @ClassName
 * @Description TODO
 * @Author xfx
 * @Date 2019/11/20 14:05
 * @Version 1.0
 **/
@ApiModel(description = "查询角色参数模型")
public class RoleModel {

    @ApiModelProperty(value="用户id",required=true)
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
