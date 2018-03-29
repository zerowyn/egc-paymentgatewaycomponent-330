/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.client;

import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.scp.paygateway.PaymentGatewayServiceApplication;
import com.eg.egsc.scp.paygateway.dto.CreateOrderRequestForBackendDto;
import com.eg.egsc.scp.paygateway.util.PaymentBusinessConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试client的相关方法类
 *
 * @author lihui
 * @since 2018年3月5日
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {PaymentGatewayServiceApplication.class})
public class CreateOrderClientTest {

    private final static Logger logger = LoggerFactory.getLogger(CreateOrderClientTest.class);

    @Autowired
    private PaymentGatewayClient paymentGatewayClientImpl;

    @Test
    public void testCreateOrderClientImpl() {

        CreateOrderRequestForBackendDto dto = new CreateOrderRequestForBackendDto();
        dto.setPlatform(PaymentBusinessConstant.WEI_XIN);
        dto.setAppid("wx5332d47f724492fa");
        dto.setMchId("1497973582");
        dto.setBody("测试微信下单功能");
        dto.setDetail("用client测试微信下单");
        dto.setTradeType("APP");
        dto.setOutTradeNo("20180305004521400008");
        dto.setTotalFee("1");
        ResponseDto order = paymentGatewayClientImpl.createOrder(dto);
        logger.info(order.toString());
    }
}