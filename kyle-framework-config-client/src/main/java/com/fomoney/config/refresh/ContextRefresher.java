/*
 * ContextRefresher.java
 * 2018年3月27日 上午9:51:25
 * Copyright 2017 Fosun Financial. All  Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Please contact Fosun Corporation or visit         
 * www.fosun.com 
 * if you need additional information or have any questions.
 * @author kyle
 * @version 1.0
 */

package com.fomoney.config.refresh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 类功能描述
 * 
 * @version
 * @author kyle 2018年3月27日上午9:51:25
 * @since 1.8
 */
@Component
public class ContextRefresher {
	@Autowired
	private AbstractApplicationContext abstractApplicationContext;

	/**
	 * 
	 * @Description:
	 * @create date 2018年3月27日下午2:42:01
	 */
	public synchronized void refresh() {
		abstractApplicationContext.refresh();
	}

}
