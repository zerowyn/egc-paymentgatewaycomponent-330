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
     * @param requestParamsMap 请求数据
     * @return sign 返回签名sign
     */
    String weixinSignature(Map requestParamsMap);

    /**
     * 接收返回数据，验证微信签名是否正确
     *
     * @param requestParamsMap
     * @return
     */
    boolean weixinSigantureCheck(Map requestParamsMap);

    /**
     * 接收请求数据，按照支付宝提供的协议进行签名
     *
     * @param requestParamsMap
     * @return
     */
    String alipaySignature(Map requestParamsMap);

    /**
     * 接收返回数据，验证支付宝签名是否正确
     *
     * @param requestParamsMap
     * @return
     */
    boolean alipaySigantureCheck(Map requestParamsMap);

}
