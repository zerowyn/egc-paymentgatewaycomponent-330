/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.demo.web;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.eg.egsc.config.AbstractUnitTestSupport;
import com.eg.egsc.scp.paygateway.mapper1.entity.DemoUser;
import com.fasterxml.jackson.databind.ObjectMapper;


public class TestDemoWebController extends AbstractUnitTestSupport {

	public DemoUser mockDemoUser() throws Exception {

		DemoUser user = new DemoUser();
		user.setId("211013");
		user.setUserCode("21104646");
		user.setUserName("211032");
		return user;
	}

	@Test
	public void testExceptionHandler() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/user/get/1*").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is5xxServerError()).andDo(MockMvcResultHandlers.print()).andReturn();

		result.getResponse().getContentAsString();
	}

	@Test 
	@Transactional 
	@Rollback(true) 
	public void testQueryPageData() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/user/queryPageData?currentPage=1&pageSize=5&userId=1000")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();

		result.getResponse().getContentAsString();
	}
	@Test 
	@Transactional 
	@Rollback(true) 
	public void testCreate() throws Exception {
		DemoUser user = this.mockDemoUser();
		ObjectMapper objectMapper = new ObjectMapper();
		String dto = objectMapper.writeValueAsString(user);
		logger.info(dto);

		testDelete();
		RequestBuilder request = MockMvcRequestBuilders.post("/user/create")
				.contentType(MediaType.APPLICATION_JSON).content(dto.getBytes());

		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andReturn();
	}

	@Test 
	@Transactional 
	@Rollback(true) 
	public void testUpdate() throws Exception {
		DemoUser user = this.mockDemoUser();
		ObjectMapper objectMapper = new ObjectMapper();
		String dto = objectMapper.writeValueAsString(user);

		RequestBuilder request = MockMvcRequestBuilders.post("/user/update")
				.contentType(MediaType.APPLICATION_JSON).content(dto.getBytes());

		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
				.andReturn();
	}

	@Test 
	@Transactional 
	@Rollback(true) 
	public void testDelete() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/user/delete?userId=2110000")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();

		result.getResponse().getContentAsString();
	}
	@Test 
	@Transactional 
	@Rollback(true) 
	public void testGetAllUserByRoleId() throws Exception {
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/user/queryForList?id=111")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();

		result.getResponse().getContentAsString();
	}
	 @Test 
	  @Transactional 
	  @Rollback(true) 
	  public void testGetUserPrincipal() throws Exception {
	    MvcResult result = mockMvc
	        .perform(MockMvcRequestBuilders.get("/user/current")
	            .contentType(MediaType.APPLICATION_JSON))
	        .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();

	    result.getResponse().getContentAsString();
	  }
}
