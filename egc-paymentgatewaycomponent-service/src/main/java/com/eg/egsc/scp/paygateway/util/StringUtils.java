/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @author gucunyang
 * @since 2018-02-08
 */
@Component
public class StringUtils {
  
  protected final static Logger logger = LoggerFactory.getLogger(StringUtils.class);
  
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
    public static Map<String,Object> transferXMLtoMap(String xmlString,String xmlCustomizedHeader){
      
      Map<String,Object> newMap = new HashMap<String,Object>();
      //<trade_state><![CDATA[SUCCESS]]></trade_state>
      xmlString = xmlString.replaceAll("<\\!\\[CDATA\\[", "");
      xmlString = xmlString.replaceAll("\\]\\]>", "");
      xmlString = xmlString.replaceAll("</xml>", "");
      xmlString = xmlString.replaceAll("<xml>", "");    
      logger.debug("=====>xmlString: "+xmlString);   
     
      Pattern p = Pattern.compile("<([^</]+)>([^</]*)</([^</]+)>");
      Matcher m = p.matcher(xmlString);
     
      while(m.find()){        
        logger.debug("===>group(0): "+m.group(0));  //将匹配出的电话号码存放到mobileList中
        logger.debug("===>group(1): "+m.group(1));
        logger.debug("===>group(2): "+m.group(2));
        logger.debug("===>group(3): "+m.group(3));
        logger.debug("===========================>");
        newMap.put(m.group(1), m.group(2));      
       }
      
      logger.debug("====newMap: "+newMap);
      
      return newMap;
      
    }
    
    

}
