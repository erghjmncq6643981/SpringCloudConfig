/*
 * RestControllerTest.java
 * 2018年3月26日 上午11:09:39
 * Copyright 2017 Fosun Financial. All  Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Please contact Fosun Corporation or visit         
 * www.fosun.com 
 * if you need additional information or have any questions.
 * @author kyle
 * @version 1.0
 */

package com.fomoney.simple.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 类功能描述
 * 
 * @version
 * @author kyle 2018年3月26日上午11:09:39
 * @since 1.8
 */
// @RestController
public class RestControllerTest {
	@Value("#{compositePropertySource.getProperty('from')}")
	private String from;

	@RequestMapping(value = "/from")
	public String hi() {
		return from;
	}
}
