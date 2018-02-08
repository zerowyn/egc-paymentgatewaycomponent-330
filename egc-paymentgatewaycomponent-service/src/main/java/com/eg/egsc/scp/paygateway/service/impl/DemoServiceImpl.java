/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eg.egsc.scp.paygateway.dao.DemoUserDao;
import com.eg.egsc.scp.paygateway.dao.DemoUserExtDao;
import com.eg.egsc.scp.paygateway.mapper1.entity.DemoUser;
import com.eg.egsc.scp.paygateway.mapper1.entity.DemoUserCriteria;
import com.eg.egsc.scp.paygateway.mapper1.entity.DemoUserCriteria.Criteria;
import com.eg.egsc.scp.paygateway.service.DemoService;
import com.eg.egsc.scp.paygateway.web.vo.DemoUserPageVo;


/**
 * 调用的接口实现类
 * @author wanghongben
 * @since 2018年1月11日
 */
@Service
public class DemoServiceImpl implements DemoService {



  @Autowired
  private DemoUserDao userDao;

  @Autowired
  private DemoUserExtDao demoUserExtDao;

  /* (non-Javadoc)
   * @see com.eg.egsc.scp.demo.service.IDemoService#getUser(java.lang.String)
   */
  @Override
  public DemoUser getUser(String id) {

    return userDao.getMapper().selectByPrimaryKey(id);
  }

  /* (non-Javadoc)
   * @see com.eg.egsc.scp.demo.service.IDemoService#insert(com.eg.egsc.scp.demo.mapper1.entity.DemoUser)
   */
  @Override
  @Transactional
  public int insert(DemoUser user) {
    return userDao.getMapper().insert(user);
  }

  /* (non-Javadoc)
   * @see com.eg.egsc.scp.demo.service.IDemoService#countByExample(com.eg.egsc.scp.demo.mapper1.entity.DemoUserCriteria)
   */
  @Override
  @Transactional(readOnly = true)
  public int countByExample(DemoUserCriteria example) {
    return userDao.getMapper().countByExample(example);
  }

  /* (non-Javadoc)
   * @see com.eg.egsc.scp.demo.service.IDemoService#selectByPrimaryKey(java.lang.String)
   */
  @Override
  @Transactional(readOnly = true)
  public DemoUser selectByPrimaryKey(String id) {
    return userDao.getMapper().selectByPrimaryKey(id);
  }

  /* (non-Javadoc)
   * @see com.eg.egsc.scp.demo.service.IDemoService#deleteByPrimaryKey(java.lang.String)
   */
  @Override
  @Transactional
  public int deleteByPrimaryKey(String id) {
    return userDao.getMapper().deleteByPrimaryKey(id);
  }

  /* (non-Javadoc)
   * @see com.eg.egsc.scp.demo.service.IDemoService#updateByPrimaryKey(com.eg.egsc.scp.demo.mapper1.entity.DemoUser)
   */
  @Override
  @Transactional
  public int updateByPrimaryKey(DemoUser record) {
    return userDao.getMapper().updateByPrimaryKey(record);
  }

  /* (non-Javadoc)
   * @see com.eg.egsc.scp.demo.service.IDemoService#getAllUser()
   */
  @Override
  @Transactional(readOnly = true)
  public List<DemoUser> getAllUser() {
    return userDao.getAllUser();
  }

  /* (non-Javadoc)
   * @see com.eg.egsc.scp.demo.service.IDemoService#queryOnePageDataByCondition(java.lang.Integer, java.lang.Integer, com.eg.egsc.scp.demo.mapper1.entity.DemoUser)
   */
  @Override
  @Transactional
  public List<DemoUser> queryOnePageDataByCondition(Integer currentPage, Integer pageSize,
      DemoUser demoUser) {
    return userDao.queryOnePageDataByCondition(currentPage, pageSize, demoUser);
  }

  /* (non-Javadoc)
   * @see com.eg.egsc.scp.demo.service.IDemoService#getAllUserByRoleId(java.lang.String)
   */
  @Override
  @Transactional(readOnly = true)
  public List<DemoUser> getAllUserByRoleId(String roleId) {
    return userDao.getAllUserByRoleId(roleId);
  }

  /* (non-Javadoc)
   * @see com.eg.egsc.scp.demo.service.IDemoService#queryForList(java.lang.String)
   */
  @Override
  @Transactional(readOnly = true)
  public List<DemoUser> queryForList(String id) {
    return demoUserExtDao.queryForList(id);
  }

  /* (non-Javadoc)
   * @see com.eg.egsc.scp.demo.service.IDemoService#queryForPage(java.lang.String, java.lang.String, java.lang.String)
   */
  @Override
  @Transactional(readOnly = true)
  public DemoUserPageVo queryForPage(String currentPage, String pageSize, String userId) {
    DemoUser user = this.selectByPrimaryKey(userId);
    DemoUserPageVo userPage = new DemoUserPageVo();
    DemoUserCriteria example = new DemoUserCriteria();
    Criteria criteria = example.createCriteria();
    criteria.andUserNameEqualTo(user.getUserName());
    example.setDistinct(true);
    List<DemoUser> demoUser = this.queryOnePageDataByCondition(Integer.parseInt(currentPage),
        Integer.parseInt(pageSize), user);
    int countPage = this.countByExample(example);
    userPage.setPageCount(countPage);
    userPage.setUser(demoUser);
    return userPage;
  }
  
  /* (non-Javadoc)
   * @see com.eg.egsc.scp.demo.service.IDemoService#insert(com.eg.egsc.scp.demo.mapper1.entity.DemoUser)
   */
  @Override
  @Transactional
  public void insertByBatch(List<DemoUser> demoUsers) {
    userDao.getMapper().insertByBatch(demoUsers);
  }

}
