/*
 * MybatisConfig.java
 * 2017年7月15日 下午2:29:33
 * Copyright 2017 Fosun Financial. All  Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Please contact Fosun Corporation or visit
 * www.fosun.com
 * if you need additional information or have any questions.
 * @author kyle
 * @version 1.0
 */

package com.kyle.security.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * 类功能描述:springboot集成mybatis的基本入口 1）创建数据源 2）创建SqlSessionFactory
 *
 * @version
 * @author kyle 2017年7月15日下午2:29:33
 * @since 1.8
 */

@Configuration // 该注解类似于spring配置文件
@MapperScan(basePackages = "com.kyle.security.mapper")
public class MybatisConfig {

	@Autowired
	private Environment env;

	/**
	 * @param dataSource
	 * @return SqlSessionFactory
	 * @throws Exception
	 * @Description: 根据数据源创建SqlSessionFactory
	 * @create date 2018年3月10日下午4:55:40
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
		fb.setDataSource(dataSource);// 指定数据源(这个必须有，否则报错)
		// 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
		fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));// 指定基包
		fb.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));// 指定xml文件位置
		return fb.getObject();
	}

}
