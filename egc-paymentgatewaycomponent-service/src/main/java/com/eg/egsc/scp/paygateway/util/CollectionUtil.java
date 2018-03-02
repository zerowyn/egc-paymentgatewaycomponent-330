/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.util;

import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;

/**
 * 集合工具类
 *
 * @author gucunyang
 * @since 2018-02-24
 */
public class CollectionUtil {


    /**
     * 判断集合是否为空
     *
     * @param c
     * @param <T>
     * @return
     */
    public static <T> boolean isEmpty(Collection<T> c) {
        return CollectionUtils.isEmpty(c);
    }
}

