package org.forbes.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 *@ClassName
 *@Description TODO
 *@Author
 *@Date 2019/11/19 19:00
 *@Version 1.0
 **/
@ApiModel(description = "修改用户状态model")
public class UpdateStatusModel {

    @ApiModelProperty(value="登录账号",required=true)
    @NotEmpty(message="登录账号不能为空")
    private String username;

    @ApiModelProperty(value="状态不能为空",required=true)
    @NotEmpty(message="状态不能为空")
    private String status;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
