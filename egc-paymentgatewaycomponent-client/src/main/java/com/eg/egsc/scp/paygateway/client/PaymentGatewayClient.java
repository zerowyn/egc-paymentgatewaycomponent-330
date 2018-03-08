/**
 * @Probject Name: egc-paymentgatewaycomponent-client
 * @Path: com.eg.egsc.scp.paygateway.clientPaymentGatewayClient.java
 * @Create By caiqinli
 * @Create In 2018年3月5日 下午3:36:33
 * TODO
 */
package com.eg.egsc.scp.paygateway.client;

import com.eg.egsc.common.exception.CommonException;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.scp.paygateway.dto.CreateOrderRequestForBackendDto;
import com.eg.egsc.scp.paygateway.dto.OrderQueryRequestForBackendDto;


/**
 * @Class Name PaymentGatewayClient
 * @Author caiqinli
 * @Create In 2018年3月5日
 */
public interface PaymentGatewayClient {

  ResponseDto orderQuery(OrderQueryRequestForBackendDto orderQueryRequestForBackendDto) throws CommonException;

  /**
   * 通过组件或者应用封装的CreateOrderRequestForBackendDto下单
   * @param createOrderRequestForBackendDto 封装的支付下单对象
   * @throws CommonException  异常信息
   */
  ResponseDto createOrder(CreateOrderRequestForBackendDto createOrderRequestForBackendDto) throws CommonException;

}
