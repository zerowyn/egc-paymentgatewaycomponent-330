/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.job.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.eg.egsc.common.component.job.model.BusinessJobConfig;
import com.eg.egsc.common.component.job.support.JobConfigService;


/**
 * 定时任务配置服务类
 * @author wanghongben
 * @since 2018年1月11日
 */
@Component("jobConfig")
public class PropertyJobConfigService implements JobConfigService {

  @Resource
  DemoJobProp demoJobProp;

  /*
   * (non-Javadoc)
   * 
   * @see com.eg.egsc.job.support.IJobInfoService#getAllJobInfos()
   */
  @Override
  public List<BusinessJobConfig> getAllJobConfigs() {
    List<BusinessJobConfig> configs = new ArrayList<BusinessJobConfig>();

    configs = demoJobProp.getJobs();
    return configs;
  }

}
