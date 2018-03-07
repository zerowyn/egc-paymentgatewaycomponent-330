/**
 * @Probject Name: scp-pay-gateway-app-service
 * @Path: com.eg.egsc.scp.paygateway.NotifyApi.java
 * @Create By fandong
 * @Create In 2018年2月25日 TODO
 */
package com.eg.egsc.scp.paygateway.api;

import com.eg.egsc.framework.service.base.api.BaseApiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
   *
   * @param requestData
   * @return
   */
  @ApiOperation(value = "缴费后台通知缴费结果")
  @RequestMapping(value = "/weixinNotifyResult", method = RequestMethod.POST)
  public String weixinNotifyResult(@RequestBody String requestData) {
    //验签
    if(!StringUtils.isEmpty(requestData)) {
      //调用缴费后台
      requestData = notifyServiceImpl.transferWeChatMessageForWeiXinString(requestData);
    }
    return requestData;
  }

  /**
   *
   * @param request
   * @return
   */
  @ApiOperation(value = "缴费后台通知缴费结果")
  @RequestMapping(value = "/alipayNotifyResult", method = RequestMethod.POST)
  public String alipayNotifyResult(HttpServletRequest request) {

    Enumeration<String> parameterNames = request.getParameterNames();
    List<String> list = new ArrayList<String>();
    while(parameterNames.hasMoreElements()){
      list.add(parameterNames.nextElement());
    }
    Map<String, Object> maps = new HashMap<String, Object>();
    for(String str :list){
      maps.put(str,request.getParameter(str));
    }


    //验签
    if(null!=maps){
      String s = notifyServiceImpl.transferAlipaytMessageForAlipayString(maps);
      return s;
    }


    return null;
  }
}


