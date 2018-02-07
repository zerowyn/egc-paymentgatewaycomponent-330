/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * 生产消息demo 继承Sender类，通过调用Sender中的convertAndSend往queue里发消息
 * @author wanghongben
 * @since 2018年1月11日
 */
@Component
public class RabbitmqTest{
	@Autowired
	private SenderDefaultDemo defaultSenderDemo;
	@Autowired
	private SenderIotbusDemo iotbusSenderDemo;

	
	@Autowired
	private SenderTopicDefaultDemo senderTopicDefaultDemo;
	@Autowired
	private SenderTopicIotbusDemo senderTopicIotbusDemo;
	/**
	 * 定时测试发送方式（test）
	 */
//	@Scheduled(cron = "0/20 * * * * ?")
	public void test() {
		defaultSenderDemo.sendMsg();
		iotbusSenderDemo.sendMsg();
	}
	 /**
   * 定时测试发送方式（testTopic）
   */
//	@Scheduled(cron = "0/30 * * * * ?")
	public void testTopic() {
		senderTopicDefaultDemo.sendDefaultTopic();
	}
  /**
   * 定时测试发送方式（testIotbusTopic）
   */
//	@Scheduled(cron = "0/50 * * * * ?")
	public void testIotbusTopic() {
		senderTopicIotbusDemo.sendIotbusTopic();
	}

}
