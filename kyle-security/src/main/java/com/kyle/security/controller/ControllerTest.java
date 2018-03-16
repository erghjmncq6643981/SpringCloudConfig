/*
 * ControllerTest.java
 * 2018年3月10日 下午2:25:20
 * Copyright 2017 Fosun Financial. All  Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Please contact Fosun Corporation or visit
 * www.fosun.com
 * if you need additional information or have any questions.
 * @author kyle
 * @version 1.0
 */

package com.kyle.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * spring security访问测试
 *
 * @version
 * @author kyle 2018年3月10日下午2:25:20
 * @since 1.8
 */
@RestController
public class ControllerTest {

	/**
	 * @param param
	 * @return String
	 * @throws Exception
	 * @Description:
	 * @create date 2018年3月10日下午2:27:47
	 */
	@RequestMapping(value = "/security/test", method = RequestMethod.GET)
	public String accessTest(@RequestParam("param") String param) throws Exception {
		return "hi," + param + "! you login successfully.";
	}
}
