/**
 * @Probject Name: scp-pay-gateway-app-service
 * @Path: com.eg.egsc.scp.paygateway.service.implOrderQueryServiceImpl.java
 * @Create By caiqinli
 * @Create In 2018年2月6日 上午11:41:04
 * TODO
 */
package com.eg.egsc.scp.paygateway.service.impl;


import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;


import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.eg.egsc.scp.paygateway.dto.OrderQueryRequestForBackendDto;
import com.eg.egsc.scp.paygateway.dto.OrderQueryResponseForBackendDto;
import com.eg.egsc.scp.paygateway.service.OrderQueryService;
import com.eg.egsc.scp.paygateway.service.SignatureService;
import com.eg.egsc.scp.paygateway.service.model.OrderQueryRequestForAliPay;
import com.eg.egsc.scp.paygateway.service.model.OrderQueryRequestForWeiXin;
import com.eg.egsc.scp.paygateway.service.model.OrderQueryResponseForAliPay;
import com.eg.egsc.scp.paygateway.service.model.OrderQueryResponseForWeiXin;
import com.eg.egsc.scp.paygateway.util.PaymentBusinessConstant;
import com.eg.egsc.scp.paygateway.util.StringUtils;
import com.google.gson.Gson;


/**
 * @Class Name OrderQueryServiceImpl
 * @Author caiqinli
 * @Create In 2018年2月6日
 */
@Service
public class OrderQueryServiceImpl implements OrderQueryService {

  protected final Logger logger = LoggerFactory.getLogger(OrderQueryServiceImpl.class);
  
//  @Autowired
//  private OrderQueryRequestForWeiXin orderQueryRequestForWeiXin;
  
//  @Autowired
//  private OrderQueryResponseForBackendDto orderQueryResponseForBackendDto;
  
//  @Autowired
//  private OrderQueryResponseForWeiXin orderQueryResponseForWeiXin;
  
  @Autowired
  SignatureService signatureServiceImpl;
  
  @Value("${payment.third.party.wechat.order.query.uri}")
  private String wechatOrderQueryUri;
  
  @Value("${payment.third.party.alipay.order.query.uri}")
  private String aliPayOrderQueryUri;
  
  @Value("${xml.customized.header}")
  private String xmlCustomizedHeader; 
  
  @Value("${payment.error.message.confirm.sign.not.pass}")
  private String confirmSignNotPassMessage; 
  
  @Value("${payment.error.message.request.platform.out.of.scope}")
  private String requestPlatformOutOfScopeMessage; 
  
  @Value("${payment.error.message.request.data.is.null}")
  private String requestDataIsNullMessage; 
  
  @Value("${payment.third.party.alipay.sign.type}")
  private String aliPaySignType; 
  
  @Value("${payment.third.party.alipay.order.query.uri.method}")
  private String aliPayOrderQueryMethod;
  
  /**
   * 接收缴费后台请求，转换为数据格式
   * @param OrderQueryRequestForBackendSystem 缴费后台提交的请求数据对象
   * @return OrderQueryRequestForWeiXin 返回给微信的请求数据对象
   */
  @Override
  public OrderQueryRequestForWeiXin transferBackendMessageForWeiXin(
      OrderQueryRequestForBackendDto orderQueryRequestForBackendDto){
    
    OrderQueryRequestForWeiXin orderQueryRequestForWeiXin = new OrderQueryRequestForWeiXin();    
        
    orderQueryRequestForWeiXin.setAppid(orderQueryRequestForBackendDto.getAppid());
    orderQueryRequestForWeiXin.setMch_id(orderQueryRequestForBackendDto.getMch_id());
    if(orderQueryRequestForBackendDto.getTransaction_id() == null 
        || "".equals(orderQueryRequestForBackendDto.getTransaction_id().trim())){
      orderQueryRequestForWeiXin.setOut_trade_no(orderQueryRequestForBackendDto.getOut_trade_no());
    }else{
      orderQueryRequestForWeiXin.setTransaction_id(orderQueryRequestForBackendDto.getTransaction_id());
    }    
    orderQueryRequestForWeiXin.setNonce_str(getNonce_str());
    orderQueryRequestForWeiXin.setSign(getSign(orderQueryRequestForWeiXin));
  
    return orderQueryRequestForWeiXin;
    
  }
  
  /**
   * 接收缴费后台请求，转换为数据格式
   * @param OrderQueryRequestForBackendSystem 缴费后台提交的请求数据对象
   * @return OrderQueryRequestForAliPay 返回给支付宝的请求数据对象
   */
  @Override
  public OrderQueryRequestForAliPay transferBackendMessageForAliPay(
      OrderQueryRequestForBackendDto orderQueryRequestForBackendDto){
    
    OrderQueryRequestForAliPay requestForAliPay = new OrderQueryRequestForAliPay();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Timestamp now = new Timestamp(System.currentTimeMillis());
    String timestampForAliPay = df.format(now);
    String biz_content = ""
        + "{\"out_trade_no\":\""+orderQueryRequestForBackendDto.getOut_trade_no()+"\","
            + "\"trade_no\":\""+orderQueryRequestForBackendDto.getTransaction_id()+"\"}";    
    System.out.println("===biz_content: "+biz_content);
    
    requestForAliPay.setApp_id(orderQueryRequestForBackendDto.getAppid());
    requestForAliPay.setMethod(aliPayOrderQueryMethod);    
    requestForAliPay.setCharset("utf-8");
    requestForAliPay.setSign_type(aliPaySignType);
    requestForAliPay.setTimestamp(timestampForAliPay);
    requestForAliPay.setVersion("1.0");
    requestForAliPay.setBiz_content(biz_content);
    requestForAliPay.setSign(getSign(requestForAliPay));    
    
    return requestForAliPay;        
  }
  
  
  /**
   * 接收微信返回数据，转换为数据格式
   * @param OrderQueryResponseForWeiXin 微信接口返回的数据对象
   * @return OrderQueryResponseForBackendSystem 返回给缴费后台的数据对象
   */
  @Override
  public OrderQueryResponseForBackendDto transferWeiXinMessageForBackendSystme(
      OrderQueryResponseForWeiXin orderQueryResponseForWeiXin){
    
    OrderQueryResponseForBackendDto orderQueryResponseForBackendDto = new OrderQueryResponseForBackendDto();
    orderQueryResponseForBackendDto.setPlatform(PaymentBusinessConstant.WEI_XIN);
    orderQueryResponseForBackendDto.setAppid(orderQueryResponseForWeiXin.getAppid());
    orderQueryResponseForBackendDto.setMch_id(orderQueryResponseForWeiXin.getMch_id());
    orderQueryResponseForBackendDto.setTransaction_id(orderQueryResponseForWeiXin.getTransaction_id());
    orderQueryResponseForBackendDto.setOut_trade_no(orderQueryResponseForWeiXin.getOut_trade_no());
    
    orderQueryResponseForBackendDto.setReturn_code(orderQueryResponseForWeiXin.getReturn_code());
    orderQueryResponseForBackendDto.setReturn_msg(orderQueryResponseForWeiXin.getReturn_msg());
    orderQueryResponseForBackendDto.setDevice_info(orderQueryResponseForWeiXin.getDevice_info());
    orderQueryResponseForBackendDto.setErr_code(orderQueryResponseForWeiXin.getErr_code());
    orderQueryResponseForBackendDto.setErr_code_des(orderQueryResponseForWeiXin.getErr_code_des());
    orderQueryResponseForBackendDto.setResult_code(orderQueryResponseForWeiXin.getResult_code());
    
    orderQueryResponseForBackendDto.setAttach(orderQueryResponseForWeiXin.getAttach());
    orderQueryResponseForBackendDto.setBank_type(orderQueryResponseForWeiXin.getBank_type());
    orderQueryResponseForBackendDto.setCash_fee(orderQueryResponseForWeiXin.getCash_fee());
    orderQueryResponseForBackendDto.setCash_fee_type(orderQueryResponseForWeiXin.getCash_fee_type());
    orderQueryResponseForBackendDto.setCoupon_count(orderQueryResponseForWeiXin.getCoupon_count());
    orderQueryResponseForBackendDto.setCoupon_fee(orderQueryResponseForWeiXin.getCoupon_fee());   
    
    orderQueryResponseForBackendDto.setIs_subscribe(orderQueryResponseForWeiXin.getIs_subscribe());
    orderQueryResponseForBackendDto.setOpenid(orderQueryResponseForWeiXin.getOpenid());
    
    orderQueryResponseForBackendDto.setSettlement_total_fee(orderQueryResponseForWeiXin.getSettlement_total_fee());
    orderQueryResponseForBackendDto.setTime_end(orderQueryResponseForWeiXin.getTime_end());
    orderQueryResponseForBackendDto.setTotal_fee(orderQueryResponseForWeiXin.getTotal_fee());
    orderQueryResponseForBackendDto.setFee_type(orderQueryResponseForWeiXin.getFee_type());
    
    orderQueryResponseForBackendDto.setTrade_state(orderQueryResponseForWeiXin.getTrade_state());
    orderQueryResponseForBackendDto.setTrade_state_desc(orderQueryResponseForWeiXin.getTrade_state_desc());
    orderQueryResponseForBackendDto.setTrade_type(orderQueryResponseForWeiXin.getTrade_type());
    
    return orderQueryResponseForBackendDto;        
  }
  
  
  /**
   * 接收微信返回数据，转换为数据格式
   * @param OrderQueryResponseForAliPay 支付宝接口返回的数据对象
   * @return OrderQueryResponseForBackendSystem 返回给缴费后台的数据对象
   */
  @Override
  public OrderQueryResponseForBackendDto transferAliPayMessageForBackendSystme(
      OrderQueryResponseForAliPay orderQueryResponseForAliPay){
    
    OrderQueryResponseForBackendDto responseForBackendSystem = new OrderQueryResponseForBackendDto();
    return responseForBackendSystem;
    
  }
  
  /**
   * 接收微信返回数据，转换为数据格式
   * @param OrderQueryResponseForAliPay 支付宝接口返回的数据对象
   * @return OrderQueryResponseForBackendSystem 返回给缴费后台的数据对象
   */
  @Override
  public OrderQueryResponseForBackendDto orderQueryRequestFromBackendSystme(
      OrderQueryRequestForBackendDto orderQueryRequestForBackendDto){
    
    OrderQueryResponseForBackendDto orderQueryResponseForBackendDto = new OrderQueryResponseForBackendDto();
    
    if(orderQueryRequestForBackendDto == null){
      String errorMeg = "Dto from backend system request for order query is null!";
      logger.error(errorMeg);
      orderQueryResponseForBackendDto.setErr_code_des(requestDataIsNullMessage);
      return orderQueryResponseForBackendDto;
    }
    
    //for wechat
    if(PaymentBusinessConstant.WEI_XIN
        .equalsIgnoreCase(orderQueryRequestForBackendDto.getPlatform())){      
      OrderQueryRequestForWeiXin orderQueryRequestForWeiXin = 
          transferBackendMessageForWeiXin(orderQueryRequestForBackendDto);
      
      String requestXmlString = 
          jaxbRequestObjectToXMLForWeiXin(orderQueryRequestForWeiXin);
      logger.info("requestXmlString = ["+requestXmlString+"]");
    
      try{        
        ResponseEntity<String> responseEntiryFromWeiXin = 
            callThirdPartyOrderQueryApi(
                orderQueryRequestForBackendDto.getPlatform(),requestXmlString);
        
        String responseMessageFromWeiXin = responseEntiryFromWeiXin.getBody();       
        logger.debug("=====responseMessageFromWeiXin====================> "+responseMessageFromWeiXin);
        
        Map<String,Object> responseMap = StringUtils.transferXMLtoMap(responseMessageFromWeiXin,xmlCustomizedHeader);
        logger.debug("=====responseMap====================> "+responseMap);
        
        if(!confirmSignForWeiXin(responseMap)){
          logger.error("Business Exception! The Signature Check failed! "
              + "This responese message stop here and will not pass to payment backend system!");
          orderQueryResponseForBackendDto.setErr_code_des(confirmSignNotPassMessage);
          return orderQueryResponseForBackendDto;          
        }
        
        StringReader sr = new StringReader(responseMessageFromWeiXin);
        JAXBContext jaxbContext = JAXBContext.newInstance(OrderQueryResponseForWeiXin.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        OrderQueryResponseForWeiXin orderQueryResponseForWeiXin = new OrderQueryResponseForWeiXin();
        orderQueryResponseForWeiXin = (OrderQueryResponseForWeiXin) unmarshaller.unmarshal(sr);
        
        orderQueryResponseForBackendDto = 
            transferWeiXinMessageForBackendSystme(orderQueryResponseForWeiXin);        
      }catch(HttpClientErrorException e){
        logger.error("error:  " + e.getResponseBodyAsString());
      }catch(Exception e){
        logger.error("error:  " + e);
      } 
      
    }
    //for alipay
    else if(PaymentBusinessConstant.ALI_PAY
        .equalsIgnoreCase(orderQueryRequestForBackendDto.getPlatform())){
      logger.debug("=====Come into Alipay business logic====================> ");
      OrderQueryRequestForAliPay orderQueryRequestForAliPay = 
          transferBackendMessageForAliPay(orderQueryRequestForBackendDto);
      
      String requestAliPayString = 
          "timestamp=".concat(orderQueryRequestForAliPay.getTimestamp()).concat
          ("&method=").concat(orderQueryRequestForAliPay.getMethod()).concat
          ("&app_id=").concat(orderQueryRequestForAliPay.getApp_id()).concat
          ("&sign_type=").concat(orderQueryRequestForAliPay.getSign_type()).concat
          ("&sign=").concat(orderQueryRequestForAliPay.getSign()).concat
          ("&version=").concat(orderQueryRequestForAliPay.getVersion()).concat
          ("&charset=").concat(orderQueryRequestForAliPay.getCharset());
      
      ResponseEntity<String> responseEntiryFromAliPay = 
          callThirdPartyOrderQueryApi(
              orderQueryRequestForBackendDto.getPlatform(),requestAliPayString,orderQueryRequestForAliPay);
      
      String responseMessageFromAliPay = responseEntiryFromAliPay.getBody();       
      logger.debug("=====responseMessageFromWeiXin====================> "+responseMessageFromAliPay);
      
      
    }else{
      logger.debug("====Request Data Exception! The Platform is not Wechat or Alipay! Nothing will be done here.=====");
      orderQueryResponseForBackendDto.setErr_code_des(requestPlatformOutOfScopeMessage);
    }
   
    return orderQueryResponseForBackendDto;    
  }
  
  
  private ResponseEntity<String> callThirdPartyOrderQueryApi(String platform,String requestString){  
    
    RestTemplate rt = new RestTemplate();
    rt.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));;
    HttpHeaders httpHeaders = new HttpHeaders();
    MediaType mediaType = MediaType.parseMediaType("application/xml; charset=utf-8");
    
    httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_ATOM_XML));
    httpHeaders.setContentType(mediaType);
    
    HttpEntity<String> formEntity = 
        new HttpEntity<String>(requestString.toString(), httpHeaders);
    
    logger.error("====Final platform: "+platform);
    logger.error("====Final requestString: "+requestString);
    
    ResponseEntity<String> responseEntiryFromThirdPartyApi = null;
    
    String thirdPartyUri = "";
    if(PaymentBusinessConstant.WEI_XIN.equalsIgnoreCase(platform)){
      thirdPartyUri = wechatOrderQueryUri;
      responseEntiryFromThirdPartyApi = 
          (ResponseEntity<String>) rt.exchange(thirdPartyUri, HttpMethod.POST,formEntity,String.class);      
    } 
    
    return responseEntiryFromThirdPartyApi;
    
  }
  
  
  private ResponseEntity<String> callThirdPartyOrderQueryApi(String platform, 
      String requestString,OrderQueryRequestForAliPay orderQueryRequestForAliPay){  
    
    RestTemplate rt = new RestTemplate();
    rt.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));;
    HttpHeaders httpHeaders = new HttpHeaders();
    MediaType mediaType = MediaType.parseMediaType("application/json; charset=utf-8");
    
    httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
    httpHeaders.setContentType(mediaType);
    
    HttpEntity<String> formEntity = 
        new HttpEntity<String>("\"biz_content\":\"{\"out_trade_no\":\"20150320010101001\",\"trade_no\":\"20150320010101001\"}\"", httpHeaders);
    
    logger.error("====Final platform: "+platform);
    logger.error("====Final requestString: "+requestString);
    
    ResponseEntity<String> responseEntiryFromThirdPartyApi = null;
    
    String thirdPartyUri = aliPayOrderQueryUri.concat("?").concat(requestString);
    
    responseEntiryFromThirdPartyApi = 
        (ResponseEntity<String>) rt.exchange(thirdPartyUri, HttpMethod.GET,formEntity,String.class,orderQueryRequestForAliPay.getBiz_content());    
    
    return responseEntiryFromThirdPartyApi;
    
  }
  
  
  
  
  private String getNonce_str(){
    String uuid = StringUtils.generateUuid();
    return uuid;
  }
  
  
  private String getSign(OrderQueryRequestForWeiXin orderQueryRequestForWeiXin){    
    Gson gson = new Gson();
    String orderQRForWeiXinJsonString = gson.toJson(orderQueryRequestForWeiXin);
    logger.info("orderQRForWeiXinJsonString: ["+orderQRForWeiXinJsonString+"]");
    
    Map signatureMapForWeiXin = 
        (Map) com.alibaba.fastjson.JSONObject.parse(orderQRForWeiXinJsonString);    
   
    String paySignature = signatureServiceImpl.weixinSignature(signatureMapForWeiXin);
   
    return paySignature;
  }
  
  
  private String getSign(OrderQueryRequestForAliPay orderQueryRequestForAliPay){    
    Gson gson = new Gson();
    String orderQueryRequestForAliPayJsonString = gson.toJson(orderQueryRequestForAliPay);
    logger.info("===orderQueryRequestForAliPayJsonString1: ["+orderQueryRequestForAliPayJsonString+"]");
    
//    orderQueryRequestForAliPayJsonString = orderQueryRequestForAliPayJsonString.replaceAll("\\\\", "");
//    logger.info("===orderQueryRequestForAliPayJsonString2: ["+orderQueryRequestForAliPayJsonString+"]");
    
    Map signatureMapForAliPay = 
        (Map) com.alibaba.fastjson.JSONObject.parse(orderQueryRequestForAliPayJsonString);    
   
    String paySignature = signatureServiceImpl.alipaySignature(signatureMapForAliPay);
   
    return paySignature;
  }
  
  
//  private boolean confirmSignForWeiXin(OrderQueryResponseForWeiXin orderQueryResponseForWeiXin){
//    boolean result = false;
//    Gson gson = new Gson();
//    String stringReturnFromWeiXin = gson.toJson(orderQueryResponseForWeiXin);
//    logger.info("stringReturnFromWeiXin: ["+stringReturnFromWeiXin+"]");
//    
//    Map signatureCheckMap = 
//        (Map) com.alibaba.fastjson.JSONObject.parse(stringReturnFromWeiXin);
//    
//    result = signatureServiceImpl.weixinSigantureCheck(signatureCheckMap);
//    
//    return result;    
//  }
  
  
  private boolean confirmSignForWeiXin(Map<String,Object> responseMap){
    boolean result = false;
    logger.info("responseMap: ["+responseMap+"]");
    if(responseMap == null || responseMap.isEmpty()){
      logger.error("responseMap is NULL or empty, the signanture check is failed.");
      return false;
    }
    result = signatureServiceImpl.weixinSigantureCheck(responseMap);
    
    return result;    
  }
  
  
  public static String jaxbRequestObjectToXMLForWeiXin(OrderQueryRequestForWeiXin orderQueryRequestForWeiXin){      
    String xmlString = "";      
    try{        
      JAXBContext context = JAXBContext.newInstance(OrderQueryRequestForWeiXin.class);
      Marshaller m = context.createMarshaller();

      m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); 

      StringWriter sw = new StringWriter();
      m.marshal(orderQueryRequestForWeiXin, sw);
      xmlString = sw.toString();

    }catch(JAXBException e){        
      e.printStackTrace();
    }

    return xmlString;
      
  }



}
