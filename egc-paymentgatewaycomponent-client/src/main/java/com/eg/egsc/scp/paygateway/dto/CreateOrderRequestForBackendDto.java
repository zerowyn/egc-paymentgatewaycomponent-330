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
  private String mch_id;

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
  private String out_trade_no;

  /**
   * 微信统一下单接口的消息格式-微信的总金额
   *
   */
  private String fee_type;

  /**
   * 微信统一下单接口的消息格式-微信的总金额
   *
   */
  private String total_fee;

  /**
   * 微信统一下单接口的消息格式-微信的终端IP
   *
   */
  private String spbill_create_ip;

  /**
   * 微信统一下单接口的消息格式-微信的交易起始时间
   *
   */
  private String time_start;

  /**
   * 微信统一下单接口的消息格式-微信的交易结束时间
   *
   */
  private String time_expire;


  /**
   * 微信统一下单接口的消息格式-微信的交易类型
   *支付类型（默认）APP: 手机app支付
   * MWEB：H5支付（网页支付，临时停车的扫码支付实际上是用的这种）
   * trade_type=NATIVE，此参数必传
   */

  private String trade_type;

  /**
   * 商品ID
   * 此id为二维码中包含的商品ID，商户自行定义
   */
  private String product_id;

  /**
   * 指定支付方式
   * no_credit--指定不能使用信用卡支付
   */
  private String limit_pay;

  /**
   * 用户标识
   *
   */
  private String openid;

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getSpbill_create_ip() {
    return spbill_create_ip;
  }

  public void setSpbill_create_ip(String spbill_create_ip) {
    this.spbill_create_ip = spbill_create_ip;
  }

  public String getTotal_fee() {
    return total_fee;
  }

  public void setTotal_fee(String total_fee) {
    this.total_fee = total_fee;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public String getFee_type() {
    return fee_type;
  }

  public void setFee_type(String fee_type) {
    this.fee_type = fee_type;
  }

  public String getTime_start() {
    return time_start;
  }

  public void setTime_start(String time_start) {
    this.time_start = time_start;
  }

  public String getTime_expire() {
    return time_expire;
  }

  public void setTime_expire(String time_expire) {
    this.time_expire = time_expire;
  }

  public String getTrade_type() {
    return trade_type;
  }

  public void setTrade_type(String trade_type) {
    this.trade_type = trade_type;
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

  public String getAttach() {
    return attach;
  }

  public void setAttach(String attach) {
    this.attach = attach;
  }

  public String getProduct_id() {
    return product_id;
  }

  public void setProduct_id(String product_id) {
    this.product_id = product_id;
  }

  public String getLimit_pay() {
    return limit_pay;
  }

  public void setLimit_pay(String limit_pay) {
    this.limit_pay = limit_pay;
  }

  public String getOpenid() {
    return openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }
}
