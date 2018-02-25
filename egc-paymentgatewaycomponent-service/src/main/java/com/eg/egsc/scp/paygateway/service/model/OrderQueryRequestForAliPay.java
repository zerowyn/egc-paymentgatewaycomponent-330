/**
 * @Probject Name: scp-pay-gateway-app-dao
 * @Path: com.eg.egsc.scp.paygateway.daoOrderQueryRequestForAliPay.java
 * @Create By caiqinli
 * @Create In 2018年2月5日 下午5:16:39
 * TODO
 */
package com.eg.egsc.scp.paygateway.service.model;

import org.springframework.stereotype.Component;

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
  private String app_id;
  
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
  private String biz_content; 
 
  
  /**
   * 转发给微信订单查询接口的消息格式-微信的订单号，优先使用
   * 
   */
  private String transaction_id;
  
  /**
   * 随机字符串，不长于32位。推荐随机数生成算法
   * 
   */
  private String nonce_str;
  
  
  /**
   * 支付宝的订单号，优先使用
   * 
   */
  private String trade_no;
  
  /**
   * 转发给微信订单查询接口的消息格式-缴费后台内部订单号，
   * 当没提供transaction_id时需要传这个。
   * 要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
   * 
   */
  private String out_trade_no;
  
  /**
   * 由支付网关调用 随机数生成算法 生成一个32位的字符串。由签名与验签模块生成。
   * 
   */
  private String sign_type;
  
  
  /**
   * 由支付网关生成的签名，详见签名生成算法。由签名与验签模块生成。
   * 
   */
  private String sign;


  /**
   * @Return the String app_id
   */
  public String getApp_id() {
    return app_id;
  }


  /**
   * @Param String app_id to set
   */
  public void setApp_id(String app_id) {
    this.app_id = app_id;
  }


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
   * @Return the String biz_content
   */
  public String getBiz_content() {
    return biz_content;
  }


  /**
   * @Param String biz_content to set
   */
  public void setBiz_content(String biz_content) {
    this.biz_content = biz_content;
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
   * @Return the String sign_type
   */
  public String getSign_type() {
    return sign_type;
  }


  /**
   * @Param String sign_type to set
   */
  public void setSign_type(String sign_type) {
    this.sign_type = sign_type;
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
