/*
 * Test.java
 * 2018年3月13日 上午9:55:30
 * Copyright 2017 Fosun Financial. All  Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Please contact Fosun Corporation or visit
 * www.fosun.com
 * if you need additional information or have any questions.
 * @author kyle
 * @version 1.0
 */

package com.kyle.security.test;

import org.springframework.util.AntPathMatcher;

/**
 * 类功能描述
 *
 * @version
 * @author kyle 2018年3月13日上午9:55:30
 * @since 1.8
 */
public class Test {
	public static void main(String[] args) {
		AntPathMatcher antPathMatcher = new AntPathMatcher();
		System.out.println(antPathMatcher.match("/security/**", "/security/test?param=kyle") + "");
	}
}
