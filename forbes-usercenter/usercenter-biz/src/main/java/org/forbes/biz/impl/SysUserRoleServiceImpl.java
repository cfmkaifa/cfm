package org.forbes.biz.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.forbes.biz.ISysUserRoleService;
import org.forbes.comm.vo.RoleVo;
import org.forbes.comm.vo.UserRoleVo;
import org.forbes.dal.entity.SysUserRole;
import org.forbes.dal.mapper.SysUserRoleMapper;
import org.forbes.dal.mapper.ext.SysUserRoleExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {


    @Autowired
    SysUserRoleExtMapper sysUserRoleExtMapper;

    /**
     *@ 作者：xfx
     *@ 参数：
     *@ 返回值：
     *@ 时间：2019/11/22
     *@ Description：根据用户id查询用户角色中间表集合
     */
    public List<UserRoleVo> selectUserRoleListByUserId(@Param("userId") Long userId) {
        return sysUserRoleExtMapper.selectUserRoleListByUserId(userId);
    }

    /***
     * selectUserNotRole方法概述:查询用户所没有的角色
     * @param
     * @return
     * @创建人 Tom
     * @创建时间 2019/12/3 15:02
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public List<RoleVo> selectUserNotRole(Long userId) {
        return sysUserRoleExtMapper.selectUserNotRole(userId);
    }
}
