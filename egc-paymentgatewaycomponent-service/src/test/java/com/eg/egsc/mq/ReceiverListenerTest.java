/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.mq;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.eg.egsc.config.AbstractUnitTestSupport;
import com.eg.egsc.scp.paygateway.dto.UserDto;
import com.eg.egsc.scp.paygateway.mq.ReceiverListenerDefaultDemo;

/**
 * 测试MQ的监听类
 * @author wanghongben
 * @since 2018年1月11日
 */

public class ReceiverListenerTest extends AbstractUnitTestSupport {


  @Autowired
  private ReceiverListenerDefaultDemo receiverListenerDefaultDemo;

  

  /**
   * 监听MQ信息，并获得消息队列数据
   * @throws Exception 
   */
  @Test
  @Transactional
  @Rollback(true)
  public void testSenderMq() {
    UserDto userDto = new UserDto();
    userDto.setName("http://localhost");
    userDto.setBusinessId("222");
    receiverListenerDefaultDemo.processUserDto(userDto);
  }
}
