/**
 * @Probject Name: scp-pay-gateway-app-service
 * @Path: com.eg.egsc.scp.paygateway.service.implNotifyServiceImpl.java
 * @Create By fandong
 * @Create In 2018年2月11日 上午10:40:35
 */
package com.eg.egsc.scp.paygateway.service.impl;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.eg.egsc.scp.paygateway.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson.JSONObject;
import com.eg.egsc.egc.paymentbackendsystem.client.PaymentResultInformClient;
import com.eg.egsc.egc.paymentbackendsystem.dto.ResultInformDto;
import com.eg.egsc.egc.paymentbackendsystem.dto.ResultInformResponseDto;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.scp.paygateway.dto.AlipayResultDto;
import com.eg.egsc.scp.paygateway.service.NotifyService;
import com.eg.egsc.scp.paygateway.service.SignatureService;
import com.eg.egsc.scp.paygateway.service.model.WeiXinNotifyResponse;

/**
 * @Class Name NotifyServiceImpl
 * @Author fandong
 * @Create In 2018年2月11日
 */
@Service
public class NotifyServiceImpl implements NotifyService {

    private static final Logger logger = LoggerFactory.getLogger(NotifyServiceImpl.class);

    @Autowired
    private PaymentResultInformClient paymentResultInformClientImpl;

    @Autowired
    private SignatureService signatureServiceImpl;

    @Value("${xml.customized.header}")
    private String header;

    /**
     * 字符串转换
     *
     * @param map
     * @return
     */
    private Map<String, Object> stringNameConversion(Map<String, Object> map) {
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        Map<String, Object> newMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : entries) {
            String s = StringNameConversionUtils.stringConversion(entry.getKey());
            newMap.put(s, entry.getValue());
        }
        return newMap;
    }


    @Override
    public String disposeMessage(Map<String, Object> map, Boolean sign) {
        Boolean flag;
        if (sign) {
            // 微信验签
            logger.info("Starting attestation");
            flag = signatureServiceImpl.weixinSignatureCheck(map);
            logger.info("WeChat test result is:{}", flag);
        } else {
            // 支付宝验签
            logger.info("The inspection of alipay.");
            flag = signatureServiceImpl.alipaySignatureAsyCheck(map);
            logger.info("The alipay inspection result is:{}", flag);
        }
        // 验签成功后转换字段
        Map<String, Object> newMap = this.stringNameConversion(map);
        ResultInformDto resultInformDto = null;
        if (!flag) {
            return "Attestation of failure";
        }
        if (sign) {
            String json = JSONObject.toJSONString(newMap).replaceAll("\\[", "{").replaceAll("]", "}");
            String conversion = ConversionUtils.conversion(json);
            //json转换实体类
            resultInformDto = JSONObject.parseObject(conversion, ResultInformDto.class);
            resultInformDto.setPlatform(PaymentBusinessConstant.WEI_XIN);
        } else {
            String json = JSONObject.toJSONString(newMap).replaceAll("\\[", "{").replaceAll("]", "}");
            String conversion = ConversionUtils.conversion(json);
            AlipayResultDto jsonObject = JSON.parseObject(conversion + "}", AlipayResultDto.class);
            resultInformDto = DtoConversionUtils.conversion(jsonObject);
        }
        // 调用后台接口回传数据
        ResponseDto dto = paymentResultInformClientImpl.getNotify(resultInformDto);
        if (ObjectUtils.isEmpty(dto) || ObjectUtils.isEmpty(dto.getData())) {
            logger.error("The received message is erro.");
            return "The message received was empty.";
        }
        ResultInformResponseDto notify = JSONObject.parseObject(JSONObject.toJSONString(dto.getData()), ResultInformResponseDto.class);
        String returnMessage = "";
        if (sign) {
            // 微信返回数据
            WeiXinNotifyResponse weiXinNotifyResponse = new WeiXinNotifyResponse();
            if (notify.getReturnCode().equalsIgnoreCase(PaymentBusinessConstant.COMMON_FRAMEWORK_SUCCESS_CODE)) {
                weiXinNotifyResponse.setReturnCode(PaymentBusinessConstant.SUCCESS_MESSAGE);
                weiXinNotifyResponse.setReturnMsg("OK");
            } else {
                weiXinNotifyResponse.setReturnCode("FAIL");
                weiXinNotifyResponse.setReturnMsg("保存信息失败");
            }
            // 组装返回第三方支付平台的数据
            returnMessage = ObjecTransformXML.jaxbRequestObjectToXMLForWeiXin(weiXinNotifyResponse).replace(header, "").trim();
        } else {
            // 支付宝返回数据
            if (notify.getReturnCode().equalsIgnoreCase(PaymentBusinessConstant.COMMON_FRAMEWORK_SUCCESS_CODE)) {
                returnMessage = PaymentBusinessConstant.SUCCESS_MESSAGE;
            }else{
                returnMessage = "The data returned by alipay is empty.";
            }
        }
        return returnMessage;
    }
}


