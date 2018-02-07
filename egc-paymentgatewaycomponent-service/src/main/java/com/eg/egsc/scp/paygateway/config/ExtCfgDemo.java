/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eg.egsc.framework.service.util.SpringEnvUtil;

/**
 * ExtCfg Demo
 * @author wanghongben
 * @since 2018年1月11日
 */
@Component
public class ExtCfgDemo {
	
	@Autowired
	private SpringEnvUtil springEnvUtil;

	public void printMyProperty() {
	 springEnvUtil.getProperty("abc");
	
	}
}
