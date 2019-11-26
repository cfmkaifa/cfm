package org.forbes.comm.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName
 * @Description 多条件查询用户返回集合
 * @Author
 * @Date 2019/11/22 14:20
 * @Version 1.0
 **/
@ApiModel(description = "多条件查询用户返回集合")
@Data
public class UserListVo implements Serializable{

    /**
     *用户集合
     **/
    @ApiModelProperty("用户集合")
    private List<SysUserVo> sysUserInfo;

}
