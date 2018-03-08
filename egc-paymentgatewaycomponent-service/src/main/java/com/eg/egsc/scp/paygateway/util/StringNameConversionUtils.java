package com.eg.egsc.scp.paygateway.util;

/**
 * 
 * 
 * 
 * @author zhangchenglong
 * @since 2018年3月8日
 */
public class StringNameConversionUtils {

  public static String StringNameConversion(String source) {
    String[] split = source.split("_");
    StringBuffer sb = new StringBuffer();
    Integer count = 1;
    for (String str : split) {
      if (count == 1) {
        sb.append(str);
        count++;
        continue;
      }
      if ('$' == str.charAt(0)) {
        sb.append(str);
      } else {
        String newStr = str.substring(0, 1).toUpperCase() + str.substring(1);
        sb.append(newStr);
      }
    }

    return sb.toString();
  }
}
