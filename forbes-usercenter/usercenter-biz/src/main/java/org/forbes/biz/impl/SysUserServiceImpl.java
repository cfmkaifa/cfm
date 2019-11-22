package org.forbes.biz.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.forbes.biz.ISysUserService;
import org.forbes.dal.entity.SysUser;
import org.forbes.dal.mapper.SysUserMapper;
import org.forbes.dal.mapper.ext.SysUserExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

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
	public PageInfo<SysUser> selectUserList(String status, Long roleId, String username, String realname, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<SysUser> userlist=sysUserExtMapper.selectUserList(status,roleId,username,realname,pageNum,pageSize);
		PageInfo<SysUser> sysUserInfo=new PageInfo<>(userlist);
		return sysUserInfo;
	}

	/**
	  *@ 作者：xfx
	  *@ 参数：username,status
	  *@ 返回值：
	  *@ 时间：2019/11/19
	  */
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
	public SysUser selectUserDetailByUsername(String username) {
		return sysUserExtMapper.selectUserDetailByUsername(username);
	}
}
