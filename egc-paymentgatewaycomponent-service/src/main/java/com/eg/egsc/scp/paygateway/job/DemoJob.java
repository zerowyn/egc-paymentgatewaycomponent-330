/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eg.egsc.common.component.auth.web.SecurityContext;
import com.eg.egsc.common.component.job.support.JobService;

/**
 * 定时任务类
 * 
 * @author wanghongben
 * @since 2018年1月11日
 */
@Component("demoJob")
public class DemoJob {
	@Autowired
	private DemoJobService demoJobService;
	@Autowired
	private JobService jobService;
	protected final Logger logger = LoggerFactory.getLogger(DemoJob.class);
	private static boolean addJob = true;
	private static int count = 0;
	public void run() {
	    logger.info("my job is running....");
	    logger.info("current user id is " + SecurityContext.getUserId());
	    logger.info("current court uuid is " + SecurityContext.getCourtUuid());
	    logger.info("current token is " + SecurityContext.getToken());
    
	    
	    /**
	     * 动态创建job
	     */
	    if(addJob){
	    	logger.info("=============== 1 ===================");
	    	demoJobService.createOutJob();
	    	
	    	logger.info("=============== 2 ===================");
	    	demoJobService.createSecondOutJob();
	    	
	    	logger.info("=============== 3 ===================");
	    	demoJobService.createReportJob();
	    	
	    	addJob = false;
	    }
	    
	    /**
	     * 查询所有执行中的任务
	     */
	    if(count == 1){
	    	logger.info(" ################   getAllRunningJob ="+jobService.getAllRunningJob());
	    }
	    
	    /**
	     * 移除job
	     */
	    if(count == 2){
	    	logger.info(" ################   outPramJob outParam = "+jobService.isJobExist("outPramJob", "outParam", "outParam001"));
	    	jobService.removeJob("outPramJob", "outParam", "outParam001");
	    	logger.info(" ################   outPramJob outParam = "+jobService.isJobExist("outPramJob", "outParam", "outParam001"));
	    }
	    count ++;
	}
}
