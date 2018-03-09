/**
 * @Probject Name: scp-pay-gateway-app-dao
 * @Path: com.eg.egsc.scp.paygateway.daoOrderQueryResponseForAliPay.java
 * @Create By caiqinli
 * @Create In 2018年2月5日 下午5:17:03
 */
package com.eg.egsc.scp.paygateway.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Class Name OrderQueryResponseForAliPay
 * @Author caiqinli
 * @Create In 2018年2月5日
 */
public class OrderQueryResponseForAliPay {
  
  @JsonProperty(value = "alipay_trade_query_response")
  private AlipayOrderQueryResponse alipayTradeQueryResponse;
  private String sign;
  

  /**
   * @Return the String sign
   */
  public String getSign() {
    return sign;
  }
  /**
   * @Param String sign to set
   */
  public void setSign(String sign) {
    this.sign = sign;
  }
  /**
   * @Return the AlipayOrderQueryResponse alipayTradeQueryResponse
   */
  public AlipayOrderQueryResponse getAlipayTradeQueryResponse() {
    return alipayTradeQueryResponse;
  }
  /**
   * @Param AlipayOrderQueryResponse alipayTradeQueryResponse to set
   */
  public void setAlipayTradeQueryResponse(AlipayOrderQueryResponse alipayTradeQueryResponse) {
    this.alipayTradeQueryResponse = alipayTradeQueryResponse;
  }
  
}
