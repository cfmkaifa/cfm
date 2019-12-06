package org.forbes.dal.mapper.ext;

import org.apache.ibatis.annotations.Param;
import org.forbes.comm.vo.RoleAuthorizationVo;
import org.forbes.comm.vo.RoleListVo;
import org.forbes.comm.vo.RoleVo;
import org.forbes.dal.entity.SysRole;

import java.util.List;

public interface SysRoleExtMapper {
    /**
     *@ 作者：xfx
     *@ 参数：
     *@ 返回值：
     *@ 时间：2019/11/20
     *@ Description：
     */
    List<RoleVo> selectRoleByUserId(@Param("userId")Long userId);

    /**
     *@ 作者：xfx
     *@ 参数：
     *@ 返回值：
     *@ 时间：2019/11/20
     *@ Description：
     */
    Integer addRole(SysRole sysRole);

    /**
     *@ 作者：xfx
     *@ 参数：sysRole
     *@ 返回值：
     *@ 时间：2019/11/21
     *@ Description：角色修改
     */
    Integer updateRoleByRoleId(SysRole sysRole);

    /**
     *@ 作者：xfx
     *@ 参数：
     *@ 返回值：
     *@ 时间：2019/11/21
     *@ Description：删除一个角色
     */
    Integer deleteRoleByRoleId(@Param("id") Long id);

    /**
     *@ 作者：xfx
     *@ 参数：
     *@ 返回值：
     *@ 时间：2019/11/21
     *@ Description：查询所有角色
     */
    List<RoleListVo> selectRoleList();

}
