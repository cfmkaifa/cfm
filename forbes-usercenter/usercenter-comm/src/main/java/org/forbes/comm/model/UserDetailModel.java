package org.forbes.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * @ClassName
 * @Description TODO
 * @Author xfx
 * @Date 2019/11/20 13:11
 * @Version 1.0
 **/
@ApiModel(description = "用户详情请求参数")
public class UserDetailModel {

    @ApiModelProperty(value = "用户名",required = true)
    @NotEmpty(message = "用户名不能为空")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
