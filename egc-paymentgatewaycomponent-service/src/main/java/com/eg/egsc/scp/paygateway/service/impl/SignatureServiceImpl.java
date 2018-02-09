/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.service.impl;

import com.eg.egsc.scp.paygateway.service.SignatureService;
import com.eg.egsc.scp.paygateway.util.Base64Utils;
import com.eg.egsc.scp.paygateway.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

/**
 * @author gucunyang
 * @since 2018-02-08
 */
public class SignatureServiceImpl implements SignatureService {

    protected final Logger logger = LoggerFactory.getLogger(SignatureServiceImpl.class);

    // 微信商户key，后期应从配置文件读取 TODO
    private static final String WEIXIN_MCH_KEY = "D8892E20D8F95CB366C6783602952CEE";

    // 支付宝应用私钥
    private static final String ALIPAY_MCH_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDMB6n8pjgpEw6lXu88RbQkoXdlMjZkm/ELg/Iy9ojN4eyQM5dP2ceodA+Mf0NN0GijydV5Iv/qFzngyJddTyJsTJHv99ivHFb4E4awJU9vmPEQiQc9pc3xbmtG0Al/IV8WbyX/6DCZbCVw4nbwS3BvUGX97tkthqRwxtEM3xWXEow/6KLiWskvcDbrIPjl9ft2cw1V6/ft0Noqo10EXSJ9KUoF4h4loezecJvBueFWUaGxefXnyYvk2WxkFfVr02zWaTY6fhqGr7UT163sNt3KtDy4f3lMkXX1bpjEplYChAi3/PI0rTijZV7xjBeHCDvmy8IA7/XxIF7Sh+ifl1itAgMBAAECggEBAIS9n7x+MB+ZBHhW0XL3LoJ+gn1WM8k1I5gIWjUJ7Gt2R0ByMEmFOL7B87PGwHUJ2GpnT/ml6bXVMpSqLMBKUez/zBrBtzoGSBbSLVjAjXPfn0bQv+M90kT3P9gsVWFexsyDt8dMuwG2OI2+6P7hNd9WAWU6VhWmeandz/h900AbaUiJ+fLjIIG4GFCVMrmn7DMPOgRApaBfHDsOh5DPLVPYvqjprBuiK5UJwdxJG6mdQrgLILlccyL8VP5X1qKxo/eb09hiOJJiDN2l060NEq4CTSvi4wbULxPg3pGHqTQbEuAxT/iMRyU9O82aCGAIV+RpbFH7xxIZB8PRHaj85MECgYEA8Zsg5+nxzqS88m/dFOgOd9P0QV9axOteDSjS6dCawkrHphvIE4ZqNNjmJi0te3du0hGKt+QxYrDoruU6c4SKUciCe/Mw64G8euun1A22pkEdZHCJxwWL6rZSoIBSeYSf2J+s4YljbR3yCQIZ6ZWj5oDaxpmxaXtL/FvYEvKiaGkCgYEA2C9xGem3vkSqZLHw2+Sr6DJIia6G3GzfHCa49V+k8XPG46fEZNGW/tyrWyNj/bhoFioQc7L9OticNy5l/ZhqOE96g6P6t/GW4Ky3Pv7uHcw0QGPRcEVSPXIk1D2Eg3o0BgwQOq+oX1TdFaCKv4QvrbyuAjcmxDDCNIoonW3gBaUCgYAcLhfcdPD7RUO0k0+aDKcDWvl2VoTjywAhUM2o7+fdGZPH0B26CUeB+np9oW9Yo0mNvvYLFNadyDybiakTqDXp7BUsF+Vw0qUFQU9F8IOJSLcA7A9l40O6kKF6jv0Pp2Rtg9JKTBgItiFw44naMNX5ILZjy7FhuDvYp23AaASBYQKBgFVy6xGC/82E9pS2gK1pxUkSFvnUshmOLuHBrHnQRDHrwUFanpX7AePa9SAg7PTsXrfkCHyy0sTfuV9WR5nlFJcNUy+VMRRjuW801Xlg6l18yWpqCe6Gd+sxor0BU89kDgwV2JRZugN0V66ktjLc3LufSGg3xGrnmtuJgGQ5ecLBAoGBAOJBGOsGwl3unwC/57ZnAyD17F2R/MKRgaMZq+i549WsdUU4bTMhJb/5ivkjRzoC0d4qCt0UnFgBsx7lZdbafMVIyaoBBvt5gXUfEsOyiaHNchxwsDzpOet9/xBzfmWbhccNA/UGh0zlNl1x2CdDT5v43/9NpVIS/rYAoJfezwBS";

    private static final String SIGN_TYPE_MD5 = "MD5";

    private static final String SIGN_TYPE_HMAC = "HmacSHA256";

    private static final String SIGN_TYPE_RSA = "RSA";

    private static final String SIGN_TYPE_RSA2 = "RSA2";

    private static final String CHARSET_NAME = "utf-8";

    private static final String SIGN_TYPE = "sign_type";

    private static final String SIGN = "sign";

    /**
     * 接收请求数据，按照微信提供的协议进行签名
     *
     * @param requestParamsMap
     * @return sign
     */
    @Override
    public String weixinSignature(Map requestParamsMap) {
        String sign = null;

        if (requestParamsMap == null) {
            String errorMeg = "requestParamsMap from paymentgateway is null!";
            logger.error(errorMeg);
            return sign;
        }

        StringBuffer content = getSignContent(requestParamsMap);
        content.append("&key=" + WEIXIN_MCH_KEY);
        String signType = (String) requestParamsMap.get(SIGN_TYPE);
        if (StringUtils.isEmpty(signType) || SIGN_TYPE_MD5.toUpperCase().equals(signType.toUpperCase())) {
            sign = md5Sign(content);
        } else if (SIGN_TYPE_HMAC.toUpperCase().equals(signType.toUpperCase())) {
            sign = hmacSHA256Sign(content);
        } else {
            String errorMeg = "Unsupported Signature Type: signType = " + signType;
            logger.error(errorMeg);
        }

        return sign;
    }

    /**
     * 接收返回数据，验证微信签名是否正确
     *
     * @param responseParamsMap
     * @return
     */
    @Override
    public boolean weixinSigantureCheck(Map responseParamsMap) {
        String sign = (String) responseParamsMap.get(SIGN);
        if (StringUtils.isEmpty(sign)) {
            String errorMeg = "sign is empty";
            logger.error(errorMeg);
            return false;
        }
        responseParamsMap.remove(SIGN);
        String signCheck = weixinSignature(responseParamsMap);
        return sign.equals(signCheck);
    }

    /**
     * 接收请求数据，按照支付宝提供的协议进行签名
     *
     * @param requestParamsMap
     * @return
     */
    @Override
    public String alipaySignature(Map requestParamsMap) {
        String sign = null;

        if (requestParamsMap == null) {
            String errorMeg = "requestParamsMap from paymentgateway is null!";
            logger.error(errorMeg);
            return sign;
        }

        StringBuffer content = getSignContent(requestParamsMap);
        String signType = (String) requestParamsMap.get(SIGN_TYPE);
        if (StringUtils.isEmpty(signType)) {
            String errorMeg = "sign_type is null";
            logger.error(errorMeg);
            return sign;
        }
        if (SIGN_TYPE_RSA2.equals(signType)) {
            sign = rsa256Sign(content);
        } else if (SIGN_TYPE_RSA.equals(signType)) {
            sign = rsaSign(content);
        } else {
            String errorMeg = "Unsupported Signature Type: signType = " + signType;
            logger.error(errorMeg);
        }

        return sign;
    }

    @Override
    public boolean alipaySigantureCheck(Map responseParamsMap) {
        String sign = (String) responseParamsMap.get(SIGN);
        if (StringUtils.isEmpty(sign)) {
            String errorMeg = "sign is empty";
            logger.error(errorMeg);
            return false;
        }
        responseParamsMap.remove(SIGN);
        String signCheck = alipaySignature(responseParamsMap);
        return sign.equals(signCheck);
    }

    /**
     * MD5签名
     *
     * @param content
     * @return
     */
    private String md5Sign(StringBuffer content) {
        String sign = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance(SIGN_TYPE_MD5);
            byte[] bytes = md5.digest(content.toString().getBytes(CHARSET_NAME));
            sign = encodeBytes(bytes);
        } catch (Exception e) {
            String errorMeg = "MD5 Signature ERROR";
            logger.error(errorMeg);
        }
        return sign;
    }

    /**
     * HmacSHA256签名
     *
     * @param content
     * @return
     */
    private String hmacSHA256Sign(StringBuffer content) {
        String sign = null;
        try {
            Mac hmacSHA256 = Mac.getInstance(SIGN_TYPE_HMAC);
            SecretKeySpec secretKey = new SecretKeySpec(WEIXIN_MCH_KEY.getBytes(CHARSET_NAME), SIGN_TYPE_HMAC);
            hmacSHA256.init(secretKey);
            byte[] bytes = hmacSHA256.doFinal(content.toString().getBytes(CHARSET_NAME));
            sign = encodeBytes(bytes);
        } catch (Exception e) {
            String errorMeg = "HMAC-SHA256 Signature ERROR";
            logger.error(errorMeg);
            e.printStackTrace();
        }
        return sign;
    }

    /**
     * RSA2签名
     *
     * @param content
     * @return
     */
    private String rsa256Sign(StringBuffer content) {
        String sign = null;
        try {
            PrivateKey privateK = getPrivateKey();
            Signature signature = Signature.getInstance("SHA256WithRSA");
            if (privateK == null) {
                return sign;
            }
            signature.initSign(privateK);
            signature.update(content.toString().getBytes(CHARSET_NAME));
            sign = Base64Utils.encode(signature.sign());
        } catch (Exception e) {
            String errorMeg = "RSA2 Signature ERROR";
            logger.error(errorMeg);
        }
        return sign;
    }

    /**
     * RSA签名
     *
     * @param content
     * @return
     */
    private String rsaSign(StringBuffer content) {
        String sign = null;
        try {
            PrivateKey privateK = getPrivateKey();
            Signature signature = Signature.getInstance("SHA1WithRSA");
            if (privateK == null) {
                return sign;
            }
            signature.initSign(privateK);
            signature.update(content.toString().getBytes(CHARSET_NAME));
            sign = Base64Utils.encode(signature.sign());
        } catch (Exception e) {
            String errorMeg = "RSA Signature ERROR";
            logger.error(errorMeg);
        }
        return sign;
    }

    /**
     * 获取待签名字符串
     *
     * @param requestParamsMap
     * @return
     */
    private StringBuffer getSignContent(Map requestParamsMap) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList(requestParamsMap.keySet());

        Collections.sort(keys);

        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            String value = (String) requestParamsMap.get(key);
            content.append((index == 0 ? "" : "&") + key + "=" + value);
            ++index;
        }
        return content;
    }

    /**
     * byte数组转换成字符串
     *
     * @param bytes
     * @return
     */
    private String encodeBytes(byte[] bytes) {
        StringBuilder hex = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString().toUpperCase();
    }

    private PrivateKey getPrivateKey() {
        try {
            byte[] keyBytes = Base64Utils.decode(ALIPAY_MCH_KEY);
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(SIGN_TYPE_RSA);
            return keyFactory.generatePrivate(pkcs8KeySpec);
        } catch (Exception e) {
            String errorMeg = "Get Private Key ERROR";
            logger.error(errorMeg);
        }
        return null;
    }




}
