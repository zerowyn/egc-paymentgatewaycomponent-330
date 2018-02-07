/**
 * @Probject Name: scp-pay-gateway-app-dao
 * @Path: com.eg.egsc.scp.paygateway.extmapper.entityOrderQueryRequestFromBackendSystem.java
 * @Create By caiqinli
 * @Create In 2018年2月5日 下午5:30:07
 * TODO
 */
package com.eg.egsc.scp.paygateway.service.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

/**
 * @Class Name OrderQueryRequestFromBackendSystem
 * @Author caiqinli
 * @Create In 2018年2月5日
 */
@Component
public class OrderQueryRequestForBackendSystem {
  
  /**
   * 指定请求的目标平台-‘WEIXIN’或‘ALIPAY’，必填
   * 
   */
  @NotBlank(message = "scp.pay.gateway.app.backend.request.platform.notblank")
  private String platform;
  
  /**
   * 转发给微信订单查询接口的消息格式-微信开放平台审核通过的应用APPID
   * 
   */
  private String appid;
  
  /**
   * 转发给微信订单查询接口的消息格式-微信支付分配的商户号
   * 
   */
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
   * @Return the String platform
   */
  public String getPlatform() {
    return platform;
  }

  /**
   * @Param String platform to set
   */
  public void setPlatform(String platform) {
    this.platform = platform;
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

}
