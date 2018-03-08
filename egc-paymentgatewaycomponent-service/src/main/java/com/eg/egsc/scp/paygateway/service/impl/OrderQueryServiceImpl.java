/**
 * @Probject Name: scp-pay-gateway-app-service
 * @Path: com.eg.egsc.scp.paygateway.service.implOrderQueryServiceImpl.java
 * @Create By caiqinli
 * @Create In 2018年2月6日 上午11:41:04
 * TODO
 */
package com.eg.egsc.scp.paygateway.service.impl;


import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import net.sf.json.JSONObject;

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
import com.eg.egsc.scp.paygateway.service.CodeMapsSerivce;
import com.eg.egsc.scp.paygateway.service.ConfigsService;
import com.eg.egsc.scp.paygateway.service.DefValSettingsService;
import com.eg.egsc.scp.paygateway.service.OrderQueryService;
import com.eg.egsc.scp.paygateway.service.SignatureService;
import com.eg.egsc.scp.paygateway.service.model.AlipayOrderQueryResponse;
import com.eg.egsc.scp.paygateway.service.model.FundBillList;
import com.eg.egsc.scp.paygateway.service.model.OrderQueryRequestForAliPay;
import com.eg.egsc.scp.paygateway.service.model.OrderQueryRequestForWeiXin;
import com.eg.egsc.scp.paygateway.service.model.OrderQueryResponseForAliPay;
import com.eg.egsc.scp.paygateway.service.model.OrderQueryResponseForWeiXin;
import com.eg.egsc.scp.paygateway.util.PaymentBusinessConstant;
import com.eg.egsc.scp.paygateway.util.StringUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


/**
 * @Class Name OrderQueryServiceImpl
 * @Author caiqinli
 * @Create In 2018年2月6日
 */
@Service
public class OrderQueryServiceImpl implements OrderQueryService {

  protected static final Logger logger = LoggerFactory.getLogger(OrderQueryServiceImpl.class);
  
  @Autowired
  SignatureService signatureServiceImpl;
  
  @Autowired
  ConfigsService configsServiceImpl;
  
  @Autowired
  DefValSettingsService defValSettingsServiceImpl;
  
  @Autowired
  CodeMapsSerivce codeMapsSerivceImpl;
  
  @Value("${payment.error.message.confirm.sign.not.pass}")
  private String confirmSignNotPassMessage; 
  
  @Value("${payment.error.message.request.platform.out.of.scope}")
  private String requestPlatformOutOfScopeMessage; 
  
  @Value("${payment.error.message.request.data.is.null}")
  private String requestDataIsNullMessage; 
  
  @Value("${payment.third.party.alipay.order.query.target.fundChannel}")
  private String aliPayOrderQueryTargetFundChannel;
  
  private String wechatOrderQueryUri = ""; 
  private String aliPayOrderQueryUri = "";  
  private String aliPaySignType = ""; 
  private String aliPayOrderQueryMethod = "";
  private String aliPayOrderQueryPrivateKey = "";
  private String aliPayOrderQueryPublicKey = "";
  private String aliPayOrderQueryFormat = "";
  private String aliPayOrderQueryCharset = "";
  private String aliPayOrderQueryVersion = "";
    
  /**
   * 接收缴费后台请求，转换为数据格式
   * @param OrderQueryRequestForBackendSystem 缴费后台提交的请求数据对象
   * @return OrderQueryRequestForWeiXin 返回给微信的请求数据对象
   */
  @Override
  public OrderQueryRequestForWeiXin transferBackendMessageForWeiXin(
      OrderQueryRequestForBackendDto orderQueryRequestForBackendDto){
    
    OrderQueryRequestForWeiXin orderQueryRequestForWeiXin = new OrderQueryRequestForWeiXin();    
        
    orderQueryRequestForWeiXin.setAppid(orderQueryRequestForBackendDto.getAppId());
    orderQueryRequestForWeiXin.setMchId(orderQueryRequestForBackendDto.getMchId());
    if(orderQueryRequestForBackendDto.getTransactionId() == null 
        || "".equals(orderQueryRequestForBackendDto.getTransactionId().trim())){
      orderQueryRequestForWeiXin.setOutTradeNo(orderQueryRequestForBackendDto.getOutTradeNo());
    }else{
      orderQueryRequestForWeiXin.setTransactionId(orderQueryRequestForBackendDto.getTransactionId());
    }    
    orderQueryRequestForWeiXin.setNonceStr(StringUtils.generateUuid());
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
    String bizContent = ""
        + "{\""+PaymentBusinessConstant.OUT_TRADE_NO+"\":\""+orderQueryRequestForBackendDto.getOutTradeNo()+"\","
            + "\""+PaymentBusinessConstant.TRADE_NO+"\":\""+orderQueryRequestForBackendDto.getTransactionId()+"\"}";
    
    requestForAliPay.setAppId(orderQueryRequestForBackendDto.getAppId());
    requestForAliPay.setMethod(aliPayOrderQueryMethod);    
    requestForAliPay.setCharset(aliPayOrderQueryCharset);
    requestForAliPay.setSignType(aliPaySignType);
    requestForAliPay.setTimestamp(timestampForAliPay);
    requestForAliPay.setVersion(aliPayOrderQueryVersion);
    requestForAliPay.setBizContent(bizContent);    
    requestForAliPay.setFormat(aliPayOrderQueryFormat);
    
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
    orderQueryResponseForBackendDto.setAppId(orderQueryResponseForWeiXin.getAppid());
    orderQueryResponseForBackendDto.setMchId(orderQueryResponseForWeiXin.getMchId());
    orderQueryResponseForBackendDto.setTransactionId(orderQueryResponseForWeiXin.getTransactionId());
    orderQueryResponseForBackendDto.setOutTradeNo(orderQueryResponseForWeiXin.getOutTradeNo());
    
    orderQueryResponseForBackendDto.setReturnCode(orderQueryResponseForWeiXin.getReturnCode());
    orderQueryResponseForBackendDto.setReturnMsg(orderQueryResponseForWeiXin.getReturnMsg());
    orderQueryResponseForBackendDto.setDeviceInfo(orderQueryResponseForWeiXin.getDeviceInfo());
    orderQueryResponseForBackendDto.setErrCode(orderQueryResponseForWeiXin.getErrCode());
    orderQueryResponseForBackendDto.setErrCodeDes(orderQueryResponseForWeiXin.getErrCodeDes());
    orderQueryResponseForBackendDto.setResultCode(orderQueryResponseForWeiXin.getResultCode());
    
    orderQueryResponseForBackendDto.setAttach(orderQueryResponseForWeiXin.getAttach());
    orderQueryResponseForBackendDto.setBankType(orderQueryResponseForWeiXin.getBankType());
    orderQueryResponseForBackendDto.setCashFee(orderQueryResponseForWeiXin.getCashFee());
    orderQueryResponseForBackendDto.setCashFeeType(orderQueryResponseForWeiXin.getCashFeeType());
    orderQueryResponseForBackendDto.setCouponCount(orderQueryResponseForWeiXin.getCouponCount());
    orderQueryResponseForBackendDto.setCouponFee(orderQueryResponseForWeiXin.getCouponFee());   
    
    orderQueryResponseForBackendDto.setIsSubscribe(orderQueryResponseForWeiXin.getIsSubscribe());
    orderQueryResponseForBackendDto.setOpenId(orderQueryResponseForWeiXin.getOpenid());
    
    orderQueryResponseForBackendDto.setSettlementTotalFee(orderQueryResponseForWeiXin.getSettlementTotalFee());
    orderQueryResponseForBackendDto.setTimeEnd(orderQueryResponseForWeiXin.getTimeEnd());
    orderQueryResponseForBackendDto.setTotalFee(orderQueryResponseForWeiXin.getTotalFee());
    orderQueryResponseForBackendDto.setFeeType(orderQueryResponseForWeiXin.getFeeType());
    
    orderQueryResponseForBackendDto.setTradeState(orderQueryResponseForWeiXin.getTradeState());
    orderQueryResponseForBackendDto.setTradeStateDesc(orderQueryResponseForWeiXin.getTradeStateDesc());
    orderQueryResponseForBackendDto.setTradeType(orderQueryResponseForWeiXin.getTradeType());
    
    orderQueryResponseForBackendDto.setCouponListJsonString(orderQueryResponseForWeiXin.getCouponListJsonString());
    
    
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
    
    AlipayOrderQueryResponse alipayTradeQueryResponse = orderQueryResponseForAliPay.getAlipayTradeQueryResponse();
    
    orderQueryResponseForBackendDto.setPlatform(PaymentBusinessConstant.ALI_PAY);    
    orderQueryResponseForBackendDto.setReturnCode(getReturnCode(alipayTradeQueryResponse.getCode()));
    orderQueryResponseForBackendDto.setReturnMsg(alipayTradeQueryResponse.getMsg());
    orderQueryResponseForBackendDto.setResultCode(alipayTradeQueryResponse.getSubCode());
    
    if(!PaymentBusinessConstant.ACQ_TRADE_HAS_SUCCESS.equals(alipayTradeQueryResponse.getSubCode())){
      orderQueryResponseForBackendDto.setErrCode(alipayTradeQueryResponse.getSubCode());
      orderQueryResponseForBackendDto.setErrCodeDes(alipayTradeQueryResponse.getSubMsg());
    }
    
    orderQueryResponseForBackendDto.setTransactionId(alipayTradeQueryResponse.getTradeNo());
    orderQueryResponseForBackendDto.setOutTradeNo(alipayTradeQueryResponse.getOutTradeNo());
    
    Map getTradeStateMap = codeMapsSerivceImpl.excodeConvertToIncode(
        PaymentBusinessConstant.ALI_PAY, PaymentBusinessConstant.QUERY, PaymentBusinessConstant.TRADE_STATUS, alipayTradeQueryResponse.getTradeStatus());
    if(getTradeStateMap != null){
      orderQueryResponseForBackendDto.setTradeState((String)getTradeStateMap.get(PaymentBusinessConstant.TRADE_STATE));
    }
    
    Map getResultCodeMap = codeMapsSerivceImpl.excodeConvertToIncode(
        PaymentBusinessConstant.ALI_PAY, PaymentBusinessConstant.QUERY, PaymentBusinessConstant.TRADE_STATUS, alipayTradeQueryResponse.getTradeStatus());
    if(getResultCodeMap != null && !"".equals((String)getResultCodeMap.get(PaymentBusinessConstant.TRADE_STATE))){      
      orderQueryResponseForBackendDto.setResultCode((String)getResultCodeMap.get(PaymentBusinessConstant.TRADE_STATE));
    }
    
    orderQueryResponseForBackendDto.setTotalFee(
        Double.parseDouble(alipayTradeQueryResponse.getTotalAmount()==null ? PaymentBusinessConstant.NUM_ZERO : alipayTradeQueryResponse.getTotalAmount() ));
    orderQueryResponseForBackendDto.setCashFee(Double.parseDouble(
        alipayTradeQueryResponse.getBuyerPayAmount() ==null ? PaymentBusinessConstant.NUM_ZERO : alipayTradeQueryResponse.getBuyerPayAmount()));
    orderQueryResponseForBackendDto.setMchId(alipayTradeQueryResponse.getStoreId());    
  
    FundBillList[] fundBillList = alipayTradeQueryResponse.getFundBillList();
    orderQueryResponseForBackendDto = updateFunBillList(orderQueryResponseForBackendDto,fundBillList);
    
    return orderQueryResponseForBackendDto;
    
  }
  
  
  public OrderQueryResponseForBackendDto updateFunBillList(OrderQueryResponseForBackendDto orderQueryResponseForBackendDto, FundBillList[] fundBillList){
    
    if(fundBillList != null){
      int couponNum = 0;
      String couponJsonString = "";
      Double couponFee = 0.00;
      Double singleBillAmount;
      for(FundBillList fb : fundBillList)
      {        
        if(aliPayOrderQueryTargetFundChannel.contains(fb.getFundChannel())){
          singleBillAmount = fb.getAmount()==null? 0.00 : fb.getAmount();
          couponJsonString = couponJsonString+"{\""+PaymentBusinessConstant.COUPON_ID+couponNum+"\":\"\","
              + "\""+PaymentBusinessConstant.COUPON_TYPE+fb.getFundChannel()+"\":\"\",\""+PaymentBusinessConstant.COUPON_FEE+singleBillAmount+"\":\"\",},";          
          couponFee = couponFee+singleBillAmount;
          couponNum++;
        }        
      }      
      orderQueryResponseForBackendDto.setCouponCount(Integer.toString(couponNum));
      orderQueryResponseForBackendDto.setCouponFee(couponFee);
      if(couponJsonString != ""){
        couponJsonString = "["+couponJsonString.substring(0, couponJsonString.length()-1)+"]";
      }
      
      orderQueryResponseForBackendDto.setCouponListJsonString(couponJsonString);      
    } 
    
    return orderQueryResponseForBackendDto;    
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
      orderQueryResponseForBackendDto.setErrCodeDes(requestDataIsNullMessage);
      return orderQueryResponseForBackendDto;
    }
    
    assignVariables();
    
    //for wechat
    if(PaymentBusinessConstant.WEI_XIN
        .equalsIgnoreCase(orderQueryRequestForBackendDto.getPlatform())){
      orderQueryResponseForBackendDto = 
          orderQueryWeiXinRequestFromBackendSystme(orderQueryRequestForBackendDto,orderQueryResponseForBackendDto);
      
    }    
    else if(PaymentBusinessConstant.ALI_PAY
        .equalsIgnoreCase(orderQueryRequestForBackendDto.getPlatform())){      
      orderQueryResponseForBackendDto = 
          orderQueryAlipayRequestFromBackendSystme(orderQueryRequestForBackendDto,orderQueryResponseForBackendDto);      
    }else{
      logger.debug("====Request Data Exception! The Platform is not Wechat or Alipay! Nothing will be done here.=====");
      orderQueryResponseForBackendDto.setErrCodeDes(requestPlatformOutOfScopeMessage);
    }
   
    return orderQueryResponseForBackendDto;    
  } 
  
  
  public OrderQueryResponseForBackendDto orderQueryWeiXinRequestFromBackendSystme(
      OrderQueryRequestForBackendDto orderQueryRequestForBackendDto,OrderQueryResponseForBackendDto orderQueryResponseForBackendDto){        
    
    OrderQueryRequestForWeiXin orderQueryRequestForWeiXin = 
        transferBackendMessageForWeiXin(orderQueryRequestForBackendDto);  
  
    try{  
      String requestXmlString = new ObjectMapper().writeValueAsString(orderQueryRequestForWeiXin);
      logger.error("requestXmlString from objectMapper = ["+requestXmlString+"]");
      
      JSONObject jsonObject = JSONObject.fromObject(requestXmlString);
      requestXmlString = StringUtils.getJson2Xml(jsonObject);
      logger.error("requestXmlString fromJson2XML= ["+requestXmlString+"]");
      
      ResponseEntity<String> responseEntiryFromWeiXin = 
          callThirdPartyOrderQueryApi(
              orderQueryRequestForBackendDto.getPlatform(),requestXmlString);
      
      String responseMessageFromWeiXin = responseEntiryFromWeiXin.getBody();       
      logger.error("=====responseMessageFromWeiXin====================> "+responseMessageFromWeiXin);
      
      Map<String,Object> responseMap = StringUtils.transferXMLtoMap(responseMessageFromWeiXin);
      logger.error("=====responseMap====================> "+responseMap);
      
      if(!confirmSignForWeiXin(responseMap)){
        logger.error("Business Exception! The WeiXin Signature Check failed! "
            + "This responese message stop here and will not pass to payment backend system!");
        orderQueryResponseForBackendDto.setErrCodeDes(confirmSignNotPassMessage);
        return orderQueryResponseForBackendDto;          
      }
      
      StringReader sr = new StringReader(responseMessageFromWeiXin);
      JAXBContext jaxbContext = JAXBContext.newInstance(OrderQueryResponseForWeiXin.class);
      Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
      
      OrderQueryResponseForWeiXin orderQueryResponseForWeiXin = 
          (OrderQueryResponseForWeiXin) unmarshaller.unmarshal(sr);
      
      //update coupon_list_json_string to accept multiple coupons
      orderQueryResponseForWeiXin = updateCouponListJsonString(orderQueryResponseForWeiXin,responseMap);  
      
      orderQueryResponseForBackendDto = 
          transferWeiXinMessageForBackendSystme(orderQueryResponseForWeiXin);        
    }catch (JsonProcessingException e1) {
      logger.error("JsonProcessingException:  ", e1);
    }catch(HttpClientErrorException e){
      logger.error("error:  " + e.getResponseBodyAsString());
    }catch(Exception e){
      logger.error("error:  " + e);
    }
    
    return orderQueryResponseForBackendDto;
    
  }
  
  
  private OrderQueryResponseForWeiXin updateCouponListJsonString(
      OrderQueryResponseForWeiXin orderQueryResponseForWeiXin,Map<String,Object> responseMap){
    
    if(!responseMap.isEmpty()){
      Set<String> xmlFiledsFromWeiXin = responseMap.keySet();
      List<String> keysStartwithCoupon = 
          xmlFiledsFromWeiXin.stream().filter(st->st.startsWith(PaymentBusinessConstant.COUPON_ID)).collect(Collectors.toList());
      
      if(!keysStartwithCoupon.isEmpty()){
        Integer maxID = 0;
        Integer tempID = 0;
        String couponJsonString = "";
        
        for(String sk : keysStartwithCoupon)
        {
          tempID = Integer.valueOf(sk.replaceAll(PaymentBusinessConstant.COUPON_ID, ""));
          if(maxID<tempID){
            maxID = tempID;
          }              
        }
        
        for(int i=0;i<maxID;i++)
        {             
          couponJsonString = couponJsonString+"{"
              + "\""+PaymentBusinessConstant.COUPON_ID+i+PaymentBusinessConstant.JSONDEL+responseMap.get(PaymentBusinessConstant.COUPON_ID+i)+"\","
                  + "\""+PaymentBusinessConstant.COUPON_TYPE+i+PaymentBusinessConstant.JSONDEL+responseMap.get(PaymentBusinessConstant.COUPON_TYPE+i)+"\","
                      + "\""+PaymentBusinessConstant.COUPON_FEE+i+PaymentBusinessConstant.JSONDEL+responseMap.get(PaymentBusinessConstant.COUPON_FEE+i)+"\"},";
        }        
        orderQueryResponseForWeiXin.setCouponListJsonString(customizedCouponJsonString(couponJsonString));        
      }
     
    }
    
    return orderQueryResponseForWeiXin;    
  }
  
  
  private String customizedCouponJsonString(String couponJsonString){
    if(!"".equals(couponJsonString)){
      couponJsonString = "["+couponJsonString.substring(0, couponJsonString.length()-1)+"]";
      return couponJsonString;
    } 
    return null;
  }
  
  
  public OrderQueryResponseForBackendDto orderQueryAlipayRequestFromBackendSystme(
      OrderQueryRequestForBackendDto orderQueryRequestForBackendDto,OrderQueryResponseForBackendDto orderQueryResponseForBackendDto){
    
    OrderQueryRequestForAliPay orderQueryRequestForAliPay = 
        transferBackendMessageForAliPay(orderQueryRequestForBackendDto);
    
    AlipayTradeQueryResponse aliResponse = orderQueryForAlipay(orderQueryRequestForAliPay);
    logger.info("===aliResponse.getBody(): "+aliResponse.getBody());
    
    ObjectMapper objectMapper = new ObjectMapper();
    OrderQueryResponseForAliPay orderQueryResponseForAliPay = new OrderQueryResponseForAliPay();
    try {
      orderQueryResponseForAliPay = objectMapper.readValue(aliResponse.getBody(), OrderQueryResponseForAliPay.class);
    } catch (JsonParseException e) {      
      logger.error("orderQueryAlipayRequestFromBackendSystme JsonParseException: ",e);     
    } catch (JsonMappingException e) {
      logger.error("orderQueryAlipayRequestFromBackendSystme JsonMappingException: ",e);
    } catch (IOException e) {
      logger.error("orderQueryAlipayRequestFromBackendSystme IOException: ",e);
    }
  
    return transferAliPayMessageForBackendSystme(orderQueryResponseForAliPay);   
        
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
      logger.error("context_JAXBException: ", e);
    }

    return xmlString;
      
  }
  
   
  private String getReturnCode(String codeFromAlipay){
    String result = PaymentBusinessConstant.RETURN_CODE_ERROR;
    if(PaymentBusinessConstant.ALIPAY_RETURN_CODE_SUCCESS.equals(codeFromAlipay)){
      result = PaymentBusinessConstant.SUCCESS_MESSAGE;
    }
    return result;    
  }
  
  
  private ResponseEntity<String> callThirdPartyOrderQueryApi(String platform,String requestString){  
    
    RestTemplate rt = new RestTemplate();
    rt.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName(PaymentBusinessConstant.CHARSET_UTF8)));
    HttpHeaders httpHeaders = new HttpHeaders();
    MediaType mediaType = MediaType.parseMediaType("application/xml; charset=utf-8");
    
    httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_ATOM_XML));
    httpHeaders.setContentType(mediaType);
    
    HttpEntity<String> formEntity = new HttpEntity<String>(requestString, httpHeaders);
    
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
   
    return signatureServiceImpl.weixinSignature(signatureMapForWeiXin);
  }
  
  
  private boolean confirmSignForWeiXin(Map<String,Object> responseMap){
    boolean result = false;
    logger.info("responseMap: ["+responseMap+"]");
    if(responseMap == null || responseMap.isEmpty()){
      logger.error("responseMap is NULL or empty, the signanture check is failed.");
      return false;
    }
    result = signatureServiceImpl.weixinSignatureCheck(responseMap);
    
    return result;    
  }  

  
  private AlipayTradeQueryResponse orderQueryForAlipay(OrderQueryRequestForAliPay orderQueryRequestForAliPay){
    
    AlipayClient alipayClient = new DefaultAlipayClient(
        aliPayOrderQueryUri,
        orderQueryRequestForAliPay.getAppId(),
        aliPayOrderQueryPrivateKey,
        orderQueryRequestForAliPay.getFormat(),
        orderQueryRequestForAliPay.getCharset(),
        aliPayOrderQueryPublicKey,
        orderQueryRequestForAliPay.getSignType());
    
    AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
    
    request.setBizContent(orderQueryRequestForAliPay.getBizContent());
    AlipayTradeQueryResponse response = new AlipayTradeQueryResponse();
    try {
      response = alipayClient.execute(request);
    } catch (AlipayApiException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    return response;
    
  }
  
  
  private void assignVariables(){
    wechatOrderQueryUri = configsServiceImpl.getConfigsValueByExample(PaymentBusinessConstant.WEIXIN_URL, PaymentBusinessConstant.QUERY); 
    aliPayOrderQueryUri = configsServiceImpl.getConfigsValueByExample(PaymentBusinessConstant.ALIPAY_URL, PaymentBusinessConstant.QUERY);
    aliPayOrderQueryPrivateKey = configsServiceImpl.getConfigsValueByExample(PaymentBusinessConstant.KEY, PaymentBusinessConstant.ALIPAY_APP_PRIVATE);
    aliPayOrderQueryPublicKey = configsServiceImpl.getConfigsValueByExample(PaymentBusinessConstant.KEY, PaymentBusinessConstant.ALIPAY_PUBLIC);
    
    aliPaySignType = defValSettingsServiceImpl.getDefValSettingsValueByExample(PaymentBusinessConstant.ALI_PAY, PaymentBusinessConstant.QUERY, PaymentBusinessConstant.SIGN_TYPE); 
    aliPayOrderQueryMethod = defValSettingsServiceImpl.getDefValSettingsValueByExample(PaymentBusinessConstant.ALI_PAY, PaymentBusinessConstant.QUERY, PaymentBusinessConstant.METHOD);    
    aliPayOrderQueryFormat = defValSettingsServiceImpl.getDefValSettingsValueByExample(PaymentBusinessConstant.ALI_PAY, PaymentBusinessConstant.QUERY, PaymentBusinessConstant.FORMAT);
    aliPayOrderQueryCharset = defValSettingsServiceImpl.getDefValSettingsValueByExample(PaymentBusinessConstant.ALI_PAY, PaymentBusinessConstant.QUERY, PaymentBusinessConstant.CHARSET);
    aliPayOrderQueryVersion = defValSettingsServiceImpl.getDefValSettingsValueByExample(PaymentBusinessConstant.ALI_PAY, PaymentBusinessConstant.QUERY, PaymentBusinessConstant.VERSION);
  }



}
