package org.forbes.servcie.impl;

import org.apache.dubbo.config.annotation.Service;
import org.forbes.comm.constant.CommonConstant;
import org.forbes.comm.constant.DataColumnConstant;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.enums.UserStausEnum;
import org.forbes.comm.model.RemoteSysUserDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.utils.PasswordUtil;
import org.forbes.comm.vo.Result;
import org.forbes.dal.entity.SysRole;
import org.forbes.dal.entity.SysUser;
import org.forbes.dal.entity.SysUserRole;
import org.forbes.dal.mapper.SysRoleMapper;
import org.forbes.dal.mapper.SysUserMapper;
import org.forbes.dal.mapper.SysUserRoleMapper;
import org.forbes.servcie.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.sf.cglib.beans.BeanCopier;
/***
 * SysUserServiceImpl概要说明：用户注册
 * @author Huanghy
 */
@Service(version = "1.0.0",interfaceClass =ISysUserService.class )
public class SysUserApiServiceImpl  implements ISysUserService{
	
	@Autowired
	SysUserMapper  sysUserMapper;
	@Autowired
	SysRoleMapper sysRoleMapper;
	@Autowired
	SysUserRoleMapper  sysUserRoleMapper;
	
	/***
	 * 增加用户
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Result<RemoteSysUserDto>  registerUser(RemoteSysUserDto remoteSysUserDto,String roleCode){
		Result<RemoteSysUserDto>  result = new Result<RemoteSysUserDto>();
		/***角色编码是否存在
		 * **/
		SysRole sysRole = sysRoleMapper.selectOne(new QueryWrapper<SysRole>()
				.eq(DataColumnConstant.ROLE_CODE, roleCode));
		if(ConvertUtils.isEmpty(sysRole)){
			result.setBizCode(BizResultEnum.ROLE_CODE_NOT_EXIST.getBizCode());
			result.setMessage(String.format(BizResultEnum.ROLE_CODE_NOT_EXIST.getBizFormateMessage(), roleCode));
			return result;
		}
		String username = remoteSysUserDto.getUsername();
		int userCount = sysUserMapper.selectCount(new QueryWrapper<SysUser>()
				.eq(DataColumnConstant.USERNAME,username)
				.or()
				.eq(DataColumnConstant.PHONE,username));
		if(userCount > 0 ){
			result.setBizCode(BizResultEnum.USER_NAME_EXISTS.getBizCode());
			result.setMessage(String.format(BizResultEnum.USER_NAME_EXISTS.getBizFormateMessage(), username));
			return result;
		}
		SysUser sysUser = new SysUser();
		BeanCopier.create(RemoteSysUserDto.class, SysUser.class,false)
		.copy(remoteSysUserDto, sysUser, null);
		String salt = ConvertUtils.randomGen(8);
		sysUser.setSalt(salt);
		if(ConvertUtils.isEmpty(sysUser.getPassword())){
			sysUser.setPassword(CommonConstant.DEFAULT_PASSWD);
		}
		String passwordEncode = PasswordUtil.encrypt(sysUser.getUsername(), sysUser.getPassword(), salt);
		sysUser.setPassword(passwordEncode);
		sysUser.setStatus(UserStausEnum.NORMAL.getCode());
		sysUserMapper.insert(sysUser);
		Long userId = sysUser.getId();
		SysUserRole  sysUserRole = new SysUserRole();
		sysUserRole.setRoleId(sysRole.getId());
		sysUserRole.setUserId(userId);
		sysUserRoleMapper.insert(sysUserRole);
		remoteSysUserDto.setId(userId);
		remoteSysUserDto.setPassword(passwordEncode);
		remoteSysUserDto.setSalt(salt);
		result.setResult(remoteSysUserDto);
		return result;
	}
	
}
