/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.service.impl;

import com.eg.egsc.scp.paygateway.service.ConfigsService;
import com.eg.egsc.scp.paygateway.service.DefValSettingsService;
import com.eg.egsc.scp.paygateway.service.SignatureService;
import com.eg.egsc.scp.paygateway.util.Base64Utils;
import com.eg.egsc.scp.paygateway.util.StreamUtil;
import com.eg.egsc.scp.paygateway.util.StringUtils;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.eg.egsc.scp.paygateway.util.PaymentBusinessConstant.*;

/**
 * 签名和验签算法
 *
 * @author gucunyang
 * @since 2018-02-08
 */
@Service
public class SignatureServiceImpl implements SignatureService {

    private static final Logger logger = LoggerFactory.getLogger(SignatureServiceImpl.class);

    @Autowired
    ConfigsService configsServiceImpl;

    @Autowired
    DefValSettingsService defValSettingsServiceImpl;

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
        String weixinKey = configsServiceImpl.getConfigsValueByExample(KEY, WEI_XIN);
        String charset = (String) requestParamsMap.get(CHARSET);
        if (StringUtils.isEmpty(charset)) {
            charset = getCharset(WEI_XIN);
        }
        StringBuffer content = getSignContent(requestParamsMap);
        content.append("&key=" + weixinKey);
        String signType = (String) requestParamsMap.get(SIGN_TYPE);
        if (StringUtils.isEmpty(signType)) {
            signType = getSignType(WEI_XIN);
        }
        if (SIGN_TYPE_MD5.toUpperCase().equals(signType.toUpperCase())) {
            sign = md5Sign(content, charset);
        } else if (SIGN_TYPE_HMAC.toUpperCase().equals(signType.toUpperCase())) {
            sign = hmacSHA256Sign(content, weixinKey, charset);
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
    public boolean weixinSignatureCheck(Map responseParamsMap) {
        if (responseParamsMap == null) {
            String errorMeg = "responseParamsMap from paymentgateway is null!";
            logger.error(errorMeg);
            return false;
        }

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
        System.out.println(content);
        String signType = (String) requestParamsMap.get(SIGN_TYPE);
        if (StringUtils.isEmpty(signType)) {
            String errorMeg = "sign_type is null";
            logger.error(errorMeg);
            return sign;
        }
        String charset = (String) requestParamsMap.get(CHARSET);
        if (StringUtils.isEmpty(charset)) {
            charset = getCharset(ALI_PAY);
        }
        if (SIGN_TYPE_RSA2.equals(signType)) {
            sign = rsa256Sign(content, charset);
        } else if (SIGN_TYPE_RSA.equals(signType)) {
            sign = rsaSign(content, charset);
        } else {
            String errorMeg = "Unsupported Signature Type: signType = " + signType;
            logger.error(errorMeg);
        }

        return sign;
    }

    /**
     * 接收异步通知数据，验证支付宝签名是否正确
     *
     * @param responseParamsMap
     * @return
     */
    @Override
    public boolean alipaySignatureAsyCheck(Map responseParamsMap) {
        if (responseParamsMap == null) {
            String errorMeg = "responseParamsMap from paymentgateway is null!";
            logger.error(errorMeg);
            return false;
        }

        String sign = (String) responseParamsMap.get(SIGN);
        if (StringUtils.isEmpty(sign)) {
            String errorMeg = "sign is empty";
            logger.error(errorMeg);
            return false;
        }
        responseParamsMap.remove(SIGN);
        String signType = (String) responseParamsMap.get(SIGN_TYPE);
        if (StringUtils.isEmpty(signType)) {
            signType = getSignType(ALI_PAY);
        }
        responseParamsMap.remove(SIGN_TYPE);

        String content = getSignContent(responseParamsMap).toString();
        return getCheckResult(content, sign, signType);

    }

    /**
     * 接收同步返回数据，验证支付宝签名是否正确
     *
     * @param content
     * @param sign
     * @return
     */
    @Override
    public boolean alipaySignatureSynCheck(String content, String sign) {
        String signType = getSignType(ALI_PAY);
        return getCheckResult(content, sign, signType);
    }

    /**
     * MD5签名
     *
     * @param content
     * @return
     */
    private String md5Sign(StringBuffer content, String charset) {
        String sign = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance(SIGN_TYPE_MD5);
            byte[] bytes = md5.digest(content.toString().getBytes(charset));
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
    private String hmacSHA256Sign(StringBuffer content, String weixinKey, String charset) {
        String sign = null;
        try {
            Mac hmacSHA256 = Mac.getInstance(SIGN_TYPE_HMAC);
            SecretKeySpec secretKey = new SecretKeySpec(weixinKey.getBytes(charset), SIGN_TYPE_HMAC);
            hmacSHA256.init(secretKey);
            byte[] bytes = hmacSHA256.doFinal(content.toString().getBytes(charset));
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
    public String rsa256Sign(StringBuffer content, String charset) {
        String sign = null;
        try {
            PrivateKey privateK = getPrivateKey();
            Signature signature = Signature.getInstance(RSA2_INSTANCE_NAME);
            if (privateK == null) {
                return sign;
            }
            signature.initSign(privateK);
            signature.update(content.toString().getBytes(charset));
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
    private String rsaSign(StringBuffer content, String charset) {
        String sign = null;
        try {
            Signature signature = Signature.getInstance(RSA_INSTANCE_NAME);
            PrivateKey privateK = getPrivateKey();
            if (privateK == null) {
                return sign;
            }
            signature.initSign(privateK);
            signature.update(content.toString().getBytes(charset));
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
            String key = keys.get(i);
            String value = requestParamsMap.get(key).toString();
            if (!StringUtils.isEmpty(value)) {
                content.append((index == 0 ? "" : "&") + key + "=" + value);
            }
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

    /**
     * 获取privateKey
     *
     * @return
     */
    private PrivateKey getPrivateKey() {
        try {
            String alipayAppPrivateKey = configsServiceImpl.getConfigsValueByExample(KEY, "ALIPAY-APP-PRIVATE");
            byte[] keyBytes = Base64Utils.decode(alipayAppPrivateKey);
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(SIGN_TYPE_RSA);
            return keyFactory.generatePrivate(pkcs8KeySpec);
        } catch (Exception e) {
            String errorMeg = "Get Private Key ERROR";
            logger.error(errorMeg);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取字符集
     *
     * @param platform
     * @return
     */
    private String getCharset(String platform) {
        String charset = defValSettingsServiceImpl.getDefValSettingsValueByExample(platform, CHARSET);
        if (StringUtils.isEmpty(charset)) {
            charset = ALI_PAY.equals(platform) ? CHARSET_GBK : CHARSET_UTF8;
        }
        return charset;
    }

    /**
     * 验证签名
     *
     * @param content
     * @param sign
     * @param alipayPublicKey
     * @param charset
     * @param rsaInstanceName
     * @return
     */
    private boolean checkSign(String content, String sign, String alipayPublicKey,
                              String charset, String rsaInstanceName) {
        try {
            PublicKey pubKey = getPublicKeyFromX509(SIGN_TYPE_RSA,
                    new ByteArrayInputStream(alipayPublicKey.getBytes()));
            Signature signature = Signature.getInstance(rsaInstanceName);
            signature.initVerify(pubKey);
            signature.update(content.toString().getBytes(charset));
            return signature.verify(Base64.decodeBase64(sign.getBytes()));
        } catch (Exception e) {
            String errorMeg = "Signature Check ERROR";
            logger.error(errorMeg);
        }
        return false;
    }

    /**
     * 获取签名结果
     *
     * @param content
     * @param sign
     * @param signType
     * @return
     */
    private boolean getCheckResult(String content, String sign, String signType) {
        String alipayPublicKey = configsServiceImpl.getConfigsValueByExample(KEY, "ALIPAY-PUBLIC");
        String charset = getCharset(ALI_PAY);
        if (SIGN_TYPE_RSA2.equals(signType)) {
            return checkSign(content, sign, alipayPublicKey, charset, RSA2_INSTANCE_NAME);
        } else if (SIGN_TYPE_RSA.equals(signType)) {
            return checkSign(content, sign, alipayPublicKey, charset, RSA_INSTANCE_NAME);
        } else {
            String errorMeg = "Unsupported Signature Type: signType = " + signType;
            logger.error(errorMeg);
            return false;
        }
    }

    /**
     * getSignTyoe
     *
     * @param platform
     * @return
     */
    private String getSignType(String platform) {
        String signType = defValSettingsServiceImpl.getDefValSettingsValueByExample(platform, SIGN_TYPE);
        if (StringUtils.isEmpty(signType)) {
            signType = ALI_PAY.equals(platform) ? SIGN_TYPE_RSA2 : SIGN_TYPE_MD5;
        }
        return signType;
    }

    /**
     * getPublicKeyFromX509
     *
     * @param algorithm
     * @param ins
     * @return
     * @throws Exception
     */
    private PublicKey getPublicKeyFromX509(String algorithm, InputStream ins) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        StringWriter writer = new StringWriter();
        StreamUtil.io(new InputStreamReader(ins), writer);
        byte[] encodedKey = writer.toString().getBytes();
        encodedKey = Base64.decodeBase64(encodedKey);
        return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
    }

}
