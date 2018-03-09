/*
 * RabbitConfi.java
 * 2018年1月11日 下午5:34:57
 * Copyright 2017 Fosun Financial. All  Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Please contact Fosun Corporation or visit
 * www.fosun.com
 * if you need additional information or have any questions.
 * @author kyle
 * @version 1.0
 */

package com.kyle.rabbitmq.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 创建队列
 *
 * @version
 * @author kyle 2018年1月11日下午5:34:57
 * @since 1.8
 */
@Configuration
public class RabbitConfig {

	/**
	 * @return Queue
	 * @Description:
	 * @create date 2018年1月11日下午5:37:22
	 */
	@Bean
	public Queue HelloQueue() {
		return new Queue("hello");
	}
}
