/**
 * @Probject Name: scp-pay-gateway-app-service
 * @Path: com.eg.egsc.scp.paygateway.NotifyApi.java
 * @Create By fandong
 * @Create In 2018年2月25日 上午10:30:08
 */
package com.eg.egsc.scp.paygateway.api;

import com.eg.egsc.framework.client.dto.RequestDto;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.framework.service.base.api.BaseApiController;
import com.eg.egsc.scp.paygateway.dto.PaymentResultDto;
import com.eg.egsc.scp.paygateway.service.NotifyService;
import com.eg.egsc.scp.paygateway.util.PaymentBusinessConstant;
import com.eg.egsc.scp.paygateway.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.Set;

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

    protected final Logger logger = LoggerFactory.getLogger(NotifyApi.class);

    /**
     * @param requestDto
     * @return ResponseDto
     * @Methods Name notifyResult
     * @Create In 2018年3月12日 By fandong
     */
    @ApiOperation(value = "缴费后台通知缴费结果")
    @RequestMapping(value = "/notifyResult", method = RequestMethod.POST)
    public ResponseDto notifyResult(@RequestBody RequestDto<PaymentResultDto> requestDto) throws Exception {
        ResponseDto responseDto = new ResponseDto();
        if (ObjectUtils.isEmpty(requestDto) || ObjectUtils.isEmpty(requestDto.getData()) || requestDto.getData().getInformStr() == null) {
            responseDto.setMessage("The parameter is empty.");
            responseDto.setData(new Object());
            responseDto.setCode(PaymentBusinessConstant.COMMON_FRAMEWORK_SUCCESS_CODE);
            logger.error("The parameter is empty.");
            return responseDto;
        }

        String disposeMessage;
        if (requestDto.getData().getPlatfrom().equalsIgnoreCase(PaymentBusinessConstant.WEI_XIN)) {
            String informStr = (String) requestDto.getData().getInformStr();
            disposeMessage = notifyServiceImpl.disposeMessage(StringUtils.transferXMLtoMap(informStr), true);
            logger.info("data------------------>:"+disposeMessage);
            responseDto.setMessage(PaymentBusinessConstant.SUCCESS_MESSAGE);
            responseDto.setData(disposeMessage);
            responseDto.setCode(PaymentBusinessConstant.COMMON_FRAMEWORK_SUCCESS_CODE);
            logger.info("End WeChat payment notification processing");
            return responseDto;
        }
        Map<String, Object> map = (Map) requestDto.getData().getInformStr();

        //循环map解码
        Set<String> set=map.keySet();
        for(String key:set)
        {
            java.net.URLDecoder.decode((String) map.get(key),"utf-8");
        }

        disposeMessage = notifyServiceImpl.disposeMessage(map, false);
        responseDto.setMessage(PaymentBusinessConstant.SUCCESS_MESSAGE);
        responseDto.setData("cdhsidsi");
        responseDto.setCode(PaymentBusinessConstant.COMMON_FRAMEWORK_SUCCESS_CODE);
        logger.info("End AliPay payment notification processing");
        return responseDto;
    }

}


