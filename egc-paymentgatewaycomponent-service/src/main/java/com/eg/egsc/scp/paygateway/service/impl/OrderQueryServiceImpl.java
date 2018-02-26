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

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.eg.egsc.scp.paygateway.dto.OrderQueryRequestForBackendDto;
import com.eg.egsc.scp.paygateway.dto.OrderQueryResponseForBackendDto;
import com.eg.egsc.scp.paygateway.service.OrderQueryService;
import com.eg.egsc.scp.paygateway.service.SignatureService;
import com.eg.egsc.scp.paygateway.service.model.AlipayOrderQueryResponse;
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
  
  @Value("${payment.third.party.alipay.order.query.private.key}")
  private String aliPayOrderQueryPrivateKey;
  
  @Value("${payment.third.party.alipay.order.query.public.key}")
  private String aliPayOrderQueryPublicKey;
  
  @Value("${payment.third.party.alipay.order.query.format}")
  private String aliPayOrderQueryFormat;
  
  @Value("${payment.third.party.alipay.order.query.charset}")
  private String aliPayOrderQueryCharset;
  
  @Value("${payment.third.party.alipay.order.query.version}")
  private String aliPayOrderQueryVersion;

  
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
    orderQueryRequestForWeiXin.setNonce_str(StringUtils.generateUuid());
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
    
    requestForAliPay.setApp_id(orderQueryRequestForBackendDto.getAppid());
    requestForAliPay.setMethod(aliPayOrderQueryMethod);    
    requestForAliPay.setCharset(aliPayOrderQueryCharset);
    requestForAliPay.setSign_type(aliPaySignType);
    requestForAliPay.setTimestamp(timestampForAliPay);
    requestForAliPay.setVersion(aliPayOrderQueryVersion);
    requestForAliPay.setBiz_content(biz_content);    
    requestForAliPay.setFormat(aliPayOrderQueryFormat);
  //requestForAliPay.setSign(getSign(requestForAliPay)); 
    
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
    
    OrderQueryResponseForBackendDto orderQueryResponseForBackendDto = new OrderQueryResponseForBackendDto();
    
    AlipayOrderQueryResponse AlipayTradeQueryResponse = orderQueryResponseForAliPay.getAlipayTradeQueryResponse();
    
    orderQueryResponseForBackendDto.setPlatform(PaymentBusinessConstant.ALI_PAY);
    
    orderQueryResponseForBackendDto.setReturn_code(getReturn_code(AlipayTradeQueryResponse.getCode()));
    orderQueryResponseForBackendDto.setReturn_msg(AlipayTradeQueryResponse.getMsg());
    orderQueryResponseForBackendDto.setResult_code(AlipayTradeQueryResponse.getSub_code());
    
    if(!"ACQ.TRADE_HAS_SUCCESS".equals(AlipayTradeQueryResponse.getSub_code())){
      orderQueryResponseForBackendDto.setErr_code(AlipayTradeQueryResponse.getSub_code());
      orderQueryResponseForBackendDto.setErr_code_des(AlipayTradeQueryResponse.getSub_msg());
    }
    
    orderQueryResponseForBackendDto.setTransaction_id(AlipayTradeQueryResponse.getTrade_no());
    orderQueryResponseForBackendDto.setOut_trade_no(AlipayTradeQueryResponse.getOut_trade_no());
    
    orderQueryResponseForBackendDto.setTrade_state(getTradeStateOrResultCode(AlipayTradeQueryResponse.getTrade_status(),"1"));
    orderQueryResponseForBackendDto.setResult_code(getTradeStateOrResultCode(AlipayTradeQueryResponse.getTrade_status(),"2"));
    
    orderQueryResponseForBackendDto.setTotal_fee(AlipayTradeQueryResponse.getTotal_amount());
    orderQueryResponseForBackendDto.setCash_fee(AlipayTradeQueryResponse.getBuyer_pay_amount());
    orderQueryResponseForBackendDto.setMch_id(AlipayTradeQueryResponse.getStore_id());
    
    return orderQueryResponseForBackendDto;
    
  }
  
  /**
   * 
   * @param tradeStatusFromAlipay 支付宝接口返回的trade status; 
   * @param flag : 1-表示需要转换为缴费后台的trade_state; 2-表示需要转换为缴费后台的result_code
   * @return String
   */
  private String getTradeStateOrResultCode(String tradeStatusFromAlipay,String flag){
    String result = "";
    if("TRADE_SUCCESS".equals(tradeStatusFromAlipay)){
      result = "SUCCESS";
    }else if("WAIT_BUYER_PAY".equals(tradeStatusFromAlipay)){
      result = "USERPAYING";
    }else if("TRADE_CLOSED".equals(tradeStatusFromAlipay)){
      result = "TRADE_CLOSED";
    }else if("TRADE_FINISHED".equals(tradeStatusFromAlipay)){
      result = "TRADE_FINISHED";
    }
    return result;    
  }  
  
  private String getReturn_code(String codeFromAlipay){
    String result = "FAIL";
    if("10000".equals(codeFromAlipay)){
      result = "SUCCESS";
    }
    return result;    
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
      logger.debug("requestXmlString = ["+requestXmlString+"]");
    
      try{        
        ResponseEntity<String> responseEntiryFromWeiXin = 
            callThirdPartyOrderQueryApi(
                orderQueryRequestForBackendDto.getPlatform(),requestXmlString);
        
        String responseMessageFromWeiXin = responseEntiryFromWeiXin.getBody();       
        logger.debug("=====responseMessageFromWeiXin====================> "+responseMessageFromWeiXin);
        
        Map<String,Object> responseMap = StringUtils.transferXMLtoMap(responseMessageFromWeiXin,xmlCustomizedHeader);
        logger.debug("=====responseMap====================> "+responseMap);
        
        if(!confirmSignForWeiXin(responseMap)){
          logger.error("Business Exception! The WeiXin Signature Check failed! "
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
      OrderQueryRequestForAliPay orderQueryRequestForAliPay = 
          transferBackendMessageForAliPay(orderQueryRequestForBackendDto);
      
      AlipayTradeQueryResponse aliResponse = orderQueryForAlipay(orderQueryRequestForAliPay);
      logger.debug("===aliResponse.getBody(): "+aliResponse.getBody());
            
      Gson gson=new Gson();
      OrderQueryResponseForAliPay orderQueryResponseForAliPay = gson.fromJson(aliResponse.getBody(), OrderQueryResponseForAliPay.class);
      System.out.println(orderQueryResponseForAliPay);
      
      orderQueryResponseForBackendDto = transferAliPayMessageForBackendSystme(orderQueryResponseForAliPay);
     
      
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
  
  
  private String getSign(OrderQueryRequestForWeiXin orderQueryRequestForWeiXin){    
    Gson gson = new Gson();
    String orderQRForWeiXinJsonString = gson.toJson(orderQueryRequestForWeiXin);
    logger.info("orderQRForWeiXinJsonString: ["+orderQRForWeiXinJsonString+"]");
    
    Map signatureMapForWeiXin = 
        (Map) com.alibaba.fastjson.JSONObject.parse(orderQRForWeiXinJsonString);    
   
    String paySignature = signatureServiceImpl.weixinSignature(signatureMapForWeiXin);
   
    return paySignature;
  }
  
  
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
  
  private AlipayTradeQueryResponse orderQueryForAlipay(OrderQueryRequestForAliPay orderQueryRequestForAliPay){
    
    AlipayClient alipayClient = new DefaultAlipayClient(
        aliPayOrderQueryUri,
        orderQueryRequestForAliPay.getApp_id(),
        aliPayOrderQueryPrivateKey,
        orderQueryRequestForAliPay.getFormat(),
        orderQueryRequestForAliPay.getCharset(),
        aliPayOrderQueryPublicKey,
        orderQueryRequestForAliPay.getSign_type());
    
    AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
    
    request.setBizContent(orderQueryRequestForAliPay.getBiz_content());
    AlipayTradeQueryResponse response = new AlipayTradeQueryResponse();
    try {
      response = alipayClient.execute(request);
    } catch (AlipayApiException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    if(response.isSuccess()){
      System.out.println("调用成功");    
    } else {
      System.out.println("调用失败");
    }
    return response;
    
  }



}
