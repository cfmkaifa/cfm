package org.forbes.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.biz.ISysRolePermissionService;
import org.forbes.comm.model.PermissionRoleDto;
import org.forbes.comm.vo.PermissionInRoleVo;
import org.forbes.comm.vo.SysRolePermissionVo;
import org.forbes.dal.entity.SysRolePermission;
import org.forbes.dal.mapper.SysRolePermissionMapper;
import org.forbes.dal.mapper.ext.SysRolePermissionExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysRolePermissionServiceImpl
        extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements ISysRolePermissionService {

     @Autowired
     SysRolePermissionExtMapper sysRolePermissionExtMapper;
    /***
     * getPermissionByRoleId方法概述: TODO 通过角色id查询一个角色的所有权限
     * @param roleId 用户名
     * @return List<SysPermission> 权限集合
     * @创建人 Tom
     * @创建时间 2019/11/20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public List<SysRolePermissionVo> getPermissionByRoleId(Long roleId) {
        return sysRolePermissionExtMapper.getPermissionByRoleId(roleId);
    }
    /***
     * getPermissionByRoleName方法概述: TODO 通过角色名查询一个角色的所有权限
     * @param roleName 角色名
     * @return List<SysPermission> 权限集合
     * @创建人 Tom
     * @创建时间 2019/11/20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public List<SysRolePermissionVo> getPermissionByRoleName(String roleName) {
        return sysRolePermissionExtMapper.getPermissionByRoleName(roleName);
    }

    /***
     * getPermissionByRole方法概述:TODO 查询所有角色与其对应的所有权限
     * @return List<SysPermission> 权限集合
     * @创建人 Tom
     * @创建时间 2019/11/20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public List<SysRolePermissionVo> getPermissionByRole() {
        return sysRolePermissionExtMapper.getPermissionByRole();
    }

    /***
     * getPermissionInRole方法概述:TODO 查询一个角色拥有的权限
     * @return
     * @创建人 Tom
     * @创建时间 2019/12/3
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public List<PermissionInRoleVo> getPermissionInRole(Long roleId) {
        return sysRolePermissionExtMapper.getPermissionInRole(roleId);
    }

    /***
     * getPermissionNotInRole方法概述:TODO 查询一个角色未拥有的权限
     * @return
     * @创建人 Tom
     * @创建时间 2019/12/3
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public List<PermissionInRoleVo> getPermissionNotInRole(Long roleId) {
        return sysRolePermissionExtMapper.getPermissionNotInRole(roleId);
    }

    /***
     * addPermissionToRole方法概述: TODO 给一个角色添加权限
     * @return
     * @创建人 Tom
     * @创建时间 2019/11/20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional
    public Integer addPermissionToRole(SysRolePermission sysRolePermission) {
        return sysRolePermissionExtMapper.addPermissionToRole(sysRolePermission);
    }

    /***
     * updatePermissionToRole方法概述:TODO 修改角色的一个权限
     * @return
     * @创建人 Tom
     * @创建时间 2019/11/21
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional
    public Integer updatePermissionToRole(PermissionRoleDto permissionRoleDto) {
        return sysRolePermissionExtMapper.updatePermissionToRole(permissionRoleDto);
    }

    /***
     * deletePermissionToRole方法概述:TODO 删除角色的一个权限
     * @return
     * @创建人 Tom
     * @创建时间 2019/11/21
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional
    public Integer deletePermissionToRole(SysRolePermission sysRolePermission) {
        return sysRolePermissionExtMapper.deletePermissionToRole(sysRolePermission);
    }

    /***
     * updateRolePermissionById方法概述:根据id修改角色权限
     * @param
     * @return
     * @创建人 Tom
     * @创建时间 2019/12/5 16:00
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional
    public Integer updateRolePermissionById(Long id, Long roleId,Long permissionId) {
        return sysRolePermissionExtMapper.updateRolePermissionById(id,roleId,permissionId);
    }

}
