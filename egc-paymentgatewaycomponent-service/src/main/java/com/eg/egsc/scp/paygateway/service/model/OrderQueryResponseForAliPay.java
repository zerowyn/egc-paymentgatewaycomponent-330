/**
 * @Probject Name: scp-pay-gateway-app-dao
 * @Path: com.eg.egsc.scp.paygateway.daoOrderQueryResponseForAliPay.java
 * @Create By caiqinli
 * @Create In 2018年2月5日 下午5:17:03
 * TODO
 */
package com.eg.egsc.scp.paygateway.service.model;

/**
 * @Class Name OrderQueryResponseForAliPay
 * @Author caiqinli
 * @Create In 2018年2月5日
 */
public class OrderQueryResponseForAliPay {
  
  
  private AlipayOrderQueryResponse alipay_trade_query_response;
  private String sign;
  
  /**
   * @Return the AlipayTradeQueryResponse alipayTradeQueryResponse
   */
  public AlipayOrderQueryResponse getAlipayTradeQueryResponse() {
    return alipay_trade_query_response;
  }
  /**
   * @Param AlipayTradeQueryResponse alipayTradeQueryResponse to set
   */
  public void setAlipayTradeQueryResponse(AlipayOrderQueryResponse alipayTradeQueryResponse) {
    this.alipay_trade_query_response = alipayTradeQueryResponse;
  }
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
  
}
