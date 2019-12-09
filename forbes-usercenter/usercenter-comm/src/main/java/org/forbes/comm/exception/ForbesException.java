package org.forbes.comm.exception;
/***
 * ForbesException概要说明：
 * @author Huanghy
 */
public class ForbesException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	/***错误编码
	 */
	private String errorCode;
	/**错误描述**/
	private String errorMsg;

	public ForbesException(String message){
		super(message);
	}
	
	/***
	 * 
	 * 构造函数:
	 * @param errorCode
	 * @param message
	 */
	public ForbesException(String errorCode,String message){
		super(message);
		this.errorCode = errorCode;
		this.errorMsg = message;
	}
	
	public ForbesException(Throwable cause)
	{
		super(cause);
	}
	
	public ForbesException(String message,Throwable cause)
	{
		super(message,cause);
	}

	/** 
	 * @return errorCode 
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/** 
	 * @return errorMsg 
	 */
	public String getErrorMsg() {
		return errorMsg;
	}
	
}