package org.forbes.biz;

import org.forbes.comm.vo.UserDeatailVo;
import org.forbes.comm.vo.UserListVo;
import org.forbes.dal.entity.SysUser;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ISysUserService extends IService<SysUser> {



	/***
	 * getUserByName方法慨述:根据用户名查询用户
	 * @param username
	 * @return SysUser
	 * @创建人 huanghy
	 * @创建时间 2019年11月15日 下午2:10:16
	 * @修改人 (修改了该文件，请填上修改人的名字)
	 * @修改日期 (请填上修改该文件时的日期)
	 */
	SysUser  getUserByName(String username);

	/**
	  *@ 作者：xfx
	  *@ 参数：
	  *@ 返回值：
	  *@ 时间：2019/11/22
	  *@ Description：多条件查询用户+分页
	  */
	List<UserListVo> selectUserList(String status, Long roleId, String username, String realname);


	/**
	  *@ 作者：xfx
	  *@ 参数：username,status
	  *@ 返回值：
	  *@ 时间：2019/11/19
	  */
	Integer updateUserStatus(String username,String status);
	
	/**
	  *@ 作者：xfx
	  *@ 参数：sysUser
	  *@ 返回值：
	  *@ 时间：2019/11/19
	  */
	Integer addUser(SysUser sysUser);

	/**
	  *@ 作者：xfx
	  *@ 参数：
	  *@ 返回值：
	  *@ 时间：2019/11/19
	  *@ Description：修改用户
	  */
	Integer updateUserByUsername(SysUser sysUser);

	/**
	  *@ 作者：xfx
	  *@ 参数：username
	  *@ 返回值：
	  *@ 时间：2019/11/20
	  *@ Description：查询用户详情
	  */
	UserDeatailVo selectUserDetailByUsername(String username);
}
