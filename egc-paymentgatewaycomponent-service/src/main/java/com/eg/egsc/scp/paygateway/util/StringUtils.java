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
     * 随机生成uuid
     *
     * @return 返回生成的uuid
     */
    public static String generateUuid() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }


}
