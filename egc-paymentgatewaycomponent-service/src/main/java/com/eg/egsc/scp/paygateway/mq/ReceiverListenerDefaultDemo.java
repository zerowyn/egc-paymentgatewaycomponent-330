/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.eg.egsc.scp.paygateway.dto.UserDto;


/**
 * 消费消息demo queues = "Q_DEMO",接收的queue的名称
 * containerFactory="rlcFactory",固定写法。【在框架commponet
 * -rabbitmq中定义,详见com.eg.egsc.common.component.rabbitmq.RabbitConfig】 使用时打开@Component和@RabbitListener注解
 * 
 * @author wanghongben
 * @since 2018年1月11日
 */
@Component
@RabbitListener(queues = "Q_DEMO_002", containerFactory = "rlcFactory")
public class ReceiverListenerDefaultDemo {
  protected final Logger logger = LoggerFactory.getLogger(this.getClass());
  /**
   * 
   * 监听MQ，并获得消息队列信息
   * @param userDto void
   */
	@RabbitHandler
	public void processUserDto(UserDto userDto) {
		if (null != userDto) {
		  logger.info("【RECEIVE】: default mq:接收消息  userDto，name="+userDto.getName());
		}
	}
}
