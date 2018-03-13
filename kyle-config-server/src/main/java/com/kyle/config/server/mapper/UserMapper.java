/*
 * UserMapper.java
 * 2018年3月11日 上午10:25:14
 * Copyright 2017 Fosun Financial. All  Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Please contact Fosun Corporation or visit
 * www.fosun.com
 * if you need additional information or have any questions.
 * @author kyle
 * @version 1.0
 */

package com.kyle.config.server.mapper;

import org.springframework.stereotype.Repository;

import com.kyle.config.server.entity.UserEntity;

/**
 * 类功能描述
 *
 * @version
 * @author kyle 2018年3月11日上午10:25:14
 * @since 1.8
 */
@Repository
public interface UserMapper {

	/**
	 * @param username
	 * @return User
	 * @Description: 根据用户名查找
	 * @create date 2018年3月11日上午10:26:27
	 */
	public UserEntity findByUsername(String username);
}
