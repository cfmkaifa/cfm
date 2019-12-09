package org.forbes.dal.mapper.ext;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.forbes.comm.model.SysUserDto;
import org.forbes.comm.vo.RoleVo;
import org.forbes.comm.vo.UserDeatailVo;
import org.forbes.comm.vo.UserPermissonVo;
import org.forbes.comm.vo.UserVo;
import org.forbes.dal.entity.SysUser;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;

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

	
	/***
	 * pageUsers方法慨述:分页查询用户信息
	 * @param page
	 * @param sysUser
	 * @return IPage<UserVo>
	 * @创建人 huanghy
	 * @创建时间 2019年12月7日 下午4:21:50
	 * @修改人 (修改了该文件，请填上修改人的名字)
	 * @修改日期 (请填上修改该文件时的日期)
	 */
	IPage<UserVo> pageUsers(IPage<UserVo> page, @Param("sysUser")SysUserDto sysUser);


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

	/**
	 * @Author xfx
	 * @Date 11:55 2019/12/9
	 * @Param [user, roles]
	 * @return void
	 * 编辑用户和角色
	 **/
	public void editUserWithRole(SysUser user,String roles);

}
