///**
// * Copyright 2017-2025 Evergrande Group.
// */
//package com.eg.egsc.scp.paygateway.service.impl;
//
//import com.eg.egsc.scp.paygateway.PaymentGatewayServiceApplication;
//import com.eg.egsc.scp.paygateway.dto.RequestForGetOpenIdDto;
//import com.eg.egsc.scp.paygateway.service.CreateOrderService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
///**
// * @author lihui
// * @since 2018-03-19
// */
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {PaymentGatewayServiceApplication.class})
//public class CreateOrderServiceImplTest {
//
//    private static final Logger logger = LoggerFactory.getLogger(CreateOrderServiceImplTest.class);
//
//    @Autowired
//    private CreateOrderService createOrderServiceImpl;
//
//    @Test
//    public void testGetOpenId(){
//        RequestForGetOpenIdDto reqDto = new RequestForGetOpenIdDto();
//        reqDto.setCode("");
//        reqDto.setAppid("");
//        reqDto.setSecret("");
//        String openId = createOrderServiceImpl.getOpenId(reqDto);
//        logger.info(openId);
//    }
//
//}
