/**
 * @Probject Name: egc-paymentgatewaycomponent-service
 * @Path: com.eg.egsc.scp.paygateway.serviceTestOrderQueryService.java
 * @Create By caiqinli
 * @Create In 2018年3月1日 下午3:17:18
 * TODO
 */
package com.eg.egsc.scp.paygateway.service;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.eg.egsc.config.AbstractUnitTestSupport;
import com.eg.egsc.scp.paygateway.dto.OrderQueryRequestForBackendDto;
import com.eg.egsc.scp.paygateway.dto.OrderQueryResponseForBackendDto;
import com.eg.egsc.scp.paygateway.service.impl.OrderQueryServiceImpl;

/**
 * @Class Name TestOrderQueryService
 * @Author caiqinli
 * @Create In 2018年3月1日
 */
public class TestOrderQueryService extends AbstractUnitTestSupport {
  
  @Autowired
  private OrderQueryServiceImpl orderQueryService;
  
  
  
  @Test
  public void queryOrderForWeiXinOrderExisted() {
    
    OrderQueryRequestForBackendDto orderQueryRequestForBackendDto = new OrderQueryRequestForBackendDto();
    orderQueryRequestForBackendDto.setAppId("wx5332d47f724492fa");    
    orderQueryRequestForBackendDto.setMchId("1497973582");
    orderQueryRequestForBackendDto.setOutTradeNo("44444444444441444");
    orderQueryRequestForBackendDto.setPlatform("WEIXIN");
    
    OrderQueryResponseForBackendDto  orderQueryResponseForBackendDto  = 
        orderQueryService.orderQueryRequestFromBackendSystme(orderQueryRequestForBackendDto);
    logger.info("getAppId: "+orderQueryResponseForBackendDto.getAppId());
    logger.info("getMchId: "+orderQueryResponseForBackendDto.getMchId());
    logger.info("getOpenId: "+orderQueryResponseForBackendDto.getOpenId());
    logger.info("getOutTradeNo: "+orderQueryResponseForBackendDto.getOutTradeNo());
    logger.info("getTransactionId: "+orderQueryResponseForBackendDto.getTransactionId());
    logger.info("getTradeState: "+orderQueryResponseForBackendDto.getTradeState());
    logger.info("getTradeStateDesc: "+orderQueryResponseForBackendDto.getTradeStateDesc());   
    assertEquals("4200000053201802100302371522",orderQueryResponseForBackendDto.getTransactionId());
    
    
  }
  
  
  @Test
  public void queryOrderForWeiXinOrderNotExisted() {
    
    OrderQueryRequestForBackendDto orderQueryRequestForBackendDto = new OrderQueryRequestForBackendDto();
    orderQueryRequestForBackendDto.setAppId("wx5332d47f724492fa");    
    orderQueryRequestForBackendDto.setMchId("1497973582");
    orderQueryRequestForBackendDto.setOutTradeNo("44444444444441488");
    orderQueryRequestForBackendDto.setPlatform("WEIXIN");
    //orderQueryRequestForBackendDto.setTransaction_id(transaction_id);
    //orderQueryRequestForBackendDto.setBusinessId(businessId);    
    
    OrderQueryResponseForBackendDto  orderQueryResponseForBackendDto  = 
        orderQueryService.orderQueryRequestFromBackendSystme(orderQueryRequestForBackendDto);
    
    logger.info("getAppId: "+orderQueryResponseForBackendDto.getAppId());
    logger.info("getMchId: "+orderQueryResponseForBackendDto.getMchId());
    logger.info("getOpenId: "+orderQueryResponseForBackendDto.getOpenId());
    logger.info("getOutTradeNo: "+orderQueryResponseForBackendDto.getOutTradeNo());
    logger.info("getTransactionId: "+orderQueryResponseForBackendDto.getTransactionId());
    logger.info("getTradeState: "+orderQueryResponseForBackendDto.getTradeState());
    logger.info("getTradeStateDesc: "+orderQueryResponseForBackendDto.getTradeStateDesc()); 
    
    assertNotEquals("44444444444441488",orderQueryResponseForBackendDto.getOutTradeNo());
    
    
  }
  
  
  @Test
  public void queryOrderForAliPayOrderNotExisted() {
    
    OrderQueryRequestForBackendDto orderQueryRequestForBackendDto = new OrderQueryRequestForBackendDto();
    orderQueryRequestForBackendDto.setAppId("2018020102122242");    
    orderQueryRequestForBackendDto.setMchId("1497973582");
    orderQueryRequestForBackendDto.setOutTradeNo("2018022300007149");
    orderQueryRequestForBackendDto.setPlatform("ALIPAY");
    orderQueryRequestForBackendDto.setBusinessId("businessId");
    orderQueryRequestForBackendDto.setExtAttributes(new HashMap());
    orderQueryRequestForBackendDto.setSourceSysId("string");
    orderQueryRequestForBackendDto.setTargetSysId("string");
    orderQueryRequestForBackendDto.setTransactionId("");
    
    OrderQueryResponseForBackendDto  orderQueryResponseForBackendDto  = 
        orderQueryService.orderQueryRequestFromBackendSystme(orderQueryRequestForBackendDto);
    
    logger.info("getAppId: "+orderQueryResponseForBackendDto.getAppId());
    logger.info("getMchId: "+orderQueryResponseForBackendDto.getMchId());
    logger.info("getOpenId: "+orderQueryResponseForBackendDto.getOpenId());
    logger.info("getOutTradeNo: "+orderQueryResponseForBackendDto.getOutTradeNo());
    logger.info("getTransactionId: "+orderQueryResponseForBackendDto.getTransactionId());
    logger.info("getTradeState: "+orderQueryResponseForBackendDto.getTradeState());
    logger.info("getTradeStateDesc: "+orderQueryResponseForBackendDto.getTradeStateDesc()); 
    
    assertNotEquals("2018022821001004680594482848",orderQueryResponseForBackendDto.getOutTradeNo());    
    
  }
  
  @Test
  public void queryOrderForAliPayOrderExisted() {
    
    OrderQueryRequestForBackendDto orderQueryRequestForBackendDto = new OrderQueryRequestForBackendDto();
    orderQueryRequestForBackendDto.setAppId("2018020102122242");    
    orderQueryRequestForBackendDto.setMchId("1497973582");
    orderQueryRequestForBackendDto.setOutTradeNo("2018022300007150");
    orderQueryRequestForBackendDto.setPlatform("ALIPAY");
    orderQueryRequestForBackendDto.setBusinessId("businessId");
    orderQueryRequestForBackendDto.setExtAttributes(new HashMap());
    orderQueryRequestForBackendDto.setSourceSysId("string");
    orderQueryRequestForBackendDto.setTargetSysId("string");
    orderQueryRequestForBackendDto.setTransactionId("");
    
    OrderQueryResponseForBackendDto  orderQueryResponseForBackendDto  = 
        orderQueryService.orderQueryRequestFromBackendSystme(orderQueryRequestForBackendDto);
    
    logger.info("getAppId: "+orderQueryResponseForBackendDto.getAppId());
    logger.info("getMchId: "+orderQueryResponseForBackendDto.getMchId());
    logger.info("getOpenId: "+orderQueryResponseForBackendDto.getOpenId());
    logger.info("getOutTradeNo: "+orderQueryResponseForBackendDto.getOutTradeNo());
    logger.info("getTransactionId: "+orderQueryResponseForBackendDto.getTransactionId());
    logger.info("getTradeState: "+orderQueryResponseForBackendDto.getTradeState());
    logger.info("getTradeStateDesc: "+orderQueryResponseForBackendDto.getTradeStateDesc());     
    
    assertEquals("2018022821001004680594482848",orderQueryResponseForBackendDto.getTransactionId());    
  }


}
