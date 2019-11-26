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
 * @Date 2019/11/20 13:11
 * @Version 1.0
 **/
@Data
@ApiModel(description = "用户详情请求参数")
public class UserDetailDto implements Serializable {

    private static final long serialVersionUID = -4013216358690192922L;
    @ApiModelProperty(value = "用户名",required = true)
    @NotEmpty(message = "用户名不能为空")
    private String username;

}
