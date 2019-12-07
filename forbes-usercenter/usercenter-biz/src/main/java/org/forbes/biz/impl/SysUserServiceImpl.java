package org.forbes.biz.impl;
import java.util.List;
import java.util.stream.Collectors;

import org.forbes.biz.ISysUserService;
import org.forbes.comm.enums.UserStausEnum;
import org.forbes.comm.model.SysUserDto;
import org.forbes.comm.model.UserRoleDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.utils.PasswordUtil;
import org.forbes.comm.vo.RoleVo;
import org.forbes.comm.vo.UserDeatailVo;
import org.forbes.comm.vo.UserPermissonVo;
import org.forbes.comm.vo.UserVo;
import org.forbes.dal.entity.SysUser;
import org.forbes.dal.entity.SysUserRole;
import org.forbes.dal.mapper.SysUserMapper;
import org.forbes.dal.mapper.SysUserRoleMapper;
import org.forbes.dal.mapper.ext.SysUserExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
	

	@Autowired
	SysUserExtMapper sysUserExtMapper;
	@Autowired
	SysUserRoleMapper sysUserRoleMapper;
	private static final String DEFAULT_PASSWD = "123456";
	
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

	/****
	 * 
	 */
	@Override
	public IPage<UserVo> pageUsers(IPage<UserVo> page, SysUserDto sysUserDto){
		return sysUserExtMapper.pageUsers(page, sysUserDto);
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
	
	/***
	 * 增加用户
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void addUser(SysUserDto sysUserDto){
		SysUser sysUser = new SysUser();
		BeanCopier.create(SysUserDto.class,SysUser.class ,false)
		.copy(sysUserDto, sysUser, null);
		String salt = ConvertUtils.randomGen(8);
		sysUser.setSalt(salt);
		sysUser.setPassword(DEFAULT_PASSWD);
		String passwordEncode = PasswordUtil.encrypt(sysUser.getUsername(), sysUser.getPassword(), salt);
		sysUser.setPassword(passwordEncode);
		sysUser.setStatus(UserStausEnum.NORMAL.getCode());
		baseMapper.insert(sysUser);
		/**角色关联**/
		List<UserRoleDto>  userRoleDtos = sysUserDto.getUserRoleDtos();
		if(ConvertUtils.isNotEmpty(userRoleDtos)){
			Long userId = sysUser.getId();
			userRoleDtos.stream().forEach(userRoleDto -> {
				SysUserRole  sysUserRole = new SysUserRole();
				sysUserRole.setRoleId(userRoleDto.getRoleId());
				sysUserRole.setUserId(userId);
				sysUserRoleMapper.insert(sysUserRole);
			});
		}
	}
}
