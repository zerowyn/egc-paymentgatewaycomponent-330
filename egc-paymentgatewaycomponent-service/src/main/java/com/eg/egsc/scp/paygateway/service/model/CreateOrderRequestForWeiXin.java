/**
 * @Probject Name: scp-pay-gateway-app-dao
 * @Path: com.eg.egsc.scp.paygateway.daoCreateOrderRequestForWeiXin.java
 * @Create By lihui
 * @Create In 2018年2月25日 上午11:15
 * TODO
 */
package com.eg.egsc.scp.paygateway.service.model;

import com.sun.tools.corba.se.idl.StringGen;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Class Name CreateOrderRequestForWeiXin
 * @Author lihui
 * @Create In 2018年2月25日
 */
@Component
@XmlRootElement(name="xml")
public class CreateOrderRequestForWeiXin {
  
  /**
   * 转发给微信创建订单接口的消息格式-微信开放平台审核通过的应用APPID
   * 
   */
  @NotBlank(message = "scp.pay.gateway.app.pay.request.appid.notblank")
  private String appid;
  
  /**
   * 转发给微信订单查询接口的消息格式-微信支付分配的商户号
   * 
   */
  @NotBlank(message = "scp.pay.gateway.app.pay.request.mch_id.notblank")
  private String mch_id;
  
  /**
   * 设备号
   * 写入默认值“WEB”（从配置表读取）
   */
  private String device_info;
  
  /**
   * 转发给微信订单查询接口的消息格式-随机字符串，不长于32位。推荐随机数生成算法
   * 
   */
  private String nonce_str;

  /**
   * 转发给微信订单查询接口的消息格式-签名
   *
   */
  private String sign;

  /**
   * 签名类型
   * 写入默认值“MD5”（从配置表读取）
   */
  private String sign_type;

  /**
   * 商品描述
   */
  private String body;

  /**
   * 商品详情
   */
  private String detail;

  /**
   * 附加数据
   */
  private String attach;

  /**
   * 转发给微信订单查询接口的消息格式-缴费后台内部订单号，
   * 当没提供transaction_id时需要传这个。
   * 要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
   *
   */
  private String out_trade_no;

  /**
   *货币类型
   */
  private String fee_type;

  /**
   * 总金额
   */
  private String total_fee;

  /**
   * 终端IP
   */
  private String spbill_create_ip;

  /**
   * 交易起始时间
   */
  private String time_start;

  /**
   * 交易结束时间
   */
  private String time_expire;

  /**
   * 订单优惠标记
   */
  private String goods_tag;

  /**
   * 通知地址
   */
  private String notify_url;

  /**
   * 交易类型
   */
  private String trade_type;

  /**
   * 商品ID
   */
  private String product_id;

  /**
   * 指定支付方式
   */
  private String limit_pay;

  /**
   * openid
   */
  private String openid;

  /**
   * 场景信息
   */
  private String scene_info;

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

  public String getDevice_info() {
    return device_info;
  }

  public void setDevice_info(String device_info) {
    this.device_info = device_info;
  }

  public String getSign_type() {
    return sign_type;
  }

  public void setSign_type(String sign_type) {
    this.sign_type = sign_type;
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

  public String getAttach() {
    return attach;
  }

  public void setAttach(String attach) {
    this.attach = attach;
  }

  public String getFee_type() {
    return fee_type;
  }

  public void setFee_type(String fee_type) {
    this.fee_type = fee_type;
  }

  public String getTotal_fee() {
    return total_fee;
  }

  public void setTotal_fee(String total_fee) {
    this.total_fee = total_fee;
  }

  public String getSpbill_create_ip() {
    return spbill_create_ip;
  }

  public void setSpbill_create_ip(String spbill_create_ip) {
    this.spbill_create_ip = spbill_create_ip;
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

  public String getGoods_tag() {
    return goods_tag;
  }

  public void setGoods_tag(String goods_tag) {
    this.goods_tag = goods_tag;
  }

  public String getNotify_url() {
    return notify_url;
  }

  public void setNotify_url(String notify_url) {
    this.notify_url = notify_url;
  }

  public String getTrade_type() {
    return trade_type;
  }

  public void setTrade_type(String trade_type) {
    this.trade_type = trade_type;
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

  public String getScene_info() {
    return scene_info;
  }

  public void setScene_info(String scene_info) {
    this.scene_info = scene_info;
  }
}
