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
 * queues = "xxx",接收的queue的名称
 * containerFactory="iotbusFactory",固定写法。【在框架commponet-rabbitmq中定义,详见com.eg.egsc.common.component.rabbitmq.RabbitConfig】
 * 使用时打开@Component和@RabbitListener注解
 * @author wanghongben
 * @since 2018年1月11日
 */
//@Component
//@RabbitListener(queues = "IOTBUS_TOPIC_MESSAGE_01", containerFactory="iotbusFactory")
public class ReceiverTopicIotbusDemo{
  protected final Logger logger = LoggerFactory.getLogger(this.getClass());
  /**
   * 监听iotbus消费消息demo，并获得消息队列信息
   * @param userDto 
   */
	@RabbitHandler
	public void processUserDto(UserDto userDto) {
		if (null != userDto) {
			logger.info("【RECEIVE TOPIC】: 【IOTBUS_TOPIC_MESSAGE_01】 default mq:接收消息  userDto，name="+userDto.getName());
		}
	}
}

//@Component
//@RabbitListener(queues = "IOTBUS_TOPIC_MESSAGE_02", containerFactory="iotbusFactory")
class ReceiverTopicIotbusDemo2{
  protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@RabbitHandler
	public void processUserDto(UserDto userDto) {
		if (null != userDto) {
	     logger.info("【RECEIVE TOPIC】: 【IOTBUS_TOPIC_MESSAGE_02】 default mq:接收消息  userDto，name="+userDto.getName());
		}
	}
}
	
	
	
//@Component
//@RabbitListener(queues = "IOTBUS_TOPIC_TEST_03", containerFactory="iotbusFactory")
class ReceiverTopicIotbusDemo3{
  protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@RabbitHandler
	public void processUserDto(UserDto userDto) {
		if (null != userDto) {
		  logger.info("【RECEIVE TOPIC】: 【IOTBUS_TOPIC_MESSAGE_03】 default mq:接收消息  userDto，name="+userDto.getName());

		}
	}
}
