package org.forbes.biz;

import com.baomidou.mybatisplus.extension.service.IService;
import org.forbes.comm.vo.UserAndRoleVo;
import org.forbes.dal.entity.SysUserRole;

import java.util.List;

public interface SysUserRoleService extends IService<SysUserRole> {
    
    /**
      *@ 作者：xfx
      *@ 参数：sysUserRole
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
    Integer deleteUserAndRole(Long userId,Long roleId);

    /**
      *@ 作者：xfx
      *@ 参数：
      *@ 返回值：
      *@ 时间：2019/11/22
      *@ Description：根据用户id查询用户角色中间表集合
      */
    List<UserAndRoleVo>  selectUserRoleListByUserId(Long userId);
}
