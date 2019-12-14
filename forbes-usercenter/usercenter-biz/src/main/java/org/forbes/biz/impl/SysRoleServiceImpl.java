package org.forbes.biz.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.forbes.biz.ISysRoleService;
import org.forbes.comm.constant.DataColumnConstant;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.model.RolePermissionDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.dal.entity.SysPermission;
import org.forbes.dal.entity.SysRole;
import org.forbes.dal.entity.SysRolePermission;
import org.forbes.dal.mapper.SysPermissionMapper;
import org.forbes.dal.mapper.SysRoleMapper;
import org.forbes.dal.mapper.SysRolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements  ISysRoleService{
    
    @Autowired
    SysPermissionMapper  sysPermissionMapper;
    @Autowired
    SysRolePermissionMapper sysRolePermissionMapper;
    
    

    /***
	 * 
	 */
	@Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeById(Serializable id) {
		sysRolePermissionMapper.delete(new QueryWrapper<SysRolePermission>().eq(DataColumnConstant.ROLE_ID, id));
        boolean delBool =  SqlHelper.delBool(baseMapper.deleteById(id));
        return delBool;
    }
	
	
	/***批量删除
	 */
	@Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
		sysRolePermissionMapper.delete(new QueryWrapper<SysRolePermission>().in(DataColumnConstant.ROLE_ID, idList));
        boolean delBool =  SqlHelper.delBool(baseMapper.deleteBatchIds(idList));
        return delBool;
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
    @Transactional(rollbackFor=Exception.class)
    public void grantRole(Long roleId,
    		List<RolePermissionDto> rolePermissionDtos) {
        if(ConvertUtils.isNotEmpty(rolePermissionDtos)){
            rolePermissionDtos.stream().forEach(permissionIdRoleDto -> {
                SysRolePermission sysRolePermission=new SysRolePermission();
                Long permissionId = permissionIdRoleDto.getPermissionId();
                /*****判断上级****/
                SysPermission sysPermission = sysPermissionMapper.selectById(permissionId);
                Long parentId = sysPermission.getParentId();
                if(0 != parentId.longValue()){
                	long notParentCount = rolePermissionDtos.stream().filter(tDto -> parentId == tDto.getPermissionId()).count();
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
