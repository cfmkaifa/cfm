package org.forbes.dal.mapper.ext;

import org.apache.ibatis.annotations.Param;
import org.forbes.comm.vo.UserAndRoleVo;
import org.forbes.dal.entity.SysUserRole;

import java.util.List;

public interface SysUserRoleExtMapper {

    /**
     *@ 作者：xfx
     *@ 参数：
     *@ 返回值：
     *@ 时间：2019/11/22
     *@ Description：添加用户角色中间表所需参数
     */
    Integer addUserAndRole(SysUserRole sysUserRole);

    /**
     *@ 作者：xfx
     *@ 参数：
     *@ 返回值：
     *@ 时间：2019/11/22
     *@ Description：用户角色中间表删除
     */
    Integer deleteUserAndRole(@Param("userId") Long userId,@Param("roleId") Long roleId);

    /**
     *@ 作者：xfx
     *@ 参数：
     *@ 返回值：
     *@ 时间：2019/11/22
     *@ Description：根据用户id查询用户角色中间表集合
     */
    List<UserAndRoleVo> selectUserRoleListByUserId(@Param("userId") Long userId);
}
