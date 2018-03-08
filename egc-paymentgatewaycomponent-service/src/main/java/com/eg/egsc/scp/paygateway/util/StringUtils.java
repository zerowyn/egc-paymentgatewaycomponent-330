/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * 字符串工具类
 *
 * @author gucunyang
 * @since 2018-02-08
 */
@Component
public class StringUtils {

    protected static final Logger logger = LoggerFactory.getLogger(StringUtils.class);

    private StringUtils() {
    }

    /**
     * 生成随机uuid
     *
     * @return 返回生成的uuid
     */
    public static String generateUuid() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    /**
     * 生成随机字符串
     *
     * @return
     */
    public static String generateRandomString() {
        return generateUuid();
    }

    /**
     * 判断字符串是否为空
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(String value) {
        int strLen;
        if (value != null && (strLen = value.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(value.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }


    /*
     *===>group(0): <nonce_str>LphINq5fQrdpPNnm</nonce_str>
      ===>group(1): nonce_str
      ===>group(2): LphINq5fQrdpPNnm
      ===>group(3): nonce_str
     */
    public static Map<String, Object> transferXMLtoMap(String xmlString) {

        Map<String, Object> newMap = new HashMap<>();       
        xmlString = xmlString.replaceAll("<\\!\\[CDATA\\[", "");
        xmlString = xmlString.replaceAll("\\]\\]>", "");
        xmlString = xmlString.replaceAll("</xml>", "");
        xmlString = xmlString.replaceAll("<xml>", "");
        logger.debug("=====>xmlString: " + xmlString);

        Pattern p = Pattern.compile("<([^</]+)>([^</]*)</([^</]+)>");
        Matcher m = p.matcher(xmlString);

        while (m.find()) {
            newMap.put(m.group(1), m.group(2));
        }

        logger.debug("====newMap: " + newMap);

        return newMap;

    }
    
    public static String getJson2Xml(JSONObject json){
      Iterator<String> it = json.keys();
      StringBuilder sb = new StringBuilder();
      String key = "";
      String value = "";
      sb.append("<xml>");
      while(it.hasNext()){
          key = it.next();
          value = json.optString(key);
          if(!"null".equalsIgnoreCase(value)) {
              try{
                  JSONObject jsonSon = JSONObject.fromObject(value);
                  sb.append("<").append(key).append(">");
                  sb.append(getJson2Xml(jsonSon));
                  sb.append(sb.append("</").append(key).append(">"));
              }catch(Exception e){
                  sb.append("<").append(key).append(">").append(value).append("</").append(key).append(">");
              }
          }
      }
      sb.append("</xml>");
      return sb.toString();
  }


}
