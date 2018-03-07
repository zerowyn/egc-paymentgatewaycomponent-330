/**
 * @Probject Name: scp-pay-gateway-app-service
 * @Path: com.eg.egsc.scp.paygateway.service.implNotifyServiceImpl.java
 * @Create By fandong
 * @Create In 2018年2月11日 TODO
 */
package com.eg.egsc.scp.paygateway.service.impl;


import com.eg.egsc.egc.paymentbackendsystem.client.PaymentResultInformClient;
import com.eg.egsc.egc.paymentbackendsystem.dto.ResultInformDto;
import com.eg.egsc.egc.paymentbackendsystem.dto.ResultInformResponseDto;
import com.eg.egsc.scp.paygateway.exception.PaymentGatewayException;
import com.eg.egsc.scp.paygateway.service.NotifyService;
import com.eg.egsc.scp.paygateway.service.SignatureService;
import com.eg.egsc.scp.paygateway.service.model.WeiXinNotifyResponse;
import com.eg.egsc.scp.paygateway.util.ErrorCodeConstant;
import com.eg.egsc.scp.paygateway.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.Map;

/**
 * @Class Name NotifyServiceImpl
 * @Author fandong
 * @Create In 2018年2月11日
 */
@Service
public class NotifyServiceImpl implements NotifyService {

  private final static Logger logger = LoggerFactory.getLogger(NotifyServiceImpl.class);

  @Autowired
  private PaymentResultInformClient paymentResultInformClientImpl;

  @Autowired
  private SignatureService signatureServiceImpl;

  @Override
  public String transferWeChatMessageForWeiXinString(String requestData) {

    // 将request xml字符串转为map
    Map<String, Object> map = StringUtils.transferXMLtoMap(requestData, "");
      System.out.println("========================"+map);
    // 验签
    boolean flag;
    flag = signatureServiceImpl.weixinSignatureCheck(map);
    // 验签成功后转换字段
    ResultInformDto resultInformDto = null;
    if (flag) {
      String json = com.alibaba.fastjson.JSONObject.toJSONString(map);
      json.replaceAll("\\{", "[");
      json.replaceAll("}", "]");
      resultInformDto =
          com.alibaba.fastjson.JSONObject.parseObject(json,
                  ResultInformDto.class);
    }

    // 调用后台接口回传数据
    ResultInformResponseDto notify = paymentResultInformClientImpl.getNotify(resultInformDto);

    if (org.springframework.util.ObjectUtils.isEmpty(notify)){
      logger.error("The received message is empty.");
    }
    String xmlString;
    WeiXinNotifyResponse weiXinNotifyResponse = new WeiXinNotifyResponse();
    if(notify.getReturn_code().equalsIgnoreCase("SUCCESS")){
      weiXinNotifyResponse.setReturn_code("SUCCESS");
      weiXinNotifyResponse.setReturn_msg("OK");
    }else{
      weiXinNotifyResponse.setReturn_code("FAIL");
      weiXinNotifyResponse.setReturn_msg("签名失败");
    }
    // 组装返回第三方支付平台的数据
    xmlString = jaxbRequestObjectToXMLForWeiXin(weiXinNotifyResponse);

    return xmlString;
  }

  public static String jaxbRequestObjectToXMLForWeiXin(WeiXinNotifyResponse weiXinNotifyResponse) {
    String xmlString = "";
    try {
      JAXBContext context = JAXBContext.newInstance(WeiXinNotifyResponse.class);
      Marshaller m = context.createMarshaller();

      m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

      StringWriter sw = new StringWriter();
      m.marshal(weiXinNotifyResponse, sw);
      xmlString = sw.toString();

    } catch (JAXBException e) {
      e.printStackTrace();
      throw new PaymentGatewayException(ErrorCodeConstant.XML_ABNORMAL_CONVERSION);
    }

    return xmlString;

  }

  @Override
  public String transferAlipaytMessageForAlipayString(Map<String,Object> map) {

      // 验签
      boolean flag;
      flag = signatureServiceImpl.alipaySignatureAsyCheck(map);
      // 验签成功后转换字段
      ResultInformDto resultInformDto = null;
      if (flag) {
          String json = com.alibaba.fastjson.JSONObject.toJSONString(map);
          json.replaceAll("\\{", "[");
          json.replaceAll("}", "]");
          resultInformDto =
                  com.alibaba.fastjson.JSONObject.parseObject(json,
                          ResultInformDto.class);
      }

      // 调用后台接口回传数据
      ResultInformResponseDto notify = paymentResultInformClientImpl.getNotify(resultInformDto);

      if (org.springframework.util.ObjectUtils.isEmpty(notify)){
          logger.error("The received message is empty.");
      }
      String xmlString;
      WeiXinNotifyResponse weiXinNotifyResponse = new WeiXinNotifyResponse();
      if(notify.getReturn_code().equalsIgnoreCase("SUCCESS")){
          return "SUCCESS";
      }
      // 组装返回第三方支付平台的数据


      return null;
  }


}
