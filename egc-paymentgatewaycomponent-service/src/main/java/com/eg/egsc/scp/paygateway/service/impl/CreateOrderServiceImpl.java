/**
 * @Probject Name: scp-pay-gateway-app-service
 * @Path: com.eg.egsc.scp.paygateway.service.impl.CreateOrderServiceImpl.java
 * @Create By lihui
 * @Create In 2018年2月25日 上午11:09
 * TODO
 */
package com.eg.egsc.scp.paygateway.service.impl;

import com.eg.egsc.scp.paygateway.dto.CreateOrderRequestForBackendDto;
import com.eg.egsc.scp.paygateway.dto.CreateOrderResponseForBackendDto;
import com.eg.egsc.scp.paygateway.service.CreateOrderService;
import com.eg.egsc.scp.paygateway.service.OrderQueryService;
import com.eg.egsc.scp.paygateway.service.SignatureService;
import com.eg.egsc.scp.paygateway.service.model.CreateOrderRequestForWeiXin;
import com.eg.egsc.scp.paygateway.service.model.CreateOrderResponseForWeiXin;
import com.eg.egsc.scp.paygateway.service.model.OrderQueryRequestForWeiXin;
import com.eg.egsc.scp.paygateway.service.model.OrderQueryResponseForWeiXin;
import com.eg.egsc.scp.paygateway.util.PaymentBusinessConstant;
import com.eg.egsc.scp.paygateway.util.StringUtils;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
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

    @Autowired
    private CreateOrderRequestForWeiXin createOrderRequestForWeiXin;

    @Autowired
    private CreateOrderResponseForWeiXin createOrderResponseForWeiXin;

    @Autowired
    private CreateOrderResponseForBackendDto createOrderResponseForBackendDto;

    @Autowired
    private SignatureService signatureServiceImpl;

    @Autowired
    private OrderQueryService orderQueryServiceImpl;

    @Override
    public CreateOrderRequestForWeiXin transferBackendMessageForWeiXin(CreateOrderRequestForBackendDto createOrderRequestForBackendDto) {
        createOrderRequestForWeiXin.setAppid(createOrderRequestForBackendDto.getAppid());
        createOrderRequestForWeiXin.setMch_id(createOrderRequestForBackendDto.getMch_id());
        createOrderRequestForWeiXin.setDevice_info("WEB");
        createOrderRequestForWeiXin.setNonce_str(getNonce_str());
        createOrderRequestForWeiXin.setSign(getSign(createOrderRequestForWeiXin));
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
        createOrderRequestForWeiXin.setNotify_url("http://");
        createOrderRequestForWeiXin.setProduct_id(createOrderRequestForBackendDto.getProduct_id());
        createOrderRequestForWeiXin.setLimit_pay(createOrderRequestForBackendDto.getLimit_pay());
        createOrderRequestForWeiXin.setOpenid(createOrderRequestForBackendDto.getOpenid());
        return createOrderRequestForWeiXin;
    }

    @Override
    public CreateOrderResponseForBackendDto transferWeiXinMessageForBackendSystme(
                            CreateOrderResponseForWeiXin createOrderResponseForWeiXin) {
        return null;
    }

    /**
     *
     * @param createOrderRequestForBackendDto 缴费后台创建支付订单的数据对象
     * @return CreateOrderResponseForBackendDto 返回给缴费后台的数据对象
     */
    @Override
    public CreateOrderResponseForBackendDto createOrderRequestFromBackendSystme(CreateOrderRequestForBackendDto createOrderRequestForBackendDto) {
        if(createOrderRequestForBackendDto == null){
            String errorMsg = "Dto from backend system request for order query is null!";
            logger.error(errorMsg);
            return null;
        }
        //for wechat
        if(PaymentBusinessConstant.WEI_XIN.equalsIgnoreCase(createOrderRequestForBackendDto.getPlatform())){
            createOrderRequestForWeiXin = transferBackendMessageForWeiXin(createOrderRequestForBackendDto);
            String requestXmlString = jaxbRequestObjectToXMLForWeiXin(createOrderRequestForWeiXin);
            logger.info("requestXmlString = ["+requestXmlString+"]");

            try {
                ResponseEntity<String> responseEntiryFromWeiXin = callThirdPartyCreateOrderApi(
                        createOrderRequestForBackendDto.getPlatform(),requestXmlString);
                String messageFromWeiXin = responseEntiryFromWeiXin.getBody();

                logger.debug("=====messageFromWeiXin====================> "+messageFromWeiXin);

                StringReader sr = new StringReader(messageFromWeiXin);
                JAXBContext jaxbContext = JAXBContext.newInstance(OrderQueryResponseForWeiXin.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                createOrderResponseForWeiXin = (CreateOrderResponseForWeiXin) unmarshaller.unmarshal(sr);

                if(confirmSignForWeiXin(createOrderResponseForWeiXin)){
                    createOrderResponseForBackendDto =
                            transferWeiXinMessageForBackendSystme(createOrderResponseForWeiXin);
                }else{
                    logger.error("Business Exception! The Signature Check failed! "
                            + "This responese message stop here and will not pass to payment backend system!");
                    return null;
                }
            } catch(HttpClientErrorException e){
                logger.error("error:  " + e.getResponseBodyAsString());
            }catch(Exception e){
                logger.error("error:  " + e.getMessage());
            }
        }

        //for alipay
        else if(PaymentBusinessConstant.ALI_PAY
                .equalsIgnoreCase(createOrderRequestForBackendDto.getPlatform())){
            logger.debug("=====Come into Alipay business logic====================> ");

        }

        return createOrderResponseForBackendDto;
    }

    private boolean confirmSignForWeiXin(CreateOrderResponseForWeiXin createOrderResponseForWeiXin){
        boolean result = false;
        Gson gson = new Gson();
        String stringReturnFromWeiXin = gson.toJson(createOrderResponseForWeiXin);
        logger.info("stringMessageReturnFromWeiXin: ["+stringReturnFromWeiXin+"]");

        Map signatureCheckMap =
                (Map) com.alibaba.fastjson.JSONObject.parse(stringReturnFromWeiXin);

        result = signatureServiceImpl.weixinSigantureCheck(signatureCheckMap);

        return result;
    }

    private ResponseEntity<String> callThirdPartyCreateOrderApi(String platform, String requestString){
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/xml; charset=utf-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_XML_VALUE);

        HttpEntity<String> formEntity =
                new HttpEntity<String>(requestString.toString(), headers);

        String thirdPartyUri = "";
        if(PaymentBusinessConstant.WEI_XIN.equalsIgnoreCase(platform)){
            thirdPartyUri = wechatCreateOrderUri;
        }else if(PaymentBusinessConstant.ALI_PAY.equalsIgnoreCase(platform)){
            thirdPartyUri = aliPayCreateOrderUri;
        }
        ResponseEntity<String> responseEntiryFromThirdPartyApi =
                (ResponseEntity<String>) rt.exchange(thirdPartyUri, HttpMethod.POST, formEntity, String.class);

        return responseEntiryFromThirdPartyApi;

    }

    private String getSign(CreateOrderRequestForWeiXin createOrderRequestForWeiXin){
        Gson gson = new Gson();
        String createOrderForWeiXinJsonString = gson.toJson(createOrderRequestForWeiXin);
        logger.info("createOrderRequestForWeiXin: ["+createOrderRequestForWeiXin+"]");

        Map signatureMap =
                (Map) com.alibaba.fastjson.JSONObject.parse(createOrderForWeiXinJsonString);

        String paySignature = signatureServiceImpl.weixinSignature(signatureMap);

        return paySignature;
    }

    private String getNonce_str(){
        String uuid = StringUtils.generateUuid();
        return uuid;
    }

    public static String jaxbRequestObjectToXMLForWeiXin(CreateOrderRequestForWeiXin createOrderRequestForWeiXin){
        String xmlString = "";
        try{
            JAXBContext context = JAXBContext.newInstance(CreateOrderRequestForWeiXin.class);
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter sw = new StringWriter();
            m.marshal(createOrderRequestForWeiXin, sw);
            xmlString = sw.toString();

        }catch(JAXBException e){
            e.printStackTrace();
        }

        return xmlString;

    }
}
