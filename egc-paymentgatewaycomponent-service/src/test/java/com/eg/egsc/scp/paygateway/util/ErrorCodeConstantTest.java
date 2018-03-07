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
public class ErrorCodeConstantTest {

    private static final Logger logger = LoggerFactory.getLogger(ErrorCodeConstantTest.class);

    /**
     * ErrorCodeConstantConstructorTest
     */
    @Test
    public void errorCodeConstantConstructorTest() {
        try {
            Class<?> classType = ErrorCodeConstant.class;
            Constructor<?> errorCodeConstantConstructor = classType.getDeclaredConstructor();
            errorCodeConstantConstructor.setAccessible(true);
            ErrorCodeConstant errorCodeConstant = (ErrorCodeConstant) errorCodeConstantConstructor.newInstance();
            logger.info(errorCodeConstant.toString());
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
}