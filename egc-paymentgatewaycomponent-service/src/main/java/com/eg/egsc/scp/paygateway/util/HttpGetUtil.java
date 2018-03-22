/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.util;

import com.eg.egsc.scp.paygateway.exception.PaymentGatewayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author lihui
 * @since 2018-03-22
 */
public class HttpGetUtil {
    private HttpGetUtil(){}

    private static final Logger logger = LoggerFactory.getLogger(HttpGetUtil.class);
    public static String httpRequestToString(String url,Map<String, String> params) {
        String result;
        try {
            InputStream is = httpRequestToStream(url, params);
            BufferedReader in = new BufferedReader(new InputStreamReader(is,PaymentBusinessConstant.CHARSET_UTF8));
            StringBuilder buffer = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
            result = buffer.toString();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
        return result;
    }

    private static InputStream httpRequestToStream(String url,Map<String, String> params) {
        InputStream is;
        try {
            StringBuilder parameters = new StringBuilder();
            boolean hasParams = false;
            for (Map.Entry<String,String> entry : params.entrySet()) {
                String value = URLEncoder.encode(entry.getValue(), PaymentBusinessConstant.CHARSET_UTF8);
                String key = entry.getKey();
                parameters.append(key).append("=").append(value).append("&");
                hasParams = true;
            }
            if (hasParams) {
                parameters.substring(0, parameters.length() - 1);
            }
            url += "?" + parameters;
            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Accept-Charset", PaymentBusinessConstant.CHARSET_UTF8);
            conn.setRequestProperty("contentType", PaymentBusinessConstant.CHARSET_UTF8);
            conn.setConnectTimeout(50000);
            conn.setReadTimeout(50000);
            conn.setDoInput(true);
            //设置请求方式，默认为GET
            conn.setRequestMethod("GET");
            is = conn.getInputStream();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new PaymentGatewayException(e.getMessage());
        }
        return is;
    }
}
