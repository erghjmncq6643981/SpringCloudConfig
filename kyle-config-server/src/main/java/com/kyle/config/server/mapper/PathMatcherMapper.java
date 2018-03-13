/*
 * PathMatcherMapper.java
 * 2018年3月13日 上午9:07:15
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

import com.kyle.config.server.entity.PathMatcherEntity;

/**
 * 类功能描述
 *
 * @version
 * @author kyle 2018年3月13日上午9:07:15
 * @since 1.8
 */
@Repository
public interface PathMatcherMapper {

	/**
	 * @return List<PathMatcherEntity>
	 * @Description: 查询所有的url匹配规则
	 * @create date 2018年3月13日上午9:08:00
	 */
	public List<PathMatcherEntity> findAllPathMatcer();
}
