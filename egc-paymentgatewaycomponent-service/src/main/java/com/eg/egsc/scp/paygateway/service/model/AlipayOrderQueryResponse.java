/**
 * @Probject Name: egc-paymentgatewaycomponent-service
 * @Path: com.eg.egsc.scp.paygateway.service.modelAlipayTradeQueryResponse.java
 * @Create By caiqinli
 * @Create In 2018年2月26日 下午9:15:04
 * TODO
 */
package com.eg.egsc.scp.paygateway.service.model;

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
  private String sub_code;
  
  /**
   * 业务返回码描述，参见具体的API接口文档
   * 
   */
  private String sub_msg;
    
  /**
   * 支付宝交易号
   * 
   */ 
  private String trade_no;
  
  /**
   * 商家订单号 
   * 
   */ 
  private String out_trade_no;
  
  /**
   * 买家支付宝账号
   * 
   */ 
  private String buyer_logon_id;
  
  /**
   * 交易状态：WAIT_BUYER_PAY（交易创建，等待买家付款）、
   * TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、
   * TRADE_SUCCESS（交易支付成功）、
   * TRADE_FINISHED（交易结束，不可退款）    * 
   */ 
  private String trade_status;
  
  /**
   * 交易的订单金额，单位为元，两位小数。
   * 
   */ 
  private String total_amount;
  
  /**
   * 实收金额，单位为元，两位小数。
   * 
   */ 
  private String receipt_amount;
  
  /**
   * 买家实付金额，单位为元，两位小数。
   * 该金额代表该笔交易买家实际支付的金额，不包含商户折扣等金额   * 
   */ 
  private String buyer_pay_amount;
  
  /**
   * 积分支付的金额，单位为元，两位小数。
   * 该金额代表该笔交易中用户使用积分支付的金额，比如集分宝或者支付宝实时优惠等    * 
   */ 
  private String point_amount;
  
  /**
   * 交易中用户支付的可开具发票的金额，单位为元，两位小数。
   * 该金额代表该笔交易中可以给用户开具发票的金额  
   */ 
  private String invoice_amount;
  
  /**
   * 本次交易打款给卖家的时间 
   * 
   */ 
  private String send_pay_date;
  
  /**
   * 商户门店编号
   * 
   */ 
  private String store_id;
  
  /**
   * 商户机具终端编号 
   * 
   */ 
  private String terminal_id;
  
  /**
   * 交易支付使用的资金渠道 
   * 
   */ 
  private FundBillList[] fund_bill_list;
  
  /**
   * 请求交易支付中的商户店铺的名称
   * 
   */ 
  private String store_name;
  
  /**
   * 买家在支付宝的用户id 
   * 
   */ 
  private String buyer_user_id;
  
  /**
   * 买家用户类型。CORPORATE:企业用户；PRIVATE:个人用户。 
   * 
   */ 
  private String buyer_user_type;

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
   * @Return the String sub_code
   */
  public String getSub_code() {
    return sub_code;
  }

  /**
   * @Param String sub_code to set
   */
  public void setSub_code(String sub_code) {
    this.sub_code = sub_code;
  }

  /**
   * @Return the String sub_msg
   */
  public String getSub_msg() {
    return sub_msg;
  }

  /**
   * @Param String sub_msg to set
   */
  public void setSub_msg(String sub_msg) {
    this.sub_msg = sub_msg;
  }

  /**
   * @Return the String trade_no
   */
  public String getTrade_no() {
    return trade_no;
  }

  /**
   * @Param String trade_no to set
   */
  public void setTrade_no(String trade_no) {
    this.trade_no = trade_no;
  }

  /**
   * @Return the String out_trade_no
   */
  public String getOut_trade_no() {
    return out_trade_no;
  }

  /**
   * @Param String out_trade_no to set
   */
  public void setOut_trade_no(String out_trade_no) {
    this.out_trade_no = out_trade_no;
  }

  /**
   * @Return the String buyer_logon_id
   */
  public String getBuyer_logon_id() {
    return buyer_logon_id;
  }

  /**
   * @Param String buyer_logon_id to set
   */
  public void setBuyer_logon_id(String buyer_logon_id) {
    this.buyer_logon_id = buyer_logon_id;
  }

  /**
   * @Return the String trade_status
   */
  public String getTrade_status() {
    return trade_status;
  }

  /**
   * @Param String trade_status to set
   */
  public void setTrade_status(String trade_status) {
    this.trade_status = trade_status;
  }


  /**
   * @Return the String receipt_amount
   */
  public String getReceipt_amount() {
    return receipt_amount;
  }

  /**
   * @Param String receipt_amount to set
   */
  public void setReceipt_amount(String receipt_amount) {
    this.receipt_amount = receipt_amount;
  }


  /**
   * @Return the String send_pay_date
   */
  public String getSend_pay_date() {
    return send_pay_date;
  }

  /**
   * @Param String send_pay_date to set
   */
  public void setSend_pay_date(String send_pay_date) {
    this.send_pay_date = send_pay_date;
  }

  /**
   * @Return the String store_id
   */
  public String getStore_id() {
    return store_id;
  }

  /**
   * @Param String store_id to set
   */
  public void setStore_id(String store_id) {
    this.store_id = store_id;
  }

  /**
   * @Return the String terminal_id
   */
  public String getTerminal_id() {
    return terminal_id;
  }

  /**
   * @Param String terminal_id to set
   */
  public void setTerminal_id(String terminal_id) {
    this.terminal_id = terminal_id;
  }

  /**
   * @Return the FundBillList[] FundBillList
   */
  public FundBillList[] getFundBillList() {
    return fund_bill_list;
  }

  /**
   * @Param FundBillList[] fundBillList to set
   */
  public void setFundBillList(FundBillList[] fundBillList) {
    fund_bill_list = fundBillList;
  }

  /**
   * @Return the String store_name
   */
  public String getStore_name() {
    return store_name;
  }

  /**
   * @Param String store_name to set
   */
  public void setStore_name(String store_name) {
    this.store_name = store_name;
  }

  /**
   * @Return the String buyer_user_id
   */
  public String getBuyer_user_id() {
    return buyer_user_id;
  }

  /**
   * @Param String buyer_user_id to set
   */
  public void setBuyer_user_id(String buyer_user_id) {
    this.buyer_user_id = buyer_user_id;
  }

  /**
   * @Return the String buyer_user_type
   */
  public String getBuyer_user_type() {
    return buyer_user_type;
  }

  /**
   * @Param String buyer_user_type to set
   */
  public void setBuyer_user_type(String buyer_user_type) {
    this.buyer_user_type = buyer_user_type;
  }

  /**
   * @Return the String total_amount
   */
  public String getTotal_amount() {
    return total_amount;
  }

  /**
   * @Param String total_amount to set
   */
  public void setTotal_amount(String total_amount) {
    this.total_amount = total_amount;
  }

  /**
   * @Return the String buyer_pay_amount
   */
  public String getBuyer_pay_amount() {
    return buyer_pay_amount;
  }

  /**
   * @Param String buyer_pay_amount to set
   */
  public void setBuyer_pay_amount(String buyer_pay_amount) {
    this.buyer_pay_amount = buyer_pay_amount;
  }

  /**
   * @Return the String point_amount
   */
  public String getPoint_amount() {
    return point_amount;
  }

  /**
   * @Param String point_amount to set
   */
  public void setPoint_amount(String point_amount) {
    this.point_amount = point_amount;
  }

  /**
   * @Return the String invoice_amount
   */
  public String getInvoice_amount() {
    return invoice_amount;
  }

  /**
   * @Param String invoice_amount to set
   */
  public void setInvoice_amount(String invoice_amount) {
    this.invoice_amount = invoice_amount;
  }

  /**
   * @Return the FundBillList[] fund_bill_list
   */
  public FundBillList[] getFund_bill_list() {
    return fund_bill_list;
  }

  /**
   * @Param FundBillList[] fund_bill_list to set
   */
  public void setFund_bill_list(FundBillList[] fund_bill_list) {
    this.fund_bill_list = fund_bill_list;
  }



}
