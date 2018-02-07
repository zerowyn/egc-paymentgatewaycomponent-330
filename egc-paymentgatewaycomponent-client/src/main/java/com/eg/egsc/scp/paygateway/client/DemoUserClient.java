/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.client;

import java.util.List;

import com.eg.egsc.common.exception.CommonException;
import com.eg.egsc.scp.paygateway.dto.UserDto;
/**
 * 提供外部组件与应用调用的接口类
 * @author wanghongben
 * @since 2018年1月11日
 */
public interface DemoUserClient {
  /**
   * 通过组件或者应用封装的UserDto创建用户
   * @param userDto 封装的UserDto对象
   * @throws CommonException  异常信息
   */
  void createUser(UserDto userDto) throws CommonException;
  /**
   *  通过组件或者应用封装的UserDto删除用户信息
   * @param userDto 封装的UserDto对象
   * @throws CommonException  异常信息
   */
  void deleteUser(UserDto userDto) throws CommonException;
  /**
   * 通过组件或者应用封装的UserDto更新用户信息
   * @param userDto 封装的UserDto对象
   * @throws CommonException  异常信息
   */
  void updateUser(UserDto userDto) throws CommonException;
  /**
   * 根据组件或者应用封装的UserDto查找用户详细信息
   * @param userDto 封装的UserDto对象
   * @return UserDto 返回查找的用户信息
   * @throws CommonException 异常信息
   */
  UserDto selectUser(UserDto userDto) throws CommonException;
  /**
   * 根据组件或者应用封装的UserDto查找符合条件的用户对象
   * @param userDto 封装的UserDto对象
   * @return List<UserDto>  返回查找的用户对象
   * @throws CommonException 异常信息
   */
  List<UserDto> getUserList(UserDto userDto) throws CommonException;
}
