/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.config;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.eg.egsc.scp.paygateway.service.TestOrderQueryService;

/**
 * 测试单元汇总功能
 * @author wanghongben
 * @since 2018年1月23日
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
  TestOrderQueryService.class

})

public class SuiteTest {

}