/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.eg.egsc.framework.dao.base.BaseDao;
import com.eg.egsc.scp.paygateway.extmapper.DemoUserExtMapper;
import com.eg.egsc.scp.paygateway.mapper1.DemoUserRoleMapper;
import com.eg.egsc.scp.paygateway.mapper1.entity.DemoUser;

/**
 * DemoUserExtDao层提供了各种访问数据库数据方法的类（扩展类）
 * @author wanghongben
 * @since 2018年1月11日
 */
@Repository
public class DemoUserExtDao extends BaseDao<DemoUserExtMapper, DemoUserRoleMapper, DemoUser> {

  /* (non-Javadoc)
   * @see com.eg.egsc.framework.dao.base.BaseDao#setMapperClass()
   */
  @Override
  protected void setMapperClass() {
    this.setMapperClass(DemoUserExtMapper.class);
    this.setExtMapperClass(DemoUserRoleMapper.class);
  }

  /* (non-Javadoc)
   * @see com.eg.egsc.framework.dao.base.BaseDao#setEntityClass()
   */
  @Override
  protected void setEntityClass() {
    this.setEntityClass(DemoUser.class);
  }

  /**
   * 根据ID查询用户对象
   * @param id  查询条件
   * @return List<DemoUser> 返回一个或者多个对象
   */
  public List<DemoUser> queryForList(String id) {
    return this.getMapper().queryForList(id);
  }

  
}
