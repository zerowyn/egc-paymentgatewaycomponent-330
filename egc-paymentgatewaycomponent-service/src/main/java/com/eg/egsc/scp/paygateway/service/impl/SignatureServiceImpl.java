/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.service.impl;

import com.eg.egsc.scp.paygateway.service.SignatureService;

import java.security.MessageDigest;
import java.util.*;

/**
 * @author gucunyang
 * @since 2018-02-08
 */
public class SignatureServiceImpl implements SignatureService {

    // 微信商户key，后期应从配置文件读取 TODO
    private static final String WEIXIN_MCH_KEY = "D8892E20D8F95CB366C6783602952CEE";

    @Override
    public String WeixinSignature(Map requestMap) {
        String sign = null;
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList(requestMap.keySet());

        Collections.sort(keys);

        int index = 0;
        for(int i = 0; i < keys.size(); i++) {
            String key = (String)keys.get(i);
            String value = (String)requestMap.get(key);
            content.append((index == 0 ? "" : "&") + key + "=" + value);
            ++index;
        }

        content.append("&key=" + WEIXIN_MCH_KEY);

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(content.toString().getBytes("utf-8"));
            StringBuilder hex = new StringBuilder(bytes.length * 2);
            for (byte b : bytes) {
                if ((b & 0xFF) < 0x10) {
                    hex.append("0");
                }
                hex.append(Integer.toHexString(b & 0xFF));
            }
            sign = hex.toString().toUpperCase();

        } catch (Exception e) {
            // 应建立异常类，输出错误日志 TODO
        }
        return sign;
    }


}
