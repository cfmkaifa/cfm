package org.forbes.comm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName
 * @Description TODO
 * @Author xfx
 * @Date 2019/11/21 9:45
 * @Version 1.0
 **/
@ApiModel("角色修改传参")
@Data
public class UpdateRoleDto implements Serializable {

    @ApiModelProperty(value = "角色名称",required = true)
    private String roleName;

    @ApiModelProperty(value = "角色编码",required = true)
    private String roleCode;

    @ApiModelProperty(value = "描述",required = true)
    private String description;

    @ApiModelProperty(value = "更新时间",required = true)
    private Date updateTime;

    @ApiModelProperty(value = "修改人",required = true)
    private String updateBy;

    @ApiModelProperty(value = "角色id",required = true)
    private Long id;
}
