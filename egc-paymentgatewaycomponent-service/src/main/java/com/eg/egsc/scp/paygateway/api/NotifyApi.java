/**
 * @Probject Name: scp-pay-gateway-app-service
 * @Path: com.eg.egsc.scp.paygateway.NotifyApi.java
 * @Create By fandong
 * @Create In 2018年2月25日 TODO
 */
package com.eg.egsc.scp.paygateway.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.framework.service.base.BaseController;
import com.eg.egsc.scp.paygateway.service.NotifyService;
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
public class NotifyApi extends BaseController {

  @Autowired
  NotifyService notifyServiceImpl;

  protected final Logger logger = LoggerFactory.getLogger(NotifyApi.class);

  /**
   * 缴费后台通知缴费结果
   * @param 支付结果通知请求微信的数据
   * @return ResponseDto 返回的结果
   */
  @ApiOperation(value = "缴费后台通知缴费结果")
  @RequestMapping(value = "/weixinNotifyResult", method = RequestMethod.POST)
  public ResponseDto weixinNotifyResult(@RequestBody String requestData) {
    ResponseDto responseDto = new ResponseDto();
    if(StringUtils.isEmpty(requestData)) {
      logger.warn("The parameters are empty");
      responseDto.setMessage("parameters is blank");
      responseDto.setData(null);
      // 调用缴费后台接口
      return responseDto;
    }
    notifyServiceImpl.transferWeChatMessageForWeiXinString(requestData);
    responseDto.setMessage("param is blank");
    responseDto.setData(null);
    return responseDto;
  }

  /**
   * 缴费后台通知缴费结果
   * @param 支付结果通知请求支付宝的数据
   * @return ResponseDto 返回的结果
   */
  @ApiOperation(value = "缴费后台通知缴费结果")
  @RequestMapping(value = "/alipayNotifyResult", method = RequestMethod.POST)
  public ResponseDto alipayNotifyResult(@RequestBody String requestMessageData) {
    ResponseDto responseDto = new ResponseDto();
    if (StringUtils.isEmpty(requestMessageData)) {
      logger.warn("param is blank null");
      responseDto.setMessage("param is blank");
      responseDto.setData(null);
      //
      return responseDto;
    }
    notifyServiceImpl.transferAlipaytMessageForAlipayString(requestMessageData);
    responseDto.setMessage("param is blank");
    responseDto.setData(null);
    return responseDto;
  }
}


