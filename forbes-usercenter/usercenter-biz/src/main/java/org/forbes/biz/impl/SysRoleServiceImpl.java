package org.forbes.biz.impl;

import java.util.List;

import org.forbes.biz.SysRoleService;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.model.PermissionIdRoleDto;
import org.forbes.comm.model.RolePermissionDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.RoleListVo;
import org.forbes.comm.vo.RoleVo;
import org.forbes.dal.entity.SysPermission;
import org.forbes.dal.entity.SysRole;
import org.forbes.dal.entity.SysRolePermission;
import org.forbes.dal.mapper.SysPermissionMapper;
import org.forbes.dal.mapper.SysRoleMapper;
import org.forbes.dal.mapper.SysRolePermissionMapper;
import org.forbes.dal.mapper.ext.SysRoleExtMapper;
import org.forbes.dal.mapper.ext.SysRolePermissionExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements  SysRoleService{
    
    @Autowired
    SysRoleExtMapper sysRoleExtMapper;
    @Autowired
    SysRolePermissionExtMapper sysRolePermissionExtMapper;
    @Autowired
    SysPermissionMapper  sysPermissionMapper;

    @Autowired
    SysRolePermissionMapper sysRolePermissionMapper;
    /**
      *@ 作者：lzw
      *@ 参数：userId
      *@ 返回值：
      *@ 时间：2019/11/20
      *@ Description：查询用户角色
      */
    public List<RoleVo> selectRoleByUserId(Long userId) {
        return sysRoleExtMapper.selectRoleByUserId(userId);
    }

    /**
     *@ 作者：lzw
     *@ 参数：
     *@ 返回值：
     *@ 时间：2019/11/20
     *@ Description：添加一个角色
     */
    @Transactional
    public Integer addRole(SysRole sysRole) {
        return sysRoleExtMapper.addRole(sysRole);
    }


    /**
     *@ 作者：lzw
     *@ 参数：sysRole
     *@ 返回值：
     *@ 时间：2019/11/21
     *@ Description：角色修改
     */
    @Transactional
    public Integer updateRoleByRoleId(SysRole sysRole) {
        return sysRoleExtMapper.updateRoleByRoleId(sysRole);
    }

    /**
     *@ 作者：lzw
     *@ 参数：
     *@ 返回值：
     *@ 时间：2019/11/21
     *@ Description：删除一个角色
     */
    @Transactional
    public Integer deleteRoleByRoleId(Long id) {
        return sysRoleExtMapper.deleteRoleByRoleId(id);
    }

    /**
     *@ 作者：lzw
     *@ 参数：
     *@ 返回值：
     *@ 时间：2019/11/21
     *@ Description：查询所有角色
     */
    public List<RoleListVo> selectRoleList() {
        return sysRoleExtMapper.selectRoleList();
    }

    /**
     *@ 作者：lzw
     *@ 参数：
     *@ 返回值：
     *@ 时间：2019/11/21
     *@ Description：删除多个角色
     */
    public Integer deleteRoleByRoleIds(Long id) {
        return sysRoleExtMapper.deleteRoleByRoleIds(id);
    }

    /***
     * updateRoleAuthorization方法概述:用户授权
     * @param
     * @return
     * @创建人 Tom
     * @创建时间 2019/12/9 11:41
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional
    public void updateRoleAuthorization(RolePermissionDto rolePermissionDto) {
        SysRole sysRole=new SysRole();
        List<PermissionIdRoleDto> permissionIdRoleDtos = rolePermissionDto.getPermissionIdRoleDtos();
        if(ConvertUtils.isNotEmpty(permissionIdRoleDtos)){
            permissionIdRoleDtos.stream().forEach(permissionIdRoleDto -> {
                Long roleId=sysRole.getId();
                SysRolePermission sysRolePermission=new SysRolePermission();
                Long permissionId = permissionIdRoleDto.getPermissionId();
                /*****判断上级****/
                SysPermission sysPermission = sysPermissionMapper.selectById(permissionId);
                Long parentId = sysPermission.getParentId();
                if(-1 != parentId.longValue()){
                	long notParentCount = permissionIdRoleDtos.stream().filter(tDto -> parentId == tDto.getPermissionId()).count();
                	if(0 == notParentCount){
                		throw new ForbesException(BizResultEnum.PERMISSION_PARENT_NO_EXISTS.getBizCode(),String.format(BizResultEnum.PERMISSION_PARENT_NO_EXISTS.getBizFormateMessage(), sysPermission.getName()));
                	}
                }
                sysRolePermission.setPermissionId(permissionIdRoleDto.getPermissionId());
                sysRolePermission.setRoleId(roleId);
                sysRolePermissionMapper.insert(sysRolePermission);
            });
        }
    }

}
