/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.eg.egsc.framework.client.mq.BaseDefaultMqSender;
import com.eg.egsc.scp.paygateway.dto.UserDto;


/**
 * 生产消息demo 继承Sender类
 * @author wanghongben
 * @since 2018年1月11日
 */
@Component
public class SenderTopicDefaultDemo extends BaseDefaultMqSender {
  protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 通过调用Sender中的sendTopic往queue里发消息
	 */
	public void sendDefaultTopic() {
		UserDto userDto = new UserDto();
		userDto.setBusinessId("businessId-user1_topic");
		userDto.setSourceSysId("sourceSysId-user1_topic");
		userDto.setTargetSysId("targetSysId-user1_topic");
		userDto.setId("0001_topic");
		userDto.setName("userDto_only_test03_topic");
		
		String routingKey1 = "message.only.topic";
		sendTopic(routingKey1, userDto);
		logger.info("【SEND TOPIC】 : default queue userDto, routingKey=" + routingKey1 + ", name="+userDto.getName());
	}
}
