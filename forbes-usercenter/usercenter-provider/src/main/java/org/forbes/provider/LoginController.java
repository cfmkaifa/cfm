package org.forbes.provider;

import javax.validation.Valid;

import org.forbes.biz.ISysUserService;
import org.forbes.comm.constant.CommonConstant;
import org.forbes.comm.model.SysLoginModel;
import org.forbes.comm.utils.JwtUtil;
import org.forbes.comm.utils.PasswordUtil;
import org.forbes.comm.vo.LoginVo;
import org.forbes.comm.vo.Result;
import org.forbes.config.RedisUtil;
import org.forbes.dal.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * @author scott
 * @since 2018-12-17
 */
@RestController
@RequestMapping("/sys")
@Api(tags={"用户登录"})
@Slf4j
public class LoginController {
	
	@Autowired
	private ISysUserService sysUserService;

	
	@Autowired
    private RedisUtil redisUtil;

	/***
	 * login方法慨述:
	 * @param sysLoginModel
	 * @return Result<JSONObject>
	 * @创建人 huanghy
	 * @创建时间 2019年5月27日 下午6:08:18
	 * @修改人 (修改了该文件，请填上修改人的名字)
	 * @修改日期 (请填上修改该文件时的日期)
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ApiOperation("登录")
	@ApiResponses(value={
			@ApiResponse(code=500,message=Result.LOGIN_NOT_USER_ERROR_MSG),
			@ApiResponse(code=200,response=LoginVo.class, message = Result.LOGIN_MSG)
	})
	public Result<LoginVo> login(@RequestBody @Valid SysLoginModel sysLoginModel,
			BindingResult bindingResult) {
		Result<LoginVo> result = new Result<LoginVo>();
		String username = sysLoginModel.getUsername();
		String password = sysLoginModel.getPassword();
		SysUser sysUser = sysUserService.getUserByName(username);
		if(sysUser==null) {
			result.error500(Result.LOGIN_NOT_USER_ERROR_MSG);
			return result;
		}else {
			//密码验证
			String userpassword = PasswordUtil.encrypt(username, password, sysUser.getSalt());
			String syspassword = sysUser.getPassword();
			if(!syspassword.equals(userpassword)) {
				result.error500(Result.LOGIN_PASS_ERROR_MSG);
				return result;
			}
			//生成token
			String token = JwtUtil.sign(username, syspassword);
			redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
			 //设置超时时间
			redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME/1000);
			LoginVo obj = new LoginVo();
			obj.setToken(token);
			obj.setUserInfo(sysUser);
			result.setResult(obj);
			result.success(Result.LOGIN_MSG);
		}
		return result;
	}
}
