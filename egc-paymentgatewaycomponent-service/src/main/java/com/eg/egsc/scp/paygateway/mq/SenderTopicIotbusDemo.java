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
 * 生产消息demo 继承BaseIotbusMqSender类
 * @author wanghongben
 * @since 2018年1月11日
 */
@Component
public class SenderTopicIotbusDemo extends BaseIotbusMqSender {
  protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 通过调用Sender中的sendTopic往queue里发消息
	 */
	public void sendIotbusTopic() {
		UserDto userDto = new UserDto();
		userDto.setBusinessId("businessId-iotbususer1_topic");
		userDto.setSourceSysId("sourceSysId-iotbususer1_topic");
		userDto.setTargetSysId("targetSysId-iotbususer1_topic");
		userDto.setId("iotbus0001_topic");
		userDto.setName("iotbus_all_topic");
		
		String routingKey1 = "message.test.topic";
		sendTopic(routingKey1, userDto);
		logger.info("【SEND TOPIC】 : iotbus queue userDto, routingKey=" + routingKey1 + ", name="+userDto.getName());
		userDto.setName("iotbus_only_test03_topic");
		String routingKey2 = "message.only.topic";
		sendTopic(routingKey2, userDto);
		logger.info("【SEND TOPIC】 : iotbus queue userDto, routingKey=" + routingKey2 + ", name="+userDto.getName());
	}
}
