/**
 * @Probject Name: scp-pay-gateway-app-dao
 * @Path: com.eg.egsc.scp.paygateway.daoCreateOrderRequestForAliPay.java
 * @Create By lihui
 * @Create In 2018年2月25日 上午11:16
 * TODO
 */
package com.eg.egsc.scp.paygateway.service.model;

/**
 * @Class Name CreateOrderRequestForAliPay
 * @Author lihui
 * @Create In 2018年2月25日
 */
public class CreateOrderRequestForAliPay {
  
  /**
   * 应用ID
   * 
   */
  private String app_id;

  /**
   * 接口名称
   */
  private String method;

  /**
   * 编码格式
   */
  private String charset;

  /**
   * 商户生成签名所使用的字符串
   */
  private String sign_type;

  /**
   * 签名
   */
  private String sign;

  /**
   * 发送请求时间时间
   */
  private String timestamp;

  /**
   * 请求参数格式
   * 仅支持JSON
   */
  private String format;

  /**
   * 版本
   */
  private String version;

  /**
   * 通知地址
   */
  private String notify_url;

  /**
   * 业务请求参数的集合
   */
  private String biz_content;

  public String getApp_id() {
    return app_id;
  }

  public void setApp_id(String app_id) {
    this.app_id = app_id;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getCharset() {
    return charset;
  }

  public void setCharset(String charset) {
    this.charset = charset;
  }

  public String getSign_type() {
    return sign_type;
  }

  public void setSign_type(String sign_type) {
    this.sign_type = sign_type;
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getNotify_url() {
    return notify_url;
  }

  public void setNotify_url(String notify_url) {
    this.notify_url = notify_url;
  }

  public String getBiz_content() {
    return biz_content;
  }

  public void setBiz_content(String biz_content) {
    this.biz_content = biz_content;
  }

  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }
}
