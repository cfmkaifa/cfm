package org.forbes.biz;

import com.baomidou.mybatisplus.extension.service.IService;
import org.forbes.dal.entity.SysPermission;

import java.util.List;

public interface SysPermissionService extends IService<SysPermission> {

    /***
     * getPermissionByRole方法概述:TODO 查询一个角色的所有权限
     * @param RoleId 角色id
     * @return List<SysPermission> 权限集合
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    List<SysPermission> getPermissionByRoleId(Integer RoleId);

    /***
     * AddPermissionToRole方法概述:TODO 给一个角色添加权限
     * @param RoleId 角色id
     * @param PermissionId 权限id
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    Integer AddPermissionToRole(Integer RoleId,Integer PermissionId);

    /***
     * AddPermission方法概述:TODO 添加一个新的权限(仅添加权限)
     * @param sysPermission
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/21
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    Integer AddPermission(SysPermission sysPermission);

    /***
     * AddPermissionRole方法概述:TODO 将新的权限和角色绑定(添加中间表)
     * @param permissionId,roleId
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    Integer AddPermissionRole(Long permissionId, Long roleId);

    /***
     * UpdatePermission方法概述:TODO 通过权限id修改权限内容
     * @param PermissionId 权限id
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/21
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    Integer UpdatePermissionById(Integer PermissionId);

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
    Integer UpdatePermissionToRole(Integer RoleId,Integer PermissionId);

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
    Integer DeletePermissionToRole(Integer RoleId,Integer PermissionId);
}
