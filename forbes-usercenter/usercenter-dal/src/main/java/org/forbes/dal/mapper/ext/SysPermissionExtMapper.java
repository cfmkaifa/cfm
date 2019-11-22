package org.forbes.dal.mapper.ext;

import org.forbes.dal.entity.SysPermission;

import java.util.List;

/***
 * SysPermissionExtMapper：扩展类
 * @author niehy(Frunk)
 */
public interface SysPermissionExtMapper {
    /***
     * getPermissionByRole方法概述:TODO 查询一个角色的所有权限
     * @param RoleId 角色名
     * @return List<SysPermission> 权限集合
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    List<SysPermission> getPermissionByRoleId(Integer RoleId);

    /***
     * getPermissionByRole方法概述:TODO 查询所有角色与其对应的所有权限
     * @return List<SysPermission> 权限集合
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    List<SysPermission> getPermissionByRole();

    /***
     * addPermissionToRole方法概述:TODO 给一个角色添加权限
     * @param RoleId 角色id
     * @param PermissionId 权限id
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    Integer addPermissionToRole(Integer RoleId,Integer PermissionId);

    /***
     * addPermission方法概述:TODO 添加一个新的权限(仅添加权限)
     * @param sysPermission
     * @return 
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/21
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    Integer addPermission(SysPermission sysPermission);

    /***
     * updatePermission方法概述:TODO 修改权限内容
     * @param sysPermission 权限对象
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/21
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    Integer updatePermission(SysPermission sysPermission);

    /***
     * updatePermissionToRole方法概述:TODO 修改一个角色的权限
     * @param RoleId 角色di
     * @param PermissionId 权限id集合
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/21
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    Integer updatePermissionToRole(Integer RoleId,Integer PermissionId);

    /***
     * deletePermissionToRole方法概述:TODO 删除角色的权限
     * @param RoleId 角色id
     * @param PermissionId 权限id
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/21
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    Integer deletePermissionToRole(Integer RoleId,Integer PermissionId);

}
