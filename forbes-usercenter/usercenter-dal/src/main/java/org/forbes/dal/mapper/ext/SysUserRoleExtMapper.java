package org.forbes.dal.mapper.ext;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.forbes.comm.model.UserRoleDto;
import org.forbes.comm.vo.RoleVo;
import org.forbes.comm.vo.UserRoleVo;
import org.forbes.dal.entity.SysUserRole;

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
    List<UserRoleVo> selectUserRoleListByUserId(@Param("userId") Long userId);

    /***
     * selectUserNotRole方法概述:查询用户所没有的角色
     * @param
     * @return
     * @创建人 Tom
     * @创建时间 2019/12/3 15:02
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    List<RoleVo> selectUserNotRole(Long userId);

    /**
     *@ 作者：xfx
     *@ 参数：
     *@ 返回值：
     *@ 时间：2019/12/5
     *@ Description：批量删除用户角色中间表
     */
    Integer batchDelUserRole(@Param("userId") Long userId,@Param("roleIdArray") Long[] roleIdArray);

    /**
     *@ 作者：xfx
     *@ 参数：addUserRoleDtoList
     *@ 返回值：Integer
     *@ 时间：2019/12/5
     *@ Description：
     */

    Integer batchAddUserAndRole(List<UserRoleDto> addUserRoleDtoList);
}
