/*
 * MySecurityConfig.java
 * 2018年3月10日 下午2:18:39
 * Copyright 2017 Fosun Financial. All  Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Please contact Fosun Corporation or visit
 * www.fosun.com
 * if you need additional information or have any questions.
 * @author kyle
 * @version 1.0
 */

package com.kyle.config.server.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.kyle.config.server.service.PathMatcherService;
import com.kyle.config.server.service.impl.UserServiceImpl;
import com.kyle.config.server.source.MyAccessDecisionManager;
import com.kyle.config.server.source.MyFilterInvocationSecurityMetadataSource;

/**
 * 类功能描述
 *
 * @version
 * @author kyle 2018年3月10日下午2:18:39
 * @since 1.8
 */
@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private PathMatcherService pathMatcherService;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated()
				.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
					@Override
					public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
						fsi.setSecurityMetadataSource(mySecurityMetadataSource());
						fsi.setAccessDecisionManager(myAccessDecisionManager());
						return fsi;
					}
				}).and().formLogin().and().httpBasic().and().csrf().disable();

	}

	/**
	 * @return securityMetadataSource
	 * @Description:
	 * @create date 2018年3月13日上午10:55:21
	 */
	@Bean
	public FilterInvocationSecurityMetadataSource mySecurityMetadataSource() {
		MyFilterInvocationSecurityMetadataSource securityMetadataSource = new MyFilterInvocationSecurityMetadataSource(
				pathMatcherService);
		return securityMetadataSource;
	}

	/**
	 * @return MyAccessDecisionManager
	 * @Description:
	 * @create date 2018年3月13日上午10:55:34
	 */
	@Bean
	public AccessDecisionManager myAccessDecisionManager() {
		return new MyAccessDecisionManager();
	}
}
