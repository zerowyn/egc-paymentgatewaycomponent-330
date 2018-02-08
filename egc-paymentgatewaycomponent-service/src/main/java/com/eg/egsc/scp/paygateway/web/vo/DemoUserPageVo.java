/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.web.vo;

import java.util.List;

import com.eg.egsc.scp.paygateway.mapper1.entity.DemoUser;

/**
 * 分页定义的VO
 * @author wanghongben
 * @since 2018年1月11日
 */
public class DemoUserPageVo {
	private int pageCount;
	private List<DemoUser> user;
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public List<DemoUser> getUser() {
		return user;
	}
	public void setUser(List<DemoUser> user) {
		this.user = user;
	}

	
}
