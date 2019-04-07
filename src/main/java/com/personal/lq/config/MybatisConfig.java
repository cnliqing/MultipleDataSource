package com.personal.lq.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

//@Configuration
public class MybatisConfig {

	@Autowired
	DataSource dataSource;
	
	@Value("${mybatis.type-aliases-package}")
	private String typeAliasesPackage;
	
	@Value("${mybatis.mapper-locations}")
	private String mapperLocations;
	
//	@Value("${mybatis.mapper-locations}")
//	private String basePackage;
	
	
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws Exception {
		
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] mapperXmlResource = resolver.getResources(mapperLocations);
		
		sqlSessionFactory.setDataSource(dataSource);
		sqlSessionFactory.setMapperLocations(mapperXmlResource);
		sqlSessionFactory.setTypeAliasesPackage(typeAliasesPackage);
		return sqlSessionFactory;
	}
	
//	@Bean
//	public MapperScannerConfigurer mapperScannerConfigurer() {
//		
//		MapperScannerConfigurer msc = new MapperScannerConfigurer();
//		
//		msc.setBasePackage("boot.dao");
//		msc.setSqlSessionFactoryBeanName("sqlSessionFactory");
//		
//		return msc;
//		
//	}

}
