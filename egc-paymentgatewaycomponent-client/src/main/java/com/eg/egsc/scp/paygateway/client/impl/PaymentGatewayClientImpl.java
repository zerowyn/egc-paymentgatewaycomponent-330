/**
 * @Probject Name: egc-paymentgatewaycomponent-client
 * @Path: com.eg.egsc.scp.paygateway.client.implPaymentGatewayClientImpl.java
 * @Create By caiqinli
 * @Create In 2018年3月5日 下午6:01:12
 * TODO
 */
package com.eg.egsc.scp.paygateway.client.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.eg.egsc.common.exception.CommonException;
import com.eg.egsc.framework.client.core.BaseApiClient;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.scp.paygateway.client.PaymentGatewayClient;
import com.eg.egsc.scp.paygateway.dto.CreateOrderRequestForBackendDto;
import com.eg.egsc.scp.paygateway.dto.OrderQueryRequestForBackendDto;


/**
 * @Class Name PaymentGatewayClientImpl
 * @Author caiqinli
 * @Create In 2018年3月5日
 */
@Component 
@Scope("prototype")
public class PaymentGatewayClientImpl extends BaseApiClient implements PaymentGatewayClient {
  
  public PaymentGatewayClientImpl(){
    super();    
    
  }
  
  /* (non-Javadoc)
   * @see com.eg.egsc.framework.client.core.BaseApiClient#getContextPath()
   */
  @Override
  protected String getContextPath() {
    
    return "/egc-paymentgatewaycomponent";
  }
  
  
  public ResponseDto orderQuery(OrderQueryRequestForBackendDto orderQueryRequestForBackendDto) throws CommonException{
    
   return post("/api/pay/orderquery", orderQueryRequestForBackendDto);
  }

  @Override
  public ResponseDto createOrder(CreateOrderRequestForBackendDto createOrderRequestForBackendDto) throws CommonException {
    return post("/pay/createorder", createOrderRequestForBackendDto);
  }


}
