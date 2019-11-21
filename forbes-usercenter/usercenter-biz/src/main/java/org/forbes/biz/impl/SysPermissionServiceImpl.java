package org.forbes.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.biz.SysPermissionService;
import org.forbes.dal.entity.SysPermission;
import org.forbes.dal.mapper.SysPermissionMapper;
import org.forbes.dal.mapper.ext.SysPermissionExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper,SysPermission> implements SysPermissionService{

    @Autowired
    SysPermissionExtMapper sysPermissionExtMapper;

    /***
     * getPermissionByRole方法概述: TODO 查询一个角色的所有权限
     * @param RoleId 用户名
     * @return List<SysPermission> 权限集合
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public List<SysPermission> getPermissionByRoleId(Integer RoleId) {
        return sysPermissionExtMapper.getPermissionByRoleId(RoleId);
    }

    /***
     * AddPermissionToRole方法概述: TODO 给一个角色添加权限
     * @param RoleId 角色id
     * @param PermissionId 权限id
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional
    public Integer AddPermissionToRole(Integer RoleId,Integer PermissionId) {
        return sysPermissionExtMapper.AddPermissionToRole(RoleId,PermissionId);
    }

    /***
     * AddPermission方法概述:TODO 添加一个新的权限(仅添加权限)
     * @param sysPermission
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/21
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional
    public Integer AddPermission(SysPermission sysPermission) {
        return sysPermissionExtMapper.AddPermission(sysPermission);
    }

    /***
     * AddPermissionRole方法概述:TODO 将新的权限和角色绑定(添加中间表)
     * @param permissionId,roleId
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional
    public Integer AddPermissionRole(Long permissionId, Long roleId) {
        return sysPermissionExtMapper.AddPermissionRole(permissionId,roleId);
    }

    /***
     * UpdatePermission方法概述:TODO 通过权限id修改权限内容
     * @param sysPermissionId 权限id
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/21
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional
    public Integer UpdatePermissionById(Integer sysPermissionId) {
        return sysPermissionExtMapper.UpdatePermissionById(sysPermissionId);
    }

    /***
     * UpdatePermissionToRole方法概述:TODO 修改一个角色的权限
     * @param RoleId 角色di
     * @param PermissionId 权限id集合
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/21
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional
    public Integer UpdatePermissionToRole(Integer RoleId, Integer PermissionId) {
        return sysPermissionExtMapper.UpdatePermissionToRole(RoleId,PermissionId);
    }

    /***
     * DeletePermissionToRole方法概述:TODO 删除角色的权限
     * @param RoleId 角色id
     * @param PermissionId 权限id
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/21
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional
    public Integer DeletePermissionToRole(Integer RoleId, Integer PermissionId) {
        return sysPermissionExtMapper.DeletePermissionToRole(RoleId,PermissionId);
    }


}
