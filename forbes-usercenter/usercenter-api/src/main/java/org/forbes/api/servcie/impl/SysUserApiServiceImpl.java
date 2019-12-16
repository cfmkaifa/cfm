package org.forbes.api.servcie.impl;

import org.apache.dubbo.config.annotation.Service;
import org.forbes.api.servcie.ISysUserService;
import org.forbes.comm.constant.CommonConstant;
import org.forbes.comm.constant.DataColumnConstant;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.enums.UserStausEnum;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.utils.PasswordUtil;
import org.forbes.comm.vo.Result;
import org.forbes.dal.entity.SysRole;
import org.forbes.dal.entity.SysUser;
import org.forbes.dal.entity.SysUserRole;
import org.forbes.dal.mapper.SysRoleMapper;
import org.forbes.dal.mapper.SysUserMapper;
import org.forbes.dal.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
/***
 * SysUserServiceImpl概要说明：用户注册
 * @author Huanghy
 */
@Service(version = "1.0.0",interfaceClass =ISysUserService.class )
public class SysUserApiServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
	
	
	@Autowired
	SysRoleMapper sysRoleMapper;
	@Autowired
	SysUserRoleMapper  sysUserRoleMapper;
	
	/***
	 * 增加用户
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Result<SysUser>  registerUser(SysUser sysUser,String roleCode){
		Result<SysUser>  result = new Result<SysUser>();
		String salt = ConvertUtils.randomGen(8);
		sysUser.setSalt(salt);
		if(ConvertUtils.isEmpty(sysUser.getPassword())){
			sysUser.setPassword(CommonConstant.DEFAULT_PASSWD);
		}
		String passwordEncode = PasswordUtil.encrypt(sysUser.getUsername(), sysUser.getPassword(), salt);
		sysUser.setPassword(passwordEncode);
		sysUser.setStatus(UserStausEnum.NORMAL.getCode());
		baseMapper.insert(sysUser);
		/***角色编码是否存在
		 * **/
		SysRole sysRole = sysRoleMapper.selectOne(new QueryWrapper<SysRole>()
				.eq(DataColumnConstant.ROLE_CODE, roleCode));
		if(ConvertUtils.isEmpty(sysRole)){
			result.setBizCode(BizResultEnum.ROLE_CODE_NOT_EXIST.getBizCode());
			result.setMessage(String.format(BizResultEnum.ROLE_CODE_NOT_EXIST.getBizFormateMessage(), roleCode));
			return result;
		}
		SysUserRole  sysUserRole = new SysUserRole();
		sysUserRole.setRoleId(sysRole.getId());
		sysUserRole.setUserId(sysUser.getId());
		sysUserRoleMapper.insert(sysUserRole);
		return result;
	}
	
}
