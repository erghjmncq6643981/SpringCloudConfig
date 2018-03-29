/*
 * ConfigClientTest.java
 * 2018年3月22日 下午3:39:52
 * Copyright 2017 Fosun Financial. All  Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Please contact Fosun Corporation or visit
 * www.fosun.com
 * if you need additional information or have any questions.
 * @author kyle
 * @version 1.0
 */

package com.fomoney.simple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 类功能描述
 *
 * @version
 * @author kyle 2018年3月22日下午3:39:52
 * @since 1.8
 */
@Controller
public class ConfigClientTest {

	// @Autowired
	// private CompositePropertySource compositePropertySource;
	// @Value("#{compositePropertySource.getProperty('from')}")
	// private String from;
	// @Autowired
	// private Test test;
	@Autowired
	private ContextRefresher refresh;
	@Value("${from}")
	private String from;

	/**
	 * @param model
	 * @return
	 * @Description: TODO
	 * @create date 2018年3月22日下午4:41:22
	 */
	@RequestMapping(value = "/client", method = RequestMethod.GET)
	public ModelAndView client(ModelMap model) {
		ModelAndView view = new ModelAndView();
		// model.put("message", compositePropertySource.getProperty("from"));
		// model.put("message", compositePropertySource.getProperty("from"));
		model.put("message", from);
		view.setViewName("/hello_world");
		return view;
	}

	@RequestMapping(value = "/from", method = RequestMethod.GET)
	public ModelAndView from(ModelMap model) {
		ModelAndView view = new ModelAndView();
		model.put("message", from);
		view.setViewName("/hello_world");
		return view;
	}

	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	public ModelAndView refresh(ModelMap model) {
		ModelAndView view = new ModelAndView();
		// refresh.refresh();
		// model.put("message", compositePropertySource.getProperty("from"));
		model.put("message", from);
		view.setViewName("/hello_world");
		return view;
	}
}
