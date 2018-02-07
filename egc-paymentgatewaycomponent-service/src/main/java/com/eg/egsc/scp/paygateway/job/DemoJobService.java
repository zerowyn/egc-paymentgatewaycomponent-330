/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.job;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eg.egsc.common.component.auth.web.vo.UserVo;
import com.eg.egsc.common.component.job.model.BusinessJobConfig;
import com.eg.egsc.common.component.job.support.JobService;

/**
 * TODO
 * @author songjie
 * @since 2018年2月1日
 */
@Component("demoJobService")
public class DemoJobService {
	@Autowired
	private JobService jobService;
	
	public void createOutJob(){
		System.out.println("=============== addJob,outPramJob.outParam");
    	BusinessJobConfig jobConfig = new BusinessJobConfig();
    	jobConfig.setServiceName("outPramJob");
    	jobConfig.setMethodName("outParam");
    	jobConfig.setId("outParam001");
    	jobConfig.setCronExpression("*/10 * * * * ?");
    	jobConfig.setStatus("1");
    	jobConfig.setRunAsAdmin(false);
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("name", "小明");
    	params.put("age", 20);
    	jobConfig.setParams(params);
    	jobService.createJob(jobConfig);
	}
	
	public void createSecondOutJob(){
    System.out.println("=============== addJob,outPramJob.secondOutParam");
      BusinessJobConfig jobConfig = new BusinessJobConfig();
      jobConfig.setServiceName("outPramJob");
      jobConfig.setMethodName("outParam");
      jobConfig.setId("outParam002");
      jobConfig.setCronExpression("*/20 * * * * ?");
      jobConfig.setStatus("1");
      jobConfig.setRunAsAdmin(false);
      
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("name", "小黄");
      params.put("age", 22);
      jobConfig.setParams(params);
      jobService.createJob(jobConfig);
  }
	
	public void createReportJob(){
    	System.out.println("=============== addJob,outPramJob.createReport");
    	BusinessJobConfig jobConfig = new BusinessJobConfig();
    	jobConfig.setServiceName("outPramJob");
    	jobConfig.setMethodName("createReport");
    	jobConfig.setId("createReport001");
    	jobConfig.setCronExpression("*/55 * * * * ?");
    	jobConfig.setStatus("1");
    	jobConfig.setRunAsAdmin(false);
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("reportNme", "年度总结报告");
    	params.put("status", "未发布");
    	
    	UserVo userVo = new UserVo();
    	userVo.setUserName("小王");
    	userVo.setUserId("111333");
    	params.put("userVo", userVo);
    	jobConfig.setParams(params);
    	jobService.createJob(jobConfig);
	}
}
