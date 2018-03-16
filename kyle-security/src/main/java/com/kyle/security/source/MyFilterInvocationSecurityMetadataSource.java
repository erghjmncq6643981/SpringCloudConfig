/*
 * MyFilterInvocationSecurityMetadataSource.java
 * 2018年3月12日 下午5:32:58
 * Copyright 2017 Fosun Financial. All  Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Please contact Fosun Corporation or visit
 * www.fosun.com
 * if you need additional information or have any questions.
 * @author kyle
 * @version 1.0
 */

package com.kyle.security.source;

import java.util.Collection;
import java.util.List;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import com.kyle.security.entity.PathMatcherEntity;
import com.kyle.security.service.PathMatcherService;

/**
 * 类功能描述
 *
 * @version
 * @author kyle 2018年3月12日下午5:32:58
 * @since 1.8
 */
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private final AntPathMatcher antPathMatcher = new AntPathMatcher();

	private PathMatcherService pathMatcherService;

	public MyFilterInvocationSecurityMetadataSource() {

	}

	public MyFilterInvocationSecurityMetadataSource(PathMatcherService pathMatcherService) {
		this.pathMatcherService = pathMatcherService;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		FilterInvocation fi = (FilterInvocation) object;
		String url = fi.getRequestUrl();
		// String httpMethod = fi.getRequest().getMethod();
		// for (Map.Entry<String, String> entry : urlRoleMap.entrySet()) {
		// if (antPathMatcher.match(entry.getKey(), url)) {
		// return SecurityConfig.createList(entry.getValue());
		// }
		// }
		List<PathMatcherEntity> PathMatcherEntities = pathMatcherService.findAllPathMatcer();
		for (int i = 0; i < PathMatcherEntities.size(); i++) {
			String path = PathMatcherEntities.get(i).getPath();
			String authority = PathMatcherEntities.get(i).getAuthority();
			if (antPathMatcher.match(path, url)) {
				return SecurityConfig.createList(authority);
			}
		}
		// 没有匹配到,默认是要登录才能访问
		return SecurityConfig.createList("ROLE_USER");
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

}
