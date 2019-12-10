package org.forbes.comm.enums;
/***
 * BizResultEnum概要说明：业务系统错误代码
 * @author Huanghy
 */
public enum BizResultEnum {
	/***
	 * 001-用户中心系统
	 * 功能暂无-表示通用异常
	 * 001-为空判断
	 */
	EMPTY("001001","参数为空","%s为空"),
	USER_NAME_EXISTS("001001002","用户名已经存在","%s对应用户已经存在"),
	USER_NAME_NOT_EXISTS("001001003","用户不存在","%s对应用户不存在"),
	USER_EMAIL_EXISTS("001001004","邮箱已存在","%s对应邮箱已存在"),
	PHONE_EXISTS("001001005","电话已存在","%s对应电话已存在"),
	/*******角色******/
	ROLE_CODE_EXIST("001002001","角色编码存在","%s对应角色编码存在"),
	ROLE_EXIST("001002002","用户角色不存在","%s对应用户角色不存在"),
	ROLE_ID_NOT_EXIST("001002003","角色id不存在","%s对应角色id不存在"),
	/**权限**/
	PERMISSION_EXIST("001003001","权限不存在","%s查询权限不存在"),
	PERMISSION_TYPE_NO_EXISTS("001003002","权限类型不对应","%s对应权限类型不存在"),
	PERMISSION_PARENT_NO_EXISTS("001003003","对应父级权限不存在","%s请选择对应父级权限");

	
	/**错误编码业务系统代码+功能编码+错误代码**/
	private String bizCode;
	/**错误描述****/
	private String bizMessage;
	/**带格式错误描述****/
	private String bizFormateMessage;

	/***
	 * 构造函数:
	 * @param bizCode
	 * @param bizMessage
	 * @param bizFormateMessage
	 */
	BizResultEnum(String bizCode,String bizMessage,String bizFormateMessage){
		this.bizCode = bizCode;
		this.bizMessage = bizMessage;
		this.bizFormateMessage = bizFormateMessage;
	}

	/** 
	 * @return bizCode 
	 */
	public String getBizCode() {
		return bizCode;
	}

	/** 
	 * @param bizCode 要设置的 bizCode 
	 */
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	/** 
	 * @return bizMessage 
	 */
	public String getBizMessage() {
		return bizMessage;
	}

	/** 
	 * @param bizMessage 要设置的 bizMessage 
	 */
	public void setBizMessage(String bizMessage) {
		this.bizMessage = bizMessage;
	}

	/** 
	 * @return bizFormateMessage 
	 */
	public String getBizFormateMessage() {
		return bizFormateMessage;
	}

	/** 
	 * @param bizFormateMessage 要设置的 bizFormateMessage 
	 */
	public void setBizFormateMessage(String bizFormateMessage) {
		this.bizFormateMessage = bizFormateMessage;
	}
}
