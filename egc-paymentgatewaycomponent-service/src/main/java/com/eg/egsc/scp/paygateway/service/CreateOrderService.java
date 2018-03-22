/**
 * @Probject Name: scp-pay-gateway-app-service
 * @Path: com.eg.egsc.scp.paygateway.service.CreateOrderService.java
 * @Create By lihui
 * @Create In 2018年2月25日 上午11:02
 */
package com.eg.egsc.scp.paygateway.service;


import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.eg.egsc.scp.paygateway.dto.CreateOrderRequestForBackendDto;
import com.eg.egsc.scp.paygateway.dto.CreateOrderResponseForBackendDto;
import com.eg.egsc.scp.paygateway.dto.RequestForGetOpenIdDto;
import com.eg.egsc.scp.paygateway.service.model.*;

/**
 * 提供API与Controller调用的接口类
 * @Class Name CreateOrderService
 * @Author lihui
 * @Create In 2018年2月25日
 */
public interface CreateOrderService {

    /**
     * 接收缴费后台请求，转换为数据格式
     * @param createOrderRequestForBackendDto 缴费后台提交的请求数据对象
     * @return CreateOrderRequestForWeiXin 返回给微信的请求数据对象
     */
    public CreateOrderRequestForWeiXin transferBackendMessageForWeiXin(CreateOrderRequestForBackendDto createOrderRequestForBackendDto);

    /**
     * 接收微信返回数据，转换为数据格式
     * @param createOrderResponseForWeiXin 微信接口返回的数据对象
     * @return CreateOrderResponseForBackendDto 返回给缴费后台的数据对象
     */
    public CreateOrderResponseForBackendDto transferWeiXinMessageForBackendSystme(
            CreateOrderResponseForWeiXin createOrderResponseForWeiXin);

    /**
     * 接收缴费后台请求，转换为数据格式
     * @param createOrderRequestForBackendDto 缴费后台提交的请求数据对象
     * @return CreateOrderRequestForAliPay 返回给支付宝的请求数据对象
     */
    public CreateOrderRequestForAliPay transferBackendMessageForAliPay(
            CreateOrderRequestForBackendDto createOrderRequestForBackendDto);

    /**
     * 接收微信返回数据，转换为数据格式
     * @param alipayTradeAppPayResponse 支付宝接口返回的数据对象
     * @return CreateOrderResponseForBackendDto 返回给缴费后台的数据对象
     */
    public CreateOrderResponseForBackendDto transferAliPayMessageForBackendSystme(
            AlipayTradeAppPayResponse alipayTradeAppPayResponse);

    /**
     *
     * @param createOrderRequestForBackendDto 缴费后台创建支付订单的数据对象
     * @return CreateOrderResponseForBackendDto 返回给缴费后台的数据对象
     */
    public CreateOrderResponseForBackendDto createOrderRequestFromBackendSystme(CreateOrderRequestForBackendDto createOrderRequestForBackendDto);
}
