/**
 * @Probject Name: scp-pay-gateway-app-client
 * @Path: com.eg.egsc.scp.paygateway.dtoOrderQueryRequestForBackendDto.java
 * @Create By caiqinli
 * @Create In 2018年2月6日 下午2:15:14
 */
package com.eg.egsc.scp.paygateway.dto;


import org.springframework.stereotype.Component;

import com.eg.egsc.framework.client.dto.BaseBusinessDto;

/**
 * @Class Name OrderQueryRequestForBackendDto
 * @Author caiqinli
 * @Create In 2018年2月6日
 */
@Component
public class OrderQueryRequestForBackendDto  extends BaseBusinessDto {
  
  /**
   * @Field long serialVersionUID 
   */
  private static final long serialVersionUID = 1L;
  
  /**
   * 指定请求的目标平台-‘WEIXIN’或‘ALIPAY’，必填
   * 
   */
  private String platform;
  
  /**
   * 转发给微信订单查询接口的消息格式-微信开放平台审核通过的应用APPID
   * 
   */
  private String appId;
  
  /**
   * 转发给微信订单查询接口的消息格式-微信支付分配的商户号
   * 
   */
  private String mchId;
  
  /**
   * 转发给微信订单查询接口的消息格式-微信的订单号，优先使用
   * 
   */
  private String transactionId;
  
  /**
   * 转发给微信订单查询接口的消息格式-缴费后台内部订单号，
   * 当没提供transaction_id时需要传这个。
   * 要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
   * 
   */
  private String outTradeNo;

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





}
