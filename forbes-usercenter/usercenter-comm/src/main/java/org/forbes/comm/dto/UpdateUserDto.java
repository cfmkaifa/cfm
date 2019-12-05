package org.forbes.comm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @ClassName
 * @Description TODO
 * @Author xfx
 * @Date 2019/11/19 19:54
 * @Version 1.0
 **/
@ApiModel(description = "修改用户信息传参")
@Data
public class UpdateUserDto implements Serializable {

    private static final long serialVersionUID = 6344108326364152869L;

    @ApiModelProperty(value="登录账号(必传)",required=true)
    @NotEmpty(message="登录账号不能为空")
    private String username;

    @ApiModelProperty(value="登录密码",required=true)
    private String password;

    @ApiModelProperty(value = "用户头像",required = true)
    private String avatar;

    @ApiModelProperty(value = "联系方式",required = true)
    private String phone;

    @ApiModelProperty(value = "姓名",required = true)
    private String realname;

    @ApiModelProperty(value = "邮箱",required = true)
    private String email;

}
