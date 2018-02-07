/**
 * @Probject Name: scp-pay-gateway-app-dao
 * @Path: com.eg.egsc.scp.paygateway.daoOrderQueryRequestForWeiXin.java
 * @Create By caiqinli
 * @Create In 2018年2月5日 下午5:14:10
 * TODO
 */
package com.eg.egsc.scp.paygateway.service.model;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @Class Name OrderQueryRequestForWeiXin
 * @Author caiqinli
 * @Create In 2018年2月5日
 */
public class OrderQueryRequestForWeiXin {
  
  /**
   * 转发给微信订单查询接口的消息格式-微信开放平台审核通过的应用APPID
   * 
   */
  @NotBlank(message = "scp.pay.gateway.app.pay.request.appid.notblank")
  private String appid;
  
  /**
   * 转发给微信订单查询接口的消息格式-微信支付分配的商户号
   * 
   */
  @NotBlank(message = "scp.pay.gateway.app.pay.request.mch_id.notblank")
  private String mch_id;
  
  /**
   * 转发给微信订单查询接口的消息格式-微信的订单号，优先使用
   * 
   */
  private String transaction_id;
  
  /**
   * 转发给微信订单查询接口的消息格式-缴费后台内部订单号，
   * 当没提供transaction_id时需要传这个。
   * 要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
   * 
   */
  private String out_trade_no;
  
  /**
   * 转发给微信订单查询接口的消息格式-随机字符串，不长于32位。推荐随机数生成算法
   * 
   */
  private String nonce_str;
  
  /**
   * 转发给微信订单查询接口的消息格式-签名
   * 
   */
  private String sign;

  /**
   * @Return the String appid
   */
  public String getAppid() {
    return appid;
  }

  /**
   * @Param String appid to set
   */
  public void setAppid(String appid) {
    this.appid = appid;
  }

  /**
   * @Return the String mch_id
   */
  public String getMch_id() {
    return mch_id;
  }

  /**
   * @Param String mch_id to set
   */
  public void setMch_id(String mch_id) {
    this.mch_id = mch_id;
  }

  /**
   * @Return the String transaction_id
   */
  public String getTransaction_id() {
    return transaction_id;
  }

  /**
   * @Param String transaction_id to set
   */
  public void setTransaction_id(String transaction_id) {
    this.transaction_id = transaction_id;
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
   * @Return the String nonce_str
   */
  public String getNonce_str() {
    return nonce_str;
  }

  /**
   * @Param String nonce_str to set
   */
  public void setNonce_str(String nonce_str) {
    this.nonce_str = nonce_str;
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
