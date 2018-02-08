
package com.eg.egsc.scp.paygateway.web;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.eg.egsc.common.component.auth.model.User;
import com.eg.egsc.common.component.auth.web.SecurityContext;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.framework.service.base.web.BaseWebController;
import com.eg.egsc.scp.paygateway.mapper1.entity.DemoUser;
import com.eg.egsc.scp.paygateway.exception.DemoException;
import com.eg.egsc.scp.paygateway.service.DemoService;
import com.eg.egsc.scp.paygateway.util.ErrorCodeConstant;
import com.eg.egsc.scp.paygateway.web.vo.DemoUserPageVo;
import com.eg.egsc.scp.paygateway.web.vo.DemoUserVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;



/**
 * 用户相关Controller
 * 
 * @author wanghongben
 * @since 2018年1月11日
 */
@RestController
@RequestMapping(value = "/user")
public class DemoUserController extends BaseWebController {

  protected final Logger logger = LoggerFactory.getLogger(DemoUserController.class);

  @Autowired
  private DemoService demoServiceImpl;

  /**
   * 根据id获取用户对象
   * 
   * @param id 查询条件
   * @return DemoUserVo 返回的VO对象
   */
  @ApiOperation("根据id获取用户对象")
  @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
  public DemoUserVo getUser(@PathVariable("id") String id) {
    if (!isValid(id)) {
      throw new DemoException("demo.usermgnt.user.id.notblank");
    }

    DemoUserVo userVo = new DemoUserVo();

    DemoUser user = demoServiceImpl.getUser(id);
    if (user != null) {
      userVo.setUser(user);
    }

    return userVo;
  }


  /**
   * 此方法用于分页查询
   * 
   * @param currentPage 当前页码数
   * @param pageSize 当前页码显示的数据个数
   * @param userId 分页查询的条件
   * @return DemoUserPageVo 返回的值
   */
  @ApiOperation("此方法用于分页查询")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "currentPage", value = "当前页数", required = true, dataType = "String"),
      @ApiImplicitParam(name = "pageSize", value = "每页显示的个数", required = true, dataType = "String"),
      @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String")})
  @RequestMapping(value = "/queryPageData", method = RequestMethod.GET)
  public DemoUserPageVo queryPageData(@RequestParam(name = "currentPage") String currentPage,
      @RequestParam(name = "pageSize") String pageSize,
      @RequestParam(name = "userId") String userId) {
    if (!isValid(userId)) {
      throw new DemoException(ErrorCodeConstant.DEMO_USERMGNT_USERISBLANK);
    }
    DemoUserPageVo userPage = demoServiceImpl.queryForPage(currentPage, pageSize, userId);
    return userPage;
  }


  /**
   * 特殊字符的校验
   * 
   * @param id
   * @return boolean
   */
  private boolean isValid(String id) {
    return !StringUtils.isEmpty(id) && id.matches("[0-9a-z]+");
  }

  /**
   * 创建用户对象
   * 
   * @param user
   */
  @ApiOperation("新增用户信息")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "user", value = "新增用户信息", required = true, dataType = "DemoUser")})
  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public void createUser(@Valid @RequestBody DemoUser user) {
    demoServiceImpl.insert(user);
  }

  /**
   * 根据id删除用户对象
   * 
   * @param userId
   * @return ResponseDto
   */
  @ApiOperation("根据id删除用户对象")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "userId", value = "用户Id", required = true, dataType = "String")})
  @RequestMapping(value = "/delete", method = RequestMethod.GET)
  public ResponseDto deleteUser(@RequestParam(name = "userId") String userId) {
    ResponseDto result = new ResponseDto();
    demoServiceImpl.deleteByPrimaryKey(userId);
    return result;
  }

  /**
   * 根据id更新用户对象
   * 
   * @param user
   * @return String
   */
  @ApiOperation("根据id更新用户对象")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "user", value = "用户对象", required = true, dataType = "DemoUser")})
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String updateUser(@Valid @RequestBody DemoUser user) {
    String result = "Success!";
    demoServiceImpl.updateByPrimaryKey(user);
    return result;
  }

  /**
   * 根据roleid获取用户对象
   * 
   * @param id
   * @return List<DemoUser>
   */
  @ApiOperation("根据roleid获取用户对象")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "id", value = "用户Id", required = true, dataType = "String")})
  @RequestMapping(value = "/queryForList", method = RequestMethod.GET)
  public List<DemoUser> queryForList(@RequestParam(name = "id") String id) {

    if (!isValid(id)) {
      throw new DemoException("demo.usermgnt.user.id.notblank");
    }

    List<DemoUser> userlist = null;

    List<DemoUser> user = demoServiceImpl.queryForList(id);
    if (!user.isEmpty() && user.size() > 0) {
      userlist = user;
    }

    return userlist;
  }

  /**
   * 获取登录用户对象
   * 
   * @return User
   */
  @ApiOperation("获取登录用户对象")
  @RequestMapping(value = "/current", method = RequestMethod.GET)
  public User getUserInContext() {
    User userPrincipal = SecurityContext.getUserPrincipal();
    return userPrincipal;
  }
}
