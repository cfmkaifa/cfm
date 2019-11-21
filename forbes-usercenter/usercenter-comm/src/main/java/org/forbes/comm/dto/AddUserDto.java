package org.forbes.comm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 *@ClassName
 *@Description TODO
 *@Author
 *@Date 2019/11/19 19:26
 *@Version 1.0
 **/
@ApiModel(description = "添加用户")
@Data
public class AddUserDto implements Serializable{

    @ApiModelProperty(value="登录账号",required=true)
    @NotEmpty(message="登录账号不能为空")
    private String username;

    @ApiModelProperty(value="登录密码",required=true)
    @NotEmpty(message="登录密码不能为空")
    private String password;

    @ApiModelProperty(value = "用户头像",required = true)
    @NotEmpty(message = "工厂头像或公司头像")
    private String avatar;

    @ApiModelProperty(value = "联系方式",required = true)
    @NotEmpty(message = "联系方式")
    private String phone;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
