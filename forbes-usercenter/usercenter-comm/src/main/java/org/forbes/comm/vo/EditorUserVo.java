package org.forbes.comm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/12/5 16:47
 * @Version 1.0
 **/
@Data
@ApiModel(description = "编辑用户返回信息")
public class EditorUserVo implements Serializable{

    private static final long serialVersionUID = 8499296380009170860L;

    @ApiModelProperty("用户详情")
    private UserDeatailVo userDeatailVo;

    @ApiModelProperty("所有角色")
    private List<RoleListVo> allRoleList;

    @ApiModelProperty("用户对应角色")
    private List<RoleVo> roleList;
}
