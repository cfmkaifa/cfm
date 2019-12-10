package org.forbes.biz;

import com.baomidou.mybatisplus.extension.service.IService;
import org.forbes.comm.model.UserRoleDto;
import org.forbes.comm.vo.RoleVo;
import org.forbes.comm.vo.UserRoleVo;
import org.forbes.dal.entity.SysUserRole;

import java.util.List;

public interface SysUserRoleService extends IService<SysUserRole> {

    /**
      *@ 作者：xfx
      *@ 参数：
      *@ 返回值：
      *@ 时间：2019/11/22
      *@ Description：根据用户id查询用户角色中间表集合
      */
    List<UserRoleVo>  selectUserRoleListByUserId(Long userId);

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
}
