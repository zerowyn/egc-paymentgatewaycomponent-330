/**
 * @Probject Name: scp-pay-gateway-app-client
 * @Path: com.eg.egsc.scp.paygateway.dtoCreateOrderRequestForBackendDto.java
 * @Create By lihui
 * @Create In 2018年2月25日 上午：11:51
 * TODO
 */
package com.eg.egsc.scp.paygateway.dto;

import com.eg.egsc.framework.client.dto.BaseBusinessDto;
import org.springframework.stereotype.Component;

/**
 * @Class Name CreateOrderRequestForBackendDto
 * @Author lihui
 * @Create In 2018年2月25日
 */
@Component
public class CreateOrderRequestForBackendDto extends BaseBusinessDto {
  
  /**
   * @Field long serialVersionUID 
   */
  private static final long serialVersionUID = 1L;
  
  /**
   * 指定请求的目标平台-‘WEIXIN’或‘ALIPAY’，必填
   * 
   */
  //@NotBlank(message = "scp.pay.gateway.app.backend.request.platform.notblank")
  private String platform;
  
  /**
   * 微信统一下单接口的消息格式-微信开放平台审核通过的应用APPID
   * 
   */
  private String appid;
  
  /**
   * 微信统一下单接口的消息格式-微信支付分配的商户号
   * 
   */
  private String mchId;

  /**
   * 微信统一下单接口的消息格式-微信的商品描述
   *
   */
  private String body;

  /**
   * 微信统一下单接口的消息格式-商品详情
   *
   */
  private String detail;

  /**
   * 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
   */
  private String attach;

  /**
   * 微信统一下单接口的消息格式-缴费后台内部订单号，
   * 当没提供transaction_id时需要传这个。
   * 要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
   *
   */
  private String outTradeNo;

  /**
   * 微信统一下单接口的消息格式-微信的总金额
   *
   */
  private String feeType;

  /**
   * 微信统一下单接口的消息格式-微信的总金额
   *
   */
  private String totalFee;

  /**
   * 微信统一下单接口的消息格式-微信的终端IP
   *
   */
  private String spbillCreateIp;

  /**
   * 微信统一下单接口的消息格式-微信的交易起始时间
   *
   */
  private String timeStart;

  /**
   * 微信统一下单接口的消息格式-微信的交易结束时间
   *
   */
  private String timeExpire;


  /**
   * 微信统一下单接口的消息格式-微信的交易类型
   *支付类型（默认）APP: 手机app支付
   * MWEB：H5支付（网页支付，临时停车的扫码支付实际上是用的这种）
   * trade_type=NATIVE，此参数必传
   */

  private String tradeType;

  /**
   * 商品ID
   * 此id为二维码中包含的商品ID，商户自行定义
   */
  private String productId;

  /**
   * 指定支付方式
   * no_credit--指定不能使用信用卡支付
   */
  private String limitPay;

  /**
   * 用户标识
   *
   */
  private String openId;

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

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

  public String getAttach() {
    return attach;
  }

  public void setAttach(String attach) {
    this.attach = attach;
  }

  public String getMchId() {
    return mchId;
  }

  public void setMchId(String mchId) {
    this.mchId = mchId;
  }

  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public String getFeeType() {
    return feeType;
  }

  public void setFeeType(String feeType) {
    this.feeType = feeType;
  }

  public String getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(String totalFee) {
    this.totalFee = totalFee;
  }

  public String getSpbillCreateIp() {
    return spbillCreateIp;
  }

  public void setSpbillCreateIp(String spbillCreateIp) {
    this.spbillCreateIp = spbillCreateIp;
  }

  public String getTimeStart() {
    return timeStart;
  }

  public void setTimeStart(String timeStart) {
    this.timeStart = timeStart;
  }

  public String getTimeExpire() {
    return timeExpire;
  }

  public void setTimeExpire(String timeExpire) {
    this.timeExpire = timeExpire;
  }

  public String getTradeType() {
    return tradeType;
  }

  public void setTradeType(String tradeType) {
    this.tradeType = tradeType;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getLimitPay() {
    return limitPay;
  }

  public void setLimitPay(String limitPay) {
    this.limitPay = limitPay;
  }

  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }
}
