/*
 * ConfigClientApplication.java
 * 2017年7月9日 下午5:40:16
 * Copyright 2017 Fosun Financial. All  Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Please contact Fosun Corporation or visit
 * www.fosun.com
 * if you need additional information or have any questions.
 * @author kyle
 * @version 1.0
 */

package com.kyle.config.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置服务中心的客户端
 *
 * @version
 * @author kyle 2017年7月9日下午5:40:16
 * @since 1.8
 */
@RefreshScope
@RestController
@EnableEurekaClient
@SpringBootApplication
public class ConfigClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}

	@Autowired
	private Environment env;

	@RequestMapping(value = "/from")
	public String hi() {
		return env.getProperty("from", "underfined");
	}

	@RequestMapping(value = "/form")
	public String hi2() {
		return env.getProperty("form", "underfined");
	}
}
