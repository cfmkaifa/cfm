package org.forbes.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/11/22 13:33
 * @Version 1.0
 **/
@Data
@ApiModel("多条件查询用户请求参数")
public class SysUserListDto implements Serializable{

    private static final long serialVersionUID = 4511975727752590523L;

    @ApiModelProperty("用户状态")
    private String status ;

    @ApiModelProperty("用户角色")
    private Long roleId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("姓名/工厂名称或者公司名称")
    private String realname;




}