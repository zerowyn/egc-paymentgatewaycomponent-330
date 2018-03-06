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
import com.eg.egsc.scp.paygateway.dto.CreateOrderResponseForBackendDto;
import com.eg.egsc.scp.paygateway.dto.OrderQueryRequestForBackendDto;
import com.eg.egsc.scp.paygateway.dto.OrderQueryResponseForBackendDto;


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
    
    return "/paymentgateway";
  }
  
  
  public OrderQueryResponseForBackendDto orderQuery(OrderQueryRequestForBackendDto orderQueryRequestForBackendDto) throws CommonException{
    
    ResponseDto res = post("/api/pay/orderquery", orderQueryRequestForBackendDto);
    return (OrderQueryResponseForBackendDto) res.getData();   
    
  }

  @Override
  public CreateOrderResponseForBackendDto createOrder(CreateOrderRequestForBackendDto createOrderRequestForBackendDto) throws CommonException {
    ResponseDto res = post("/pay/createorder", createOrderRequestForBackendDto);
    return (CreateOrderResponseForBackendDto) res.getData();
  }


}
