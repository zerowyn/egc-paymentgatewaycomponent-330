/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.eg.egsc.common.component.auth.web.SecurityContext;

/**
 * 定时任务类
 * 
 * @author wanghongben
 * @since 2018年1月11日
 */
@Component("demoJob2")
public class DemoJob2 {
  protected final Logger logger = LoggerFactory.getLogger(DemoJob2.class);

  public void run() {
    logger.info("my job 2 is running....");
    logger.info("current user id is " + SecurityContext.getUserId());
    logger.info("current court uuid is " + SecurityContext.getCourtUuid());
    logger.info("current token is " + SecurityContext.getToken());
  }
}
