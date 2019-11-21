package org.forbes.comm.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.forbes.dal.entity.SysRole;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName
 * @Description 查询角色返回VO
 * @Author xfx
 * @Date 2019/11/21 14:02
 * @Version 1.0
 **/
@ApiModel(description = "查询角色返回对象")
@Data
public class RoleListVo implements Serializable{

    /**
      *角色集合
      */
    private List<SysRole> sysRoleList;
}
