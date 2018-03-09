package com.eg.egsc.scp.paygateway.util;

/**
 * 将带下划线的String转换成驼峰式命名
 *
 * @author fandong
 * @since 2018年3月8日
 */
public class ConversionUtils {

  private ConversionUtils() {}

  /**
   * 
   * 去掉参数中的下划线改成驼峰形式
   * 
   * @param obj
   * @return String
   */
  public static String conversion(String str) {
    StringBuilder builder = new StringBuilder();
    String[] split = str.split("_");
    int count = 1;
    for (String string : split) {
      if (count == 1 || string.charAt(0) == '$' || string.charAt(0) >= 'A' && string.charAt(0) <= 'Z') {
        builder.append(string);
        count++;
      } else {
        builder.append(string.substring(0, 1).toUpperCase() + string.substring(1, string.length() - 1));
      }

    }
    return builder.toString();
  }
}
