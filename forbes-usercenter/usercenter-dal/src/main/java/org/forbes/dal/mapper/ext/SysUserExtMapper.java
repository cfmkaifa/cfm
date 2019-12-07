package org.forbes.dal.mapper.ext;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.forbes.comm.model.SysUserListDto;
import org.forbes.comm.vo.*;
import org.forbes.dal.entity.SysUser;

import java.util.List;

/***
 * SysUserExtMapper概要说明：扩展类
 * @author Huanghy
 */
public interface SysUserExtMapper extends BaseMapper<SysUser>{

	List<SysUser> selectByMyWrapper(@Param(Constants.WRAPPER) Wrapper<SysUser> userWrapper);
	
	
	/***
	 * getUserByName方法慨述:根据用户名查询用户
	 * @param username
	 * @return SysUser
	 * @创建人 huanghy
	 * @创建时间 2019年11月15日 下午2:10:16
	 * @修改人 (修改了该文件，请填上修改人的名字)
	 * @修改日期 (请填上修改该文件时的日期)
	 */
	SysUser  getUserByName(@Param("username")String username);

	/**
	 *@ 作者：xfx
	 *@ 参数：
	 *@ 返回值：
	 *@ 时间：2019/11/22
	 *@ Description：多条件查询用户+分页
	 */
	List<UserListVo> selectUserList(SysUserListDto sysUserListDto);
	
	/**
	  *@ 作者：xfx
	  *@ 参数：
	  *@ 返回值：
	  *@ 时间：2019/11/19
	  */
	Integer updateUserStatus(@Param("username") String username,@Param("status") String status);

	/**
	  *@ 作者：xfx
	  *@ 参数：sysUsers
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
	 *@ Description：
	 */
	UserDeatailVo selectUserDetailByUsername(@Param("username")String username);

	/**
	 *@ 作者：xfx
	 *@ 参数：
	 *@ 返回值：
	 *@ 时间：2019/12/2
	 *@ Description：
	 */
	List<RoleVo>  getRoleListByName(@Param("username")String username);

	/**
	 *@ 作者：xfx
	 *@ 参数：
	 *@ 返回值：
	 *@ 时间：2019/12/2
	 *@ Description：根据用户名查询权限
	 */
	List<UserPermissonVo> getPermissonListByUsername(@Param("username")String username);

}
