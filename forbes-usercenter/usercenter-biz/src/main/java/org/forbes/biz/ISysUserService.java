package org.forbes.biz;

import org.forbes.dal.entity.SysUser;

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
}
