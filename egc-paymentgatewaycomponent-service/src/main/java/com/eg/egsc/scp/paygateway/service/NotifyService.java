/**
 * @Probject Name: scp-pay-gateway-app-service
 * @Path: com.eg.egsc.scp.paygateway.serviceNotifyService.java
 * @Create By fandong
 * @Create In 2018年2月11日 下午2:40:36
 */
package com.eg.egsc.scp.paygateway.service;

import java.util.Map;

/**
 * 提供API与Controller调用的接口类
 *
 * @Class Name NotifyService
 * @Author fandong
 * @Create In 2018年2月11日
 */

public interface NotifyService {

    /**
     * 缴费结果通知处理消息
     *
     * @param map
     * @return
     */
    String disposeMessage(Map<String, Object> map, Boolean flag);

}
