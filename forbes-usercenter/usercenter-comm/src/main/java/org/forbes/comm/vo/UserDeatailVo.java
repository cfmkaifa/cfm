package org.forbes.comm.vo;

import io.swagger.annotations.ApiModel;
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

    /***当前用户信息
     */
    private SysUser userInfo;
}