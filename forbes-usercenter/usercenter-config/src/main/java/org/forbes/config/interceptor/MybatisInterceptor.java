package org.forbes.config.interceptor;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.ArrayUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.forbes.comm.utils.ConvertUtils;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class MybatisInterceptor implements Interceptor {

	
	public static final String CREATE_BY = "createBy";
	public static final String CREATE_BY_TABLE = "create_by";
	public static final String CREATE_TIME_TABLE = "create_time";
	public static final String CREATE_TIME = "createTime";
	/***默认用户
	 */
	private static final String DEFAULT_SYS_USER = "system";
	private static final String DEFAULT_EMPTY = "";
	
	/***
	 * 
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		String sqlId = mappedStatement.getId();
		log.debug("------sqlId------" + sqlId);
		SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
		Object parameter = invocation.getArgs()[1];
		log.debug("------sqlCommandType------" + sqlCommandType);
		if (parameter == null) {
			return invocation.proceed();
		}
	}
	
	
	/***
	 * receFields方法慨述:
	 * @param obj
	 * @return List<Field>
	 * @创建人 huanghy
	 * @创建时间 2019年11月26日 上午8:45:10
	 * @修改人 (修改了该文件，请填上修改人的名字)
	 * @修改日期 (请填上修改该文件时的日期)
	 */
	private List<Field> receFields(Object obj){
		Field[] fields = (Field[]) ArrayUtils.addAll(obj.getClass().getDeclaredFields(), obj.getClass().getSuperclass().getDeclaredFields());
		return Arrays.asList(fields);
	}
	
	private void insertInvocation(Object parameter,List<Field> fields){
		
		List<Field>
		for (Field field : fields) {
			log.debug("------field.name------" + field.getName());
			try {
				if (CREATE_BY.equals(field.getName())) {
					field.setAccessible(true);
					Object local_createBy = field.get(parameter);
					field.setAccessible(false);
					if(ConvertUtils.isEmpty(local_createBy)){
						field.setAccessible(true);
						field.set(parameter, DEFAULT_SYS_USER);
						field.setAccessible(false);
					}
				}
				// 注入创建时间
				if (CREATE_TIME.equals(field.getName())) {
					field.setAccessible(true);
					Object local_createDate = field.get(parameter);
					field.setAccessible(false);
					if(ConvertUtils.isEmpty(local_createDate)){
						field.setAccessible(true);
						field.set(parameter, new Date());
						field.setAccessible(false);
					}
				}
			} catch (Exception e) {
			}
		}
	
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties arg0) {
	}

}
