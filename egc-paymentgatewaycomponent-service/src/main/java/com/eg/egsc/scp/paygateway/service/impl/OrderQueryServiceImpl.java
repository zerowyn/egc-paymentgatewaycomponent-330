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
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
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
import com.google.gson.Gson;


/**
 * @Class Name OrderQueryServiceImpl
 * @Author caiqinli
 * @Create In 2018年2月6日
 */
@Service
public class OrderQueryServiceImpl implements OrderQueryService {

  protected final static Logger logger = LoggerFactory.getLogger(OrderQueryServiceImpl.class);
  
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
    orderQueryRequestForWeiXin.setMch_id(orderQueryRequestForBackendDto.getMchId());
    if(orderQueryRequestForBackendDto.getTransactionId() == null 
        || "".equals(orderQueryRequestForBackendDto.getTransactionId().trim())){
      orderQueryRequestForWeiXin.setOut_trade_no(orderQueryRequestForBackendDto.getOutTradeNo());
    }else{
      orderQueryRequestForWeiXin.setTransaction_id(orderQueryRequestForBackendDto.getTransactionId());
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
        + "{\""+PaymentBusinessConstant.OUT_TRADE_NO+"\":\""+orderQueryRequestForBackendDto.getOutTradeNo()+"\","
            + "\""+PaymentBusinessConstant.TRADE_NO+"\":\""+orderQueryRequestForBackendDto.getTransactionId()+"\"}";
    
    requestForAliPay.setApp_id(orderQueryRequestForBackendDto.getAppId());
    requestForAliPay.setMethod(aliPayOrderQueryMethod);    
    requestForAliPay.setCharset(aliPayOrderQueryCharset);
    requestForAliPay.setSign_type(aliPaySignType);
    requestForAliPay.setTimestamp(timestampForAliPay);
    requestForAliPay.setVersion(aliPayOrderQueryVersion);
    requestForAliPay.setBiz_content(biz_content);    
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
    orderQueryResponseForBackendDto.setMchId(orderQueryResponseForWeiXin.getMch_id());
    orderQueryResponseForBackendDto.setTransactionId(orderQueryResponseForWeiXin.getTransaction_id());
    orderQueryResponseForBackendDto.setOutTradeNo(orderQueryResponseForWeiXin.getOut_trade_no());
    
    orderQueryResponseForBackendDto.setReturnCode(orderQueryResponseForWeiXin.getReturn_code());
    orderQueryResponseForBackendDto.setReturnMsg(orderQueryResponseForWeiXin.getReturn_msg());
    orderQueryResponseForBackendDto.setDeviceInfo(orderQueryResponseForWeiXin.getDevice_info());
    orderQueryResponseForBackendDto.setErrCode(orderQueryResponseForWeiXin.getErr_code());
    orderQueryResponseForBackendDto.setErrCodeDes(orderQueryResponseForWeiXin.getErr_code_des());
    orderQueryResponseForBackendDto.setResultCode(orderQueryResponseForWeiXin.getResult_code());
    
    orderQueryResponseForBackendDto.setAttach(orderQueryResponseForWeiXin.getAttach());
    orderQueryResponseForBackendDto.setBankType(orderQueryResponseForWeiXin.getBank_type());
    orderQueryResponseForBackendDto.setCashFee(orderQueryResponseForWeiXin.getCash_fee());
    orderQueryResponseForBackendDto.setCashFeeType(orderQueryResponseForWeiXin.getCash_fee_type());
    orderQueryResponseForBackendDto.setCouponCount(orderQueryResponseForWeiXin.getCoupon_count());
    orderQueryResponseForBackendDto.setCouponFee(orderQueryResponseForWeiXin.getCoupon_fee());   
    
    orderQueryResponseForBackendDto.setIsSubscribe(orderQueryResponseForWeiXin.getIs_subscribe());
    orderQueryResponseForBackendDto.setOpenId(orderQueryResponseForWeiXin.getOpenid());
    
    orderQueryResponseForBackendDto.setSettlementTotalFee(orderQueryResponseForWeiXin.getSettlement_total_fee());
    orderQueryResponseForBackendDto.setTimeEnd(orderQueryResponseForWeiXin.getTime_end());
    orderQueryResponseForBackendDto.setTotalFee(orderQueryResponseForWeiXin.getTotal_fee());
    orderQueryResponseForBackendDto.setFeeType(orderQueryResponseForWeiXin.getFee_type());
    
    orderQueryResponseForBackendDto.setTradeState(orderQueryResponseForWeiXin.getTrade_state());
    orderQueryResponseForBackendDto.setTradeStateDesc(orderQueryResponseForWeiXin.getTrade_state_desc());
    orderQueryResponseForBackendDto.setTradeType(orderQueryResponseForWeiXin.getTrade_type());
    
    orderQueryResponseForBackendDto.setCouponListJsonString(orderQueryResponseForWeiXin.getCoupon_list_json_string());
    
    
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
    orderQueryResponseForBackendDto.setReturnCode(getReturn_code(alipayTradeQueryResponse.getCode()));
    orderQueryResponseForBackendDto.setReturnMsg(alipayTradeQueryResponse.getMsg());
    orderQueryResponseForBackendDto.setResultCode(alipayTradeQueryResponse.getSub_code());
    
    if(!PaymentBusinessConstant.ACQ_TRADE_HAS_SUCCESS.equals(alipayTradeQueryResponse.getSub_code())){
      orderQueryResponseForBackendDto.setErrCode(alipayTradeQueryResponse.getSub_code());
      orderQueryResponseForBackendDto.setErrCodeDes(alipayTradeQueryResponse.getSub_msg());
    }
    
    orderQueryResponseForBackendDto.setTransactionId(alipayTradeQueryResponse.getTrade_no());
    orderQueryResponseForBackendDto.setOutTradeNo(alipayTradeQueryResponse.getOut_trade_no());
    
    Map getTradeStateMap = codeMapsSerivceImpl.excodeConvertToIncode(
        PaymentBusinessConstant.ALI_PAY, PaymentBusinessConstant.QUERY, PaymentBusinessConstant.TRADE_STATUS, alipayTradeQueryResponse.getTrade_status());
    if(getTradeStateMap != null){
      orderQueryResponseForBackendDto.setTradeState((String)getTradeStateMap.get(PaymentBusinessConstant.TRADE_STATE));
    }
    
    Map getResultCodeMap = codeMapsSerivceImpl.excodeConvertToIncode(
        PaymentBusinessConstant.ALI_PAY, PaymentBusinessConstant.QUERY, PaymentBusinessConstant.TRADE_STATUS, alipayTradeQueryResponse.getTrade_status());
    if(getResultCodeMap != null && !"".equals((String)getResultCodeMap.get(PaymentBusinessConstant.TRADE_STATE))){      
      orderQueryResponseForBackendDto.setResultCode((String)getResultCodeMap.get(PaymentBusinessConstant.TRADE_STATE));
    }
    
    orderQueryResponseForBackendDto.setTotalFee(
        Double.parseDouble(alipayTradeQueryResponse.getTotal_amount()==null ? PaymentBusinessConstant.NUM_ZERO : alipayTradeQueryResponse.getTotal_amount() ));
    orderQueryResponseForBackendDto.setCashFee(Double.parseDouble(
        alipayTradeQueryResponse.getBuyer_pay_amount() ==null ? PaymentBusinessConstant.NUM_ZERO : alipayTradeQueryResponse.getBuyer_pay_amount()));
    orderQueryResponseForBackendDto.setMchId(alipayTradeQueryResponse.getStore_id());    
  
    FundBillList[] fundBillList = alipayTradeQueryResponse.getFundBillList();
    orderQueryResponseForBackendDto = updateFunBillList(orderQueryResponseForBackendDto,fundBillList);
    
    return orderQueryResponseForBackendDto;
    
  }
  
  
  public OrderQueryResponseForBackendDto updateFunBillList(OrderQueryResponseForBackendDto orderQueryResponseForBackendDto, FundBillList[] fundBillList){
    
    if(fundBillList != null){
      int coupon_num = 0;
      String coupon_jsonString = "";
      Double coupon_fee = 0.00;
      Double singleBillAmount;
      for(FundBillList fb : fundBillList)
      {        
        if(aliPayOrderQueryTargetFundChannel.contains(fb.getFund_channel())){
          singleBillAmount = fb.getAmount()==null? 0.00 : fb.getAmount();
          coupon_jsonString = coupon_jsonString+"{\""+PaymentBusinessConstant.COUPON_ID+coupon_num+"\":\"\","
              + "\""+PaymentBusinessConstant.COUPON_TYPE+fb.getFund_channel()+"\":\"\",\""+PaymentBusinessConstant.COUPON_FEE+singleBillAmount+"\":\"\",},";          
          coupon_fee = coupon_fee+singleBillAmount;
          coupon_num++;
        }        
      }      
      orderQueryResponseForBackendDto.setCouponCount(Integer.toString(coupon_num));
      orderQueryResponseForBackendDto.setCouponFee(coupon_fee);
      if(coupon_jsonString != ""){
        coupon_jsonString = "["+coupon_jsonString.substring(0, coupon_jsonString.length()-1)+"]";
      }
      
      orderQueryResponseForBackendDto.setCouponListJsonString(coupon_jsonString);      
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
    
    String requestXmlString = 
        jaxbRequestObjectToXMLForWeiXin(orderQueryRequestForWeiXin);
    logger.debug("requestXmlString = ["+requestXmlString+"]");
  
    try{        
      ResponseEntity<String> responseEntiryFromWeiXin = 
          callThirdPartyOrderQueryApi(
              orderQueryRequestForBackendDto.getPlatform(),requestXmlString);
      
      String responseMessageFromWeiXin = responseEntiryFromWeiXin.getBody();       
      logger.debug("=====responseMessageFromWeiXin====================> "+responseMessageFromWeiXin);
      
      Map<String,Object> responseMap = StringUtils.transferXMLtoMap(responseMessageFromWeiXin);
      logger.debug("=====responseMap====================> "+responseMap);
      
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
      List<String> keys_startwith_coupon = 
          xmlFiledsFromWeiXin.stream().filter(st->st.startsWith(PaymentBusinessConstant.COUPON_ID)).collect(Collectors.toList());
      
      if(keys_startwith_coupon.size()>0){
        Integer maxID = 0;
        Integer tempID = 0;
        String coupon_json_string = "";
        
        for(String sk : keys_startwith_coupon)
        {
          tempID = Integer.valueOf(sk.replaceAll(PaymentBusinessConstant.COUPON_ID, ""));
          if(maxID<tempID){
            maxID = tempID;
          }              
        }
        
        for(int i=0;i<maxID;i++)
        {             
          coupon_json_string = coupon_json_string+"{"
              + "\""+PaymentBusinessConstant.COUPON_ID+i+PaymentBusinessConstant.JSONDEL+responseMap.get(PaymentBusinessConstant.COUPON_ID+i)+"\","
                  + "\""+PaymentBusinessConstant.COUPON_TYPE+i+PaymentBusinessConstant.JSONDEL+responseMap.get(PaymentBusinessConstant.COUPON_TYPE+i)+"\","
                      + "\""+PaymentBusinessConstant.COUPON_FEE+i+PaymentBusinessConstant.JSONDEL+responseMap.get(PaymentBusinessConstant.COUPON_FEE+i)+"\"},";
        }        
        orderQueryResponseForWeiXin.setCoupon_list_json_string(customizedCouponJsonString(coupon_json_string));        
      }
     
    }
    
    return orderQueryResponseForWeiXin;    
  }
  
  
  private String customizedCouponJsonString(String coupon_json_string){
    if(!"".equals(coupon_json_string)){
      coupon_json_string = "["+coupon_json_string.substring(0, coupon_json_string.length()-1)+"]";
      return coupon_json_string;
    } 
    return null;
  }
  
  
  public OrderQueryResponseForBackendDto orderQueryAlipayRequestFromBackendSystme(
      OrderQueryRequestForBackendDto orderQueryRequestForBackendDto,OrderQueryResponseForBackendDto orderQueryResponseForBackendDto){
    
    OrderQueryRequestForAliPay orderQueryRequestForAliPay = 
        transferBackendMessageForAliPay(orderQueryRequestForBackendDto);
    
    AlipayTradeQueryResponse aliResponse = orderQueryForAlipay(orderQueryRequestForAliPay);
    logger.debug("===aliResponse.getBody(): "+aliResponse.getBody());
          
    Gson gson=new Gson();
    OrderQueryResponseForAliPay orderQueryResponseForAliPay = gson.fromJson(aliResponse.getBody(), OrderQueryResponseForAliPay.class);
    
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
  
   
  private String getReturn_code(String codeFromAlipay){
    String result = PaymentBusinessConstant.RETURN_CODE_ERROR;
    if(PaymentBusinessConstant.ALIPAY_RETURN_CODE_SUCCESS.equals(codeFromAlipay)){
      result = PaymentBusinessConstant.SUCCESS_MESSAGE;
    }
    return result;    
  }
  
  
  private ResponseEntity<String> callThirdPartyOrderQueryApi(String platform,String requestString){  
    
    RestTemplate rt = new RestTemplate();
    rt.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName(PaymentBusinessConstant.CHARSET_UTF8)));;
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
    result = signatureServiceImpl.weixinSignatureCheck(responseMap);
    
    return result;    
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
