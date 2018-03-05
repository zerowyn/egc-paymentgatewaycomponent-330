/**
 * @Probject Name: scp-pay-gateway-app-service
 * @Path: com.eg.egsc.scp.paygateway.serviceNotifyService.java
 * @Create By fandong
 * @Create In 2018年2月11日 TODO
 */
package com.eg.egsc.scp.paygateway.service;

/**
 * 提供API与Controller调用的接口类
 * 
 * @Class Name NotifyService
 * @Author fandong
 * @Create In 2018年2月11日
 */

public interface NotifyService {

  /**
   * 缴费后台微信对象 转换微信消息 （String xmlString); xmlString ----> Map
   *
   * Map ----> 验签 转换数据 微信请求实体 转换成 缴费后台微信对象实体 return
   */

  public void transferWeChatMessageForWeiXinString(String requestData);

  /**
   * 
   * @Methods Name transferAlipaytMessageForAlipayString
   * @Create In 2018年3月5日 By fandong
   * @param requestData
   * @return PayResultsNotifyResponseForBackendDto
   */
  public void transferAlipaytMessageForAlipayString(String requestData);



}
