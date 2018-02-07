/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.exception;

import com.eg.egsc.common.exception.CommonException;

/**
 * 异常信息处理类
 * @author wanghongben
 * @since 2018年1月11日
 */
public class DemoException extends CommonException {

  private static final long serialVersionUID = 1620521772541012990L;

 
  /**
   * @param code 异常码
   * @param message 异常信息
   * @param values 参数
   * @param cause  异常
   */
  public DemoException(String code, String message, Object[] values, Throwable cause) {
    super(code, message, values, cause);
  }

  /**
   * @param code 异常码
   * @param message 异常信息
   * @param values   参数
   */
  public DemoException(String code, String message, Object[] values) {
    super(code, message, values);
  }

  /**
   * @param code 异常码
   * @param message 异常信息
   */
  public DemoException(String code, String message) {
    super(code, message);
  }

  /**
   * @param code 异常码
   */
  public DemoException(String code) {
    super(code);
  }
}
