/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.mail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.eg.egsc.common.mail.EmailService;
import com.eg.egsc.config.AbstractUnitTestSupport;

/**
 * 测试邮件发送类
 * 
 * @author wanghongben
 * @since 2018年1月11日
 */

public class TestMail extends AbstractUnitTestSupport {

  @Autowired
  private EmailService emailServiceImpl;

  /**
   * 测试邮件发送方法
   */
  @Test
  public void testSendMain() {

    String content = "Test content";
    String subject = "Test Subject";
    String receiver = "gaoyanlong@hdsc.com";
    emailServiceImpl.sendEmail(receiver, subject, content);
  }
}
