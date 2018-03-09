/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.api;

import com.eg.egsc.framework.client.dto.Header;
import com.eg.egsc.framework.client.dto.RequestDto;
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

import java.util.HashMap;

/**
 * 测试Api的相关方法类
 * @author lihui
 * @since 2018年3月5日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {PaymentGatewayServiceApplication.class})
public class CreateOrderApiTest {

    private static final Logger logger = LoggerFactory.getLogger(CreateOrderApiTest.class);

    @Autowired
    private CreateOrderApi createOrderApi;

    /**
     * 微信下单功能测试
     * @return
     * @throws Exception
     */
    @Test
    public void createOrderForWeiXin(){
        ResponseDto order = null;
        try {
            RequestDto<CreateOrderRequestForBackendDto> req = new RequestDto<>();
            CreateOrderRequestForBackendDto dto = new CreateOrderRequestForBackendDto();
            dto.setPlatform(PaymentBusinessConstant.WEI_XIN);
            dto.setAppid("wx5332d47f724492fa");
            dto.setTotalFee("1");
            dto.setBody("测试微信下单功能");
            dto.setTradeType("APP");
            dto.setOutTradeNo("2018030800000001");
            dto.setMchId("1497973582");
            req.setData(dto);
            Header header = new Header();
            header.setBusinessId("tetfgyf");
            header.setCharset("utf-8");
            header.setSourceSysId("fyrehh");
            header.setContentType("application/json");
            header.setCreateTimestamp(165456465465L);
            HashMap<String, Object> extMap = new HashMap<>();
            extMap.put("test","test01");
            header.setExtAttributes(extMap);
            req.setHeader(header);
            order = createOrderApi.createOrder(req);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.info("微信返回给缴费后台的实体类-->{}",order);
    }

    /**
     * 支付宝下单功能测试
     * @return
     * @throws Exception
     */
    @Test
    public void createOrderForAliPay(){
        ResponseDto order = null;
        try {
            RequestDto<CreateOrderRequestForBackendDto> req = new RequestDto<>();
            CreateOrderRequestForBackendDto dto = new CreateOrderRequestForBackendDto();
            dto.setPlatform(PaymentBusinessConstant.ALI_PAY);
            dto.setAppid("2018020102122242");
            dto.setOutTradeNo("2018022300007158");
            dto.setTotalFee("0.02");
            req.setData(dto);
            dto.setBody("测试支付宝下单功能");
            Header header = new Header();
            header.setCharset("utf-8");
            header.setContentType("application/json");
            header.setSourceSysId("dfsd454545tew");
            header.setCreateTimestamp(16545589745L);
            header.setBusinessId("fsdfdsf54654");
            HashMap<String, Object> extMap = new HashMap<>();
            extMap.put("test","test02");
            header.setExtAttributes(extMap);
            req.setHeader(header);
            order = createOrderApi.createOrder(req);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.info("支付宝返回给缴费后台的实体类-->{}",order);
    }

}