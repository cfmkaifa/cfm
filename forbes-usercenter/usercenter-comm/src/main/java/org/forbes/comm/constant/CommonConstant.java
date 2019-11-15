package org.forbes.comm.constant;
/***
 * CommonConstant概要说明：常用常量
 * @author Huanghy
 */
public interface CommonConstant {
	
	
	/*******http状态码********/
	public static final String X_ACCESS_TOKEN = "X-Access-Token";
	/** {@code 500 Server Error} (HTTP/1.0 - RFC 1945) */
    public static final Integer SC_INTERNAL_SERVER_ERROR_500 = 500;
    /** {@code 200 OK} (HTTP/1.0 - RFC 1945) */
    public static final Integer SC_OK_200 = 200;
    /***********/
    public static String PREFIX_USER_ROLE = "PREFIX_USER_ROLE";
    public static String PREFIX_USER_PERMISSION  = "PREFIX_USER_PERMISSION ";
    public static int  TOKEN_EXPIRE_TIME  = 3600; //3600秒即是一小时
    public static String PREFIX_USER_TOKEN  = "PREFIX_USER_TOKEN ";
    /****默认空值
     */
    public static String DEFAULT_EMPTY = "";
    public static final String USER_NAME = "username";
}
