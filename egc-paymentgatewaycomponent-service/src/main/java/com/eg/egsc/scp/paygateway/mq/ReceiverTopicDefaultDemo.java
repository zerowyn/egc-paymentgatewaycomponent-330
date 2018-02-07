/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Component;

import com.eg.egsc.scp.paygateway.dto.UserDto;



/**
 * topic消费demo
 * queues = "xxx",接收的queue的名称
 * containerFactory="rlcFactory",固定写法。【在框架commponet-rabbitmq中定义,详见com.eg.egsc.common.component.rabbitmq.RabbitConfig】
 * 使用时打开@Component和@RabbitListener注解
 * @author wanghongben
 * @since 2018年1月11日
 */
@Component
//@RabbitListener(queues = "DEFAULT_TOPIC_MESSAGE_01", containerFactory="rlcFactory")
public class ReceiverTopicDefaultDemo{
  protected final Logger logger = LoggerFactory.getLogger(this.getClass());
  /**
   * 监听消费demo，并获得消息队列信息
   * @param userDto 
   */
	@RabbitHandler
	public void processUserDto(UserDto userDto) {
		if (null != userDto) {
			logger.info("【RECEIVE TOPIC】: 【DEFAULT_TOPIC_MESSAGE_01】 default mq:接收消息  userDto，name="+userDto.getName());
		}
	}

}

//@Component
//@RabbitListener(queues = "DEFAULT_TOPIC_MESSAGE_02", containerFactory="rlcFactory")
class ReceiverTopicDefaultDemo2{
  protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@RabbitHandler
	public void processUserDto(UserDto userDto) {
		if (null != userDto) {
		  logger.info("【RECEIVE TOPIC】: 【DEFAULT_TOPIC_MESSAGE_02】 default mq:接收消息  userDto，name="+userDto.getName());
		}
	}
}

//@Component
//@RabbitListener(queues = "DEFAULT_TOPIC_TEST_03", containerFactory="rlcFactory")
class ReceiverTopicDefaultDemo3{
  protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@RabbitHandler
	public void processUserDto(UserDto userDto) {
		if (null != userDto) {
	     logger.info("【RECEIVE TOPIC】: 【DEFAULT_TOPIC_TEST_03】 default mq:接收消息  userDto，name="+userDto.getName());
		}
	}
}
