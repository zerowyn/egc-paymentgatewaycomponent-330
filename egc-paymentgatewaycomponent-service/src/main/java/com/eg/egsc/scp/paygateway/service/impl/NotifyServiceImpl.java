/**
 * @Probject Name: scp-pay-gateway-app-service
 * @Path: com.eg.egsc.scp.paygateway.service.implNotifyServiceImpl.java
 * @Create By fandong
 * @Create In 2018年2月11日 TODO
 */
package com.eg.egsc.scp.paygateway.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.eg.egsc.egc.paymentbackendsystem.client.PaymentResultInformClient;
import com.eg.egsc.egc.paymentbackendsystem.dto.ResultInformDto;
import com.eg.egsc.egc.paymentbackendsystem.dto.ResultInformResponseDto;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.scp.paygateway.service.NotifyService;
import com.eg.egsc.scp.paygateway.service.SignatureService;
import com.eg.egsc.scp.paygateway.service.model.WeiXinNotifyResponse;
import com.eg.egsc.scp.paygateway.util.ObjecTransformXML;
import com.eg.egsc.scp.paygateway.util.PaymentBusinessConstant;
import com.eg.egsc.scp.paygateway.util.StringNameConversionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
            String s = StringNameConversionUtils.StringNameConversion(entry.getKey());
            newMap.put(s, entry.getValue());
        }
        return newMap;
    }


    @Override
    public String disposeMessage(Map<String, Object> map, Boolean b) {
        boolean flag;
        if (b) {
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
        if (flag) {
            String json = JSONObject.toJSONString(newMap);
            json.replaceAll("\\{", "[");
            json.replaceAll("}", "]");

            resultInformDto = JSONObject.parseObject(json, ResultInformDto.class);
        }
        // 调用后台接口回传数据
        ResponseDto dto = paymentResultInformClientImpl.getNotify(resultInformDto);

        if (!(ObjectUtils.isEmpty(dto) && ObjectUtils.isEmpty(dto.getData()))) {
            logger.error("The received message is erro.");
        }

        ResultInformResponseDto notify = JSONObject.parseObject(JSONObject.toJSONString(dto.getData()), ResultInformResponseDto.class);
        String returnMessage = null;
        if (b) {
            // 微信返回数据
            WeiXinNotifyResponse weiXinNotifyResponse = new WeiXinNotifyResponse();
            if (notify.getReturnCode().equalsIgnoreCase(PaymentBusinessConstant.SUCCESS_MESSAGE)) {
                weiXinNotifyResponse.setReturnCode(PaymentBusinessConstant.SUCCESS_MESSAGE);
                weiXinNotifyResponse.setReturnMsg("OK");
            } else {
                weiXinNotifyResponse.setReturnCode("FAIL");
                weiXinNotifyResponse.setReturnMsg("签名失败");
            }
            // 组装返回第三方支付平台的数据
            returnMessage = ObjecTransformXML.jaxbRequestObjectToXMLForWeiXin(weiXinNotifyResponse);

        } else {
            // 支付宝返回数据
            if (notify.getReturnCode().equalsIgnoreCase(PaymentBusinessConstant.SUCCESS_MESSAGE)) {
                returnMessage = PaymentBusinessConstant.SUCCESS_MESSAGE;
            }
        }
        return returnMessage;
    }
}




