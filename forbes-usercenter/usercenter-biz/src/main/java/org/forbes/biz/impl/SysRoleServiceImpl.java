package org.forbes.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.biz.SysRoleService;
import org.forbes.comm.model.AddRoleModel;
import org.forbes.comm.vo.LoginVo;
import org.forbes.dal.entity.SysRole;
import org.forbes.dal.mapper.SysRoleMapper;
import org.forbes.dal.mapper.ext.SysRoleExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements  SysRoleService{
    
    @Autowired
    SysRoleExtMapper sysRoleExtMapper;
    /**
      *@ 作者：xfx
      *@ 参数：userId
      *@ 返回值：
      *@ 时间：2019/11/20
      *@ Description：查询用户角色
      */
    public List<SysRole> selectRoleByUserId(Long userId) {
        return sysRoleExtMapper.selectRoleByUserId(userId);
    }

    /**
     *@ 作者：xfx
     *@ 参数：
     *@ 返回值：
     *@ 时间：2019/11/20
     *@ Description：添加一个角色
     */
    public Integer addRole(SysRole sysRole) {
        AddRoleModel addRoleModel=new AddRoleModel();
        String roleName=addRoleModel.getRoleName();
        String roleCode=addRoleModel.getRoleCode();
        String description=addRoleModel.getDescription();
        LoginVo loginVo=new LoginVo();
        sysRole.setCreateBy(loginVo.getUserInfo().getRealname());
        sysRole.setDescription(description);
        sysRole.setRoleCode(roleCode);
        sysRole.setRoleName(roleName);
        return sysRoleExtMapper.addRole(sysRole);
    }


    /**
     *@ 作者：xfx
     *@ 参数：sysRole
     *@ 返回值：
     *@ 时间：2019/11/21
     *@ Description：角色修改
     */
    public Integer updateRoleByRoleId(SysRole sysRole) {

        return sysRoleExtMapper.updateRoleByRoleId(sysRole);
    }

    /**
     *@ 作者：xfx
     *@ 参数：
     *@ 返回值：
     *@ 时间：2019/11/21
     *@ Description：删除一个角色
     */
    public Integer deleteRoleByRoleId(Long id) {
        return sysRoleExtMapper.deleteRoleByRoleId(id);
    }

    /**
     *@ 作者：xfx
     *@ 参数：
     *@ 返回值：
     *@ 时间：2019/11/21
     *@ Description：查询所有角色
     */
    public List<SysRole> selectRoleList() {
        return sysRoleExtMapper.selectRoleList();
    }
}
