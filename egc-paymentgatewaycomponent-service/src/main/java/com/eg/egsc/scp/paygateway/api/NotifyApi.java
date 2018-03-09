/**
 * @Probject Name: scp-pay-gateway-app-service
 * @Path: com.eg.egsc.scp.paygateway.NotifyApi.java
 * @Create By fandong
 * @Create In 2018年2月25日 上午10:30:08
 */
package com.eg.egsc.scp.paygateway.api;

import com.alibaba.fastjson.JSONObject;
import com.eg.egsc.framework.service.base.api.BaseApiController;
import com.eg.egsc.scp.paygateway.service.model.WeiXinNotifyResponse;
import com.eg.egsc.scp.paygateway.util.ObjecTransformXML;
import com.eg.egsc.scp.paygateway.util.PaymentBusinessConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.eg.egsc.scp.paygateway.service.NotifyService;
import com.eg.egsc.scp.paygateway.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Class Name NotifyApi
 * @Author fandong
 * @Create In 2018年2月25日
 */
@Api(value = "支付网关通知缴费结果相关api")
@RestController
@RequestMapping(value = "/pay")
public class NotifyApi extends BaseApiController {

    @Autowired
    NotifyService notifyServiceImpl;

    @Value("${xml.customized.header}")
    private String xmlCustomizedHeader;

    protected final Logger logger = LoggerFactory.getLogger(NotifyApi.class);

    /**
     * @param requestData
     * @return
     */
    @ApiOperation(value = "缴费后台通知缴费结果")
    @RequestMapping(value = "/weixinNotifyResult", method = RequestMethod.POST)
    public String weixinNotifyResult(@RequestBody String requestData) {
        WeiXinNotifyResponse responseData = new WeiXinNotifyResponse();
        if (StringUtils.isEmpty(requestData)) {
            logger.warn("The request parameter is empty.");
            responseData.setReturnMsg("The request parameter is empty.");
            responseData.setReturnCode(PaymentBusinessConstant.RETURN_CODE_ERROR);
            return ObjecTransformXML.jaxbRequestObjectToXMLForWeiXin(responseData);
        }
        // 将request xml字符串转为map
        Map<String, Object> map = StringUtils.transferXMLtoMap(requestData);
        if (!ObjectUtils.isEmpty(map)) {
            requestData = notifyServiceImpl.disposeMessage(map,true);
        }
        return requestData;
    }

    /**
     * @param request
     * @return
     */
    @ApiOperation(value = "缴费后台通知缴费结果")
    @RequestMapping(value = "/alipayNotifyResult", method = RequestMethod.GET)
    public String alipayNotifyResult(HttpServletRequest request) {
        logger.info("Start processing the request parameters.");
        Enumeration<String> parameterNames = request.getParameterNames();
        List<String> list = new ArrayList<>();
        while (parameterNames.hasMoreElements()) {
            list.add(parameterNames.nextElement());
        }
        Map<String, Object> map = new HashMap<>();
        for (String str : list) {
            map.put(str, request.getParameter(str));
        }
        logger.info("The input parameter is:{} ", JSONObject.toJSONString(map));
        //验签
        if (!ObjectUtils.isEmpty(map)) {
            return notifyServiceImpl.disposeMessage(map,false);
        }
        return null;
    }
}


