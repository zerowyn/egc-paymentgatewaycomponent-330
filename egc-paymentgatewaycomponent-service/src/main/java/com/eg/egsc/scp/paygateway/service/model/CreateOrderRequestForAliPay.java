/**
 * @Probject Name: scp-pay-gateway-app-dao
 * @Path: com.eg.egsc.scp.paygateway.daoCreateOrderRequestForAliPay.java
 * @Create By lihui
 * @Create In 2018年2月25日 上午11:16
 * TODO
 */
package com.eg.egsc.scp.paygateway.service.model;

import org.springframework.stereotype.Component;

/**
 * @Class Name CreateOrderRequestForAliPay
 * @Author lihui
 * @Create In 2018年2月25日
 */
@Component
public class CreateOrderRequestForAliPay {
  
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
   * 由支付网关调用 随机数生成算法 生成一个32位的字符串。由签名与验签模块生成。
   * 
   */
  private String nonce_str;
  
  
  /**
   * 由支付网关生成的签名，详见签名生成算法。由签名与验签模块生成。
   * 
   */
  private String sign;
  
  

}
