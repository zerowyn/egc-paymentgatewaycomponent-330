/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.ssl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.eg.egsc.common.exception.CommonException;
import com.eg.egsc.framework.client.core.BaseApiClient;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.scp.paygateway.dto.UserDto;


/**
 * TODO
 * @author songjie
 * @since 2018年1月22日
 */
@Component
public class RequestService extends BaseApiClient {

//	 private String defaultHost = "https://scp.hdsc.com";
	private String defaultHost = "";
	/* (non-Javadoc)
	 * @see com.eg.egsc.framework.client.core.BaseApiClient#getContextPath()
	 */
	@Override
	protected String getContextPath() {
		// TODO Auto-generated method stub
		return defaultHost;
	}

	public ResponseDto post(String url, UserDto userDto) throws CommonException {
		if(StringUtils.isBlank(url)){
			url = defaultHost;
		}
		return super.post(url, userDto);
	}
	
	public ResponseDto post(String url, ImageDto imageDto) throws CommonException {
		if(StringUtils.isBlank(url)){
			url = defaultHost;
		}
		return super.post(url, imageDto);
	}

}
