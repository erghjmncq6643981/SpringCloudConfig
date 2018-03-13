/*
 * AuthoritiesMapper.java
 * 2018年3月12日 上午9:31:41
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

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kyle.config.server.entity.AuthoritiesEntity;

/**
 * 类功能描述
 *
 * @version
 * @author kyle 2018年3月12日上午9:31:41
 * @since 1.8
 */
@Repository
public interface AuthoritiesMapper {

	/**
	 * @param username
	 * @return List<AuthoritiesEntry>
	 * @Description: 根据用户名查询权限
	 * @create date 2018年3月12日上午9:32:47
	 */
	public List<AuthoritiesEntity> findByUsername(String username);
}
