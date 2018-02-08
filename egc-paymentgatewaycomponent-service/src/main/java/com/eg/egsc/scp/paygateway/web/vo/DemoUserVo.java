/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.web.vo;

import java.util.List;

import com.eg.egsc.scp.paygateway.mapper1.entity.DemoRole;
import com.eg.egsc.scp.paygateway.mapper1.entity.DemoUser;

/**
 * 用户信息定义的VO
 * @author wanghongben
 * @since 2018年1月11日
 */
public class DemoUserVo {
	private DemoUser user;
	private List<DemoRole> roles;

	public DemoUser getUser() {
		return user;
	}

	public void setUser(DemoUser user) {
		this.user = user;
	}

	public List<DemoRole> getRoles() {
		return roles;
	}

	public void setRoles(List<DemoRole> roles) {
		this.roles = roles;
	}

}
