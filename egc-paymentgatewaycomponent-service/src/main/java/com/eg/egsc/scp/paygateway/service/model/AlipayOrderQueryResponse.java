/**
 * @Probject Name: egc-paymentgatewaycomponent-service
 * @Path: com.eg.egsc.scp.paygateway.service.modelAlipayTradeQueryResponse.java
 * @Create By caiqinli
 * @Create In 2018年2月26日 下午9:15:04
 * TODO
 */
package com.eg.egsc.scp.paygateway.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Class Name AlipayTradeQueryResponse
 * @Author caiqinli
 * @Create In 2018年2月26日
 */
public class AlipayOrderQueryResponse {
  
  
  
  /**
   * 网关返回码 
   * 
   */ 
  private String code;
  
  /**
   * 网关返回码描述
   * 
   */ 
  private String msg;
  
  /**
   * 业务返回码，参见具体的API接口文档
   * 
   */
  @JsonProperty(value = "sub_code")
  private String subCode;
  
  /**
   * 业务返回码描述，参见具体的API接口文档
   * 
   */
  @JsonProperty(value = "sub_msg")
  private String subMsg;
    
  /**
   * 支付宝交易号
   * 
   */ 
  @JsonProperty(value = "trade_no")
  private String tradeNo;
  
  /**
   * 商家订单号 
   * 
   */ 
  @JsonProperty(value = "out_trade_no")
  private String outTradeNo;
  
  /**
   * 买家支付宝账号
   * 
   */ 
  @JsonProperty(value = "buyer_logon_id")
  private String buyerLogonId;
  
  /**
   * 交易状态：WAIT_BUYER_PAY（交易创建，等待买家付款）、
   * TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、
   * TRADE_SUCCESS（交易支付成功）、
   * TRADE_FINISHED（交易结束，不可退款）    * 
   */ 
  @JsonProperty(value = "trade_status")
  private String tradeStatus;
  
  /**
   * 交易的订单金额，单位为元，两位小数。
   * 
   */ 
  @JsonProperty(value = "total_amount")
  private String totalAmount;
  
  /**
   * 实收金额，单位为元，两位小数。
   * 
   */ 
  @JsonProperty(value = "receipt_amount")
  private String receiptAmount;
  
  /**
   * 买家实付金额，单位为元，两位小数。
   * 该金额代表该笔交易买家实际支付的金额，不包含商户折扣等金额   * 
   */ 
  @JsonProperty(value = "buyer_pay_amount")
  private String buyerPayAmount;
  
  /**
   * 积分支付的金额，单位为元，两位小数。
   * 该金额代表该笔交易中用户使用积分支付的金额，比如集分宝或者支付宝实时优惠等    * 
   */ 
  @JsonProperty(value = "point_amount")
  private String pointAmount;
  
  /**
   * 交易中用户支付的可开具发票的金额，单位为元，两位小数。
   * 该金额代表该笔交易中可以给用户开具发票的金额  
   */ 
  @JsonProperty(value = "invoice_amount")
  private String invoiceAmount;
  
  /**
   * 本次交易打款给卖家的时间 
   * 
   */ 
  @JsonProperty(value = "send_pay_date")
  private String sendPayDate;
  
  /**
   * 商户门店编号
   * 
   */ 
  @JsonProperty(value = "store_id")
  private String storeId;
  
  /**
   * 商户机具终端编号 
   * 
   */ 
  @JsonProperty(value = "terminal_id")
  private String terminalId;
  
  /**
   * 交易支付使用的资金渠道 
   * 
   */ 
  @JsonProperty(value = "fund_bill_list")
  private FundBillList[] fundBillList;
  
  /**
   * 请求交易支付中的商户店铺的名称
   * 
   */ 
  @JsonProperty(value = "store_name")
  private String storeName;
  
  /**
   * 买家在支付宝的用户id 
   * 
   */ 
  @JsonProperty(value = "buyer_user_id")
  private String buyerUserId;
  
  /**
   * 买家用户类型。CORPORATE:企业用户；PRIVATE:个人用户。 
   * 
   */ 
  @JsonProperty(value = "buyer_user_type")
  private String buyerUserType;

  /**
   * @Return the String code
   */
  public String getCode() {
    return code;
  }

  /**
   * @Param String code to set
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * @Return the String msg
   */
  public String getMsg() {
    return msg;
  }

  /**
   * @Param String msg to set
   */
  public void setMsg(String msg) {
    this.msg = msg;
  }

  /**
   * @Return the String subCode
   */
  public String getSubCode() {
    return subCode;
  }

  /**
   * @Param String subCode to set
   */
  public void setSubCode(String subCode) {
    this.subCode = subCode;
  }

  /**
   * @Return the String subMsg
   */
  public String getSubMsg() {
    return subMsg;
  }

  /**
   * @Param String subMsg to set
   */
  public void setSubMsg(String subMsg) {
    this.subMsg = subMsg;
  }

  /**
   * @Return the String tradeNo
   */
  public String getTradeNo() {
    return tradeNo;
  }

  /**
   * @Param String tradeNo to set
   */
  public void setTradeNo(String tradeNo) {
    this.tradeNo = tradeNo;
  }

  /**
   * @Return the String outTradeNo
   */
  public String getOutTradeNo() {
    return outTradeNo;
  }

  /**
   * @Param String outTradeNo to set
   */
  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  /**
   * @Return the String buyerLogonId
   */
  public String getBuyerLogonId() {
    return buyerLogonId;
  }

  /**
   * @Param String buyerLogonId to set
   */
  public void setBuyerLogonId(String buyerLogonId) {
    this.buyerLogonId = buyerLogonId;
  }

  /**
   * @Return the String tradeStatus
   */
  public String getTradeStatus() {
    return tradeStatus;
  }

  /**
   * @Param String tradeStatus to set
   */
  public void setTradeStatus(String tradeStatus) {
    this.tradeStatus = tradeStatus;
  }

  /**
   * @Return the String totalAmount
   */
  public String getTotalAmount() {
    return totalAmount;
  }

  /**
   * @Param String totalAmount to set
   */
  public void setTotalAmount(String totalAmount) {
    this.totalAmount = totalAmount;
  }

  /**
   * @Return the String receiptAmount
   */
  public String getReceiptAmount() {
    return receiptAmount;
  }

  /**
   * @Param String receiptAmount to set
   */
  public void setReceiptAmount(String receiptAmount) {
    this.receiptAmount = receiptAmount;
  }

  /**
   * @Return the String buyerPayAmount
   */
  public String getBuyerPayAmount() {
    return buyerPayAmount;
  }

  /**
   * @Param String buyerPayAmount to set
   */
  public void setBuyerPayAmount(String buyerPayAmount) {
    this.buyerPayAmount = buyerPayAmount;
  }

  /**
   * @Return the String pointAmount
   */
  public String getPointAmount() {
    return pointAmount;
  }

  /**
   * @Param String pointAmount to set
   */
  public void setPointAmount(String pointAmount) {
    this.pointAmount = pointAmount;
  }

  /**
   * @Return the String invoiceAmount
   */
  public String getInvoiceAmount() {
    return invoiceAmount;
  }

  /**
   * @Param String invoiceAmount to set
   */
  public void setInvoiceAmount(String invoiceAmount) {
    this.invoiceAmount = invoiceAmount;
  }

  /**
   * @Return the String sendPayDate
   */
  public String getSendPayDate() {
    return sendPayDate;
  }

  /**
   * @Param String sendPayDate to set
   */
  public void setSendPayDate(String sendPayDate) {
    this.sendPayDate = sendPayDate;
  }

  /**
   * @Return the String storeId
   */
  public String getStoreId() {
    return storeId;
  }

  /**
   * @Param String storeId to set
   */
  public void setStoreId(String storeId) {
    this.storeId = storeId;
  }

  /**
   * @Return the String terminalId
   */
  public String getTerminalId() {
    return terminalId;
  }

  /**
   * @Param String terminalId to set
   */
  public void setTerminalId(String terminalId) {
    this.terminalId = terminalId;
  }

  /**
   * @Return the FundBillList[] fundBillList
   */
  public FundBillList[] getFundBillList() {
    return fundBillList;
  }

  /**
   * @Param FundBillList[] fundBillList to set
   */
  public void setFundBillList(FundBillList[] fundBillList) {
    this.fundBillList = fundBillList;
  }

  /**
   * @Return the String storeName
   */
  public String getStoreName() {
    return storeName;
  }

  /**
   * @Param String storeName to set
   */
  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  /**
   * @Return the String buyerUserId
   */
  public String getBuyerUserId() {
    return buyerUserId;
  }

  /**
   * @Param String buyerUserId to set
   */
  public void setBuyerUserId(String buyerUserId) {
    this.buyerUserId = buyerUserId;
  }

  /**
   * @Return the String buyerUserType
   */
  public String getBuyerUserType() {
    return buyerUserType;
  }

  /**
   * @Param String buyerUserType to set
   */
  public void setBuyerUserType(String buyerUserType) {
    this.buyerUserType = buyerUserType;
  }




}
