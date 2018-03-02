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
     * @param requestParamsMap 请求参数集合
     * @return 签名
     */
    String weixinSignature(Map requestParamsMap);

    /**
     * 接收返回数据，验证微信签名是否正确
     *
     * @param responseParamsMap 返回参数集合
     * @return 验签结果
     */
    boolean weixinSignatureCheck(Map responseParamsMap);

    /**
     * 接收请求数据，按照支付宝提供的协议进行签名
     *
     * @param requestParamsMap 请求参数集合
     * @return 签名
     */
    String alipaySignature(Map requestParamsMap);

    /**
     * 接收异步通知数据，验证支付宝签名是否正确
     *
     * @param responseParamsMap 返回参数集合
     * @return 验签结果
     */
    boolean alipaySignatureAsyCheck(Map responseParamsMap);

    /**
     * 接收同步返回数据，验证支付宝签名是否正确
     *
     * @param content 返回字符串
     * @param sign    签名
     * @return 验签结果
     */
    boolean alipaySignatureSynCheck(String content, String sign);

}
