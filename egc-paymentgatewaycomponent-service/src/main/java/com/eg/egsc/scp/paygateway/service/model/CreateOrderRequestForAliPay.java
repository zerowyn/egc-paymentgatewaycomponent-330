/**
 * @Probject Name: scp-pay-gateway-app-dao
 * @Path: com.eg.egsc.scp.paygateway.daoCreateOrderRequestForAliPay.java
 * @Create By lihui
 * @Create In 2018年2月25日 上午11:16
 */
package com.eg.egsc.scp.paygateway.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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
  @JsonProperty(value = "app_id")
  private String appId;

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
  @JsonProperty(value = "sign_type")
  private String signType;

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
  @JsonProperty(value = "notify_uri")
  private String notifyUrl;

  /**
   * 业务请求参数的集合
   */
  @JsonProperty(value = "biz_content")
  private String bizContent;

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

  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getSignType() {
    return signType;
  }

  public void setSignType(String signType) {
    this.signType = signType;
  }

  public String getNotifyUrl() {
    return notifyUrl;
  }

  public void setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl;
  }

  public String getBizContent() {
    return bizContent;
  }

  public void setBizContent(String bizContent) {
    this.bizContent = bizContent;
  }
}
