/**
 * @Probject Name: scp-pay-gateway-app-service
 * @Path: com.eg.egsc.scp.paygateway.CreateOrderApi.java
 * @Create By lihui
 * @Create In 2018年2月25日 s上午午10:42
 */
package com.eg.egsc.scp.paygateway.api;

import com.eg.egsc.framework.client.dto.RequestDto;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.framework.service.base.BaseController;
import com.eg.egsc.scp.paygateway.dto.CreateOrderRequestForBackendDto;
import com.eg.egsc.scp.paygateway.dto.CreateOrderResponseForBackendDto;
import com.eg.egsc.scp.paygateway.dto.RequestForGetOpenIdDto;
import com.eg.egsc.scp.paygateway.service.CreateOrderService;
import com.eg.egsc.scp.paygateway.util.PaymentBusinessConstant;
import com.eg.egsc.scp.paygateway.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Class Name CreateOrderApi
 * @Author lihui
 * @Create In 2018年2月25日
 */
@Api(value = "支付网关创建订单相关api")
@RestController
@RequestMapping(value = "/pay")
public class CreateOrderApi extends BaseController {

    @Autowired
    private CreateOrderService createOrderServiceImpl;

    @Autowired
    private CreateOrderResponseForBackendDto createOrderResponseForBackendDto;

    protected final Logger logger = LoggerFactory.getLogger(CreateOrderApi.class);

    @ApiOperation(value = "支付网关获取OPENID")
    @RequestMapping(value = "/get/openid",method = RequestMethod.POST)
    public ResponseDto getOpenId(@RequestBody RequestDto<RequestForGetOpenIdDto> req){
        ResponseDto responseDto = new ResponseDto();
        RequestForGetOpenIdDto data = req.getData();
        String openId = createOrderServiceImpl.getOpenId(data);
        if(StringUtils.isEmpty(openId)){
            responseDto.setData(openId);
            responseDto.setCode("00099");
            responseDto.setMessage("获取openId失败！");
        }else{
            responseDto.setData(openId);
            responseDto.setCode("00000");
            responseDto.setMessage("数据返回正常！");
        }
        return responseDto;
    }

    /**
     * 缴费后台创建支付订单
     *
     * @param req 创建支付订单的参数
     * @return ResponseDto 返回的结果
     */
    @ApiOperation(value = "支付网关创建订单信息")
    @RequestMapping(value = "/createorder", method = RequestMethod.POST)
    public ResponseDto createOrder(@RequestBody RequestDto<CreateOrderRequestForBackendDto> req) {
        ResponseDto result = new ResponseDto();
            createOrderResponseForBackendDto = createOrderServiceImpl.createOrderRequestFromBackendSystme(req.getData());
            if (!PaymentBusinessConstant.SUCCESS_MESSAGE.equalsIgnoreCase(createOrderResponseForBackendDto.getReturnCode()) ||
            !PaymentBusinessConstant.SUCCESS_MESSAGE.equalsIgnoreCase(createOrderResponseForBackendDto.getResultCode())) {
                logger.error(createOrderResponseForBackendDto.getErrCodeDes());
                result.setCode("00099");
                result.setMessage(createOrderResponseForBackendDto.getErrCodeDes());
            } else {
                result.setCode("00000");
                result.setMessage("00000:数据返回正常");
            }
        result.setData(createOrderResponseForBackendDto);
        return result;
    }
}


