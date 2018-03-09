/*
 * HelloApplicationTests.java
 * 2018年1月11日 下午5:38:15
 * Copyright 2017 Fosun Financial. All  Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Please contact Fosun Corporation or visit
 * www.fosun.com
 * if you need additional information or have any questions.
 * @author kyle
 * @version 1.0
 */

package com.kyle.rabbitmq.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kyle.rabbitmq.HelloApplication;
import com.kyle.rabbitmq.send.Sender;

/**
 * rabbitmq测试
 *
 * @version
 * @author kyle 2018年1月11日下午5:38:15
 * @since 1.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HelloApplication.class)
public class HelloApplicationTests {

	@Autowired
	private Sender sender;

	/**
	 * @throws Exception
	 * @Description:
	 * @create date 2018年1月11日下午5:42:43
	 */
	@Test
	public void hello() throws Exception {
		sender.send();
	}
}
