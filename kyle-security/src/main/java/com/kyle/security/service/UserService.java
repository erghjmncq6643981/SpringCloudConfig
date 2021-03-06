/*
 * UserService.java
 * 2018年3月11日 上午10:31:39
 * Copyright 2017 Fosun Financial. All  Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Please contact Fosun Corporation or visit
 * www.fosun.com
 * if you need additional information or have any questions.
 * @author kyle
 * @version 1.0
 */

package com.kyle.security.service;

import com.kyle.security.entity.UserEntity;

/**
 * 类功能描述
 * 
 * @version
 * @author kyle 2018年3月11日上午10:31:39
 * @since 1.8
 */
public interface UserService {

	/**
	 * @param username
	 * @return User
	 * @Description: 根据用户名查找
	 * @create date 2018年3月11日上午10:26:27
	 */
	public UserEntity findByUsername(String username);
}
