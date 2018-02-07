/**
 * @Probject Name: scp-pay-gateway-app-service
 * @Path: com.eg.egsc.scp.paygateway.service.implOrderQueryServiceImpl.java
 * @Create By caiqinli
 * @Create In 2018年2月6日 上午11:41:04
 * TODO
 */
package com.eg.egsc.scp.paygateway.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eg.egsc.scp.paygateway.dto.OrderQueryRequestForBackendDto;
import com.eg.egsc.scp.paygateway.service.OrderQueryService;
import com.eg.egsc.scp.paygateway.service.model.OrderQueryRequestForAliPay;
import com.eg.egsc.scp.paygateway.service.model.OrderQueryRequestForBackendSystem;
import com.eg.egsc.scp.paygateway.service.model.OrderQueryRequestForWeiXin;
import com.eg.egsc.scp.paygateway.service.model.OrderQueryResponseForAliPay;
import com.eg.egsc.scp.paygateway.service.model.OrderQueryResponseForBackendSystem;
import com.eg.egsc.scp.paygateway.service.model.OrderQueryResponseForWeiXin;
import com.eg.egsc.scp.paygateway.util.PaymentBusinessConstant;

/**
 * @Class Name OrderQueryServiceImpl
 * @Author caiqinli
 * @Create In 2018年2月6日
 */
@Service
public class OrderQueryServiceImpl implements OrderQueryService {

  protected final Logger logger = LoggerFactory.getLogger(OrderQueryServiceImpl.class);
  /**
   * 接收缴费后台请求，转换为数据格式
   * @param OrderQueryRequestForBackendSystem 缴费后台提交的请求数据对象
   * @return OrderQueryRequestForWeiXin 返回给微信的请求数据对象
   */
  @Override
  public OrderQueryRequestForWeiXin transferBackendMessageForWeiXin(
      OrderQueryRequestForBackendSystem orderQueryRequestForBackendSystem){
    
    OrderQueryRequestForWeiXin requestForWeiXin = new OrderQueryRequestForWeiXin();
    return requestForWeiXin;
    
  }
  
  /**
   * 接收缴费后台请求，转换为数据格式
   * @param OrderQueryRequestForBackendSystem 缴费后台提交的请求数据对象
   * @return OrderQueryRequestForAliPay 返回给支付宝的请求数据对象
   */
  @Override
  public OrderQueryRequestForAliPay transferBackendMessageForAliPay(
      OrderQueryRequestForBackendSystem orderQueryRequestForBackendSystem){
    
    OrderQueryRequestForAliPay requestForAliPay = new OrderQueryRequestForAliPay();
    return requestForAliPay;        
  }
  
  /**
   * 接收微信返回数据，转换为数据格式
   * @param OrderQueryResponseForWeiXin 微信接口返回的数据对象
   * @return OrderQueryResponseForBackendSystem 返回给缴费后台的数据对象
   */
  @Override
  public OrderQueryResponseForBackendSystem transferWeiXinMessageForBackendSystme(
      OrderQueryResponseForWeiXin orderQueryResponseForWeiXin){
    
    OrderQueryResponseForBackendSystem responseForBackendSystem = new OrderQueryResponseForBackendSystem();
    return responseForBackendSystem;        
  }
  
  /**
   * 接收微信返回数据，转换为数据格式
   * @param OrderQueryResponseForAliPay 支付宝接口返回的数据对象
   * @return OrderQueryResponseForBackendSystem 返回给缴费后台的数据对象
   */
  @Override
  public OrderQueryResponseForBackendSystem transferWeiXinMessageForBackendSystme(
      OrderQueryResponseForAliPay orderQueryResponseForAliPay){
    
    OrderQueryResponseForBackendSystem responseForBackendSystem = new OrderQueryResponseForBackendSystem();
    return responseForBackendSystem;
    
  }
  
  /**
   * 接收微信返回数据，转换为数据格式
   * @param OrderQueryResponseForAliPay 支付宝接口返回的数据对象
   * @return OrderQueryResponseForBackendSystem 返回给缴费后台的数据对象
   */
  @Override
  public String orderQueryRequestFromBackendSystme(OrderQueryRequestForBackendSystem orderQueryRequestForBackendSystem){
    
    if(orderQueryRequestForBackendSystem == null){
      String errorMeg = "Dto from backend system request for order query is null!";
      logger.error(errorMeg);
      return errorMeg;
    }
    //for wechat
    if(PaymentBusinessConstant.WEI_XIN.equalsIgnoreCase(orderQueryRequestForBackendSystem.getPlatform())){
      
      
    }
    //for alipay
    if(PaymentBusinessConstant.ALI_PAY.equalsIgnoreCase(orderQueryRequestForBackendSystem.getPlatform())){
      
    }
    
    
    return "To Be implement";    
  }


}
