package org.forbes.comm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.dal.entity.SysUser;

import java.io.Serializable;

/**
 * @ClassName
 * @Description TODO
 * @Author xfx
 * @Date 2019/11/20 13:14
 * @Version 1.0
 * 用户详情返回对象
 **/

@ApiModel(description = "用户详情返回对象")
@Data
public class UserDeatailVo implements Serializable {

    private static final long serialVersionUID = 2804937542306643999L;
    /***当前用户信息
     */
    @ApiModelProperty("用户详情")
    private SysUser userInfo;
}
