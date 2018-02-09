/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.util;

import java.util.UUID;

/**
 * @author gucunyang
 * @since 2018-02-08
 */
public class StringUtils {

    /**
     * 生成随机uuid
     *
     * @return 返回生成的uuid
     */
    public static String generateUuid() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    /**
     * 生成随机字符串
     *
     * @return
     */
    public static String generateRandomString() {
        return generateUuid();
    }

    /**
     * 判断字符串是否为空
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(String value) {
        int strLen;
        if (value != null && (strLen = value.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(value.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

}
