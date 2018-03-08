/**
 * @Probject Name: scp-pay-gateway-app-client
 * @Path: com.eg.egsc.scp.paygateway.daoCreateOrderResponseForWeiXin.java
 * @Create By lihui
 * @Create In 2018年2月25日 上午：11:51
 * TODO
 */
package com.eg.egsc.scp.paygateway.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Class Name CreateOrderResponseForWeiXin
 * @Author lihui
 * @Create In 2018年2月25日
 */
@Component
@XmlRootElement(name = "xml")
public class CreateOrderResponseForWeiXin {

  /**
   * 返回信息
   */
  @JsonProperty(value = "return_msg")
  private String returnMsg;

  /**
   * 微信统一下单接口的消息格式-微信支付分配的商户号
   *
   */
  @JsonProperty(value = "mch_id")
  private String mchId;

  /**
   * 设备号
   * 调用接口提交的终端设备号，
   */
  @JsonProperty(value = "device_info")
  private String deviceInfo;

  /**
   * 返回状态码
   * SUCCESS/FAIL
   */
  @JsonProperty(value = "return_code")
  private String returnCode;

  /**
   * 随机字符串
   * 微信返回的随机字符串（验签用，不向缴费后台传送）
   */
  @JsonProperty(value = "nonce_str")
  private String nonceStr;
  /**
   * 签名
   *
   */
  private String sign;


  /**
   * 业务结果
   *
   */
  @JsonProperty(value = "result_code")
  private String resultCode;

  /**
   * 错误代码
   *
   */
  @JsonProperty(value = "err_code")
  private String errCode;

  /**
   * 微信统一下单接口的消息格式-微信开放平台审核通过的应用APPID
   *
   */
  private String appid;

  /**
   * 微信统一下单接口的消息格式-微信的交易类型
   *
   */
  @JsonProperty(value = "trade_type")
  private String tradeType;

  /**
   * 预支付交易会话标识
   *
   */
  @JsonProperty(value = "prepay_id")
  private String prepayId;

  /**
   * 支付跳转链接
   * mweb_url为拉起微信支付收银台的中间页面，可通过访问该url来拉起微信客户端，完成支付,mweb_url的有效期为5分钟。
   * 当trade_type为MWEB时返回。
   */
  private String mwebUrl;

  /**
   * 错误代码描述
   *
   */
  @JsonProperty(value = "err_code_des")
  private String errCodeDes;

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

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public String getReturnMsg() {
    return returnMsg;
  }

  public void setReturnMsg(String returnMsg) {
    this.returnMsg = returnMsg;
  }

  public String getMchId() {
    return mchId;
  }

  public void setMchId(String mchId) {
    this.mchId = mchId;
  }

  public String getDeviceInfo() {
    return deviceInfo;
  }

  public void setDeviceInfo(String deviceInfo) {
    this.deviceInfo = deviceInfo;
  }

  public String getReturnCode() {
    return returnCode;
  }

  public void setReturnCode(String returnCode) {
    this.returnCode = returnCode;
  }

  public String getNonceStr() {
    return nonceStr;
  }

  public void setNonceStr(String nonceStr) {
    this.nonceStr = nonceStr;
  }

  public String getResultCode() {
    return resultCode;
  }

  public void setResultCode(String resultCode) {
    this.resultCode = resultCode;
  }

  public String getErrCode() {
    return errCode;
  }

  public void setErrCode(String errCode) {
    this.errCode = errCode;
  }

  public String getTradeType() {
    return tradeType;
  }

  public void setTradeType(String tradeType) {
    this.tradeType = tradeType;
  }

  public String getPrepayId() {
    return prepayId;
  }

  public void setPrepayId(String prepayId) {
    this.prepayId = prepayId;
  }

  public String getMwebUrl() {
    return mwebUrl;
  }

  public void setMwebUrl(String mwebUrl) {
    this.mwebUrl = mwebUrl;
  }

  public String getErrCodeDes() {
    return errCodeDes;
  }

  public void setErrCodeDes(String errCodeDes) {
    this.errCodeDes = errCodeDes;
  }
}
