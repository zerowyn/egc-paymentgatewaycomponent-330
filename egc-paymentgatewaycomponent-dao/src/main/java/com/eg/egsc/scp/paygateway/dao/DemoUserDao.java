/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.eg.egsc.framework.dao.base.BaseDao;
import com.eg.egsc.framework.paging.PageUtils;
import com.eg.egsc.scp.paygateway.mapper.DemoUserMapper;
import com.eg.egsc.scp.paygateway.mapper.DemoUserRoleMapper;
import com.eg.egsc.scp.paygateway.mapper.entity.DemoUser;
import com.eg.egsc.scp.paygateway.mapper.entity.DemoUserCriteria;
import com.eg.egsc.scp.paygateway.mapper.entity.DemoUserRole;
import com.eg.egsc.scp.paygateway.mapper.entity.DemoUserRoleCriteria;


/**
 * DemoUserDao层提供了各种访问数据库数据方法的类
 * @author wanghongben
 * @since 2018年1月11日
 */
@Repository
public class DemoUserDao extends BaseDao<DemoUserMapper, DemoUserRoleMapper, DemoUser> {

  /* (non-Javadoc)
   * @see com.eg.egsc.framework.dao.base.BaseDao#setMapperClass()
   */
  @Override
  protected void setMapperClass() {
    this.setMapperClass(DemoUserMapper.class);
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
   * 查询所有用户（不需要加任何查询条件）。
   * @return List<DemoUser> 返回一个或者多个用户对象
   */
  public List<DemoUser> getAllUser() {
    DemoUserCriteria dcsc = new DemoUserCriteria();
    return this.getMapper().selectByExample(dcsc);
  }  
  /**
   * 根据名字查询用户信息
   * @param userName 查询条件
   * @return List<DemoUser> 返回一个或者多个用户对象
   */
  public List<DemoUser> getUserByName(String userName) {
    if (StringUtils.isBlank(userName)) {
      return new ArrayList<DemoUser>(0);
    }
    DemoUserCriteria duc = new DemoUserCriteria();
    DemoUserCriteria.Criteria ducc = duc.createCriteria();
    ducc.andUserNameEqualTo(userName);
    return this.getMapper().selectByExample(duc);
  }
  /**
   * 根据角色IP查询用户对象。
   * @param roleId 查询条件
   * @return List<DemoUser> 返回一个或者多个用户对象
   */
  public List<DemoUser> getAllUserByRoleId(String roleId) {
    if (StringUtils.isBlank(roleId)) {
      return new ArrayList<DemoUser>(0);
    }
    DemoUserRoleCriteria dcsc = new DemoUserRoleCriteria();
    DemoUserRoleCriteria.Criteria c = dcsc.createCriteria();
    c.andRoleIdEqualTo(roleId);
    List<DemoUserRole> userRoleList = this.getExtMapper().selectByExample(dcsc);
    if (CollectionUtils.isEmpty(userRoleList)) {
      return new ArrayList<DemoUser>(0);
    }
    List<String> userIds = new ArrayList<String>(userRoleList.size());
    for (DemoUserRole userRole : userRoleList) {
      String userId = userRole.getUserId();
      userIds.add(userId);
    }
    DemoUserCriteria duc = new DemoUserCriteria();
    duc.createCriteria().andIdIn(userIds);
    List<DemoUser> userList = this.getMapper().selectByExample(duc);
    return userList;
  }
  /**
   * 此方法用于分页
   * @param currentPage 目前是第几页
   * @param pageSize 每页显示的条数
   * @param DemoUser 查询条件
   * @return List<DemoUser> 返回一个或者多个用户对象
   */
  public List<DemoUser> queryOnePageDataByCondition(Integer currentPage, Integer pageSize,
      DemoUser DemoUser) {
    DemoUserCriteria dcsc = new DemoUserCriteria();
    DemoUserCriteria.Criteria c = dcsc.createCriteria();
    if (StringUtils.isNotBlank(DemoUser.getUserName())) {
      c.andUserNameEqualTo(DemoUser.getUserName());
    }
    RowBounds rowBounds =
        new RowBounds(PageUtils.getOffset(currentPage, pageSize), PageUtils.getLimit(pageSize));
    List<DemoUser> dcsInfo = this.getMapper().selectByExampleWithRowbounds(dcsc, rowBounds);
    return dcsInfo;
  }

}
