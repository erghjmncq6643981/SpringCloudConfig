/*
 * LocalConfigurer.java
 * 2018年3月27日 下午4:33:46
 * Copyright 2017 Fosun Financial. All  Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Please contact Fosun Corporation or visit         
 * www.fosun.com 
 * if you need additional information or have any questions.
 * @author Wang Zhaosheng
 * @version 1.0
 */

package com.fomoney.config.configurer;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.fomoney.config.environment.Environment;
import com.fomoney.config.environment.PropertySource;
import com.fomoney.config.source.BasicAuthorizationInterceptor;

/**
 * 配置中心资源加载类
 * 
 * @version
 * @author Wang Zhaosheng 2018年3月27日下午4:33:46
 * @since 1.8
 */
public class LocalPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	@Override
	protected Properties mergeProperties() throws IOException {

		Properties params = new Properties();

		if (this.localOverride) {
			loadProperties(params);
		}

		if (this.localProperties != null) {
			for (Properties localProp : this.localProperties) {
				CollectionUtils.mergePropertiesIntoMap(localProp, params);
			}
		}

		if (!this.localOverride) {
			loadProperties(params);
		}

		Properties properties = new Properties();
		RestTemplate restTemplate = new RestTemplate();

		String username = params.get("chaos.config.username") == null ? ""
				: params.get("chaos.config.username").toString();
		String password = params.get("chaos.config.password") == null ? ""
				: params.get("chaos.config.password").toString();
		String ip = params.get("chaos.config.ip") == null ? "" : params.get("chaos.config.ip").toString();
		String application = params.get("chaos.config.application") == null ? ""
				: params.get("chaos.config.application").toString();
		String profile = params.get("chaos.config.profile") == null ? ""
				: params.get("chaos.config.profile").toString();
		String label = params.get("chaos.config.label") == null ? "" : params.get("chaos.config.label").toString();

		BasicAuthorizationInterceptor interceptor = new BasicAuthorizationInterceptor(username, password);
		restTemplate.getInterceptors().add(interceptor);
		String url = "http://" + ip + "/" + application + "/" + profile + "/" + label;
		ResponseEntity<Environment> responseEntity = restTemplate.getForEntity(url, Environment.class);
		Environment resultEnv = responseEntity.getBody();
		for (PropertySource source : resultEnv.getPropertySources()) {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) source.getSource();
			for (String key : map.keySet()) {
				properties.put(key, map.get(key));
			}
		}
		return properties;
	}

}
