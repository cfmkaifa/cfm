package org.forbes.biz;

import java.util.List;

import org.forbes.comm.model.SysUserDto;
import org.forbes.comm.vo.RoleVo;
import org.forbes.comm.vo.UserDeatailVo;
import org.forbes.comm.vo.UserPermissonVo;
import org.forbes.comm.vo.UserVo;
import org.forbes.dal.entity.SysUser;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

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

	
	
	/***
	 * pageUsers方法慨述:分页查询用户信息
	 * @param page
	 * @param sysUserDto
	 * @return IPage<UserVo>
	 * @创建人 huanghy
	 * @创建时间 2019年12月7日 下午4:20:25
	 * @修改人 (修改了该文件，请填上修改人的名字)
	 * @修改日期 (请填上修改该文件时的日期)
	 */
	IPage<UserVo> pageUsers(IPage<UserVo> page, SysUserDto sysUserDto);



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

	/**
	  *@ 作者：xfx
	  *@ 参数：
	  *@ 返回值：
	  *@ 时间：2019/12/2
	  *@ Description：
	  */
	List<RoleVo>  getRoleListByName(String username);

	/**
	  *@ 作者：xfx
	  *@ 参数：
	  *@ 返回值：
	  *@ 时间：2019/12/2
	  *@ Description：根据用户名查询权限
	  */
	List<UserPermissonVo> getPermissonListByUsername(String username);
	
	/***
	 * addUser方法慨述:增加用户
	 * @param sysUserDto void
	 * @创建人 huanghy
	 * @创建时间 2019年12月7日 下午5:01:22
	 * @修改人 (修改了该文件，请填上修改人的名字)
	 * @修改日期 (请填上修改该文件时的日期)
	 */
	void addUser(SysUserDto sysUserDto);
}
