package org.forbes.comm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
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
@Builder
@ApiModel("多条件查询用户请求参数")
public class SysUserListDto implements Serializable{

    @ApiModelProperty("用户状态")
    private String status ;

    @ApiModelProperty("用户角色")
    private Long roleId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("姓名/工厂名称或者公司名称")
    private String realname;

    @ApiModelProperty("页码")
    @Builder.Default
    Integer pageNum=1;

    @ApiModelProperty("默认每页显示条数")
    Integer pageSize=10;


}
