/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;

import com.eg.egsc.scp.paygateway.dto.UserDto;



/**
 * iotbus消费消息demo
 * queues = "Q_DEMO",接收的queue的名称
 * containerFactory="iotbusFactory",固定写法。【在框架commponet-rabbitmq中定义,详见com.eg.egsc.common.component.rabbitmq.RabbitConfig】
 * 使用时打开@Component和@RabbitListener注解
 * @author wanghongben
 * @since 2018年1月11日
 */
//@Component
//@RabbitListener(queues = "Q_TEST_001", containerFactory="iotbusFactory")
public class ReceiverListenerIotbusDemo{
  protected final Logger logger = LoggerFactory.getLogger(this.getClass());
  /**
   * 监听总线MQ，并获得消息队列信息
   * @param userDto 
   */
	@RabbitHandler
	public void processUserDto(UserDto userDto) {
		if (null != userDto) {
	
			logger.info("【RECEIVE】: iotbus mq:接收消息  userDto，name="+userDto.getName());
		}
	}
}
