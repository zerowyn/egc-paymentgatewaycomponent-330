/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.util;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;

/**
 * @author gucunyang
 * @since 2018-03-07
 */
public class CollectionUtilTest {
    private static final Logger logger = LoggerFactory.getLogger(CollectionUtilTest.class);

    /**
     * CollectionUtilConstructorTest
     */
    @Test
    public void collectionUtilConstructorTest() {
        try {
            Class<?> classType = CollectionUtil.class;
            Constructor<?> collectionUtilConstructor = classType.getDeclaredConstructor();
            collectionUtilConstructor.setAccessible(true);
            CollectionUtil collectionUtil = (CollectionUtil) collectionUtilConstructor.newInstance();
            logger.info(collectionUtil.toString());
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
}