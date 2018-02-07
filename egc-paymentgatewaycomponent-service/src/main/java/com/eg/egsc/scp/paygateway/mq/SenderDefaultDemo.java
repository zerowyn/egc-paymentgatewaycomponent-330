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
public class SenderDefaultDemo extends BaseDefaultMqSender {
  protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static String Q_DEMO = "Q_DEMO_002";// queue的名称
	
	/**
	 * 通过调用Sender中的convertAndSend往queue里发消息
	 */
	public void sendMsg() {
		UserDto userDto = new UserDto();
		userDto.setBusinessId("businessId");
		userDto.setSourceSysId("sourceSysId");
		userDto.setTargetSysId("targetSysId");
		userDto.setId("001");
		userDto.setName("user");
		sendMessage(Q_DEMO, userDto);
		logger.info("【SEND】: default queue userDto, queue=" + Q_DEMO + ", name="+userDto.getName());
	}
}
