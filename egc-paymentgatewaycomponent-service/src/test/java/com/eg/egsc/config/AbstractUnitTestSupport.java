/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.config;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.web.SpringBootMockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.support.GenericWebApplicationContext;

import com.eg.egsc.framework.client.core.ClientConfig;

/**
 * 测试单元的统一配置
 * @author wanghongben
 * @since 2018年1月23日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ClientConfig.class})
public abstract class AbstractUnitTestSupport {

  protected final Logger logger = Logger.getLogger(this.getClass());

  protected MockMvc mockMvc;

  @Autowired
  private GenericWebApplicationContext wac;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    logger.debug("##################################################");
    logger.debug(wac.getApplicationName());
    SpringBootMockServletContext servletContext =
        (SpringBootMockServletContext) wac.getServletContext();

    // 设置context-path
    servletContext.setContextPath("/paymentgateway");
  }

 
 /* protected void getController(String path, Map<String, String> params)
      throws DeviceCommonException {
    try {
      if (path == null || StringUtils.isEmpty(path))
        return;

      MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(path);
      if (params != null && !params.isEmpty()) {
        for (Map.Entry<String, String> k : params.entrySet()) {
          request = request.param(k.getKey(), k.getValue());
        }
      }

      mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
          .andDo(MockMvcResultHandlers.print()).andReturn();
    } catch (Exception e) {
      throw new DeviceCommonException("getController:{}", e.getMessage());
    }
  }

  protected static String createUser = "scpTestData2816515";
  private static String deviceTypeUuid = "0aa6e6218bd5412dbed5ff4a05c75269";
  private static String deviceType = "2002";
  private static String deviceTypeDesc = "球机";
  private static String courtUuid = "testCourtUuid";
*/
}
