package org.forbes.config;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;

import io.seata.rm.datasource.DataSourceProxy;
import io.seata.spring.annotation.GlobalTransactionScanner;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Configuration
@ConditionalOnProperty(value="seata.auto.enabled",matchIfMissing=true)
public class SeataAutoConfig {
	
	
	@Value("${spring.datasource.dynamic.datasource.master.url}")
	private String datasourceUrl;
	@Value("${spring.datasource.dynamic.datasource.master.username}")
	private String datasourceUsername;
	@Value("${spring.datasource.dynamic.datasource.master.password}")
	private String datasourcePassword;
	@Value("${spring.datasource.dynamic.datasource.master.driver-class-name}")
	private String datasourceDriverClassName;
	@Value("${spring.datasource.dynamic.druid.maxWait}")
	private Long datasourceMaxWait;
	@Value("${spring.datasource.dynamic.druid.maxActive}")
	private Integer datasourceMaxActive;
	
	@Value("${spring.datasource.dynamic.druid.initial-size}")
	private Integer datasourceInitialSize;
	
	@Value("${spring.datasource.dynamic.druid.min-idle}")
	private Integer datasourceMinIdle;
	
	
	
	
	@Bean(name = "druidDataSource") // 声明其为Bean实例
	public DataSource druidDataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUrl(datasourceUrl);
		druidDataSource.setUsername(datasourceUsername);
		druidDataSource.setPassword(datasourcePassword);
		druidDataSource.setDriverClassName(datasourceDriverClassName);
		druidDataSource.setInitialSize(datasourceInitialSize);
		druidDataSource.setMaxActive(datasourceMaxActive);
		druidDataSource.setMaxWait(datasourceMaxWait);
		druidDataSource.setMinIdle(datasourceMinIdle);
		druidDataSource.setValidationQuery("Select 1 from DUAL");
		druidDataSource.setTestOnBorrow(false);
		druidDataSource.setTestOnReturn(false);
		druidDataSource.setTestWhileIdle(true);
		druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
		druidDataSource.setMinEvictableIdleTimeMillis(25200000);
		druidDataSource.setRemoveAbandoned(true);
		druidDataSource.setRemoveAbandonedTimeout(1800);
		druidDataSource.setLogAbandoned(true);
		log.info("装载dataSource........");
		return druidDataSource;
	}

	/**
	 * init datasource proxy
	 * 
	 * @Param: druidDataSource datasource bean instance
	 * @Return: DataSourceProxy datasource proxy
	 */
	@Bean(name = "dataSource")
	@Primary // 在同样的DataSource中，首先使用被标注的DataSource
	public DataSourceProxy dataSourceProxy(@Qualifier(value = "druidDataSource") DruidDataSource druidDataSource) {
		log.info("代理dataSource........");
		return new DataSourceProxy(druidDataSource);
	}

	/**
	 * init global transaction scanner
	 *
	 * @Return: GlobalTransactionScanner
	 */
	@Bean
	public GlobalTransactionScanner globalTransactionScanner() {
		log.info("配置seata........");
		return new GlobalTransactionScanner("usercenter-service", "usercenter-group");
	}
}
