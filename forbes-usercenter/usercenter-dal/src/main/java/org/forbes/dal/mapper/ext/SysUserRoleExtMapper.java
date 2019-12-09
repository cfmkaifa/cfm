package org.forbes.dal.mapper.ext;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.forbes.comm.model.UserRoleDto;
import org.forbes.comm.vo.RoleVo;
import org.forbes.comm.vo.UserRoleVo;
import org.forbes.dal.entity.SysUserRole;

public interface SysUserRoleExtMapper {

    /**
     * @Author xfx
     * @Date 14:53 2019/12/9
     * @Param [userId]
     * @return java.util.List<org.forbes.comm.vo.UserRoleVo>
     *@ Description：根据用户id查询用户角色中间表集合
     **/
    List<UserRoleVo> selectUserRoleListByUserId(@Param("userId") Long userId);

    /**
     * @Author xfx
     * @Date 14:53 2019/12/9
     * @Param [userId]
     * @return java.util.List<org.forbes.comm.vo.UserRoleVo>
     *@ Description：查询用户所没有的角色
     **/
    List<RoleVo> selectUserNotRole(Long userId);
}
