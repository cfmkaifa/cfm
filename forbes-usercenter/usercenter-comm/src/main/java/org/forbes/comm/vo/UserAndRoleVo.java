package org.forbes.comm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.dal.entity.SysUserRole;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName
 * @Description 用户角色中间表返回集合对象
 * @Author
 * @Date 2019/11/22 13:05
 * @Version 1.0
 **/
@ApiModel(description="用户角色中间表返回集合")
@Data
public class UserAndRoleVo implements Serializable{

    /**
     *用户角色中间表返回集合
     **/
    @ApiModelProperty("用户角色中间表返回集合")
    private List<SysUserRole>  sysUserRoleList;
}
