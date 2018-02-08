/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eg.egsc.framework.client.dto.RequestDto;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.framework.service.base.api.BaseApiController;
import com.eg.egsc.scp.paygateway.dto.UserDto;
import com.eg.egsc.scp.paygateway.exception.DemoException;
import com.eg.egsc.scp.paygateway.mapper1.entity.DemoUser;
import com.eg.egsc.scp.paygateway.service.DemoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户相关api
 * @author wanghongben
 * @since 2018年1月11日
 */
@Api(value = "用户相关api")
@RestController
@RequestMapping(value = "/api/user")
public class DemoUserApi extends BaseApiController {
	
  @Autowired
  DemoService demoServiceImpl;
  
  protected final Logger logger = LoggerFactory.getLogger(DemoUserApi.class);
  /**
   * 查询所有用户对象信息
   * @param req 查询条件
   * @return ResponseDto 返回查询结果
   */
  @ApiOperation(value = "查询所有用户信息")
  @RequestMapping(value = "/lists", method = RequestMethod.POST)
  public ResponseDto getUserList(@Valid @RequestBody RequestDto<UserDto> req) {
    List<DemoUser> userlist = demoServiceImpl.getAllUser();     
    ResponseDto res = super.getDefaultResponseDto();
    if (userlist.size() > 0) {
      res.setData(userlist);
    }
    return res;
  }
  /**
   * 新增用户详细信息
   * @param req 新增的用户信息
   * @return ResponseDto 返回的结果
   */
  @ApiOperation(value = "新增用户详细信息")
  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public ResponseDto createUser(@Valid @RequestBody RequestDto<UserDto> req) {
    ResponseDto res = new ResponseDto();
    List<DemoUser> list = new ArrayList<DemoUser>();
    UserDto userDto = req.getData();
    if (!isValid(userDto.getId())) {
      throw new DemoException("demo.usermgnt.user.id.notblank");
    }

    DemoUser user = new DemoUser();
    user.setId(req.getData().getId());
    user.setUserName(req.getData().getName());
    user.setUserCode(req.getData().getId());
    int i = demoServiceImpl.insert(user);
    if (i > 0) {
      list.add(user);
      res.setData(list);
      res.setMessage("添加用户成功！");
    } else {
      res.setMessage("添加用户失败！");
    }

    return res;
  }
 /**
  * 根据用户ID删除用户详细信息
   * @param req 删除用户信息的条件
   * @return ResponseDto 返回的结果
  */
  @ApiOperation(value = "根据用户ID删除用户详细信息")
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public ResponseDto deleteUser(@RequestBody RequestDto<UserDto> req) {
    ResponseDto result = new ResponseDto();
    try {

      int i = demoServiceImpl.deleteByPrimaryKey(req.getData().getId());
      if (i > 0) {
        result.setMessage("删除成功");
      } else {
        result.setMessage("删除失败！");
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
      result.setMessage("删除失败！");
    }
    return result;
  }
  /**
   * 根据用户ID更新用户详细信息
    * @param req 更新用户信息的条件
    * @return ResponseDto 返回的结果
   */
  @ApiOperation(value = "根据用户ID更新用户详细信息")
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public ResponseDto updateUser(@RequestBody RequestDto<UserDto> req) {
    ResponseDto result = new ResponseDto();
    DemoUser user = new DemoUser();
    user.setId(req.getData().getId());
    user.setUserName(req.getData().getName());
    user.setUserCode(req.getData().getId());
    try {
      int i = demoServiceImpl.updateByPrimaryKey(user);
      if (i > 0) {
        result.setMessage("更新成功！");
      } else {
        result.setMessage("更新失败！");
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
      result.setMessage("更新失败！");
    }
    return result;
  }
 /**
  * 特殊符号的校验
  * @param id 校验的参数
  * @return boolean
  */
  private boolean isValid(String id) {
    return !StringUtils.isEmpty(id) && id.matches("[0-9a-z]+");
  }
  /**
   * 根据用户id查询用户信息
    * @param req 查询用户信息的条件
    * @return ResponseDto 返回的结果
   */
  @ApiOperation("根据用户id查询用户信息")
  @RequestMapping(value = "/select", method = RequestMethod.POST)
  public ResponseDto selectUser(@RequestBody RequestDto<UserDto> req) {

    UserDto userDto = req.getData();
    if (!isValid(userDto.getId())) {
      throw new RuntimeException("If you see it, it means exception handler is working. ");
    }

    DemoUser userEntity = demoServiceImpl.selectByPrimaryKey(userDto.getId());

    ResponseDto res = super.getDefaultResponseDto();

    if (userEntity != null) {
      UserDto user = new UserDto();
      user.setName(userEntity.getUserName());
      user.setId(userEntity.getId());
      res.setData(user);
    }

    return res;

  }

}
