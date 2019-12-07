package org.forbes.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.biz.SysRoleService;
import org.forbes.comm.vo.RoleListVo;
import org.forbes.comm.vo.RoleVo;
import org.forbes.dal.entity.SysRole;
import org.forbes.dal.mapper.SysRoleMapper;
import org.forbes.dal.mapper.ext.SysRoleExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements  SysRoleService{
    
    @Autowired
    SysRoleExtMapper sysRoleExtMapper;
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

}
