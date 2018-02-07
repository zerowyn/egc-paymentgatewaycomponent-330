/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.eg.egsc.common.component.utils.JsonUtil;
import com.eg.egsc.config.AbstractUnitTestSupport;
import com.eg.egsc.scp.paygateway.mapper.entity.DemoUser;
import com.eg.egsc.scp.paygateway.mapper.entity.DemoUserCriteria;
import com.eg.egsc.scp.paygateway.service.impl.DemoServiceImpl;

/**
 * 测试Service服务的类
 * @author wanghongben
 * @since 2018年1月11日
 */
public class TestDemoService extends AbstractUnitTestSupport {


	@Autowired
	private DemoServiceImpl demoService;

	/**
	 * 封装一个用户对象
	 */
//	@Test
	@Transactional
	@Rollback(true)
	public void insert() {
	  DemoUserCriteria example = new DemoUserCriteria();
    example.createCriteria().andCreateUserIsNull();
    int userCount = demoService.countByExample(example);
		DemoUser user = new DemoUser();
		user.setId("678311222"+userCount+"");
		user.setUserCode("6783111222");
		user.setUserName("TEST");
		user.setTenantId("67832002");
		user.setCreateTime(new Date());
		user.setCreateUser("wanger");
		user.setUpdateTime(new Date());
		user.setUpdateUser("wanger");
		user.setUserDesc("21432");
		demoService.insert(user);
		JsonUtil.toJsonString(user);
	}
	
	/**
	 * 批量插入用户对象
	 * @Methods Name insertBatch
	 * @Create In 2018年1月16日 By songjie void
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void insertBatch() {
		List<DemoUser> list = new ArrayList<DemoUser>();
		for(int i=1;i<4;i++){
			DemoUserCriteria example = new DemoUserCriteria();
		    example.createCriteria().andCreateUserIsNull();
		    int userCount = demoService.countByExample(example);
			DemoUser user = new DemoUser();
			user.setId("778311222" + userCount + i);
			user.setUserCode("7783111222"+i);
			user.setUserName("000"+i);
			user.setTenantId("67832002");
			user.setCreateTime(new Date());
			user.setCreateUser("zhangsan"+i);
			user.setUpdateTime(new Date());
			user.setUpdateUser("zhangsan"+i);
			user.setUserDesc("21432"+i);
			list.add(user);
			JsonUtil.toJsonString(user);	
		}
		if(CollectionUtils.isNotEmpty(list)){
			demoService.insertByBatch(list);
		}
	  
	}
	
	
	/**
	 * 测试根据条件查询用户数量
	 */
	@Test
	@Transactional
	@Rollback(true)
	public void countByExample() {
		DemoUserCriteria example = new DemoUserCriteria();
		example.createCriteria().andCreateUserIsNull();
		int userCount = demoService.countByExample(example);
		JsonUtil.toJsonString(userCount);
	}
	
  @Test
  public void getAllUser() {

    List<DemoUser> userList = demoService.getAllUser();
    JsonUtil.toJsonString(userList);
  }
  @Test
  public void getAllUserByRoleId() {
    List<DemoUser> userList = demoService.getAllUserByRoleId("zhangsan");
    JsonUtil.toJsonString(userList);
  }
  @Test
  public void getUser() {
    JsonUtil.toJsonString(demoService.getUser("123"));
  }
  @Test
  public void queryForList() {
     demoService.queryForList("1345");
  }
  @Test
  public void selectByPrimaryKey() {
     demoService.selectByPrimaryKey("24354");
  }
  @Test
  public void queryOnePageDataByCondition() {
    DemoUser demoUser = new DemoUser();
    demoUser.setId("778311222");
    demoUser.setTenantId("67832002");
  //   demoService.queryForPage("1", "2", "263423");
     demoService.queryOnePageDataByCondition(1, 2, demoUser);
  }
}
