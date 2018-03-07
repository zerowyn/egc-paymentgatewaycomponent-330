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
public class StringUtilsTest {

    private static final Logger logger = LoggerFactory.getLogger(StringUtilsTest.class);

    /**
     * StringUtilsConstructorTest
     */
    @Test
    public void stringUtilsConstructorTest() {
        try {
            Class<?> classType = StringUtils.class;
            Constructor<?> stringUtilsConstructor = classType.getDeclaredConstructor();
            stringUtilsConstructor.setAccessible(true);
            StringUtils stringUtils = (StringUtils) stringUtilsConstructor.newInstance();
            logger.info(stringUtils.toString());
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
}