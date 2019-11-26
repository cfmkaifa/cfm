package org.forbes.comm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @ClassName
 * @Description 删除角色
 * @Author
 * @Date 2019/11/21 10:30
 * @Version 1.0
 **/
@ApiModel("删除角色传参")
@Data
public class DeleteRoleDto implements Serializable{

    private static final long serialVersionUID = 4266736212045696576L;


    @ApiModelProperty(value = "角色id",required = true)
    private Long id;

}
