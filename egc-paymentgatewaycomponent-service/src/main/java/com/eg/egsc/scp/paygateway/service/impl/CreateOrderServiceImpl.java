/**
 * @Probject Name: scp-pay-gateway-app-service
 * @Path: com.eg.egsc.scp.paygateway.service.impl.CreateOrderServiceImpl.java
 * @Create By lihui
 * @Create In 2018年2月25日 上午11:09
 * TODO
 */
package com.eg.egsc.scp.paygateway.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.eg.egsc.scp.paygateway.dto.CreateOrderRequestForBackendDto;
import com.eg.egsc.scp.paygateway.dto.CreateOrderResponseForBackendDto;
import com.eg.egsc.scp.paygateway.service.CreateOrderService;
import com.eg.egsc.scp.paygateway.service.SignatureService;
import com.eg.egsc.scp.paygateway.service.model.*;
import com.eg.egsc.scp.paygateway.util.PaymentBusinessConstant;
import com.eg.egsc.scp.paygateway.util.StringUtils;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Map;

/**
 * @Class Name CreateOrderServiceImpl
 * @Author lihui
 * @Create In 2018年2月25日
 */
@Service
public class CreateOrderServiceImpl implements CreateOrderService {

    protected final Logger logger = LoggerFactory.getLogger(CreateOrderService.class);

    @Value("${payment.third.party.wechat.order.create.uri}")
    private String wechatCreateOrderUri;

    @Value("${payment.third.party.alipay.order.create.uri}")
    private String aliPayCreateOrderUri;

    @Value("${payment.third.party.alipay.order.query.private.key}")
    private String aliPayOrderQueryPrivateKey;

    @Value("${payment.third.party.alipay.order.query.public.key}")
    private String aliPayOrderQueryPublicKey;

    @Value("${payment.third.party.alipay.order.create.uri.method}")
    private String aliPayCreateOrderMethod;

    @Value("${payment.error.message.request.platform.out.of.scope}")
    private String requestPlatformOutOfScopeMessage;

    @Value("${payment.error.message.confirm.sign.not.pass}")
    private String confirmSignNotPassMessage;

    @Value("${xml.customized.header}")
    private String xmlCustomizedHeader;

    @Autowired
    private CreateOrderResponseForWeiXin createOrderResponseForWeiXin;

    @Autowired
    private SignatureService signatureServiceImpl;

    @Override
    public CreateOrderRequestForWeiXin transferBackendMessageForWeiXin(CreateOrderRequestForBackendDto createOrderRequestForBackendDto) {
        CreateOrderRequestForWeiXin createOrderRequestForWeiXin = new CreateOrderRequestForWeiXin();
        createOrderRequestForWeiXin.setAppid(createOrderRequestForBackendDto.getAppid());
        createOrderRequestForWeiXin.setMch_id(createOrderRequestForBackendDto.getMch_id());
        createOrderRequestForWeiXin.setDevice_info("WEB");
        createOrderRequestForWeiXin.setNonce_str(getNonce_str());
        createOrderRequestForWeiXin.setSign_type("MD5");
        createOrderRequestForWeiXin.setBody(createOrderRequestForBackendDto.getBody());
        createOrderRequestForWeiXin.setDetail(createOrderRequestForBackendDto.getDetail());
        createOrderRequestForWeiXin.setAttach(createOrderRequestForBackendDto.getAttach());
        createOrderRequestForWeiXin.setOut_trade_no(createOrderRequestForBackendDto.getOut_trade_no());
        createOrderRequestForWeiXin.setFee_type(createOrderRequestForBackendDto.getFee_type());
        createOrderRequestForWeiXin.setTotal_fee(createOrderRequestForBackendDto.getTotal_fee());
        createOrderRequestForWeiXin.setSpbill_create_ip(createOrderRequestForBackendDto.getSpbill_create_ip());
        createOrderRequestForWeiXin.setTime_start(createOrderRequestForBackendDto.getTime_start());
        createOrderRequestForWeiXin.setTime_expire(createOrderRequestForBackendDto.getTime_expire());
        createOrderRequestForWeiXin.setNotify_url("http://www.weixin.qq.com/wxpay/pay.php");
        createOrderRequestForWeiXin.setProduct_id(createOrderRequestForBackendDto.getProduct_id());
        createOrderRequestForWeiXin.setLimit_pay(createOrderRequestForBackendDto.getLimit_pay());
        createOrderRequestForWeiXin.setOpenid(createOrderRequestForBackendDto.getOpenid());
        createOrderRequestForWeiXin.setTrade_type(createOrderRequestForBackendDto.getTrade_type());
        createOrderRequestForWeiXin.setSign(getSign(createOrderRequestForWeiXin));
        return createOrderRequestForWeiXin;
    }

    @Override
    public CreateOrderResponseForBackendDto transferWeiXinMessageForBackendSystme(
            CreateOrderResponseForWeiXin createOrderResponseForWeiXin) {
        CreateOrderResponseForBackendDto createOrderResponseForBackendDto = new CreateOrderResponseForBackendDto();
        createOrderResponseForBackendDto.setPlatform(PaymentBusinessConstant.WEI_XIN);
        createOrderResponseForBackendDto.setResult_code(createOrderResponseForWeiXin.getResult_code());
        createOrderResponseForBackendDto.setReturn_msg(createOrderResponseForWeiXin.getReturn_msg());
        createOrderResponseForBackendDto.setAppid(createOrderResponseForWeiXin.getAppid());
        createOrderResponseForBackendDto.setMch_id(createOrderResponseForWeiXin.getMch_id());
        createOrderResponseForBackendDto.setDevice_info(createOrderResponseForWeiXin.getDevice_info());
        createOrderResponseForBackendDto.setSign(createOrderResponseForWeiXin.getSign());
        createOrderResponseForBackendDto.setResult_code(createOrderResponseForWeiXin.getResult_code());
        createOrderResponseForBackendDto.setErr_code(createOrderResponseForWeiXin.getErr_code());
        createOrderResponseForBackendDto.setErr_code_des(createOrderResponseForWeiXin.getErr_code_des());
        createOrderResponseForBackendDto.setTrade_type(createOrderResponseForWeiXin.getTrade_type());
        createOrderResponseForBackendDto.setPrepay_id(createOrderResponseForWeiXin.getPrepay_id());
        createOrderResponseForBackendDto.setMweb_url(createOrderResponseForWeiXin.getMweb_url());
        createOrderResponseForBackendDto.setReturn_code(createOrderResponseForWeiXin.getReturn_code());
        return createOrderResponseForBackendDto;
    }

    @Override
    public CreateOrderRequestForAliPay transferBackendMessageForAliPay(
            CreateOrderRequestForBackendDto createOrderRequestForBackendDto) {
        CreateOrderRequestForAliPay requestForAliPay = new CreateOrderRequestForAliPay();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp now = new Timestamp(System.currentTimeMillis());
        String timestampForAliPay = df.format(now);
        String biz_content = ""
                + "{\"subject\":\"" + createOrderRequestForBackendDto.getDetail() + "\","
                + "\"out_trade_no\":\"" + createOrderRequestForBackendDto.getOut_trade_no() + "\","
                + "\"total_amount\":\"" + createOrderRequestForBackendDto.getTotal_fee() + "\","
                + "\"product_code\":\"" + "QUICK_MSECURITY_PAY" + "\" }";
        System.out.println("===biz_content: " + biz_content);

        requestForAliPay.setApp_id(createOrderRequestForBackendDto.getAppid());
        requestForAliPay.setMethod(aliPayCreateOrderMethod);
        requestForAliPay.setCharset("utf-8");
        requestForAliPay.setSign_type("RSA2");
        requestForAliPay.setTimestamp(timestampForAliPay);
        requestForAliPay.setVersion("1.0");
        requestForAliPay.setBiz_content(biz_content);
        requestForAliPay.setSign(getSign(requestForAliPay));
        return requestForAliPay;
    }

    @Override
    public CreateOrderResponseForBackendDto transferAliPayMessageForBackendSystme(CreateOrderResponseForAliPay createOrderResponseForAliPay) {
        CreateOrderResponseForBackendDto createOrderResponseForBackendDto = new CreateOrderResponseForBackendDto();
        createOrderResponseForBackendDto.setPlatform(PaymentBusinessConstant.ALI_PAY);
        createOrderResponseForBackendDto.setReturn_code(createOrderResponseForAliPay.getCode());
        createOrderResponseForBackendDto.setReturn_msg(createOrderResponseForAliPay.getMsg());
        if ("SUCCESS".equalsIgnoreCase(createOrderResponseForAliPay.getCode())) {
            createOrderResponseForBackendDto.setSign(createOrderResponseForAliPay.getSign());
            createOrderResponseForBackendDto.setNonce_str(getNonce_str());
            createOrderResponseForBackendDto.setResult_code(createOrderResponseForAliPay.getSub_code());
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
        //for wechat
        if (PaymentBusinessConstant.WEI_XIN.equalsIgnoreCase(createOrderRequestForBackendDto.getPlatform())) {
            CreateOrderRequestForWeiXin createOrderRequestForWeiXin = transferBackendMessageForWeiXin(createOrderRequestForBackendDto);
            String requestXmlString = jaxbRequestObjectToXMLForWeiXin(createOrderRequestForWeiXin);
            logger.info("requestXmlString = [" + requestXmlString + "]");

            try {
                ResponseEntity<String> responseEntiryFromWeiXin = callThirdPartyCreateOrderApi(
                        createOrderRequestForBackendDto.getPlatform(), requestXmlString);
                String messageFromWeiXin = responseEntiryFromWeiXin.getBody();
                logger.debug("=====messageFromWeiXin====================> " + messageFromWeiXin);

                Map<String, Object> responseMap = StringUtils.transferXMLtoMap(messageFromWeiXin, xmlCustomizedHeader);
                logger.debug("=====responseMap====================> " + responseMap);

                if (!confirmSignForWeiXin(responseMap)) {
                    logger.error("Business Exception! The WeiXin Signature Check failed! "
                            + "This responese message stop here and will not pass to payment backend system!");
                    createOrderResponseForBackendDto.setErr_code_des(confirmSignNotPassMessage);
                    return createOrderResponseForBackendDto;
                }
                StringReader sr = new StringReader(messageFromWeiXin);
                JAXBContext jaxbContext = JAXBContext.newInstance(CreateOrderResponseForWeiXin.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                createOrderResponseForWeiXin = (CreateOrderResponseForWeiXin) unmarshaller.unmarshal(sr);
                createOrderResponseForBackendDto =
                        transferWeiXinMessageForBackendSystme(createOrderResponseForWeiXin);
            } catch (HttpClientErrorException e) {
                logger.error("error:  " + e.getResponseBodyAsString());
            } catch (Exception e) {
                logger.error("error:  " + e.getMessage());
            }
        }

        //for alipay
        else if (PaymentBusinessConstant.ALI_PAY
                .equalsIgnoreCase(createOrderRequestForBackendDto.getPlatform())) {
            logger.debug("=====Come into Alipay business logic====================> ");
            CreateOrderRequestForAliPay createOrderRequestForAliPay = transferBackendMessageForAliPay(createOrderRequestForBackendDto);

            AlipayTradeQueryResponse aliResponse = createOrderForAlipay(createOrderRequestForAliPay);
            logger.debug("===aliResponse.getBody(): " + aliResponse.getBody());

            Gson gson = new Gson();
            CreateOrderResponseForAliPay createOrderResponseForAliPay = gson.fromJson(aliResponse.getBody(), CreateOrderResponseForAliPay.class);
            System.out.println(createOrderResponseForAliPay);

            createOrderResponseForBackendDto = transferAliPayMessageForBackendSystme(createOrderResponseForAliPay);

        } else {
            logger.debug("====Request Data Exception! The Platform is not Wechat or Alipay! Nothing will be done here.=====");
            createOrderResponseForBackendDto.setErr_code_des(requestPlatformOutOfScopeMessage);
        }

        return createOrderResponseForBackendDto;
    }

    private boolean confirmSignForWeiXin(Map<String, Object> responseMap) {
        boolean result = false;
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
                new HttpEntity<String>(requestString.toString(), headers);

        String thirdPartyUri = "";
        if (PaymentBusinessConstant.WEI_XIN.equalsIgnoreCase(platform)) {
            thirdPartyUri = wechatCreateOrderUri;
        }
        ResponseEntity<String> responseEntiryFromThirdPartyApi =
                (ResponseEntity<String>) rt.exchange(thirdPartyUri, HttpMethod.POST, formEntity, String.class);

        return responseEntiryFromThirdPartyApi;

    }

    private String getSign(CreateOrderRequestForAliPay createOrderRequestForAliPay) {
        Gson gson = new Gson();
        String createOrderRequestForAliPayJsonString = gson.toJson(createOrderRequestForAliPay);
        logger.info("===createOrderRequestForAliPayJsonString1: [" + createOrderRequestForAliPayJsonString + "]");

//    orderQueryRequestForAliPayJsonString = orderQueryRequestForAliPayJsonString.replaceAll("\\\\", "");
//    logger.info("===orderQueryRequestForAliPayJsonString2: ["+orderQueryRequestForAliPayJsonString+"]");

        Map signatureMapForAliPay =
                (Map) com.alibaba.fastjson.JSONObject.parse(createOrderRequestForAliPayJsonString);

        String paySignature = signatureServiceImpl.alipaySignature(signatureMapForAliPay);

        return paySignature;
    }

    private String getSign(CreateOrderRequestForWeiXin createOrderRequestForWeiXin) {
        Gson gson = new Gson();
        String createOrderForWeiXinJsonString = gson.toJson(createOrderRequestForWeiXin);
        logger.info("createOrderRequestForWeiXin: [" + createOrderRequestForWeiXin + "]");

        Map signatureMap =
                (Map) com.alibaba.fastjson.JSONObject.parse(createOrderForWeiXinJsonString);

        String paySignature = signatureServiceImpl.weixinSignature(signatureMap);

        return paySignature;
    }

    private String getNonce_str() {
        String uuid = StringUtils.generateUuid();
        return uuid;
    }

    public static String jaxbRequestObjectToXMLForWeiXin(CreateOrderRequestForWeiXin createOrderRequestForWeiXin) {
        String xmlString = "";
        try {
            JAXBContext context = JAXBContext.newInstance(CreateOrderRequestForWeiXin.class);
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter sw = new StringWriter();
            m.marshal(createOrderRequestForWeiXin, sw);
            xmlString = sw.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return xmlString;

    }

    private AlipayTradeQueryResponse createOrderForAlipay(CreateOrderRequestForAliPay createOrderRequestForAliPay) {

        AlipayClient alipayClient = new DefaultAlipayClient(
                aliPayCreateOrderUri,
                createOrderRequestForAliPay.getApp_id(),
                aliPayOrderQueryPrivateKey,
                createOrderRequestForAliPay.getFormat(),
                createOrderRequestForAliPay.getCharset(),
                aliPayOrderQueryPublicKey,
                createOrderRequestForAliPay.getSign_type());

        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();

        request.setBizContent(createOrderRequestForAliPay.getBiz_content());
        AlipayTradeQueryResponse response = new AlipayTradeQueryResponse();
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (response.isSuccess()) {
            System.out.println("调用成功。。。");
        } else {
            System.out.println("调用失败。。。");
        }
        return response;

    }
}
