/**
 * @Probject Name: scp-pay-gateway-app-service
 * @Path: com.eg.egsc.scp.paygateway.service.impl.CreateOrderServiceImpl.java
 * @Create By lihui
 * @Create In 2018年2月25日 上午11:09
 */
package com.eg.egsc.scp.paygateway.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.eg.egsc.scp.paygateway.dto.CreateOrderRequestForBackendDto;
import com.eg.egsc.scp.paygateway.dto.CreateOrderResponseForBackendDto;
import com.eg.egsc.scp.paygateway.exception.PaymentGatewayException;
import com.eg.egsc.scp.paygateway.service.ConfigsService;
import com.eg.egsc.scp.paygateway.service.CreateOrderService;
import com.eg.egsc.scp.paygateway.service.DefValSettingsService;
import com.eg.egsc.scp.paygateway.service.SignatureService;
import com.eg.egsc.scp.paygateway.service.model.*;
import com.eg.egsc.scp.paygateway.util.ErrorCodeConstant;
import com.eg.egsc.scp.paygateway.util.PaymentBusinessConstant;
import com.eg.egsc.scp.paygateway.util.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Class Name CreateOrderServiceImpl
 * @Author lihui
 * @Create In 2018年2月25日
 */
@Service
public class CreateOrderServiceImpl implements CreateOrderService {

    protected static final Logger logger = LoggerFactory.getLogger(CreateOrderService.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${payment.error.message.request.platform.out.of.scope}")
    private String requestPlatformOutOfScopeMessage;
    @Value("${payment.error.message.confirm.sign.not.pass}")
    private String confirmSignNotPassMessage;

    @Autowired
    private CreateOrderResponseForWeiXin createOrderResponseForWeiXin;

    @Autowired
    private SignatureService signatureServiceImpl;

    @Autowired
    private ConfigsService configsServiceImpl;

    @Autowired
    private DefValSettingsService defValSettingsServiceImpl;

    private String wechatCreateOrderUri;
    private String aliPayCreateOrderUri;
    private String aliPayOrderQueryPrivateKey;
    private String aliPayOrderQueryPublicKey;
    private String aliPayCreateOrderMethod;
    private String productCode;
    private String aliPayOrderQueryFormat;
    private String aliPayOrderQueryCharset;
    private String aliPayOrderQueryVersion;
    private String aliPaySignType;
    private String aliPayNotifyUrl;
    private String weiXinNotifyUrl;
    private String weiXinDeviceInfo;

    @Override
    public CreateOrderRequestForWeiXin transferBackendMessageForWeiXin(CreateOrderRequestForBackendDto createOrderRequestForBackendDto) {
        CreateOrderRequestForWeiXin createOrderRequestForWeiXin = new CreateOrderRequestForWeiXin();
        createOrderRequestForWeiXin.setAppid(createOrderRequestForBackendDto.getAppid());
        createOrderRequestForWeiXin.setMchId(createOrderRequestForBackendDto.getMchId());
        createOrderRequestForWeiXin.setDeviceInfo(weiXinDeviceInfo);
        createOrderRequestForWeiXin.setNonceStr(getNonceStr());
        createOrderRequestForWeiXin.setSignType(PaymentBusinessConstant.SIGN_TYPE_MD5);
        createOrderRequestForWeiXin.setBody(createOrderRequestForBackendDto.getBody());
        createOrderRequestForWeiXin.setDetail(createOrderRequestForBackendDto.getDetail());
        createOrderRequestForWeiXin.setAttach(createOrderRequestForBackendDto.getAttach());
        createOrderRequestForWeiXin.setOutTradeNo(createOrderRequestForBackendDto.getOutTradeNo());
        createOrderRequestForWeiXin.setFeeType(createOrderRequestForBackendDto.getFeeType());
        createOrderRequestForWeiXin.setTotalFee(createOrderRequestForBackendDto.getTotalFee());
        createOrderRequestForWeiXin.setTimeStart(createOrderRequestForBackendDto.getTimeStart());
        createOrderRequestForWeiXin.setTimeExpire(createOrderRequestForBackendDto.getTimeExpire());
        createOrderRequestForWeiXin.setNotifyUrl(weiXinNotifyUrl);
        createOrderRequestForWeiXin.setProductId(createOrderRequestForBackendDto.getProductId());
        createOrderRequestForWeiXin.setLimitPay(createOrderRequestForBackendDto.getLimitPay());
        createOrderRequestForWeiXin.setTradeType(createOrderRequestForBackendDto.getTradeType());
        createOrderRequestForWeiXin.setSpbillCreateIp(createOrderRequestForBackendDto.getSpbillCreateIp());
        createOrderRequestForWeiXin.setSign(getSign(createOrderRequestForWeiXin));
        return createOrderRequestForWeiXin;
    }

    @Override
    public CreateOrderResponseForBackendDto transferWeiXinMessageForBackendSystme(
            CreateOrderResponseForWeiXin createOrderResponseForWeiXin) {
        CreateOrderResponseForBackendDto createOrderResponseForBackendDto = new CreateOrderResponseForBackendDto();

        createOrderResponseForBackendDto.setPlatform(PaymentBusinessConstant.WEI_XIN);
        createOrderResponseForBackendDto.setReturnCode(createOrderResponseForWeiXin.getReturnCode());
        createOrderResponseForBackendDto.setReturnMsg(createOrderResponseForWeiXin.getReturnMsg());
        if (PaymentBusinessConstant.SUCCESS_MESSAGE.equalsIgnoreCase(createOrderResponseForWeiXin.getReturnCode())) {
            createOrderResponseForBackendDto.setAppid(createOrderResponseForWeiXin.getAppid());
            createOrderResponseForBackendDto.setPartnerid(createOrderResponseForWeiXin.getMchId());
            createOrderResponseForBackendDto.setDeviceInfo(createOrderResponseForWeiXin.getDeviceInfo());
            createOrderResponseForBackendDto.setResultCode(createOrderResponseForWeiXin.getResultCode());
            createOrderResponseForBackendDto.setErrCode(createOrderResponseForWeiXin.getErrCode());
            createOrderResponseForBackendDto.setErrCodeDes(createOrderResponseForWeiXin.getErrCodeDes());
        }
        if (PaymentBusinessConstant.SUCCESS_MESSAGE.equalsIgnoreCase(createOrderResponseForWeiXin.getReturnCode()) &&
                (PaymentBusinessConstant.SUCCESS_MESSAGE.equalsIgnoreCase(createOrderResponseForWeiXin.getResultCode()))) {
            createOrderResponseForBackendDto.setTradeType(createOrderResponseForWeiXin.getTradeType());
            createOrderResponseForBackendDto.setPrepayid(createOrderResponseForWeiXin.getPrepayId());
            String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
            createOrderResponseForBackendDto.setTimestamp(timeStamp);
            createOrderResponseForBackendDto.setPackageValue(PaymentBusinessConstant.PACKAGE);
            createOrderResponseForBackendDto.setNoncestr(createOrderResponseForWeiXin.getNonceStr());
        }
        createOrderResponseForBackendDto.setSign(getBackSign(createOrderResponseForBackendDto));
        return createOrderResponseForBackendDto;
    }

    @Override
    public CreateOrderRequestForAliPay transferBackendMessageForAliPay(
            CreateOrderRequestForBackendDto createOrderRequestForBackendDto) {
        CreateOrderRequestForAliPay requestForAliPay = new CreateOrderRequestForAliPay();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp now = new Timestamp(System.currentTimeMillis());
        String timestampForAliPay = df.format(now);
        if(!"APP".equalsIgnoreCase(createOrderRequestForBackendDto.getTradeType())){
            productCode = "QUICK_WAP_WAY";
            aliPayCreateOrderMethod = "alipay.trade.wap.pay";
        }
        String bizContent = ""
                + "{\"out_trade_no\":\"" + createOrderRequestForBackendDto.getOutTradeNo() + "\","
                + "\"product_code\":\"" + productCode + "\","
                + "\"total_amount\":\"" + createOrderRequestForBackendDto.getTotalFee() + "\","
                + "\"subject\":\"" + createOrderRequestForBackendDto.getDetail() + "\"}";
        requestForAliPay.setAppId(createOrderRequestForBackendDto.getAppid());
        requestForAliPay.setMethod(aliPayCreateOrderMethod);
        requestForAliPay.setCharset(aliPayOrderQueryCharset);
        requestForAliPay.setSignType(aliPaySignType);
        requestForAliPay.setTimestamp(timestampForAliPay);
        requestForAliPay.setVersion(aliPayOrderQueryVersion);
        requestForAliPay.setBizContent(bizContent);
        requestForAliPay.setFormat(aliPayOrderQueryFormat);
        requestForAliPay.setNotifyUrl(aliPayNotifyUrl);
        return requestForAliPay;
    }

    @Override
    public CreateOrderResponseForBackendDto transferAliPayMessageForBackendSystme(AlipayTradeAppPayResponse alipayTradeAppPayResponse) {
        CreateOrderResponseForBackendDto createOrderResponseForBackendDto = new CreateOrderResponseForBackendDto();
        createOrderResponseForBackendDto.setPlatform(PaymentBusinessConstant.ALI_PAY);
        if (alipayTradeAppPayResponse.isSuccess()) {
            createOrderResponseForBackendDto.setReturnCode(PaymentBusinessConstant.SUCCESS_MESSAGE);
            createOrderResponseForBackendDto.setReturnMsg("支付宝下单请求成功！");
            createOrderResponseForBackendDto.setResultCode(PaymentBusinessConstant.SUCCESS_MESSAGE);
            createOrderResponseForBackendDto.setOrderStr(alipayTradeAppPayResponse.getBody());
        } else {
            createOrderResponseForBackendDto.setReturnCode("FIAL");
            createOrderResponseForBackendDto.setReturnMsg("支付宝下单请求失败！");
        }
        return createOrderResponseForBackendDto;
    }

    /**
     * @param createOrderRequestForBackendDto 缴费后台创建支付订单的数据对象
     * @return CreateOrderResponseForBackendDto 返回给缴费后台的数据对象
     */
    @Override
    public CreateOrderResponseForBackendDto createOrderRequestFromBackendSystme(CreateOrderRequestForBackendDto createOrderRequestForBackendDto) {
        CreateOrderResponseForBackendDto createOrderResponseForBackendDto = new CreateOrderResponseForBackendDto();
        if (createOrderRequestForBackendDto == null) {
            String errorMsg = "Dto from backend system request for order query is null!";
            logger.error(errorMsg);
            return null;
        }
        assignVariables();
        //for wechat
        if (PaymentBusinessConstant.WEI_XIN.equalsIgnoreCase(createOrderRequestForBackendDto.getPlatform())) {
            CreateOrderRequestForWeiXin createOrderRequestForWeiXin = transferBackendMessageForWeiXin(createOrderRequestForBackendDto);
            try {
                String requestXmlString = objectMapper.writeValueAsString(createOrderRequestForWeiXin);
                JSONObject jsonObject = JSONObject.fromObject(requestXmlString);
                requestXmlString = StringUtils.getJson2Xml(jsonObject);
                logger.info("requestXmlString = [" + requestXmlString + "]");
                ResponseEntity<String> responseEntiryFromWeiXin = callThirdPartyCreateOrderApi(
                        createOrderRequestForBackendDto.getPlatform(), requestXmlString);
                String messageFromWeiXin = responseEntiryFromWeiXin.getBody();
                logger.debug("=====messageFromWeiXin====================> " + messageFromWeiXin);
                Map<String, Object> responseMap = StringUtils.transferXMLtoMap(messageFromWeiXin);
                logger.debug("=====responseMap====================> " + responseMap);
                if (PaymentBusinessConstant.SUCCESS_MESSAGE.equalsIgnoreCase((String) responseMap.get(PaymentBusinessConstant.RETURN_CODE)) && !confirmSignForWeiXin(responseMap)) {
                    logger.error("Business Exception! The WeiXin Signature Check failed! "
                            + "This responese message stop here and will not pass to payment backend system!");
                    createOrderResponseForBackendDto.setErrCodeDes(confirmSignNotPassMessage);
                    return createOrderResponseForBackendDto;
                } else if (!PaymentBusinessConstant.SUCCESS_MESSAGE.equalsIgnoreCase((String) responseMap.get(PaymentBusinessConstant.RETURN_CODE))) {
                    createOrderResponseForBackendDto.setErrCodeDes((String) responseMap.get("return_msg"));
                    return createOrderResponseForBackendDto;
                }
                String jsonStr = objectMapper.writeValueAsString(responseMap);
                createOrderResponseForWeiXin = objectMapper.readValue(jsonStr, CreateOrderResponseForWeiXin.class);
                createOrderResponseForBackendDto =
                        transferWeiXinMessageForBackendSystme(this.createOrderResponseForWeiXin);
            } catch (Exception e) {
                logger.error("error:  " + e.getMessage());
                throw new PaymentGatewayException(ErrorCodeConstant.WEIXIN_CREATE_ORDER_ERROR);
            }
        }

        //For Alipay
        else if (PaymentBusinessConstant.ALI_PAY
                .equalsIgnoreCase(createOrderRequestForBackendDto.getPlatform())) {
            logger.debug("=====Come into Alipay business logic====================> ");
            CreateOrderRequestForAliPay createOrderRequestForAliPay = transferBackendMessageForAliPay(createOrderRequestForBackendDto);
            AlipayTradeAppPayResponse aliResponse = createOrderForAlipay(createOrderRequestForAliPay);
            logger.debug("===aliResponse.getBody(): " + aliResponse.getBody());
            createOrderResponseForBackendDto = transferAliPayMessageForBackendSystme(aliResponse);
        } else {
            logger.debug("====Request Data Exception! The Platform is not Wechat or Alipay! Nothing will be done here.=====");
            createOrderResponseForBackendDto.setErrCodeDes(requestPlatformOutOfScopeMessage);
        }
        return createOrderResponseForBackendDto;
    }

    private boolean confirmSignForWeiXin(Map<String, Object> responseMap) {
        boolean result;
        logger.info("responseMap : [" + responseMap + "]");
        if (responseMap == null || responseMap.isEmpty()) {
            logger.error("responseMap is NULL or empty, the signanture check is failed.");
            return false;
        }
        result = signatureServiceImpl.weixinSignatureCheck(responseMap);
        return result;
    }

    private ResponseEntity<String> callThirdPartyCreateOrderApi(String platform, String requestString) {
        RestTemplate rt = new RestTemplate();
        rt.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/xml; charset=utf-8");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_ATOM_XML));
        headers.setContentType(type);
        HttpEntity<String> formEntity =
                new HttpEntity<>(requestString, headers);
        String thirdPartyUri = "";
        if (PaymentBusinessConstant.WEI_XIN.equalsIgnoreCase(platform)) {
            thirdPartyUri = wechatCreateOrderUri;
        }
        return rt.exchange(thirdPartyUri, HttpMethod.POST, formEntity, String.class);

    }

    private String getSign(CreateOrderRequestForWeiXin createOrderRequestForWeiXin) {
        String createOrderForWeiXinJsonString;
        try {
            createOrderForWeiXinJsonString = new ObjectMapper().writeValueAsString(createOrderRequestForWeiXin);
        } catch (JsonProcessingException e) {
            logger.info(e.getMessage());
            throw new PaymentGatewayException(ErrorCodeConstant.ABNORMAL_CONVERSION);
        }
        logger.info("createOrderRequestForWeiXinBefore: [" + createOrderRequestForWeiXin + "]");
        Map signatureMap =
                (Map) com.alibaba.fastjson.JSONObject.parse(createOrderForWeiXinJsonString);
        return signatureServiceImpl.weixinSignature(signatureMap);
    }

    private String getBackSign(CreateOrderResponseForBackendDto createOrderResponseForBackendDto) {
        Map<String, Object> signatureMap = new HashMap<>();
        signatureMap.put("appid", createOrderResponseForBackendDto.getAppid());
        signatureMap.put("partnerid", createOrderResponseForBackendDto.getPartnerid());
        signatureMap.put("timestamp", createOrderResponseForBackendDto.getTimestamp());
        signatureMap.put("package", createOrderResponseForBackendDto.getPackageValue());
        signatureMap.put("prepayid", createOrderResponseForBackendDto.getPrepayid());
        signatureMap.put("noncestr", createOrderResponseForBackendDto.getNoncestr());
        return signatureServiceImpl.weixinSignature(signatureMap);
    }

    private String getNonceStr() {
        return StringUtils.generateUuid();
    }

    private AlipayTradeAppPayResponse createOrderForAlipay(CreateOrderRequestForAliPay createOrderRequestForAliPay) {
        AlipayClient alipayClient = new DefaultAlipayClient(
                aliPayCreateOrderUri,
                createOrderRequestForAliPay.getAppId(),
                aliPayOrderQueryPrivateKey,
                createOrderRequestForAliPay.getFormat(),
                createOrderRequestForAliPay.getCharset(),
                aliPayOrderQueryPublicKey,
                createOrderRequestForAliPay.getSignType());
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        request.setNotifyUrl(createOrderRequestForAliPay.getNotifyUrl());
        request.setApiVersion(createOrderRequestForAliPay.getVersion());
        request.setBizContent(createOrderRequestForAliPay.getBizContent());
        AlipayTradeAppPayResponse response;
        try {
            response = alipayClient.sdkExecute(request);
        } catch (AlipayApiException e) {
            logger.error(e.getMessage());
            throw new PaymentGatewayException(ErrorCodeConstant.CREATE_ORDER_FOR_ALIPAY);
        }
        if (response.isSuccess()) {
            logger.info("支付宝成功！");
        } else {
            logger.info("支付宝失败！");
        }
        return response;

    }

    private void assignVariables() {
        wechatCreateOrderUri = configsServiceImpl.getConfigsValueByExample("WEIXIN-URL", PaymentBusinessConstant.CREATE_METHOD);
        aliPayCreateOrderUri = configsServiceImpl.getConfigsValueByExample("ALIPAY-URL", PaymentBusinessConstant.CREATE_METHOD);
        aliPayOrderQueryPrivateKey = configsServiceImpl.getConfigsValueByExample("KEY", "ALIPAY-APP-PRIVATE");
        aliPayOrderQueryPublicKey = configsServiceImpl.getConfigsValueByExample("KEY", "ALIPAY-PUBLIC");
        aliPayCreateOrderMethod = defValSettingsServiceImpl.getDefValSettingsValueByExample(PaymentBusinessConstant.ALI_PAY, PaymentBusinessConstant.CREATE_METHOD, "method");
        productCode = defValSettingsServiceImpl.getDefValSettingsValueByExample(PaymentBusinessConstant.ALI_PAY, PaymentBusinessConstant.CREATE_METHOD, "product_code");
        aliPayOrderQueryFormat = defValSettingsServiceImpl.getDefValSettingsValueByExample(PaymentBusinessConstant.ALI_PAY, "format");
        aliPayOrderQueryCharset = defValSettingsServiceImpl.getDefValSettingsValueByExample(PaymentBusinessConstant.ALI_PAY, "charset");
        aliPayOrderQueryVersion = defValSettingsServiceImpl.getDefValSettingsValueByExample(PaymentBusinessConstant.ALI_PAY, "version");
        aliPaySignType = defValSettingsServiceImpl.getDefValSettingsValueByExample(PaymentBusinessConstant.ALI_PAY, "sign_type");
        aliPayNotifyUrl = defValSettingsServiceImpl.getDefValSettingsValueByExample(PaymentBusinessConstant.ALI_PAY, PaymentBusinessConstant.CREATE_METHOD, "notify_url");
        weiXinNotifyUrl = defValSettingsServiceImpl.getDefValSettingsValueByExample(PaymentBusinessConstant.WEI_XIN, PaymentBusinessConstant.CREATE_METHOD, "notify_url");
        weiXinDeviceInfo = defValSettingsServiceImpl.getDefValSettingsValueByExample(PaymentBusinessConstant.WEI_XIN, PaymentBusinessConstant.CREATE_METHOD, "device_info");
    }
}
