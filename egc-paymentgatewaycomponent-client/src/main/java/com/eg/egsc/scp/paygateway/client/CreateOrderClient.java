/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.client;

import com.eg.egsc.common.exception.CommonException;
import com.eg.egsc.scp.paygateway.dto.CreateOrderRequestForBackendDto;

/**
 * 提供外部组件与应用调用的接口类
 * @author lihui
 * @since 2018年3月5日
 */
public interface CreateOrderClient {
    /**
     * 通过组件或者应用封装的CreateOrderRequestForBackendDto下单
     * @param createOrderRequestForBackendDto 封装的支付下单对象
     * @throws CommonException  异常信息
     */
    void createOrder(CreateOrderRequestForBackendDto createOrderRequestForBackendDto) throws CommonException;
}
