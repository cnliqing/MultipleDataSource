package com.personal.lq.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@MapperScan(basePackages = "com.personal.lq.mapper", sqlSessionTemplateRef = "db2SqlSessionTemplate")
public class DataSource2Config {

	@Value("${spring.datasource.db2.url}")
    private String dbUrl;
 
    @Value("${spring.datasource.db2.username}")
    private String username;
 
    @Value("${spring.datasource.db2.password}")
    private String password;
 
    @Value("${spring.datasource.db2.driver-class-name}")
    private String driverClassName;
 
    @Value("${spring.datasource.druid.initial-size}")
	private int initialSize;

    @Value("${spring.datasource.druid.min-idle}")
    private int minIdle;
 
    @Value("${spring.datasource.druid.max-active}")
    private int maxActive;
 
    @Value("${spring.datasource.druid.max-wait}")
    private int maxWait;
 
    @Value("${spring.datasource.druid.time-between-eviction-runs-millis}")
    private int timeBetweenEvictionRunsMillis;
 
    @Value("${spring.datasource.druid.min-evictable-idle-time-millis}")
    private int minEvictableIdleTimeMillis;
 
    @Value("${spring.datasource.druid.validation-query}")
    private String validationQuery;
 
    @Value("${spring.datasource.druid.test-while-idle}")
    private boolean testWhileIdle;
 
    @Value("${spring.datasource.druid.test-on-borrow}")
    private boolean testOnBorrow;
 
    @Value("${spring.datasource.druid.test-on-return}")
    private boolean testOnReturn;
 
    @Value("${spring.datasource.druid.pool-prepared-statements}")
    private boolean poolPreparedStatements;
 
    @Value("${spring.datasource.druid.max-pool-prepared-statement-per-connection-size}")
    private int maxPoolPreparedStatementPerConnectionSize;
 
    @Value("${spring.datasource.druid.filters}")
    private String filters;
    
    @Bean
    public DataSource db2DataSource() {
    	DruidDataSource datasource = new DruidDataSource();
		 
        datasource.setUrl(dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
 
        /** configuration */
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(filters);
        } catch (Exception e) {
            System.out.println("druid configuration initialization filter\n" + e);
        }
        return datasource;
    }
 
    @Bean
    public SqlSessionFactory db2SqlSessionFactory(@Qualifier("db2DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }
 
    @Bean
    public DataSourceTransactionManager db2TransactionManager(@Qualifier("db2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    
    @Bean
    public JdbcTemplate db2JdbcTemplate(@Qualifier("db2DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
 
    @Bean
    public SqlSessionTemplate db2SqlSessionTemplate(@Qualifier("db2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
