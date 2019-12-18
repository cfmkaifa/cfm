package org.forbes.servcie;

import org.forbes.comm.model.RemoteSysUserDto;
import org.forbes.comm.vo.Result;
/***
 * ISysUserService概要说明：用户外部接口
 * @author Huanghy
 */
public interface ISysUserService{

	
	
	/***
	 * registerUser方法慨述:
	 * @param sysUser
	 * @param roleCode
	 * @return Result<RemoteSysUserDto>
	 * @创建人 huanghy
	 * @创建时间 2019年12月16日 上午9:28:49
	 * @修改人 (修改了该文件，请填上修改人的名字)
	 * @修改日期 (请填上修改该文件时的日期)
	 */
	public Result<RemoteSysUserDto> registerUser(RemoteSysUserDto sysUser,String roleCode);
}
