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
public class PaymentBusinessConstantTest {

    private static final Logger logger = LoggerFactory.getLogger(PaymentBusinessConstantTest.class);

    /**
     * PaymentBusinessConstantConstructorTest
     */
    @Test
    public void paymentBusinessConstantConstructorTest() {
        try {
            Class<?> classType = PaymentBusinessConstant.class;
            Constructor<?> paymentBusinessConstantConstructor = classType.getDeclaredConstructor();
            paymentBusinessConstantConstructor.setAccessible(true);
            PaymentBusinessConstant paymentBusinessConstant = (PaymentBusinessConstant) paymentBusinessConstantConstructor.newInstance();
            logger.info(paymentBusinessConstant.toString());
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
}