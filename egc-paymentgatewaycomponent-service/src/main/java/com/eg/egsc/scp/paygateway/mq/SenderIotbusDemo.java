/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.eg.egsc.framework.client.mq.BaseIotbusMqSender;
import com.eg.egsc.scp.paygateway.dto.UserDto;


/**
 *  iotbus生产消息demo
 * @author wanghongben
 * @since 2018年1月11日
 */
@Component
public class SenderIotbusDemo extends BaseIotbusMqSender{
  protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static String Q_DEMO = "Q_TEST_001";// queue的名称
	/**
	 * 通过调用Sender中的convertAndSend往queue里发消息
	 */
	public void sendMsg() {
		UserDto dto = new UserDto();
		dto.setBusinessId("iotbususer1");
		dto.setSourceSysId("iotbususer2");
		dto.setTargetSysId("iotbususer3");
		dto.setId("iotbus2301");
		dto.setName("iotbus3r1");
		sendMessage(Q_DEMO, dto);
		logger.info("【SEND】: iotbus queue userDto, queue=" + Q_DEMO + ", name="+dto.getName());
	}
}
