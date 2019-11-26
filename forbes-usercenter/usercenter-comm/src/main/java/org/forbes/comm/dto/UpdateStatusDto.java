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
 *@Date 2019/11/19 19:00
 *@Version 1.0
 **/
@ApiModel(description = "修改用户状态model")
@Data
public class UpdateStatusDto implements Serializable {

    private static final long serialVersionUID = 5439223157377317771L;
    @ApiModelProperty(value="用户名账号",required=true)
    @NotEmpty(message="用户名账号不能为空")
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
