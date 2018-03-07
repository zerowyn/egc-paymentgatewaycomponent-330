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
public class StreamUtilTest {

    private static final Logger logger = LoggerFactory.getLogger(StreamUtilTest.class);

    /**
     * StreamUtilConstructorTest
     */
    @Test
    public void streamUtilConstructorTest() {
        try {
            Class<?> classType = StreamUtil.class;
            Constructor<?> streamUtilConstructor = classType.getDeclaredConstructor();
            streamUtilConstructor.setAccessible(true);
            StreamUtil streamUtil = (StreamUtil) streamUtilConstructor.newInstance();
            logger.info(streamUtil.toString());
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
}