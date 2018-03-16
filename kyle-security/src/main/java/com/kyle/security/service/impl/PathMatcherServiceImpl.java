/*
 * PathMatcherServiceImpl.java
 * 2018年3月13日 上午9:10:49
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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyle.security.dao.PathMatcherDao;
import com.kyle.security.entity.PathMatcherEntity;
import com.kyle.security.service.PathMatcherService;

/**
 * 类功能描述
 *
 * @version
 * @author kyle 2018年3月13日上午9:10:49
 * @since 1.8
 */
@Service
public class PathMatcherServiceImpl implements PathMatcherService {

	@Autowired
	private PathMatcherDao pathMatcherDao;

	@Override
	public List<PathMatcherEntity> findAllPathMatcer() {
		return pathMatcherDao.findAllPathMatcer();
	}

}
