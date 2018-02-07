/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.service;

import java.util.List;




import com.eg.egsc.scp.paygateway.mapper.entity.DemoUser;
import com.eg.egsc.scp.paygateway.mapper.entity.DemoUserCriteria;
import com.eg.egsc.scp.paygateway.web.vo.DemoUserPageVo;


/**
 * 提供API与Controller调用的接口类
 * @author wanghongben
 * @since 2018年1月11日
 */
public interface DemoService {
  /**
   * 根据id查询用户信息
   * @param id 查询条件
   * @return DemoUser 返回用户信息
   */
  public DemoUser getUser(String id);
  /**
   * 新增用户信息
   * @param user 用户信息
   * @return int 返回1代表成功，返回0代表失败
   */
   int insert(DemoUser user);
  /**
   * 根据条件统计数量
   * @param example  查询条件
   * @return int  返回值
   */
   int countByExample(DemoUserCriteria example);
  /**
   * 根据主键ID查询用户信息
   * @param id  查询条件
   * @return DemoUser  返回用户对象
   */
  DemoUser selectByPrimaryKey(String id);
  
  /**
   * 根据主键ID删除用户信息
   * @param id  查询条件
   * @return int  返回1代表成功，返回0代表失败
   */
  int deleteByPrimaryKey(String id);
  /**
   * 根据用户信息更新
   * @param DemoUser  更新条件
   * @return int  返回1代表成功，返回0代表失败
   */
  int updateByPrimaryKey(DemoUser record);
    /**
   * 查询所有用户
   * @return int  返回一个或者多个用户对象
   */
  List<DemoUser> getAllUser();
     /**
   * 分页方法
   * @param currentPage 当前页码
   * @param pageSize    当前显示个数
   * @param DemoUser    查询的条件
   * @return int  返回一个或者多个用户对象
   */
  List<DemoUser> queryOnePageDataByCondition(Integer currentPage, Integer pageSize, DemoUser DemoUser);
  
  
  /**
   * 根据角色ID信息查询
   * @param roleId  查询的条件
   * @return List<DemoUser>  返回一个或者多个用户对象
   */
  List<DemoUser> getAllUserByRoleId(String roleId);
  /**
   * 根据ID进行查询
   * @param id  查询的条件
   * @return List<DemoUser>  返回一个或者多个用户对象
   */
  List<DemoUser> queryForList(String id);
  /**
   * 根据ID进行查询
   * @param id  查询的条件
   * @return DemoUserPageVo 返回总数，以及返回的多个用户对象
   */
  DemoUserPageVo queryForPage(String currentPage, String pageSize, String userId);

  /**
   * 批量插入数据
   * @Methods Name insertByBatch
   * @Create In 2018年1月16日 By songjie
   * @param demoUsers void
   */
  void insertByBatch(List<DemoUser> demoUsers);
  
}
