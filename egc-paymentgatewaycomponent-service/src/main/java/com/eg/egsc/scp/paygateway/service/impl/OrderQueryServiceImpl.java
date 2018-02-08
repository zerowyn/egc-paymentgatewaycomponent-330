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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import com.eg.egsc.scp.paygateway.dto.OrderQueryRequestForBackendDto;
import com.eg.egsc.scp.paygateway.dto.OrderQueryResponseForBackendDto;
import com.eg.egsc.scp.paygateway.service.OrderQueryService;
import com.eg.egsc.scp.paygateway.service.model.OrderQueryRequestForAliPay;
import com.eg.egsc.scp.paygateway.service.model.OrderQueryRequestForWeiXin;
import com.eg.egsc.scp.paygateway.service.model.OrderQueryResponseForAliPay;
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
  
  @Autowired
  private OrderQueryRequestForWeiXin orderQueryRequestForWeiXin;
  
  @Autowired
  private OrderQueryResponseForBackendDto orderQueryResponseForBackendDto;
  
  @Autowired
  private OrderQueryResponseForWeiXin orderQueryResponseForWeiXin;
  
  /**
   * 接收缴费后台请求，转换为数据格式
   * @param OrderQueryRequestForBackendSystem 缴费后台提交的请求数据对象
   * @return OrderQueryRequestForWeiXin 返回给微信的请求数据对象
   */
  @Override
  public OrderQueryRequestForWeiXin transferBackendMessageForWeiXin(
      OrderQueryRequestForBackendDto orderQueryRequestForBackendDto){
    
    orderQueryRequestForWeiXin.setAppid(orderQueryRequestForBackendDto.getAppid());
    orderQueryRequestForWeiXin.setMch_id(orderQueryRequestForBackendDto.getMch_id());
    orderQueryRequestForWeiXin.setTransaction_id(orderQueryRequestForBackendDto.getTransaction_id());
    orderQueryRequestForWeiXin.setOut_trade_no(orderQueryRequestForBackendDto.getOut_trade_no());
    orderQueryRequestForWeiXin.setNonce_str(getNonce_str());
    orderQueryRequestForWeiXin.setSign(getSign());
  
    return orderQueryRequestForWeiXin;
    
  }
  
  /**
   * 接收缴费后台请求，转换为数据格式
   * @param OrderQueryRequestForBackendSystem 缴费后台提交的请求数据对象
   * @return OrderQueryRequestForAliPay 返回给支付宝的请求数据对象
   */
  @Override
  public OrderQueryRequestForAliPay transferBackendMessageForAliPay(
      OrderQueryRequestForBackendDto orderQueryRequestForBackendDto){
    
    OrderQueryRequestForAliPay requestForAliPay = new OrderQueryRequestForAliPay();
    return requestForAliPay;        
  }
  
  /**
   * 接收微信返回数据，转换为数据格式
   * @param OrderQueryResponseForWeiXin 微信接口返回的数据对象
   * @return OrderQueryResponseForBackendSystem 返回给缴费后台的数据对象
   */
  @Override
  public OrderQueryResponseForBackendDto transferWeiXinMessageForBackendSystme(
      OrderQueryResponseForWeiXin orderQueryResponseForWeiXin){
    
    OrderQueryResponseForBackendDto responseForBackendSystem = new OrderQueryResponseForBackendDto();
    return responseForBackendSystem;        
  }
  
  /**
   * 接收微信返回数据，转换为数据格式
   * @param OrderQueryResponseForAliPay 支付宝接口返回的数据对象
   * @return OrderQueryResponseForBackendSystem 返回给缴费后台的数据对象
   */
  @Override
  public OrderQueryResponseForBackendDto transferAliPayMessageForBackendSystme(
      OrderQueryResponseForAliPay orderQueryResponseForAliPay){
    
    OrderQueryResponseForBackendDto responseForBackendSystem = new OrderQueryResponseForBackendDto();
    return responseForBackendSystem;
    
  }
  
  /**
   * 接收微信返回数据，转换为数据格式
   * @param OrderQueryResponseForAliPay 支付宝接口返回的数据对象
   * @return OrderQueryResponseForBackendSystem 返回给缴费后台的数据对象
   */
  @Override
  public OrderQueryResponseForBackendDto orderQueryRequestFromBackendSystme(OrderQueryRequestForBackendDto orderQueryRequestForBackendDto){
    
    if(orderQueryRequestForBackendDto == null){
      String errorMeg = "Dto from backend system request for order query is null!";
      logger.error(errorMeg);
      return null;
    }
    //for wechat
    if(PaymentBusinessConstant.WEI_XIN.equalsIgnoreCase(orderQueryRequestForBackendDto.getPlatform())){      
      orderQueryRequestForWeiXin = transferBackendMessageForWeiXin(orderQueryRequestForBackendDto);
      
      //send the data to ngix and call wechat api to query order 
      
      //transfer the wechat retrun data to payment backend system
      orderQueryResponseForBackendDto = transferWeiXinMessageForBackendSystme(orderQueryResponseForWeiXin);
      
      
    }
    //for alipay
    else if(PaymentBusinessConstant.ALI_PAY.equalsIgnoreCase(orderQueryRequestForBackendDto.getPlatform())){
      
    }
   
    return orderQueryResponseForBackendDto;    
  }
  
  private String getNonce_str(){
    
    return "getNonce_strgetNonce_strgetNonce_str";
  }
  
  private String getSign(){
    
    return "getSign";
  }


}
