/**
 * @Probject Name: egc-paymentgatewaycomponent-service
 * @Path: com.eg.egsc.scp.paygateway.service.modelFundBillList.java
 * @Create By caiqinli
 * @Create In 2018年2月26日 下午9:15:48
 * TODO
 */
package com.eg.egsc.scp.paygateway.service.model;

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
  private String fund_channel;
  
  /**
   * 该支付工具类型所使用的金额
   * 
   */ 
  private Integer amount;
  
  /**
   * 渠道实际付款金额 
   * 
   */ 
  private Integer real_amount;

  /**
   * @Return the String fund_channel
   */
  public String getFund_channel() {
    return fund_channel;
  }

  /**
   * @Param String fund_channel to set
   */
  public void setFund_channel(String fund_channel) {
    this.fund_channel = fund_channel;
  }

  /**
   * @Return the Integer amount
   */
  public Integer getAmount() {
    return amount;
  }

  /**
   * @Param Integer amount to set
   */
  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  /**
   * @Return the Integer real_amount
   */
  public Integer getReal_amount() {
    return real_amount;
  }

  /**
   * @Param Integer real_amount to set
   */
  public void setReal_amount(Integer real_amount) {
    this.real_amount = real_amount;
  }
  


}
