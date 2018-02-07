/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.config;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.eg.egsc.common.component.auth.utils.WhiteResourceList;
import com.eg.egsc.scp.paygateway.config.ExtCfgDemo;


/**
 * 测试扩展配置
 * @author wanghongben
 * @since 2018年1月23日
 */
public class TestExtCfgDemo extends AbstractUnitTestSupport {
  protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExtCfgDemo extCfgDemo;
	
	@Autowired
  private WhiteResourceList whiteList;

	/**
	 * 测试配置
	 */
	@Test
	public void testGetAbc() {
		extCfgDemo.printMyProperty();
	}
	
	/**
   * 测试白名单配置
   */
  @Test
  public void testGetWhiteList() {
   logger.info(whiteList.toString());
  }
}
