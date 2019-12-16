package org.forbes.api.servcie;

import org.forbes.comm.vo.Result;
import org.forbes.dal.entity.SysUser;

import com.baomidou.mybatisplus.extension.service.IService;
/***
 * ISysUserService概要说明：用户外部接口
 * @author Huanghy
 */
public interface ISysUserService extends IService<SysUser> {

	
	
	/***
	 * registerUser方法慨述:
	 * @param sysUser
	 * @param roleCode
	 * @return Result<SysUser>
	 * @创建人 huanghy
	 * @创建时间 2019年12月16日 上午9:28:49
	 * @修改人 (修改了该文件，请填上修改人的名字)
	 * @修改日期 (请填上修改该文件时的日期)
	 */
	public Result<SysUser> registerUser(SysUser sysUser,String roleCode);
}
