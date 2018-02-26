/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.util;

import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;

/**
 * @author gucunyang
 * @since 2018-02-24
 */
public class CollectionUtil {

    public static <T> boolean isEmpty(Collection<T> c) {
        return CollectionUtils.isEmpty(c);
    }
}

