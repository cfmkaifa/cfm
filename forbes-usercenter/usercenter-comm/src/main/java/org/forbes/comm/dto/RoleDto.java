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
@ApiModel(description = "查询角色参数模型")
@Data
public class RoleDto implements Serializable{

    @ApiModelProperty(value="用户id",required=true)
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
