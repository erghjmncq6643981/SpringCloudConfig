/*
 * MyAccessDecisionManager.java
 * 2018年3月12日 下午5:24:33
 * Copyright 2017 Fosun Financial. All  Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Please contact Fosun Corporation or visit
 * www.fosun.com
 * if you need additional information or have any questions.
 * @author kyle
 * @version 1.0
 */

package com.kyle.config.server.source;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.CollectionUtils;

/**
 * 类功能描述
 *
 * @version
 * @author kyle 2018年3月12日下午5:24:33
 * @since 1.8
 */
public class MyAccessDecisionManager implements AccessDecisionManager {

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		if (CollectionUtils.isEmpty(configAttributes)) {
			throw new AccessDeniedException("not allow");
		}
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		while (ite.hasNext()) {
			ConfigAttribute ca = ite.next();
			String needRole = ((SecurityConfig) ca).getAttribute();
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				if (ga.getAuthority().equals(needRole)) {
					// 匹配到有对应角色,则允许通过
					return;
				}
			}
		}
		// 该url有配置权限,但是当然登录用户没有匹配到对应权限,则禁止访问
		throw new AccessDeniedException("not allow");

	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
