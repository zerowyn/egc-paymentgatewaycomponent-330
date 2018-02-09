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
  
  @Autowired
  private OrderQueryRequestForWeiXin orderQueryRequestForWeiXin;
  
  @Autowired
  private OrderQueryResponseForBackendDto orderQueryResponseForBackendDto;
  
  @Autowired
  private OrderQueryResponseForWeiXin orderQueryResponseForWeiXin;
  
  @Autowired
  SignatureService signatureServiceImpl;
  
  @Value("${payment.third.party.wechat.order.query.uri}")
  private String wechatOrderQueryUri;
  
  @Value("${payment.third.party.alipay.order.query.uri}")
  private String aliPayOrderQueryUri;
  
  /**
   * 接收缴费后台请求，转换为数据格式
   * @param OrderQueryRequestForBackendSystem 缴费后台提交的请求数据对象
   * @return OrderQueryRequestForWeiXin 返回给微信的请求数据对象
   */
  @Override
  public OrderQueryRequestForWeiXin transferBackendMessageForWeiXin(
      OrderQueryRequestForBackendDto orderQueryRequestForBackendDto){
    
    orderQueryRequestForWeiXin.setAppid(orderQueryRequestForBackendDto.getAppid());
    orderQueryRequestForWeiXin.setMch_id(orderQueryRequestForBackendDto.getMch_id());
    orderQueryRequestForWeiXin.setTransaction_id(orderQueryRequestForBackendDto.getTransaction_id());
    orderQueryRequestForWeiXin.setOut_trade_no(orderQueryRequestForBackendDto.getOut_trade_no());
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
    
    if(orderQueryRequestForBackendDto == null){
      String errorMeg = "Dto from backend system request for order query is null!";
      logger.error(errorMeg);
      return null;
    }
    //for wechat
    if(PaymentBusinessConstant.WEI_XIN
        .equalsIgnoreCase(orderQueryRequestForBackendDto.getPlatform())){      
      orderQueryRequestForWeiXin = 
          transferBackendMessageForWeiXin(orderQueryRequestForBackendDto);
      
      String requestXmlString = 
          jaxbRequestObjectToXMLForWeiXin(orderQueryRequestForWeiXin);
      logger.info("requestXmlString = ["+requestXmlString+"]");
    
      try{        
        ResponseEntity<String> responseEntiryFromWeiXin = 
            callThirdPartyOrderQueryApi(
                orderQueryRequestForBackendDto.getPlatform(),requestXmlString);
        
        String messageFromWeiXin = responseEntiryFromWeiXin.getBody();
       
        logger.debug("=====messageFromWeiXin====================> "+messageFromWeiXin);
        
        StringReader sr = new StringReader(messageFromWeiXin);
        JAXBContext jaxbContext = JAXBContext.newInstance(OrderQueryResponseForWeiXin.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        orderQueryResponseForWeiXin = (OrderQueryResponseForWeiXin) unmarshaller.unmarshal(sr);
        
        if(confirmSignForWeiXin(orderQueryResponseForWeiXin)){
          orderQueryResponseForBackendDto = 
              transferWeiXinMessageForBackendSystme(orderQueryResponseForWeiXin);
        }else{
          logger.error("Business Exception! The Signature Check failed! "
              + "This responese message stop here and will not pass to payment backend system!");
          return null;
        }
        
      }catch(HttpClientErrorException e){
        logger.error("error:  " + e.getResponseBodyAsString());
      }catch(Exception e){
        logger.error("error:  " + e.getMessage());
      } 
      
    }
    //for alipay
    else if(PaymentBusinessConstant.ALI_PAY
        .equalsIgnoreCase(orderQueryRequestForBackendDto.getPlatform())){
      logger.debug("=====Come into Alipay business logic====================> ");
      
    }
   
    return orderQueryResponseForBackendDto;    
  }
  
  private ResponseEntity<String> callThirdPartyOrderQueryApi(String platform, String requestString){
    RestTemplate rt = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    MediaType type = MediaType.parseMediaType("application/xml; charset=UTF-8");
    headers.setContentType(type);
    headers.add("Accept", MediaType.APPLICATION_XML_VALUE);
    
    HttpEntity<String> formEntity = 
        new HttpEntity<String>(requestString.toString(), headers);
    
    String thirdPartyUri = "";
    if(PaymentBusinessConstant.WEI_XIN.equalsIgnoreCase(platform)){
      thirdPartyUri = wechatOrderQueryUri;      
    }else if(PaymentBusinessConstant.ALI_PAY.equalsIgnoreCase(platform)){
      thirdPartyUri = aliPayOrderQueryUri; 
    }
    
    ResponseEntity<String> responseEntiryFromThirdPartyApi = 
        (ResponseEntity<String>) rt.exchange(thirdPartyUri, HttpMethod.POST, formEntity, String.class);
    
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
    
    Map signatureMap = 
        (Map) com.alibaba.fastjson.JSONObject.parse(orderQRForWeiXinJsonString);    
   
    String paySignature = signatureServiceImpl.weixinSignature(signatureMap);
   
    return paySignature;
  }
  
  
  private boolean confirmSignForWeiXin(OrderQueryResponseForWeiXin orderQueryResponseForWeiXin){
    boolean result = false;
    Gson gson = new Gson();
    String stringReturnFromWeiXin = gson.toJson(orderQueryResponseForWeiXin);
    logger.info("stringReturnFromWeiXin: ["+stringReturnFromWeiXin+"]");
    
    Map signatureCheckMap = 
        (Map) com.alibaba.fastjson.JSONObject.parse(stringReturnFromWeiXin);
    
    result = signatureServiceImpl.weixinSigantureCheck(signatureCheckMap);
    
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
