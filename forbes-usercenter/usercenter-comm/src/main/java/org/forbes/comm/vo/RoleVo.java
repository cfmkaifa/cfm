package org.forbes.comm.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.forbes.dal.entity.SysRole;

import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/11/20 14:19
 * @Version 1.0
 **/
@Data
@ApiModel(description = "角色集合")
public class RoleVo {

    /**
     * 角色集合
     **/
    private List<SysRole> sysRoleList;
}
