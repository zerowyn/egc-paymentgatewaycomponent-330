/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.extmapper;

import java.util.List;

import com.eg.egsc.scp.paygateway.mapper1.DemoUserMapper;
import com.eg.egsc.scp.paygateway.mapper1.entity.DemoUser;

/**
 * 扩展的Mapper类（要继承原有的Mapper）
 * @author wanghongben
 * @since 2018年1月11日
 */
public interface DemoUserExtMapper extends DemoUserMapper{
  /**
   * 根据ID查询用户对象
   * @param id  查询条件
   * @return List<DemoUser> 返回一个或者多个对象
   */
    List<DemoUser> queryForList(String id);
}
