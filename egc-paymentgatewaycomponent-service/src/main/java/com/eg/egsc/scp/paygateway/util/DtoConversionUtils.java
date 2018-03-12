/**
 * @Probject Name: egc-paymentgatewaycomponent-service
 * @Path: com.eg.egsc.scp.paygateway.utilDtoConversionUtils.java
 * @Create By fandong
 * @Create In 2018年3月12日 上午10:15:11
 * TODO
 */
package com.eg.egsc.scp.paygateway.util;

import com.eg.egsc.egc.paymentbackendsystem.dto.ResultInformDto;
import com.eg.egsc.scp.paygateway.dto.AlipayResultDto;

/**
 * @Class Name DtoConversionUtils
 * @Author fandong
 * @Create In 2018年3月12日
 */
public class DtoConversionUtils {

    /**
     * @Methods Name conversion
     * @Create In 2018年3月12日 By fandong
     * @param alipayResultDto
     * @return ResultInformDto
     */
    public static ResultInformDto conversion(AlipayResultDto alipayResultDto) {
        ResultInformDto resultInformDto=new ResultInformDto();
        resultInformDto.setAppId(alipayResultDto.getAppId());
        resultInformDto.setTransactionId(alipayResultDto.getTradeNo());
        resultInformDto.setOutTradeNo(alipayResultDto.getOutTradeNo());
        resultInformDto.setMchId(alipayResultDto.getSellerId());
        resultInformDto.setResultCode(alipayResultDto.getTradeStatus());
        resultInformDto.setTotalFee(Integer.parseInt(String.valueOf(alipayResultDto.getTotalAmount())));
        resultInformDto.setCashFee(Integer.parseInt(String.valueOf(alipayResultDto.getBuyerPayAmount())));
        resultInformDto.setTimeEnd((alipayResultDto.getGmtPayment()).toString());
        resultInformDto.setCouponListJsonString(alipayResultDto.getFundChannel());
        resultInformDto.setAttach(alipayResultDto.getPassbackParams());
        return resultInformDto;
    }
}
