/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.client.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.eg.egsc.common.exception.CommonException;
import com.eg.egsc.framework.client.core.BaseApiClient;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.scp.paygateway.client.DemoUserClient;
import com.eg.egsc.scp.paygateway.dto.UserDto;

/**
 * 外部组件与组件调用的接口实现类
 * @author wanghongben
 * @since 2018年1月11日
 */
@Component
public class DemoUserClientImpl extends BaseApiClient implements DemoUserClient {

	public DemoUserClientImpl() {
		super();
	}
 
	/**
	 * @param gatewayUrl
	 */
	public DemoUserClientImpl(String gatewayUrl) {
		super(gatewayUrl);
	}
  
	/* (non-Javadoc)
	 * @see com.eg.egsc.framework.client.core.BaseApiClient#getContextPath()
	 */
	@Override
	protected String getContextPath() {
		return "/paymentgateway";
	}
  /* (non-Javadoc)
   * @see com.eg.egsc.scp.paygateway.client.DemoUserClient#createUser(com.eg.egsc.scp.paygateway.dto.UserDto)
   */
  @Override
	public void createUser(UserDto userDto) throws CommonException {
		post("/api/user/create", userDto);
		return;
	}
  
 /* (non-Javadoc)
 * @see com.eg.egsc.scp.paygateway.client.DemoUserClient#deleteUser(com.eg.egsc.scp.paygateway.dto.UserDto)
 */
@Override
  public void deleteUser(UserDto userDto) throws CommonException {
		post("/api/user/delete", userDto);
		return;
	}

	/* (non-Javadoc)
	 * @see com.eg.egsc.scp.paygateway.client.DemoUserClient#updateUser(com.eg.egsc.scp.paygateway.dto.UserDto)
	 */
	@Override
  public void updateUser(UserDto userDto) throws CommonException {
		post("/api/user/update", userDto);
		return;
	}

	/* (non-Javadoc)
	 * @see com.eg.egsc.scp.paygateway.client.DemoUserClient#selectUser(com.eg.egsc.scp.paygateway.dto.UserDto)
	 */
	@Override
  public UserDto selectUser(UserDto userDto) throws CommonException {
		ResponseDto res = post("/api/user/select", userDto);
		return (UserDto) res.getData();
	}

	/* (non-Javadoc)
	 * @see com.eg.egsc.scp.paygateway.client.DemoUserClient#getUserList(com.eg.egsc.scp.paygateway.dto.UserDto)
	 */
	@Override
  @SuppressWarnings("unchecked")
	public List<UserDto> getUserList(UserDto userDto) throws CommonException {
		ResponseDto res = post("/api/user/lists", userDto);
		return (List<UserDto>) res.getData();
	}
}
