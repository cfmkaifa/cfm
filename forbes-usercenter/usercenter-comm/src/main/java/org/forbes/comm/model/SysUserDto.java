package org.forbes.comm.model;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/11/22 13:33
 * @Version 1.0
 **/
@Data
@ApiModel("多条件查询用户请求参数")
public class SysUserDto implements Serializable{

    private static final long serialVersionUID = 4511975727752590523L;


    @ApiModelProperty("用户状态")
    private String status;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("姓名/工厂名称或者公司名称")
    private String realname;
    
    
    @ApiModelProperty("用户角色信息")
    private List<UserRoleDto>  userRoleDtos;

}
