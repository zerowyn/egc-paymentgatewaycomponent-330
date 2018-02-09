/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.service;

import java.util.Map;

/**
 * @author gucunyang
 * @since 2018-02-08
 */
public interface SignatureService {
    /**
     * 接收请求数据，按照微信提供的协议进行签名
     *
     * @param requestParamsMap 请求数据 TODO 根据接收数据的格式，修改参数
     * @return sign 返回签名sign
     */
    public String WeixinSignature(Map requestParamsMap);

    public boolean WeixinSigantureCheck(Map requestParamsMap);

    public String AlipaySignature(Map requestParamsMap);

    public boolean AlipaySigantureCheck(Map requestParamsMap);

}
