/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.config;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.eg.egsc.mail.TestMail;
import com.eg.egsc.mq.ReceiverListenerTest;
import com.eg.egsc.mq.TestRabbitmq;
import com.eg.egsc.redis.TestRedis;
import com.eg.egsc.scp.demo.api.TestDemoApi;
import com.eg.egsc.scp.demo.service.TestDemoService;
import com.eg.egsc.scp.demo.web.TestDemoWebController;

/**
 * 测试单元汇总功能
 * @author wanghongben
 * @since 2018年1月23日
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({

  TestExtCfgDemo.class,
  TestMail.class,
    
  TestRabbitmq.class,
  ReceiverListenerTest.class,
  TestRedis.class, 
  TestDemoApi.class,
  TestDemoService.class, 
  TestDemoWebController.class,

})

public class SuiteTest {

}