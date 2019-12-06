package org.forbes.biz.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import java.util.Map;

@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper,SysPermission> implements SysPermissionService{

    @Autowired
    SysPermissionExtMapper sysPermissionExtMapper;

    /***
     * getPermissionById方法概述:TODO 通过权限id查询权限内容
     * @param id 权限id
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/5
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
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
     * deletePermission方法概述:TODO 删除一个权限
     * @param id
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/5
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional
    public Integer deletePermission(Long id) {
        //根据权限id查询该权限在中间表是否存在
        //i:存在的条数
        Integer i = sysPermissionExtMapper.selectPermissionId(id);
        if(i!=0){
            return -1;
        }else {
            return sysPermissionExtMapper.deletePermission(id);
        }
    }

    /***
     * deletePermissions方法概述:TODO 批量删除权限
     * @param ids
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/5
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional
    public Integer deletePermissions(List<Long> ids) {
        //根据权限id查询该权限在中间表是否存在
        //i:存在的条数
        Integer i = sysPermissionExtMapper.selectPermissionIds(ids);
        if(i!=0){
            return -1;
        }else {
            return sysPermissionExtMapper.deletePermissions(ids);
        }
    }



}
