/*
 * AuthoritiesEntry.java
 * 2018年3月12日 上午9:27:31
 * Copyright 2017 Fosun Financial. All  Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Please contact Fosun Corporation or visit
 * www.fosun.com
 * if you need additional information or have any questions.
 * @author kyle
 * @version 1.0
 */

package com.kyle.security.entity;

/**
 * 类功能描述
 *
 * @version
 * @author kyle 2018年3月12日上午9:27:31
 * @since 1.8
 */
public class AuthoritiesEntity {
	private long id;
	private String username;
	private String authority;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
