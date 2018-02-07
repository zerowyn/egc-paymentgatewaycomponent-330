/*package com.eg.egsc.scp.websocket.job;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.eg.egsc.common.component.job.model.BusinessJobConfig;
import com.eg.egsc.config.AbstractUnitTestSupport;
import com.eg.egsc.scp.demo.job.DemoJob;
import com.eg.egsc.scp.demo.job.DemoJob2;
import com.eg.egsc.scp.demo.job.config.PropertyJobConfigService;

*//**
 * 测试Job类
 * @author wanghongben
 * @since 2018年1月30日
 *//*

public class JobPropTest extends AbstractUnitTestSupport {

	  protected final Logger logger = LoggerFactory.getLogger(JobPropTest.class);
	  
	  @Resource
	  DemoJob demoJob;
	  
	  @Resource
	  PropertyJobConfigService propertyJobConfigService;
	  
	  @Resource
	  DemoJob2 demoJob2;
	  
	  *//**
	   *  testBusinessJobConfigListSize
	   *//*
	  @Test
	  @Transactional
	  @Rollback(true)
	  public void testBusinessJobConfigListSize() {
		 demoJob.run();
		logger.info(" testFlushdemoJob1");
	  }
	  
	  
	  
	  *//**
	   *  testBusinessJobConfig
	   *//*
	  @Test
	  @Transactional
	  @Rollback(true)
	  public void testBusinessJobConfig() {
		List<BusinessJobConfig> list = propertyJobConfigService.getAllJobConfigs();
		logger.info(" testBusinessJobConfig	List<BusinessJobConfig> list.size :"+list.size());
	  }
	  
	  *//**
	   *  testBusinessJobConfig
	   *//*
	  @Test
	  @Transactional
	  @Rollback(true)
	  public void testFlushWebsocketSessionJob() {
	    demoJob2.run();
		logger.info(" testFlushdemoJob2");
	  }
	  
}
*/