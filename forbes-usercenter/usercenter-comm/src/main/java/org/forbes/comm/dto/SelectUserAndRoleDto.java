package org.forbes.comm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/11/22 13:03
 * @Version 1.0
 **/
@Data
@ApiModel("用户角色中间表查询传参")
public class SelectUserAndRoleDto implements Serializable{

    private static final long serialVersionUID = 565714674112139969L;

    @ApiModelProperty("用户id")
    private Long userId;

}
