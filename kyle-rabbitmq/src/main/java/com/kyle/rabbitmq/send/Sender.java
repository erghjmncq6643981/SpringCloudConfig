/*
 * Sender.java
 * 2018年1月11日 下午5:21:00
 * Copyright 2017 Fosun Financial. All  Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Please contact Fosun Corporation or visit
 * www.fosun.com
 * if you need additional information or have any questions.
 * @author kyle
 * @version 1.0
 */

package com.kyle.rabbitmq.send;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 提供者
 *
 * @version
 * @author kyle 2018年1月11日下午5:21:36
 * @since 1.8
 */
@Component
public class Sender {
	@Autowired
	private AmqpTemplate rabbitTemplate;

	/**
	 *
	 * @Description: TODO
	 * @create date 2018年1月11日下午5:24:25
	 */
	public void send() {
		String context = "hello" + new Date();
		System.out.println("Sender:" + context);
		this.rabbitTemplate.convertAndSend("hello", context);
	}
}
