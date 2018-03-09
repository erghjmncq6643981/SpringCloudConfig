/*
 * Receiver.java
 * 2018年1月11日 下午5:24:55
 * Copyright 2017 Fosun Financial. All  Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Please contact Fosun Corporation or visit
 * www.fosun.com
 * if you need additional information or have any questions.
 * @author kyle
 * @version 1.0
 */

package com.kyle.rabbitmq.recevie;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 *
 * @version
 * @author kyle 2018年1月11日下午5:24:55
 * @since 1.8
 */
@Component
@RabbitListener(queues = "hello")
public class Receiver {

	/**
	 * @param hello
	 * @Description: TODO
	 * @create date 2018年1月11日下午5:33:53
	 */
	@RabbitHandler
	public void process(String hello) {
		System.out.println("Receiver :" + hello);
	}
}
