/*
 * UserService.java
 * 2018年3月11日 上午10:29:02
 * Copyright 2017 Fosun Financial. All  Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Please contact Fosun Corporation or visit
 * www.fosun.com
 * if you need additional information or have any questions.
 * @author kyle
 * @version 1.0
 */

package com.kyle.security.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kyle.security.dao.AuthoritiesDao;
import com.kyle.security.dao.UserDao;
import com.kyle.security.entity.AuthoritiesEntity;
import com.kyle.security.entity.UserEntity;
import com.kyle.security.service.UserService;

/**
 * 类功能描述
 *
 * @version
 * @author kyle 2018年3月11日上午10:29:02
 * @since 1.8
 */
@Service
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private AuthoritiesDao authoritiesDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 查找有效用户
		UserEntity userEntry = findByUsername(username);
		if (userEntry != null) {
			// 查找用户权限
			List<AuthoritiesEntity> authoritiesEntries = authoritiesDao.findByUsername(username);
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(authoritiesEntries.size());
			for (int i = 0; i < authoritiesEntries.size(); i++) {
				authorities.add(new SimpleGrantedAuthority(authoritiesEntries.get(i).getAuthority()));
			}
			return new User(userEntry.getUsername(), userEntry.getPassword(), authorities);
		}
		throw new UsernameNotFoundException("User '" + username + "' not found");
	}

	@Override
	public UserEntity findByUsername(String username) {
		return userDao.findByUsername(username);
	}
}
