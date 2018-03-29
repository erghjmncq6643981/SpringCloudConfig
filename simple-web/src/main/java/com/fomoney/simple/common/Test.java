/*
 * Test.java
 * 2018年3月22日 下午4:59:41
 * Copyright 2017 Fosun Financial. All  Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Please contact Fosun Corporation or visit
 * www.fosun.com
 * if you need additional information or have any questions.
 * @author kyle
 * @version 1.0
 */

package com.fomoney.simple.common;

import org.springframework.context.annotation.Configuration;

/**
 * 类功能描述
 * 
 * @version
 * @author kyle 2018年3月22日下午4:59:41
 * @since 1.8
 */
@Configuration
public class Test {
	// @Value("#{compositePropertySource.getProperty('from')}")
	private String from;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	// @Bean
	// @RefreshScope
	public Test getTest() {
		return this;
	}

}
