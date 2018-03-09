/**
 * @Probject Name: scp-pay-gateway-app-dao
 * @Path: com.eg.egsc.scp.paygateway.daoOrderQueryRequestForAliPay.java
 * @Create By caiqinli
 * @Create In 2018年2月5日 下午5:16:39
 */
package com.eg.egsc.scp.paygateway.service.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Class Name OrderQueryRequestForAliPay
 * @Author caiqinli
 * @Create In 2018年2月5日
 */
@Component
public class OrderQueryRequestForAliPay {
 
  
  /**
   * 支付宝分配给开发者的应用ID
   * 
   */
  @JsonProperty(value = "app_id")
  private String appId;
  
  /**
   * 接口名称-根据platform参数从配置表获取外发地址
   * 
   */
  private String method;
  
  /**
   * 仅支持JSON-默认指定JSON
   * 
   */
  private String format;
  
  /**
   * 请求使用的编码格式，如utf-8,gbk,gb2312等, 默认是utf-8
   * 
   */
  private String charset;
  
  /**
   * 发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"
   * 
   */
  private String timestamp;
  
  /**
   * 调用的接口版本，固定为：1.0
   * 
   */
  private String version;
  
  
  /**
   * 业务请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递，具体参照各产品快速接入文档
   * 
   */
  @JsonProperty(value = "biz_content")
  private String bizContent; 
 
  
  /**
   * 转发给微信订单查询接口的消息格式-微信的订单号，优先使用
   * 
   */
  @JsonProperty(value = "transaction_id")
  private String transactionId;
  
  /**
   * 随机字符串，不长于32位。推荐随机数生成算法
   * 
   */
  @JsonProperty(value = "nonce_str")
  private String nonceStr;
  
  
  /**
   * 支付宝的订单号，优先使用
   * 
   */
  @JsonProperty(value = "trade_no")
  private String tradeNo;
  
  /**
   * 转发给微信订单查询接口的消息格式-缴费后台内部订单号，
   * 当没提供transaction_id时需要传这个。
   * 要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
   * 
   */
  @JsonProperty(value = "out_trade_no")
  private String outTradeNo;
  
  /**
   * 由支付网关调用 随机数生成算法 生成一个32位的字符串。由签名与验签模块生成。
   * 
   */
  @JsonProperty(value = "sign_type")
  private String signType;
  
  
  /**
   * 由支付网关生成的签名，详见签名生成算法。由签名与验签模块生成。
   * 
   */
  private String sign;


  /**
   * @Return the String method
   */
  public String getMethod() {
    return method;
  }


  /**
   * @Param String method to set
   */
  public void setMethod(String method) {
    this.method = method;
  }


  /**
   * @Return the String format
   */
  public String getFormat() {
    return format;
  }


  /**
   * @Param String format to set
   */
  public void setFormat(String format) {
    this.format = format;
  }


  /**
   * @Return the String charset
   */
  public String getCharset() {
    return charset;
  }


  /**
   * @Param String charset to set
   */
  public void setCharset(String charset) {
    this.charset = charset;
  }


  /**
   * @Return the String timestamp
   */
  public String getTimestamp() {
    return timestamp;
  }


  /**
   * @Param String timestamp to set
   */
  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }


  /**
   * @Return the String version
   */
  public String getVersion() {
    return version;
  }


  /**
   * @Param String version to set
   */
  public void setVersion(String version) {
    this.version = version;
  }


  /**
   * @Return the String bizContent
   */
  public String getBizContent() {
    return bizContent;
  }


  /**
   * @Param String bizContent to set
   */
  public void setBizContent(String bizContent) {
    this.bizContent = bizContent;
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
   * @Return the String signType
   */
  public String getSignType() {
    return signType;
  }


  /**
   * @Param String signType to set
   */
  public void setSignType(String signType) {
    this.signType = signType;
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


  /**
   * @Return the String appId
   */
  public String getAppId() {
    return appId;
  }


  /**
   * @Param String appId to set
   */
  public void setAppId(String appId) {
    this.appId = appId;
  }


  

}
