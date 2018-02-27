/**
 * @Probject Name: scp-pay-gateway-app-client
 * @Path: com.eg.egsc.scp.paygateway.daoCreateOrderResponseForWeiXin.java
 * @Create By lihui
 * @Create In 2018年2月25日 上午：11:51
 * TODO
 */
package com.eg.egsc.scp.paygateway.service.model;

import com.eg.egsc.framework.client.dto.BaseBusinessDto;
import org.springframework.stereotype.Component;
import sun.awt.SunHints;

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
   * 返回状态码
   * SUCCESS/FAIL
   */
  private String return_code;

  /**
   * 返回信息
   */
  private String return_msg;

  /**
   * 微信统一下单接口的消息格式-微信开放平台审核通过的应用APPID
   *
   */
  private String appid;

  /**
   * 微信统一下单接口的消息格式-微信支付分配的商户号
   *
   */
  private String mch_id;

  /**
   * 设备号
   * 调用接口提交的终端设备号，
   */
  private String device_info;

  /**
   * 随机字符串
   * 微信返回的随机字符串（验签用，不向缴费后台传送）
   */
  private String nonce_str;

  /**
   * 签名
   *
   */
  private String sign;

  /**
   * 业务结果
   *
   */
  private String result_code;

  /**
   * 错误代码
   *
   */
  private String err_code;

  /**
   * 错误代码描述
   *
   */
  private String err_code_des;

  /**
   * 微信统一下单接口的消息格式-微信的交易类型
   *
   */
  private String trade_type;

  /**
   * 预支付交易会话标识
   *
   */
  private String prepay_id;

  /**
   * 支付跳转链接
   * mweb_url为拉起微信支付收银台的中间页面，可通过访问该url来拉起微信客户端，完成支付,mweb_url的有效期为5分钟。
   * 当trade_type为MWEB时返回。
   */
  private String mweb_url;

  public String getTrade_type() {
    return trade_type;
  }

  public void setTrade_type(String trade_type) {
    this.trade_type = trade_type;
  }

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

  public String getReturn_code() {
    return return_code;
  }

  public void setReturn_code(String return_code) {
    this.return_code = return_code;
  }

  public String getReturn_msg() {
    return return_msg;
  }

  public void setReturn_msg(String return_msg) {
    this.return_msg = return_msg;
  }

  public String getDevice_info() {
    return device_info;
  }

  public void setDevice_info(String device_info) {
    this.device_info = device_info;
  }

  public String getNonce_str() {
    return nonce_str;
  }

  public void setNonce_str(String nonce_str) {
    this.nonce_str = nonce_str;
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public String getResult_code() {
    return result_code;
  }

  public void setResult_code(String result_code) {
    this.result_code = result_code;
  }

  public String getErr_code() {
    return err_code;
  }

  public void setErr_code(String err_code) {
    this.err_code = err_code;
  }

  public String getErr_code_des() {
    return err_code_des;
  }

  public void setErr_code_des(String err_code_des) {
    this.err_code_des = err_code_des;
  }

  public String getPrepay_id() {
    return prepay_id;
  }

  public void setPrepay_id(String prepay_id) {
    this.prepay_id = prepay_id;
  }

  public String getMweb_url() {
    return mweb_url;
  }

  public void setMweb_url(String mweb_url) {
    this.mweb_url = mweb_url;
  }
}
