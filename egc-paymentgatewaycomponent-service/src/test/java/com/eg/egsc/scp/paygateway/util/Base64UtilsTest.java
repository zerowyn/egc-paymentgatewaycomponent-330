/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Constructor;

/**
 * @author gucunyang
 * @since 2018-03-07
 */
@RunWith(SpringRunner.class)
public class Base64UtilsTest {

    private static final Logger logger = LoggerFactory.getLogger(Base64UtilsTest.class);

    /**
     * Base64UtilsConstructorTest
     */
    @Test
    public void base64UtilsConstructorTest() {
        try {
            Class<?> classType = Base64Utils.class;
            Constructor<?> base64UtilsConstructor = classType.getDeclaredConstructor();
            base64UtilsConstructor.setAccessible(true);
            Base64Utils base64Utils = (Base64Utils) base64UtilsConstructor.newInstance();
            logger.info(base64Utils.toString());
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
}