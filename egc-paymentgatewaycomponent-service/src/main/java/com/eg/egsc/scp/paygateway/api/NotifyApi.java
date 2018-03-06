/**
 * @Probject Name: scp-pay-gateway-app-service
 * @Path: com.eg.egsc.scp.paygateway.NotifyApi.java
 * @Create By fandong
 * @Create In 2018年2月25日 TODO
 */
package com.eg.egsc.scp.paygateway.api;

import com.eg.egsc.framework.service.base.api.BaseApiController;
import com.eg.egsc.scp.paygateway.exception.PaymentGatewayException;
import com.eg.egsc.scp.paygateway.service.CreateOrderService;
import com.eg.egsc.scp.paygateway.service.SignatureService;
import com.eg.egsc.scp.paygateway.service.impl.CreateOrderServiceImpl;
import com.eg.egsc.scp.paygateway.service.model.CreateOrderRequestForWeiXin;
import com.eg.egsc.scp.paygateway.service.model.WeiXinNotifyResponse;
import com.eg.egsc.scp.paygateway.util.ErrorCodeConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.scp.paygateway.service.NotifyService;
import com.eg.egsc.scp.paygateway.util.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.Map;

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

  @Autowired
  private SignatureService signatureServiceImpl;


  protected final Logger logger = LoggerFactory.getLogger(NotifyApi.class);

  /**
   * 缴费后台通知缴费结果
   * @param 支付结果通知请求微信的数据
   * @return ResponseDto 返回的结果
   */
  @ApiOperation(value = "缴费后台通知缴费结果")
  @RequestMapping(value = "/weixinNotifyResult", method = RequestMethod.POST)
  public String weixinNotifyResult(@RequestBody String requestData) {
    String xmlString = null;
    //验签
    if(!StringUtils.isEmpty(requestData)) {
      //调用缴费后台
      xmlString = notifyServiceImpl.transferWeChatMessageForWeiXinString(requestData);
    }
    return xmlString;
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


