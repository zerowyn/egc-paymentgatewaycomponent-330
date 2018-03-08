/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.service.impl;

import com.eg.egsc.scp.paygateway.PaymentGatewayServiceApplication;
import com.eg.egsc.scp.paygateway.service.SignatureService;
import com.eg.egsc.scp.paygateway.util.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static com.eg.egsc.scp.paygateway.util.PaymentBusinessConstant.SIGN_TYPE;


/**
 * @author gucunyang
 * @since 2018-02-27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PaymentGatewayServiceApplication.class})
public class SignatureServiceImplTest {

    @Autowired
    SignatureService signatureServiceImpl;

    private static final Logger logger = LoggerFactory.getLogger(SignatureServiceImplTest.class);

    /**
     * 测试微信签名
     */
    @Test
    public void weixinSignature() {
        String sign;
        Map weixinRequestMap = null;
        sign = signatureServiceImpl.weixinSignature(weixinRequestMap);
        logger.info("test the param is null, the result is sign=" + sign);
        weixinRequestMap = new HashMap();
        weixinRequestMap.put("appid", "wx5332d47f724492fa");
        weixinRequestMap.put("mch_id", "1497973582");
        String nonceStr = StringUtils.generateRandomString();
        weixinRequestMap.put("nonce_str=", nonceStr);
        weixinRequestMap.put("out_trade_no", "44444444444441444");
        sign = signatureServiceImpl.weixinSignature(weixinRequestMap);
        logger.info("test the default sign type, the result is sign=" + sign);
        weixinRequestMap.put(SIGN_TYPE, "HmacSHA256");
        sign = signatureServiceImpl.weixinSignature(weixinRequestMap);
        logger.info("test the sign type of HmacSHA256, the result is sign=" + sign);
        weixinRequestMap.put(SIGN_TYPE, "RSA");
        sign = signatureServiceImpl.weixinSignature(weixinRequestMap);
        logger.info("test the unsupported sign type, the result is sign=" + sign);
    }

    /**
     * 测试微信验签
     */
    @Test
    public void weixinSignatureCheck() {
        Map weixinResponseMap = null;
        boolean result;
        result = signatureServiceImpl.weixinSignatureCheck(weixinResponseMap);
        logger.info("test the param is null, the result=" + result);
        String responseXmlString = "<xml><return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "<return_msg><![CDATA[OK]]></return_msg>\n" +
                "<appid><![CDATA[wx5332d47f724492fa]]></appid>\n" +
                "<mch_id><![CDATA[1497973582]]></mch_id>\n" +
                "<nonce_str><![CDATA[WYyhNJajecPgmDao]]></nonce_str>\n" +
                "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "<openid><![CDATA[obmFx0sukNV5Ntpb7RaAPfMzD7OA]]></openid>\n" +
                "<is_subscribe><![CDATA[N]]></is_subscribe>\n" +
                "<trade_type><![CDATA[NATIVE]]></trade_type>\n" +
                "<bank_type><![CDATA[CFT]]></bank_type>\n" +
                "<total_fee>8</total_fee>\n" +
                "<fee_type><![CDATA[CNY]]></fee_type>\n" +
                "<transaction_id><![CDATA[4200000053201802100302371522]]></transaction_id>\n" +
                "<out_trade_no><![CDATA[44444444444441444]]></out_trade_no>\n" +
                "<attach><![CDATA[]]></attach>\n" +
                "<time_end><![CDATA[20180210100430]]></time_end>\n" +
                "<trade_state><![CDATA[SUCCESS]]></trade_state>\n" +
                "<cash_fee>8</cash_fee>\n" +
                "</xml>";
        weixinResponseMap = StringUtils.transferXMLtoMap(responseXmlString);
        result = signatureServiceImpl.weixinSignatureCheck(weixinResponseMap);
        logger.info("test the sign is null, the result=" + result);
        weixinResponseMap.put("sign", "9F193955FA4A50B80351ECC745A94CEA");
        result = signatureServiceImpl.weixinSignatureCheck(weixinResponseMap);
        logger.info("test the method, the result=" + result);
    }

    /**
     * 测试支付宝签名
     */
    @Test
    public void alipaySignature() {
        Map alipayRequestMap = null;
        signatureServiceImpl.alipaySignature(alipayRequestMap);
        logger.info("test the param is null");
        alipayRequestMap = new HashMap();
        alipayRequestMap.put("app_id", "2018020102122242");
        alipayRequestMap.put("biz_content", "{\"out_trade_no\":\"20150320010101001\",\"trade_no\":" +
                "\"20150320010101001\"}");
        alipayRequestMap.put("method", "alipay.trade.query");
        alipayRequestMap.put("timestamp", "2018-02-26 10:44:30");
        alipayRequestMap.put("version", "1.0");
        String sign = signatureServiceImpl.alipaySignature(alipayRequestMap);
        logger.info("test the sign type is null, the result is sign=" + sign);
        alipayRequestMap.put(SIGN_TYPE, "RSA2");
        sign = signatureServiceImpl.alipaySignature(alipayRequestMap);
        logger.info("test the sign type of RSA2, the result is sign=" + sign);
        alipayRequestMap.put(SIGN_TYPE, "RSA");
        sign = signatureServiceImpl.alipaySignature(alipayRequestMap);
        logger.info("test the sign type of RSA, the result is sign=" + sign);
        alipayRequestMap.put(SIGN_TYPE, "MD5");
        sign = signatureServiceImpl.alipaySignature(alipayRequestMap);
        logger.info("test other sign type , the result is sign=" + sign);
    }

    /**
     * 测试支付宝异步验签
     */
    @Test
    public void alipaySignatureAsyCheck() {
        Map alipayresponseMap = null;
        signatureServiceImpl.alipaySignatureAsyCheck(alipayresponseMap);
        String responseXmlString = "<code>10000</code>" +
                "<msg>Success</msg>  <trade_no>2013112011001004330000121536</trade_no>" +
                "  <out_trade_no>6823789339978248</out_trade_no>" +
                "  <buyer_logon_id>159****5620</buyer_logon_id>" +
                "  <trade_status>TRADE_CLOSED</trade_status>" +
                "  <total_amount>88.88</total_amount>";
        String sign = "gFKPnquWb/cNbdAYHONbeHQ/Wg7UVzK8ZaX24yoHZUr2L0QxfWXe9GJ5JbOzUDcVMmSlv3J7xHylj" +
                "/qKKzu2Z5+3wul/At/ihNORMNtFPH1i7pd6H8hFQB64gcIo3z6i1dlMCzz218tRoSFR4IcVrnpPDLWq27xTTEcc" +
                "XTvCKJ7sR/YTGtF0JCgFpTYhmhPVw+6YEzIrd9bIBBPzszr8dTtWt3m4X3TwmyCb/nyxlzUQ6FDoP0cSqvWnV7Qn" +
                "Sn2UPfmdH33hCer3H4kIMYjFmvxjDdp0SV9WBHKSQFV7N+OfS5bk6cFasyoRVU3lgGFcLsX7ZS5657C47jWJcmTsFg==";
        alipayresponseMap = StringUtils.transferXMLtoMap(responseXmlString);
        boolean result = signatureServiceImpl.alipaySignatureAsyCheck(alipayresponseMap);
        logger.info("test the sign of alipaySignatureAsyCheck is null, the result=" + result);
        alipayresponseMap.put("sign", sign);
        result = signatureServiceImpl.alipaySignatureAsyCheck(alipayresponseMap);
        logger.info("test the method alipaySignatureAsyCheck, the result=" + result);

    }

    @Test
    public void alipaySignatureSynCheck() {
        String content = "{\"code\":\"40004\",\"msg\":\"Business Failed\",\"sub_code\":\"ACQ.TRADE_NOT_EXIST\"," +
                "\"sub_msg\":\"交易不存在\",\"buyer_pay_amount\":\"0.00\",\"invoice_amount\":\"0.00\"," +
                "\"out_trade_no\":\"20150320010101001\",\"point_amount\":\"0.00\",\"receipt_amount\":\"0.00\"}";
        String sign = "gFKPnquWb/cNbdAYHONbeHQ/Wg7UVzK8ZaX24yoHZUr2L0QxfWXe9GJ5JbOzUDcVMmSlv3J7xHylj" +
                "/qKKzu2Z5+3wul/At/ihNORMNtFPH1i7pd6H8hFQB64gcIo3z6i1dlMCzz218tRoSFR4IcVrnpPDLWq27xTTEcc" +
                "XTvCKJ7sR/YTGtF0JCgFpTYhmhPVw+6YEzIrd9bIBBPzszr8dTtWt3m4X3TwmyCb/nyxlzUQ6FDoP0cSqvWnV7Qn" +
                "Sn2UPfmdH33hCer3H4kIMYjFmvxjDdp0SV9WBHKSQFV7N+OfS5bk6cFasyoRVU3lgGFcLsX7ZS5657C47jWJcmTsFg==";
        boolean result = signatureServiceImpl.alipaySignatureSynCheck(content, sign);
        logger.info("test the method alipaySignatureSynCheck, the result=" + result);
    }

}