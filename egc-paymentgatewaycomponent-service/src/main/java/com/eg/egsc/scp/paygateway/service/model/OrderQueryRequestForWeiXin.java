/**
 * @Probject Name: scp-pay-gateway-app-dao
 * @Path: com.eg.egsc.scp.paygateway.daoOrderQueryRequestForWeiXin.java
 * @Create By caiqinli
 * @Create In 2018年2月5日 下午5:14:10
 */
package com.eg.egsc.scp.paygateway.service.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Class Name OrderQueryRequestForWeiXin
 * @Author caiqinli
 * @Create In 2018年2月5日
 */
@Component
@XmlRootElement(name="xml")
public class OrderQueryRequestForWeiXin {
  
  /**
   * 转发给微信订单查询接口的消息格式-微信开放平台审核通过的应用APPID
   * 
   */
  private String appid;
  
  /**
   * 转发给微信订单查询接口的消息格式-微信支付分配的商户号
   * 
   */
  @JsonProperty(value = "mch_id")
  private String mchId;
  
  /**
   * 转发给微信订单查询接口的消息格式-微信的订单号，优先使用
   * 
   */
  @JsonProperty(value = "transaction_id")
  private String transactionId;
  
  /**
   * 转发给微信订单查询接口的消息格式-缴费后台内部订单号，
   * 当没提供transaction_id时需要传这个。
   * 要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
   * 
   */
  @JsonProperty(value = "out_trade_no")
  private String outTradeNo;
  
  /**
   * 转发给微信订单查询接口的消息格式-随机字符串，不长于32位。推荐随机数生成算法
   * 
   */
  @JsonProperty(value = "nonce_str")
  private String nonceStr;
  
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
   * @Return the String mchId
   */
  public String getMchId() {
    return mchId;
  }

  /**
   * @Param String mchId to set
   */
  public void setMchId(String mchId) {
    this.mchId = mchId;
  }

  /**
   * @Return the String transactionId
   */
  public String getTransactionId() {
    return transactionId;
  }

  /**
   * @Param String transactionId to set
   */
  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
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
   * @Return the String nonceStr
   */
  public String getNonceStr() {
    return nonceStr;
  }

  /**
   * @Param String nonceStr to set
   */
  public void setNonceStr(String nonceStr) {
    this.nonceStr = nonceStr;
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
