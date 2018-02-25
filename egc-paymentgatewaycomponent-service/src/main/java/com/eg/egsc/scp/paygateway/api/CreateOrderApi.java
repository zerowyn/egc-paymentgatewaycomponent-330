/**
 * @Probject Name: scp-pay-gateway-app-service
 * @Path: com.eg.egsc.scp.paygateway.CreateOrderApi.java
 * @Create By lihui
 * @Create In 2018年2月25日 s上午午10:42
 * TODO
 */
package com.eg.egsc.scp.paygateway.api;

import com.eg.egsc.framework.client.dto.RequestDto;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.framework.service.base.BaseController;
import com.eg.egsc.scp.paygateway.dto.CreateOrderRequestForBackendDto;
import com.eg.egsc.scp.paygateway.dto.CreateOrderResponseForBackendDto;
import com.eg.egsc.scp.paygateway.service.CreateOrderService;
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

    /**
     * 缴费后台创建支付订单
     *
     * @param req 创建支付订单的参数
     * @return ResponseDto 返回的结果
     */
    @ApiOperation(value = "缴费后台创建订单信息")
    @RequestMapping(value = "/createorder", method = RequestMethod.POST)
    public ResponseDto createOrder(@RequestBody RequestDto<CreateOrderRequestForBackendDto> req) {

        ResponseDto result = new ResponseDto();
        try {
            createOrderResponseForBackendDto = createOrderServiceImpl.createOrderRequestFromBackendSystme(req.getData());
            String message = "";
            if(createOrderResponseForBackendDto == null){
                message = "程序异常，数据返回对象为空";
                logger.error(message);
                result.setMessage(message);
            }else{
                message = "数据返回正常";
                result.setMessage(message);
            }
            result.setData(createOrderResponseForBackendDto);
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.setMessage("支付网关：创建订单出现异常");
        }
        return result;
    }
}


