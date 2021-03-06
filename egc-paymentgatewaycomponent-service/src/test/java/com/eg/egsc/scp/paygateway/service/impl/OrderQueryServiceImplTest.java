/**
 * @Probject Name: egc-paymentgatewaycomponent-service
 * @Path: com.eg.egsc.scp.paygateway.serviceTestOrderQueryService.java
 * @Create By caiqinli
 * @Create In 2018年3月1日 下午3:17:18
 * TODO
 */
package com.eg.egsc.scp.paygateway.service.impl;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.eg.egsc.scp.paygateway.PaymentGatewayServiceApplication;
import com.eg.egsc.scp.paygateway.dto.OrderQueryRequestForBackendDto;
import com.eg.egsc.scp.paygateway.dto.OrderQueryResponseForBackendDto;
import com.eg.egsc.scp.paygateway.service.OrderQueryService;
import com.eg.egsc.scp.paygateway.service.impl.OrderQueryServiceImpl;

/**
 * @Class Name TestOrderQueryService
 * @Author caiqinli
 * @Create In 2018年3月1日
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PaymentGatewayServiceApplication.class})
public class OrderQueryServiceImplTest {
  
  @Autowired
  private OrderQueryService orderQueryServiceImpl;
  
  private static final String MCHID = "1497973582";
  private static final String TEMP_PARAMETER = "string";
  
  
  @Test
  public void queryOrderRequestDtoIsNull() {   
    
    OrderQueryRequestForBackendDto orderQueryRequestForBackendDto = null;    
    
    OrderQueryResponseForBackendDto  orderQueryResponseForBackendDto  =   
        orderQueryServiceImpl.orderQueryRequestFromBackendSystme(orderQueryRequestForBackendDto);
    
    assertEquals("调用出现异常，因为请求方发送过来的数据是空的",orderQueryResponseForBackendDto.getErrCodeDes());
    
    
  }
  
  
  @Test
  public void queryOrderForWeiXinOrderExisted() {   
    
    OrderQueryRequestForBackendDto orderQueryRequestForBackendDto = new OrderQueryRequestForBackendDto();
    orderQueryRequestForBackendDto.setAppId("wx5332d47f724492fa");    
    orderQueryRequestForBackendDto.setMchId(MCHID);
    orderQueryRequestForBackendDto.setOutTradeNo("44444444444441444");
    orderQueryRequestForBackendDto.setPlatform("WEIXIN");
    
    OrderQueryResponseForBackendDto  orderQueryResponseForBackendDto  =   orderQueryServiceImpl.orderQueryRequestFromBackendSystme(orderQueryRequestForBackendDto);
    
    assertEquals("4200000053201802100302371522",orderQueryResponseForBackendDto.getTransactionId());
    
    
  }
  
  
  @Test
  public void queryOrderForWeiXinOrderNotExisted() {
    
    OrderQueryRequestForBackendDto orderQueryRequestForBackendDto = new OrderQueryRequestForBackendDto();
    orderQueryRequestForBackendDto.setAppId("wx5332d47f724492fa");    
    orderQueryRequestForBackendDto.setMchId(MCHID);
    orderQueryRequestForBackendDto.setOutTradeNo("44444444444441488");
    orderQueryRequestForBackendDto.setPlatform("WEIXIN");
    
    OrderQueryResponseForBackendDto  orderQueryResponseForBackendDto  = 
        orderQueryServiceImpl.orderQueryRequestFromBackendSystme(orderQueryRequestForBackendDto);
    
    assertNotEquals("44444444444441488",orderQueryResponseForBackendDto.getOutTradeNo());
    
    
  }
  
  
  @Test
  public void queryOrderForAliPayOrderNotExisted() {
    
    OrderQueryRequestForBackendDto orderQueryRequestForBackendDto = new OrderQueryRequestForBackendDto();
    orderQueryRequestForBackendDto.setAppId("2018020102122242");    
    orderQueryRequestForBackendDto.setMchId(MCHID);
    orderQueryRequestForBackendDto.setOutTradeNo("2018022300007149");
    orderQueryRequestForBackendDto.setPlatform("ALIPAY");
    orderQueryRequestForBackendDto.setBusinessId("businessId");
    orderQueryRequestForBackendDto.setExtAttributes(new HashMap());
    orderQueryRequestForBackendDto.setSourceSysId(TEMP_PARAMETER);
    orderQueryRequestForBackendDto.setTargetSysId(TEMP_PARAMETER);
    orderQueryRequestForBackendDto.setTransactionId("");
    
    OrderQueryResponseForBackendDto  orderQueryResponseForBackendDto  = 
        orderQueryServiceImpl.orderQueryRequestFromBackendSystme(orderQueryRequestForBackendDto);
    
    assertNotEquals("2018022821001004680594482848",orderQueryResponseForBackendDto.getOutTradeNo());    
    
  }
  
  @Test
  public void queryOrderForAliPayOrderExisted() {
    
    OrderQueryRequestForBackendDto orderQueryRequestForBackendDto = new OrderQueryRequestForBackendDto();
    orderQueryRequestForBackendDto.setAppId("2018020102122242");    
    orderQueryRequestForBackendDto.setMchId(MCHID);
    orderQueryRequestForBackendDto.setOutTradeNo("2018022300007150");
    orderQueryRequestForBackendDto.setPlatform("ALIPAY");
    orderQueryRequestForBackendDto.setBusinessId("businessId");
    orderQueryRequestForBackendDto.setExtAttributes(new HashMap());
    orderQueryRequestForBackendDto.setSourceSysId(TEMP_PARAMETER);
    orderQueryRequestForBackendDto.setTargetSysId(TEMP_PARAMETER);
    orderQueryRequestForBackendDto.setTransactionId("");
    
    OrderQueryResponseForBackendDto  orderQueryResponseForBackendDto  = 
        orderQueryServiceImpl.orderQueryRequestFromBackendSystme(orderQueryRequestForBackendDto);
    
    assertEquals("2018022821001004680594482848",orderQueryResponseForBackendDto.getTransactionId());    
  }


}
