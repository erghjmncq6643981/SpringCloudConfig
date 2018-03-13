/*
 * UserDao.java
 * 2018年3月11日 上午10:27:07
 * Copyright 2017 Fosun Financial. All  Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Please contact Fosun Corporation or visit
 * www.fosun.com
 * if you need additional information or have any questions.
 * @author kyle
 * @version 1.0
 */

package com.kyle.config.server.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kyle.config.server.entity.UserEntity;
import com.kyle.config.server.mapper.UserMapper;

/**
 * 类功能描述
 *
 * @version
 * @author kyle 2018年3月11日上午10:27:07
 * @since 1.8
 */
@Repository
public class UserDao {

	@Autowired
	private UserMapper userMapper;

	/**
	 * @param username
	 * @return User
	 * @Description: 根据用户名查找
	 * @create date 2018年3月11日上午10:28:18
	 */
	public UserEntity findByUsername(String username) {
		return userMapper.findByUsername(username);
	}
}
