package org.forbes.comm.vo;

import java.io.Serializable;

import org.forbes.comm.constant.CommonConstant;

import lombok.Data;
/***
 * Result概要说明：统一返回错误
 * @author Huanghy
 */
@Data
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 成功标志
	 */
	private boolean success = true;

	/**
	 * 返回处理消息
	 */
	private String message = "操作成功！";
	/******登录相关提示信息****/
	public static final  String LOGIN_MSG = "登录成功";
	public static final  String LOGIN_PASS_ERROR_MSG = "用户名或密码错误";
	public static final  String LOGIN_NOT_USER_ERROR_MSG = "该用户不存在";
	public static final  String LOGOUT_SUCCESS_MSG = "退出登录成功！";

	/******修改用户状态提示信息****/
	public static final  String UPDATE_STATUS_MSG = "状态修改成功";
	public static final  String UPDATE_STATUS_ERROR_MSG = "状态修改失败";

	/******添加用户提示信息****/
	public static final  String ADD_USER_MSG="添加用户成功";
	public static final  String ADD_USER_ERROR_MSG="添加用户失败";

	/******修改用户提示信息****/
	public static final  String UPDATE_USER_MSG="修改用户成功";
	public static final  String UPDATE_USER_ERROR_MSG="修改用户失败";

	/******用户详情****/
	public static final  String DETAIL_USER_MSG="查询用户详情成功";
	public static final  String DETAIL_USER_ERROR_MSG="查询用户详情失败";
	public static final  String DETAIL_USER_EMPTY_MSG="未查询到该用户";

	/******查询角色****/
	public static final  String ROLE_MSG="查询角色成功";
	public static final  String ROLE_ERROR_MSG="查询角色失败";
	public static final  String ROLE_EMPTY_MSG="未查询到该用户的角色";

	/******添加角色信息****/
	public static final  String ADD_ROLE_MSG="添加角色成功";
	public static final  String ADD_ROLE_ERROR_MSG="添加角色失败";

	/**
	 * 返回代码
	 */
	private Integer code = CommonConstant.SC_OK_200;
	
	/**
	 * 返回数据对象 data
	 */
	private T result;

	public Result() {
		
	}
	
	/**
	 * 时间戳
	 */
	private long timestamp = System.currentTimeMillis();

	public void error500(String message) {
		this.message = message;
		this.code = CommonConstant.SC_INTERNAL_SERVER_ERROR_500;
		this.success = false;
	}

	public void success(String message) {
		this.message = message;
		this.code = CommonConstant.SC_OK_200;
		this.success = true;
	}
	
	public static Result<Object> error(String msg) {
		return error(CommonConstant.SC_INTERNAL_SERVER_ERROR_500, msg);
	}
	
	public static Result<Object> error(int code, String msg) {
		Result<Object> r = new Result<Object>();
		r.setCode(code);
		r.setMessage(msg);
		r.setSuccess(false);
		return r;
	}
	
	public static Result<Object> ok(String msg) {
		Result<Object> r = new Result<Object>();
		r.setSuccess(true);
		r.setCode(CommonConstant.SC_OK_200);
		r.setMessage(msg);
		return r;
	}
	
	public static Result<Object> ok(Object data) {
		Result<Object> r = new Result<Object>();
		r.setSuccess(true);
		r.setCode(CommonConstant.SC_OK_200);
		r.setResult(data);
		return r;
	}
}

