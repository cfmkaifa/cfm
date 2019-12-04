package org.forbes.biz.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Param;
import org.forbes.biz.SysPermissionService;
import org.forbes.comm.dto.AddPermissionToRoleDto;
import org.forbes.comm.dto.DeletePermissionToRoleDto;
import org.forbes.comm.dto.UpdatePermissionDto;
import org.forbes.comm.dto.UpdatePermissionToRoleDto;
import org.forbes.comm.vo.PermissionInRoleVo;
import org.forbes.comm.vo.PermissionVo;
import org.forbes.comm.vo.SysRolePermissionVo;
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

    @Override
    public List<PermissionVo> getPermissionById(@Param("id") Long id) {
        return sysPermissionExtMapper.getPermissionById(id);
    }

    /***
     * getPermission方法概述:TODO 查询所有权限
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/2
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public List<PermissionVo> getPermission() {
        return sysPermissionExtMapper.getPermission();
    }

    /***
     * getPermissionByRoleId方法概述: TODO 通过角色id查询一个角色的所有权限
     * @param roleId 用户名
     * @return List<SysPermission> 权限集合
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public List<SysRolePermissionVo> getPermissionByRoleId(Long roleId) {
        return sysPermissionExtMapper.getPermissionByRoleId(roleId);
    }
    /***
     * getPermissionByRoleName方法概述: TODO 通过角色名查询一个角色的所有权限
     * @param roleName 角色名
     * @return List<SysPermission> 权限集合
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public List<SysRolePermissionVo> getPermissionByRoleName(String roleName) {
        return sysPermissionExtMapper.getPermissionByRoleName(roleName);
    }

    /***
     * getPermissionByRole方法概述:TODO 查询所有角色与其对应的所有权限
     * @return List<SysPermission> 权限集合
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public List<SysRolePermissionVo> getPermissionByRole() {
        return sysPermissionExtMapper.getPermissionByRole();
    }

    /***
     * getPermissionInRole方法概述:TODO 查询一个角色拥有的权限
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/3
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public List<PermissionInRoleVo> getPermissionInRole(Long roleId) {
        return sysPermissionExtMapper.getPermissionInRole(roleId);
    }

    /***
     * getPermissionNotInRole方法概述:TODO 查询一个角色未拥有的权限
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/3
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public List<PermissionInRoleVo> getPermissionNotInRole(Long roleId) {
        return sysPermissionExtMapper.getPermissionNotInRole(roleId);
    }

    /***
     * addPermissionToRole方法概述: TODO 给一个角色添加权限
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional
    public Integer addPermissionToRole(AddPermissionToRoleDto addPermissionToRoleDto) {
        addPermissionToRoleDto.getId();//主键id
        addPermissionToRoleDto.getUpdateBy();//修改人
        addPermissionToRoleDto.getUpdateTime();//修改时间
        addPermissionToRoleDto.getCreateBy();//创建人
        addPermissionToRoleDto.getCreateTime();//创建时间

        return sysPermissionExtMapper.addPermissionToRole(addPermissionToRoleDto);
    }

    /***
     * addPermission方法概述:TODO 添加一个新的权限(仅添加权限)
     * @param sysPermission
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/21
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional
    public Integer addPermission(SysPermission sysPermission) {
        return sysPermissionExtMapper.addPermission(sysPermission);
    }

    /***
     * updatePermission方法概述:TODO 修改权限内容
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/21
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional
    public Integer updatePermission(UpdatePermissionDto updatePermissionDto) {
        return sysPermissionExtMapper.updatePermission(updatePermissionDto);
    }

    /***
     * updatePermissionToRole方法概述:TODO 修改角色的一个权限
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/21
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional
    public Integer updatePermissionToRole(UpdatePermissionToRoleDto updatePermissionToRoleDto) {
        return sysPermissionExtMapper.updatePermissionToRole(updatePermissionToRoleDto);
    }

    /***
     * deletePermissionToRole方法概述:TODO 删除角色的一个权限
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/21
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional
    public Integer deletePermissionToRole(DeletePermissionToRoleDto deletePermissionToRoleDto) {
        return sysPermissionExtMapper.deletePermissionToRole(deletePermissionToRoleDto);
    }


}
