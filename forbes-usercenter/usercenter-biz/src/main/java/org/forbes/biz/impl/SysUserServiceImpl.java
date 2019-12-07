package org.forbes.biz.impl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.forbes.biz.ISysUserService;
import org.forbes.comm.model.SysUserListDto;
import org.forbes.comm.vo.*;
import org.forbes.dal.entity.SysUser;
import org.forbes.dal.mapper.SysUserMapper;
import org.forbes.dal.mapper.ext.SysUserExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
	


	@Autowired
	SysUserExtMapper sysUserExtMapper;
	
	/***
	 * getUserByName方法慨述:根据用户名查询用户
	 * @param username
	 * @return SysUser
	 * @创建人 huanghy
	 * @创建时间 2019年11月15日 下午2:10:16
	 * @修改人 (修改了该文件，请填上修改人的名字)
	 * @修改日期 (请填上修改该文件时的日期)
	 */
	public SysUser  getUserByName(String username){
		return sysUserExtMapper.getUserByName(username);
	}

	/**
	 *@ 作者：xfx
	 *@ 参数：
	 *@ 返回值：
	 *@ 时间：2019/11/22
	 *@ Description：多条件查询用户+分页
	 */
	public List<UserListVo> selectUserList(SysUserListDto sysUserListDto) {
		return sysUserExtMapper.selectUserList(sysUserListDto);
	}

	/**
	  *@ 作者：xfx
	  *@ 参数：username,status
	  *@ 返回值：
	  *@ 时间：2019/11/19
	  */
	@Transactional
	public Integer updateUserStatus(String username, String status) {
		return sysUserExtMapper.updateUserStatus(username,status);
	}
	/**
	  *@ 作者：xfx
	  *@ 参数：
	  *@ 返回值：
	  *@ 时间：2019/11/19
	  *@ Description：
	  */
	@Transactional
	public Integer addUser(SysUser sysUser) {
		return sysUserExtMapper.addUser(sysUser);
	}

	/**
	  *@ 作者：xfx
	  *@ 参数：
	  *@ 返回值：
	  *@ 时间：2019/11/19
	  *@ Description：
	  */
	@Transactional
	public Integer updateUserByUsername(SysUser sysUser) {
		return sysUserExtMapper.updateUserByUsername(sysUser);
	}

	/**
	  *@ 作者：xfx
	  *@ 参数：
	  *@ 返回值：
	  *@ 时间：2019/11/20
	  *@ Description：
	  */
	public UserDeatailVo selectUserDetailByUsername(String username) {
		return sysUserExtMapper.selectUserDetailByUsername(username);
	}

	/**
	 *@ 作者：xfx
	 *@ 参数：
	 *@ 返回值：
	 *@ 时间：2019/12/2
	 *@ Description：
	 */
	@Override
	public List<RoleVo> getRoleListByName(String username) {
		return sysUserExtMapper.getRoleListByName(username);
	}

	/**
	 *@ 作者：xfx
	 *@ 参数：
	 *@ 返回值：
	 *@ 时间：2019/12/2
	 *@ Description：根据用户名查询权限
	 */
	@Override
	public List<UserPermissonVo> getPermissonListByUsername(String username) {
		return sysUserExtMapper.getPermissonListByUsername(username);
	}
}
