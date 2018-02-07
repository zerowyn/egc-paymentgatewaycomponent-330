/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.job;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 定时任务类
 * 
 * @author wanghongben
 * @since 2018年1月11日
 */
@Component("outPramJob")
public class OutPramJob {

  protected final Logger logger = LoggerFactory.getLogger(OutPramJob.class);
  
  public void outParam(Map params){
	  logger.info("     --------------【1】OutPramJob - outParam is running....");
	  logger.info("     --------------【outParam】 "+params.toString());
  }
  
  public void createReport(Map params){
	  logger.info("     --------------【2】OutPramJob - createReport is running....");
	  logger.info("     --------------【createReport】 "+params.toString());
  }
}
