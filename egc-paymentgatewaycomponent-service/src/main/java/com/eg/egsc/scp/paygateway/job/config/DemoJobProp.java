/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.job.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.eg.egsc.common.component.job.model.BusinessJobConfig;


/**
 * 读取定时任务配置类
 * 
 * @author wanghongben
 * @since 2018年1月11日
 */
@Component
@ConfigurationProperties(prefix = "egsc.schedule")
public class DemoJobProp {

  private List<BusinessJobConfig> jobs = new ArrayList<BusinessJobConfig>();

  /**
   * @Return the List<BusinessJobConfig> jobs
   */
  public List<BusinessJobConfig> getJobs() {
    return jobs;
  }

  /**
   * @Param List<BusinessJobConfig> jobs to set
   */
  public void setJobs(List<BusinessJobConfig> jobs) {
    this.jobs = jobs;
  }

}
