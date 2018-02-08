/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.demo.api;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.eg.egsc.common.component.utils.BeanUtils;
import com.eg.egsc.config.AbstractUnitTestSupport;
import com.eg.egsc.framework.client.dto.Header;
import com.eg.egsc.framework.client.dto.RequestDto;
import com.eg.egsc.scp.paygateway.dto.UserDto;
import com.eg.egsc.scp.paygateway.mapper1.entity.DemoUser;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 测试Api的相关方法类
 * @author wanghongben
 * @since 2018年1月11日
 */

public class TestDemoApi extends AbstractUnitTestSupport {


  /**
   * 封装一个公共的测试对象
   * @return
   * @throws Exception RequestDto<UserDto>
   */
  public RequestDto<UserDto> newMockRequestDto() throws Exception {
    RequestDto<UserDto> requestDto = new RequestDto<UserDto>();
    DemoUser user = new DemoUser();
    user.setId("2110000");
    user.setUserCode("2110210");
    user.setUserName("211031");
    Header header = new Header();
    header.setBusinessId("testid");
    header.setSourceSysId("test");
    header.setTargetSysId("13214");
    header.setContentType("json");
    header.setCharset("utf-8");
    requestDto.setHeader(header);
    UserDto userDto = new UserDto();
    BeanUtils.copyProperties(userDto, user);
    requestDto.setData(userDto);
    return requestDto;
  }

  /**
   * 测试用户新增
   * @throws Exception 
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testCreate() throws Exception {
    this.testDelete();
    RequestDto<UserDto> requestDto = this.newMockRequestDto();
    ObjectMapper objectMapper = new ObjectMapper();
    String dto = objectMapper.writeValueAsString(requestDto);

    testDelete();
    RequestBuilder request = MockMvcRequestBuilders.post("/api/user/create")
        .contentType(MediaType.APPLICATION_JSON).content(dto.getBytes());

    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print()).andReturn();
  }

  /**
   * 测试查询所有用户对象
   * @throws Exception 
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testLists() throws Exception {
    RequestDto<UserDto> requestDto = this.newMockRequestDto();
    ObjectMapper objectMapper = new ObjectMapper();
    String dto = objectMapper.writeValueAsString(requestDto);

    RequestBuilder request = MockMvcRequestBuilders.post("/api/user/lists")
        .contentType(MediaType.APPLICATION_JSON).content(dto.getBytes());

    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print()).andReturn();
  }

  /**
   * 测试删除用户
   * @throws Exception 
   */
  @Test
  @Transactional
  public void testDelete() throws Exception {
    RequestDto<UserDto> requestDto = this.newMockRequestDto();
    ObjectMapper objectMapper = new ObjectMapper();
    String dto = objectMapper.writeValueAsString(requestDto);

    RequestBuilder request = MockMvcRequestBuilders.post("/api/user/delete")
        .contentType(MediaType.APPLICATION_JSON).content(dto.getBytes());

    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print()).andReturn();
  }

  /**
   * 测试更新用户信息
   * @throws Exception 
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testUpdateUser() throws Exception {
    RequestDto<UserDto> requestDto = this.newMockRequestDto();
    ObjectMapper objectMapper = new ObjectMapper();
    String dto = objectMapper.writeValueAsString(requestDto);

    RequestBuilder request = MockMvcRequestBuilders.post("/api/user/update")
        .contentType(MediaType.APPLICATION_JSON).content(dto.getBytes());

    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print()).andReturn();
  }

  /**
   * 测试根据用户信息查询用户
   * @throws Exception
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testselectUser() throws Exception {
    RequestDto<UserDto> requestDto = this.newMockRequestDto();
    ObjectMapper objectMapper = new ObjectMapper();
    String dto = objectMapper.writeValueAsString(requestDto);

    RequestBuilder request = MockMvcRequestBuilders.post("/api/user/select")
        .contentType(MediaType.APPLICATION_JSON).content(dto.getBytes());

    mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print()).andReturn();
  }
}
