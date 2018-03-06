/**
 * @Probject Name: egc-paymentgatewaycomponent-client
 * @Path: com.eg.egsc.scp.paygateway.clientPaymentGatewayClient.java
 * @Create By caiqinli
 * @Create In 2018年3月5日 下午3:36:33
 * TODO
 */
package com.eg.egsc.scp.paygateway.client;

import com.eg.egsc.common.exception.CommonException;
import com.eg.egsc.scp.paygateway.dto.OrderQueryRequestForBackendDto;
import com.eg.egsc.scp.paygateway.dto.OrderQueryResponseForBackendDto;


/**
 * @Class Name PaymentGatewayClient
 * @Author caiqinli
 * @Create In 2018年3月5日
 */
public interface PaymentGatewayClient {
  
  OrderQueryResponseForBackendDto orderQuery(OrderQueryRequestForBackendDto orderQueryRequestForBackendDto) throws CommonException;

}
