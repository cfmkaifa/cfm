package org.forbes.biz;

import com.baomidou.mybatisplus.extension.service.IService;
import org.forbes.comm.model.UpdateRoleAuthorizationDto;
import org.forbes.comm.vo.RoleListVo;
import org.forbes.comm.vo.RoleVo;
import org.forbes.dal.entity.SysRole;

import java.util.List;

public interface SysRoleService  extends IService<SysRole> {
    
    /**
      *@ 作者：xfx
      *@ 参数：
      *@ 返回值：
      *@ 时间：2019/11/20
      *@ Description：角色查询
      */
    List<RoleVo> selectRoleByUserId(Long userId);

    /**
      *@ 作者：xfx
      *@ 参数：
      *@ 返回值：
      *@ 时间：2019/11/20
      *@ Description：添加角色
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
    Integer deleteRoleByRoleId(Long id);

    /**
      *@ 作者：xfx
      *@ 参数：
      *@ 返回值：
      *@ 时间：2019/11/21
      *@ Description：查询所有角色
      */
    List<RoleListVo> selectRoleList();

    /**
     *@ 作者：lzw
     *@ 参数：
     *@ 返回值：
     *@ 时间：2019/11/21
     *@ Description：删除多个角色
     */
    Integer deleteRoleByRoleIds(Long id);

    /***
     * updateRoleAuthorization方法概述:用户授权
     * @param
     * @return
     * @创建人 Tom
     * @创建时间 2019/12/9 11:41
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void updateRoleAuthorization(UpdateRoleAuthorizationDto updateRoleAuthorizationDto);

}