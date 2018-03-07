/**
 * @Probject Name: egc-paymentgatewaycomponent-service
 * @Path: com.eg.egsc.scp.paygateway.serviceTestOrderQueryService.java
 * @Create By caiqinli
 * @Create In 2018年3月1日 下午3:17:18
 * TODO
 */
package com.eg.egsc.scp.paygateway.service;

import static org.junit.Assert.*;

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
    orderQueryRequestForBackendDto.setAppid("wx5332d47f724492fa");    
    orderQueryRequestForBackendDto.setMch_id("1497973582");
    orderQueryRequestForBackendDto.setOut_trade_no("44444444444441444");
    orderQueryRequestForBackendDto.setPlatform("WEIXIN");
    //orderQueryRequestForBackendDto.setTransaction_id(transaction_id);
    //orderQueryRequestForBackendDto.setBusinessId(businessId);
    
    OrderQueryResponseForBackendDto  orderQueryResponseForBackendDto  = 
        orderQueryService.orderQueryRequestFromBackendSystme(orderQueryRequestForBackendDto);
    assertEquals("4200000053201802100302371522",orderQueryResponseForBackendDto.getTransaction_id());
    
    
  }
  
  
  @Test
  public void queryOrderForWeiXinOrderNotExisted() {
    
    OrderQueryRequestForBackendDto orderQueryRequestForBackendDto = new OrderQueryRequestForBackendDto();
    orderQueryRequestForBackendDto.setAppid("wx5332d47f724492fa");    
    orderQueryRequestForBackendDto.setMch_id("1497973582");
    orderQueryRequestForBackendDto.setOut_trade_no("44444444444441488");
    orderQueryRequestForBackendDto.setPlatform("WEIXIN");
    //orderQueryRequestForBackendDto.setTransaction_id(transaction_id);
    //orderQueryRequestForBackendDto.setBusinessId(businessId);
    
    OrderQueryResponseForBackendDto  orderQueryResponseForBackendDto  = 
        orderQueryService.orderQueryRequestFromBackendSystme(orderQueryRequestForBackendDto);
    assertNotEquals("44444444444441488",orderQueryResponseForBackendDto.getOut_trade_no());
    
    
  }
  
  
  @Test
  public void queryOrderForAliPayOrderNotExisted() {
    
    OrderQueryRequestForBackendDto orderQueryRequestForBackendDto = new OrderQueryRequestForBackendDto();
    orderQueryRequestForBackendDto.setAppid("2018020102122242");    
    orderQueryRequestForBackendDto.setMch_id("1497973582");
    orderQueryRequestForBackendDto.setOut_trade_no("2018022300007149");
    orderQueryRequestForBackendDto.setPlatform("ALIPAY");
    //orderQueryRequestForBackendDto.setTransaction_id(transaction_id);
    //orderQueryRequestForBackendDto.setBusinessId(businessId);
    
    OrderQueryResponseForBackendDto  orderQueryResponseForBackendDto  = 
        orderQueryService.orderQueryRequestFromBackendSystme(orderQueryRequestForBackendDto);
    assertNotEquals("2018022821001004680594482848",orderQueryResponseForBackendDto.getOut_trade_no());    
    
  }
  
  @Test
  public void queryOrderForAliPayOrderExisted() {
    
    OrderQueryRequestForBackendDto orderQueryRequestForBackendDto = new OrderQueryRequestForBackendDto();
    orderQueryRequestForBackendDto.setAppid("2018020102122242");    
    orderQueryRequestForBackendDto.setMch_id("1497973582");
    orderQueryRequestForBackendDto.setOut_trade_no("2018022300007152");
    orderQueryRequestForBackendDto.setPlatform("ALIPAY");
    //orderQueryRequestForBackendDto.setTransaction_id(transaction_id);
    //orderQueryRequestForBackendDto.setBusinessId(businessId);
    
    OrderQueryResponseForBackendDto  orderQueryResponseForBackendDto  = 
        orderQueryService.orderQueryRequestFromBackendSystme(orderQueryRequestForBackendDto);
    System.out.println("=====orderQueryResponseForBackendDto.getTransaction_id(): "+orderQueryResponseForBackendDto.getTransaction_id());
    assertEquals("2018030121001004360285191410",orderQueryResponseForBackendDto.getTransaction_id());
    
    
  }
  
  
  

  /**
   * @Methods Name main
   * @Create In 2018年3月1日 By caiqinli
   * @param args void
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    TestOrderQueryService tq = new TestOrderQueryService();
    tq.queryOrderForAliPayOrderExisted();

  }

}
