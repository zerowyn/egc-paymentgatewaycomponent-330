/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.ssl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.eg.egsc.framework.client.core.BaseApiClient;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.scp.paygateway.DemoServiceApplication;

/**
 * 测试Rabbitmq的功能类
 * 
 * @author wanghongben
 * @since 2018年1月11日
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { DemoServiceApplication.class })
public class RequestTest {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private RequestService requestService;

	@Before
	public void setup() {
		MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	/**
	 * ssl Test
	 * TODO void
	 */
	@Test
	public void sslTest() {
		ImageDto userDto = new ImageDto();
		userDto.setBusinessId("DM0000000119");
		userDto.setSourceSysId("demo");
		userDto.setTargetSysId("api");
		((BaseApiClient)requestService).setServiceUrl("https://scp.hdsc.com");
		try{
			ResponseDto res = requestService.post("/api/image/download", userDto);
			System.out.println(" ----------------- 111111111 "+res.toString());;
		}catch(Exception e){
			System.out.println(" ********* 11111 exception ");
		}
		
		System.out.println(" =============================================  ");
		((BaseApiClient)requestService).setServiceUrl("http://192.168.0.242:39046");
		ResponseDto res1 = requestService.post("/api/image/download", userDto);
		System.out.println(" ----------------- 222222222 "+res1.toString());;
	}
}
