/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.mq;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.eg.egsc.config.AbstractUnitTestSupport;
import com.eg.egsc.scp.paygateway.mq.SenderDefaultDemo;
import com.eg.egsc.scp.paygateway.mq.SenderTopicDefaultDemo;

/**
 * 测试Rabbitmq的功能类
 * 
 * @author wanghongben
 * @since 2018年1月11日
 */

public class TestRabbitmq extends AbstractUnitTestSupport {


	@Autowired
	private SenderDefaultDemo defaultSenderDemo;
	@Autowired
	private SenderTopicDefaultDemo senderTopicDefaultDemo;

	/**
	 * 测试defaultSenderDemo发送方法
	 */
	@Test
	public void testSendQueue() {
		defaultSenderDemo.sendMsg();
	}

	/**
	 * 测试senderTopicDefaultDemo发送方法
	 */
	@Test
	public void testSendTopic() {
		senderTopicDefaultDemo.sendDefaultTopic();
	}
}
