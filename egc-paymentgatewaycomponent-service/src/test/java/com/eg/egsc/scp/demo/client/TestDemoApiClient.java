/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.demo.client;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.eg.egsc.config.AbstractUnitTestSupport;
import com.eg.egsc.framework.client.core.BaseApiClient;
import com.eg.egsc.scp.paygateway.client.DemoUserClient;
import com.eg.egsc.scp.paygateway.dto.UserDto;
import com.eg.egsc.scp.paygateway.exception.DemoException;


public class TestDemoApiClient extends AbstractUnitTestSupport {
  // protected final Logger logger = LoggerFactory.getLogger(TestDemoApiClient.class);

  @Autowired
  private DemoUserClient demoUserClientImpl;

  @Test
  public void testDemoUseClient() {

    try {
      UserDto user = new UserDto();
      user.setBusinessId("buzId");
      user.getExtAttributes().put("ext1", "extvalue1");
      user.getExtAttributes().put("ext2", "extvalue2");
      user.setId("1");

      ((BaseApiClient) demoUserClientImpl).setServiceUrl("http://localhost:8082");
      List<UserDto> list = demoUserClientImpl.getUserList(user);
      logger.info(list.toString());
    } catch (DemoException e) {
      logger.error(e.getMessage());
      // Do something if needed.
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
