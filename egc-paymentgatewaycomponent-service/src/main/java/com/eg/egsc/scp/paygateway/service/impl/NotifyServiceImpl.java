/**
 * @Probject Name: scp-pay-gateway-app-service
 * @Path: com.eg.egsc.scp.paygateway.service.implNotifyServiceImpl.java
 * @Create By fandong
 * @Create In 2018年2月11日 TODO
 */
package com.eg.egsc.scp.paygateway.service.impl;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eg.egsc.scp.paygateway.dto.PayResultsNotifyResponseForBackendDto;
import com.eg.egsc.scp.paygateway.service.NotifyService;
import com.eg.egsc.scp.paygateway.service.SignatureService;
import com.eg.egsc.scp.paygateway.util.StringUtils;

/**
 * @Class Name NotifyServiceImpl
 * @Author fandong
 * @Create In 2018年2月11日
 */
@Service
public class NotifyServiceImpl implements NotifyService {

  private final static Logger logger = LoggerFactory.getLogger(NotifyServiceImpl.class);

  @Autowired
  private SignatureService signatureServiceImpl;

  @Override
  public void transferWeChatMessageForWeiXinString(String requestData) {

    // 将request xml字符串转为map
    Map<String, Object> map = StringUtils.transferXMLtoMap(requestData, "");
    // 验签
    boolean flag;
    flag = signatureServiceImpl.weixinSignatureCheck(map);
    // 验签成功后转换字段
    if (flag) {
      String json = com.alibaba.fastjson.JSONObject.toJSONString(map);
      json.replaceAll("\\{", "[");
      json.replaceAll("}", "]");
      PayResultsNotifyResponseForBackendDto payResultsNotifyResponseForBackendDto =
          com.alibaba.fastjson.JSONObject.parseObject(json,
              PayResultsNotifyResponseForBackendDto.class);

    }

    // 调用后台接口回传数据

    // 组装返回第三方支付平台的数据

  }

  @Override
  public void transferAlipaytMessageForAlipayString(String requestData) {

  }


}
