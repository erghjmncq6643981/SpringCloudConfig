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

package com.kyle.security.config;

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

import com.kyle.security.service.PathMatcherService;
import com.kyle.security.service.impl.UserServiceImpl;
import com.kyle.security.source.MyAccessDecisionManager;
import com.kyle.security.source.MyFilterInvocationSecurityMetadataSource;

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
		// super.configure(auth);
		// auth.inMemoryAuthentication().withUser("user").password("password").roles("USER").and().withUser("admin")
		// .password("password").roles("USER", "ADMIN");
		// 重写默认的用户查询功能
		// auth.jdbcAuthentication().dataSource(dataSource)
		// .usersByUsernameQuery("select username, password, enabled " + "from
		// users where username=?")
		// .authoritiesByUsernameQuery("select username, authority from
		// authorities where username=?");
		auth.userDetailsService(userService);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// http.authorizeRequests().antMatchers("/security")
		// .access("hasRole('ROLE_SECURITY') and
		// hasIpAddress('192.168.1.2')")
		// .hasRole("USER").antMatchers(HttpMethod.GET,
		// "/security").hasRole("USER").anyRequest().permitAll().and()
		// .requiresChannel().antMatchers("/security/form").requiresSecure();
		// HTTP Basic
		// http.authorizeRequests().antMatchers("/security").hasRole("USER").and().formLogin().and().httpBasic();
		http.authorizeRequests().anyRequest().authenticated()
				.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
					@Override
					public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
						fsi.setSecurityMetadataSource(mySecurityMetadataSource());
						fsi.setAccessDecisionManager(myAccessDecisionManager());
						return fsi;
					}
				}).and().formLogin().and().httpBasic();

	}

	@Bean
	public FilterInvocationSecurityMetadataSource mySecurityMetadataSource() {
		MyFilterInvocationSecurityMetadataSource securityMetadataSource = new MyFilterInvocationSecurityMetadataSource(
				pathMatcherService);
		return securityMetadataSource;
	}

	@Bean
	public AccessDecisionManager myAccessDecisionManager() {
		return new MyAccessDecisionManager();
	}
}
