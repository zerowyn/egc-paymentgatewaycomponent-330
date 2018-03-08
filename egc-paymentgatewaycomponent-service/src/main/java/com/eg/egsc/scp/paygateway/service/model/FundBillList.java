/**
 * @Probject Name: egc-paymentgatewaycomponent-service
 * @Path: com.eg.egsc.scp.paygateway.service.modelFundBillList.java
 * @Create By caiqinli
 * @Create In 2018年2月26日 下午9:15:48
 * TODO
 */
package com.eg.egsc.scp.paygateway.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Class Name FundBillList
 * @Author caiqinli
 * @Create In 2018年2月26日
 */
public class FundBillList {
  

  
  /**
   * 交易使用的资金渠道，详见 支付渠道列表 
   * 
   */ 
  @JsonProperty(value = "fund_channel")
  private String fundChannel;
  
  /**
   * 该支付工具类型所使用的金额
   * 
   */ 
  private Double amount;
  
  /**
   * 渠道实际付款金额 
   * 
   */ 
  @JsonProperty(value = "real_amount")
  private Double realAmount;

  /**
   * @Return the String fundChannel
   */
  public String getFundChannel() {
    return fundChannel;
  }

  /**
   * @Param String fundChannel to set
   */
  public void setFundChannel(String fundChannel) {
    this.fundChannel = fundChannel;
  }

  /**
   * @Return the Double amount
   */
  public Double getAmount() {
    return amount;
  }

  /**
   * @Param Double amount to set
   */
  public void setAmount(Double amount) {
    this.amount = amount;
  }

  /**
   * @Return the Double realAmount
   */
  public Double getRealAmount() {
    return realAmount;
  }

  /**
   * @Param Double realAmount to set
   */
  public void setRealAmount(Double realAmount) {
    this.realAmount = realAmount;
  }

 
 


}
