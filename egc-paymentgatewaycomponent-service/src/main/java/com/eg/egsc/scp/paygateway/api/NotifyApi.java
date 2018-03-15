/**
 * @Probject Name: scp-pay-gateway-app-service
 * @Path: com.eg.egsc.scp.paygateway.NotifyApi.java
 * @Create By fandong
 * @Create In 2018年2月25日 上午10:30:08
 */
package com.eg.egsc.scp.paygateway.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eg.egsc.framework.client.dto.RequestDto;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.framework.service.base.api.BaseApiController;
import com.eg.egsc.scp.paygateway.dto.PaymentResultDto;
import com.eg.egsc.scp.paygateway.service.NotifyService;
import com.eg.egsc.scp.paygateway.util.PaymentBusinessConstant;
import com.eg.egsc.scp.paygateway.util.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
     * @param requestDto
     * @return ResponseDto
     * @Methods Name notifyResult
     * @Create In 2018年3月12日 By fandong
     */
    @ApiOperation(value = "缴费后台通知缴费结果")
    @RequestMapping(value = "/notifyResult", method = RequestMethod.POST)
    public ResponseDto notifyResult(@RequestBody RequestDto<PaymentResultDto> requestDto) {
        ResponseDto responseDto = new ResponseDto();
        if (ObjectUtils.isEmpty(requestDto) || null == requestDto.getData()) {
            logger.info("The requestDto that begins to receive is empty.");
            responseDto.setMessage("The parameter is empty.");
            responseDto.setData(null);
            responseDto.setCode("00099");
            logger.info("The requestDto at the end is empty.");
            return responseDto;
        }
        PaymentResultDto resultDto = requestDto.getData();
        String informStr = resultDto.getInformStr();
        Map<String, Object> map ;
        if (resultDto.getPlatfrom().equalsIgnoreCase(PaymentBusinessConstant.WEI_XIN)) {
            logger.info("It started out as WeChat payment platform.");
            map = StringUtils.transferXMLtoMap(informStr);
            String disposeMessage = notifyServiceImpl.disposeMessage(map, true);
            responseDto.setMessage("SUCCESS");
            responseDto.setData(disposeMessage);
            responseDto.setCode("00000");
            logger.info("At the end of it is the alipay payment platform.");
            return responseDto;
        }
        Map<String, Object> map2 = new HashMap<>();
        int indexOf = informStr.indexOf('?');
        Arrays.asList(informStr.substring(indexOf+1).split("&")).forEach(str -> {
            String[] split2 = str.split("=");
            map2.put(split2[0], split2[1]);
        });

        String disposeMessage = notifyServiceImpl.disposeMessage(map2, false);
        responseDto.setMessage("SUCCESS");
        responseDto.setData(disposeMessage);
        responseDto.setCode("00000");
        return responseDto;
    }

}


