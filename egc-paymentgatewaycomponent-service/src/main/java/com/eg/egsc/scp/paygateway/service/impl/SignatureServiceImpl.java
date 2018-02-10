/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.service.impl;

import com.eg.egsc.scp.paygateway.service.SignatureService;
import com.eg.egsc.scp.paygateway.util.Base64Utils;
import com.eg.egsc.scp.paygateway.util.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
@Service
public class SignatureServiceImpl implements SignatureService {

    protected final Logger logger = LoggerFactory.getLogger(SignatureServiceImpl.class);

    // 微信商户key，后期应从配置文件读取 TODO
    private static final String WEIXIN_MCH_KEY = "D8892E20D8F95CB366C6783602952CEE";

    // 支付宝应用私钥
    private static final String ALIPAY_MCH_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCkoppNC/ab1OfRobJfpWbD7LzhjE6bUQODsu9PJhTI8N9aa4KKY8l9wIqpoxLJCvIfljy3nBRFHEbeK2eA0T+Nfd1xP4X2Pei/aaBO9pd2TOXJfPkCwkXqEic4ww4oma4IZF7sBXZKdszdBo6TRuIQMtVBPv0T861rX/UpVfycrRJEOfNviH9+F9mCRsSY1+6a359lxKxGLyJgCqSpP5hbw03oyeLcrW/4ZbbChHetkGGJnCNC7ngk95gAZcFfsw8DTVXfTBX7k1vjviFZ9NJDjrdppNN1fzXS4v5eeqdBMnhh6tQohSywwId74E2NZj6SZOF0NJrLeNvjzB/SZMahAgMBAAECggEAdrJM8ROY/wzm/frIcbD8gHFkVHEmE1C7ae5OHxBjl+QiBDzS5xe+o41364oI9y2PFzroF7DN5G3YokFE5Fj4qvh9+TJFUR2derOawpOC6+XRHg2eMmECqxnfcUsgICF8mcxTq3LcsB8q4Ifjr0dBoVAk9F+HcyUSeQG77ctyzznDPBQjD0kog3ztTW5qZgAUO5m+jin9DQVz3qKzf8cYxk6OVBVTNsokUIuc+g3Ym8skZEOoBE4aRHch7SkiU/aNII3Lz0O0FFW22kNoAt4v/sA1HNi9nTjzjT5HfTg+j/+c/rRoCsSTMR2ir/J8YG3Rj5raxvsBexkDwGNv1mEOyQKBgQD4MT+V+lx27Ql1HKzXHL8FPXtDshNYfvoliBcvBMp0FYEcxIFBaqG2poRj3SEUa7n5S0louW4MNY5DQ/kDTex4XBkcrt+NSMihhiYQHoqUF9dD389W4W73MXf6ZxyXziTZ8/1elzVejNDmruPV5cukbIUN3EZ8b56KyYeVzPcnrwKBgQCp0HLPx8jJrpOSqiEpX3oWXk1oIZwi5wTIzweoNhnKmV/IFV1MdVJeVlWK0oSxpYemekoJZAe6NhN8exbDATPoIjsPFev6vzNBmUb1YdPVtPQDrEK4vWvb0PIPcb1U2kENR51jXh6F/wCnC2UkJc87iAN2yMIcdkHksR6qin06rwKBgGXZX+yd9v/eufjTMaJ8626tEj5vfzzbrq9kL49d+e61PGyfvyMnLQGVR9LrVb3Zj1HcMV3GaoCcIas8450VhyrFMJDyuk5yRdLzB/+paNjpB6+U0rMmg7DxHfkmAzfQopLGa307s+z/AGMJ9fk6+dyZo+hSMqKx82+kz/0LRYmFAoGAZFQDgT9IIeBC0CQz8321ZAHTOfKhjP1wljO7EmbkVg7HyC7XMJUgVRws7hfyzgI+Yt1dYK744cuRN7qBeDT9teiDfCY3ha3xWpB0nAVwYpLGmGTuBrfjQbvxR66rbbaFOJNXKZ4x6nj+qbLRzsmH5mQ1p7h09tvnknYS6C9qTYECgYEArovKgqWqj19jJsxMpIxDfHaFFcvqGOVFc5lCfHXb7cTH4ivCjRLc0WR1/U1bCMDLv80gA1mu5zG5cwpac/7PuNj6sTfh3CT/Sff4Ed5v753tMS051CMrVLMa0xokplmKKPQdT/gZOxUu1o/P8x8MEVVImR0VmPTf9Y+uA9uOKjg=";

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
