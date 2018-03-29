/*
 * Test.java
 * 2018年3月28日 下午3:39:49
 * Copyright 2017 Fosun Financial. All  Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Please contact Fosun Corporation or visit         
 * www.fosun.com 
 * if you need additional information or have any questions.
 * @author kyle
 * @version 1.0
 */

package com.fomoney.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.fomoney.config.configurer.LocalPropertyPlaceholderConfigurer;

/**
 * 类功能描述
 * 
 * @version
 * @author kyle 2018年3月28日下午3:39:49
 * @since 1.8
 */
@Configuration
public class AutoConfiguration {

	@Bean
	public LocalPropertyPlaceholderConfigurer getLocalPropertyPlaceholderConfigurer() {
		LocalPropertyPlaceholderConfigurer configurer = new LocalPropertyPlaceholderConfigurer();
		configurer.setIgnoreResourceNotFound(true);
		configurer.setIgnoreUnresolvablePlaceholders(true);
		configurer.setOrder(2);
		Resource resource = new FileSystemResource("classpath*:config/config-center.properties");
		configurer.setLocation(resource);
		return configurer;
	}
}
