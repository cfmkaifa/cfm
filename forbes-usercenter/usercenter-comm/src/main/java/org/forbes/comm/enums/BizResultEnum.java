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
	ROLE_EXIST("001002001","用户角色不存在","%s对应用户角色不存在"),
	PERMISSION_EXIST("001003004","权限不存在","%s查询权限不存在");

	
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
