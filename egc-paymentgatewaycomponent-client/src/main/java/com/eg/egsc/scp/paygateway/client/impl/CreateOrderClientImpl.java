/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.client.impl;

import com.eg.egsc.common.exception.CommonException;
import com.eg.egsc.framework.client.core.BaseApiClient;
import com.eg.egsc.scp.paygateway.client.CreateOrderClient;
import com.eg.egsc.scp.paygateway.dto.CreateOrderRequestForBackendDto;
import org.springframework.stereotype.Component;

/**
 * 提供外部组件与应用调用的实现类
 * @author wanghongben
 * @since 2018年1月11日
 */

@Component
public class CreateOrderClientImpl extends BaseApiClient implements CreateOrderClient{

    public CreateOrderClientImpl(){
        super();
    }

    public CreateOrderClientImpl(String gatewayUrl){
        super(gatewayUrl);
    }

    @Override
    public void createOrder(CreateOrderRequestForBackendDto createOrderRequestForBackendDto) throws CommonException {
        post("/pay/createorder",createOrderRequestForBackendDto);
        return;
    }

    @Override
    protected String getContextPath() {
        return "/paymentgateway";
    }
}
