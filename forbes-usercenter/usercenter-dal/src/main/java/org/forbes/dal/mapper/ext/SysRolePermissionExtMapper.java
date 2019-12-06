package org.forbes.dal.mapper.ext;

import org.apache.ibatis.annotations.Param;
import org.forbes.comm.dto.AddPermissionToRoleDto;
import org.forbes.comm.dto.DeletePermissionToRoleDto;
import org.forbes.comm.dto.UpdatePermissionToRoleDto;
import org.forbes.comm.vo.PermissionInRoleVo;
import org.forbes.comm.vo.SysRolePermissionVo;

import java.util.List;

/**
 * @author lzw
 * @date 2019/12/6 9:12
 */
public interface SysRolePermissionExtMapper {

    /***
     * getPermissionByRole方法概述:TODO 通过角色id查询一个角色的所有权限
     * @param roleId 角色id
     * @return List<SysPermission> 权限集合
     * @创建人 Tom
     * @创建时间 2019/11/20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    List<SysRolePermissionVo> getPermissionByRoleId(@Param("roleId") Long roleId);

    /***
     * getPermissionByRole方法概述:TODO 通过角色名查询一个角色的所有权限
     * @param roleName 角色名
     * @return List<SysPermission> 权限集合
     * @创建人 Tom
     * @创建时间 2019/11/20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    List<SysRolePermissionVo> getPermissionByRoleName(@Param("roleName") String roleName);

    /***
     * getPermissionByRole方法概述:TODO 查询所有角色与其对应的所有权限
     * @return List<SysPermission> 权限集合
     * @创建人 Tom
     * @创建时间 2019/11/20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    List<SysRolePermissionVo> getPermissionByRole();

    /***
     * getPermissionInRole方法概述:TODO 查询一个角色拥有的权限
     * @return
     * @创建人 Tom
     * @创建时间 2019/12/3
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    List<PermissionInRoleVo> getPermissionInRole(@Param("roleId") Long roleId);

    /***
     * getPermissionNotInRole方法概述:TODO 查询一个角色未拥有的权限
     * @return
     * @创建人 Tom
     * @创建时间 2019/12/3
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    List<PermissionInRoleVo> getPermissionNotInRole(@Param("roleId") Long roleId);

    /***
     * addPermissionToRole方法概述:TODO 给一个角色添加权限
     * @return
     * @创建人 Tom
     * @创建时间 2019/11/20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    Integer addPermissionToRole(AddPermissionToRoleDto addPermissionToRoleDto);

    /***
     * updatePermissionToRole方法概述:TODO 修改角色的一个权限
     * @return
     * @创建人 Tom
     * @创建时间 2019/11/21
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    Integer updatePermissionToRole(UpdatePermissionToRoleDto updatePermissionToRoleDto);

    /***
     * deletePermissionToRole方法概述:TODO 删除角色的一个权限
     * @return
     * @创建人 Tom
     * @创建时间 2019/11/21
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    Integer deletePermissionToRole(DeletePermissionToRoleDto deletePermissionToRoleDto);

    /***
     * updateRolePermissionById方法概述:根据id修改角色权限
     * @param
     * @return
     * @创建人 Tom
     * @创建时间 2019/12/5 16:00
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    Integer updateRolePermissionById(@Param("id")Long id,@Param("roleId") Long roleId,@Param("permissionId")Long permissionId);

}