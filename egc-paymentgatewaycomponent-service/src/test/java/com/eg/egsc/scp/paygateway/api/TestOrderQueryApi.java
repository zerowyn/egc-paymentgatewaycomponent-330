/**
 * @Probject Name: egc-paymentgatewaycomponent-service
 * @Path: com.eg.egsc.scp.paygateway.apiTestOrderQueryApi.java
 * @Create By caiqinli
 * @Create In 2018年3月9日 下午5:20:17
 */
package com.eg.egsc.scp.paygateway.api;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eg.egsc.config.AbstractUnitTestSupport;
import com.eg.egsc.framework.client.dto.Header;
import com.eg.egsc.framework.client.dto.RequestDto;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.scp.paygateway.PaymentGatewayServiceApplication;
import com.eg.egsc.scp.paygateway.dto.OrderQueryRequestForBackendDto;
import com.eg.egsc.scp.paygateway.service.impl.OrderQueryServiceImpl;
import com.eg.egsc.scp.paygateway.util.PaymentBusinessConstant;

/**
 * @Class Name TestOrderQueryApi
 * @Author caiqinli
 * @Create In 2018年3月9日
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {PaymentGatewayServiceApplication.class})
public class TestOrderQueryApi  extends AbstractUnitTestSupport {
  
  @Autowired
  private OrderQueryApi orderQueryApi;
  
  private static final String MCHID = "1497973582";
  private static final String TEMP_PARAMETER = "string";
  
  @Test
  public void queryOrderForWeiXin(){    
   
    try {
        RequestDto<OrderQueryRequestForBackendDto> req = new RequestDto<>();
        OrderQueryRequestForBackendDto dto = new OrderQueryRequestForBackendDto();
        dto.setPlatform(PaymentBusinessConstant.WEI_XIN);        
        dto.setAppId("wx5332d47f724492fa");    
        dto.setMchId(MCHID);
        dto.setOutTradeNo("44444444444441444");       
        req.setData(dto);
        
        Header header = new Header();
        header.setBusinessId(TEMP_PARAMETER);
        header.setCharset("utf-8");
        header.setSourceSysId("fyrehh");
        header.setContentType("application/json");
       
        HashMap<String, Object> extMap = new HashMap<>();
        extMap.put("test","test01");
        header.setExtAttributes(extMap);
        req.setHeader(header);
        ResponseDto order = orderQueryApi.orderQuery(req);
    } catch (Exception e) {
        logger.error(e.getMessage());
    }
    
  }
  
  @Test
  public void queryOrderForWeiXinOrderNotExisted(){    
   
    try {
        RequestDto<OrderQueryRequestForBackendDto> req = new RequestDto<>();
        OrderQueryRequestForBackendDto dto = new OrderQueryRequestForBackendDto();
        dto.setPlatform(PaymentBusinessConstant.WEI_XIN);        
        dto.setAppId("wx5332d47f724492fa");    
        dto.setMchId(MCHID);
        dto.setOutTradeNo("44444444444441443");       
        req.setData(dto);
        
        Header header = new Header();
        header.setBusinessId(TEMP_PARAMETER);
        header.setCharset("utf-8");
        header.setSourceSysId("fyrehh");
        header.setContentType("application/json");
       
        HashMap<String, Object> extMap = new HashMap<>();
        extMap.put("test","test01");
        header.setExtAttributes(extMap);
        req.setHeader(header);
        ResponseDto order = orderQueryApi.orderQuery(req);
    } catch (Exception e) {
        logger.error(e.getMessage());
    }
    
  }
  
  
  
  @Test
  public void queryOrderForAlipay(){    
   
    try {
        RequestDto<OrderQueryRequestForBackendDto> req = new RequestDto<>();
        OrderQueryRequestForBackendDto dto = new OrderQueryRequestForBackendDto();
        dto.setPlatform(PaymentBusinessConstant.ALI_PAY);        
        dto.setAppId("2018020102122242");    
        dto.setMchId(MCHID);
        dto.setOutTradeNo("2018022300007150");       
        req.setData(dto);
        
        Header header = new Header();
        header.setBusinessId(TEMP_PARAMETER);
        header.setCharset("utf-8");
        header.setSourceSysId("fyrehh");
        header.setContentType("application/json");
       
        HashMap<String, Object> extMap = new HashMap<>();
        extMap.put("test","test01");
        header.setExtAttributes(extMap);
        req.setHeader(header);
        ResponseDto order = orderQueryApi.orderQuery(req);
    } catch (Exception e) {
        logger.error(e.getMessage());
    }
    
  }
  
  
  @Test
  public void queryOrderForAlipayOrderNotExisted(){    
   
    try {
        RequestDto<OrderQueryRequestForBackendDto> req = new RequestDto<>();
        OrderQueryRequestForBackendDto dto = new OrderQueryRequestForBackendDto();
        dto.setPlatform(PaymentBusinessConstant.ALI_PAY);        
        dto.setAppId("2018020102122242");    
        dto.setMchId(MCHID);
        dto.setOutTradeNo("2018022300007112");       
        req.setData(dto);
        
        Header header = new Header();
        header.setBusinessId(TEMP_PARAMETER);
        header.setCharset("utf-8");
        header.setSourceSysId("fyrehh");
        header.setContentType("application/json");
       
        HashMap<String, Object> extMap = new HashMap<>();
        extMap.put("test","test01");
        header.setExtAttributes(extMap);
        req.setHeader(header);
        ResponseDto order = orderQueryApi.orderQuery(req);
    } catch (Exception e) {
        logger.error(e.getMessage());
    }
    
  }
  
  @Test
  public void queryOrderForOthers(){    
   
    try {
        RequestDto<OrderQueryRequestForBackendDto> req = new RequestDto<>();
        OrderQueryRequestForBackendDto dto = new OrderQueryRequestForBackendDto();
        dto.setPlatform("OTHERS");        
        dto.setAppId("201802010212233");    
        dto.setMchId(MCHID);
        dto.setOutTradeNo("2018022300007199");       
        req.setData(dto);
        
        Header header = new Header();
        header.setBusinessId(TEMP_PARAMETER);
        header.setCharset("utf-8");
        header.setSourceSysId("fyrehh");
        header.setContentType("application/json");
       
        HashMap<String, Object> extMap = new HashMap<>();
        extMap.put("test","test01");
        header.setExtAttributes(extMap);
        req.setHeader(header);
        ResponseDto order = orderQueryApi.orderQuery(req);
    } catch (Exception e) {
        logger.error(e.getMessage());
    }
    
  }
  
  

}
