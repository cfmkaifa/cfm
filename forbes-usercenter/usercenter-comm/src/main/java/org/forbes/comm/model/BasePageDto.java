package org.forbes.comm.model;

import lombok.Data;

/***
 * BasePageDto概要说明：页码抽象类
 * @author Huanghy
 */
@Data
public class BasePageDto<T> {

	/***当前页码
	 */
	private Integer pageNo;
	/**每页显示记录数**/
	private Integer pageSize;
	/**当前实体对象**/
	T  data;
	
}
