package org.forbes.comm.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.forbes.comm.constant.UpdateValid;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName
 * @Description 用户信息
 * @Author
 * @Date 2019/11/22 13:33
 * @Version 1.0
 **/
@Data
@ApiModel(description="用户信息")
@EqualsAndHashCode(callSuper = false)
public class SysUserDto implements Serializable{

    private static final long serialVersionUID = 4511975727752590523L;

    
    @ApiModelProperty(value = "主键ID",example = "0")
    @NotNull(message="主键ID为空",groups = UpdateValid.class)
    private Long id;
    
    @ApiModelProperty("用户状态")
    @NotEmpty(message="用户状态为空")
    private String status;

    @ApiModelProperty("用户名")
    @NotEmpty(message="用户名为空")
    private String username;

    @ApiModelProperty("姓名/工厂名称或者公司名称")
    private String realname;

    @ApiModelProperty("电话")
    @Pattern(regexp ="^[1][3,4,5,7,8][0-9]{9}$")
    private String phone;

    @ApiModelProperty("邮箱")
    @Pattern(regexp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")
    private String email;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("头像")
    private String avatar;
    
    
    @ApiModelProperty(value="管理员标识(0-超级管理员,1-普通人员)")
	@NotEmpty(message="管理员标识不能为空")
    private String adminFlag;
    
    @ApiModelProperty("用户角色关联信息")
    private List<UserRoleDto>  userRoleDtos;

}
