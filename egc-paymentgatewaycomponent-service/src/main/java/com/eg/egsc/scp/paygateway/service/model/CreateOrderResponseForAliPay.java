/**
 * @Probject Name: scp-pay-gateway-app-dao
 * @Path: com.eg.egsc.scp.paygateway.daoCreateOrderResponseForAliPay.java
 * @Create By caiqinli
 * @Create In 2018年2月5日 下午5:17:03
 */
package com.eg.egsc.scp.paygateway.service.model;

/**
 * @Class Name CreateOrderResponseForAliPay
 * @Author lihui
 * @Create In 2018年2月25日
 */
public class CreateOrderResponseForAliPay {

    /**
     *网关返回码描述
     */
    private String msg;

    /**
     * 业务返回码描述
     */
    private String subMsg;

    /**
     * 网关返回码
     */
    private String code;

    /**
     * 签名
     */
    private String sign;

    /**
     * 支付宝交易号
     */
    private String tradeNo;

    /**
     * 上家订单号
     */
    private String outTradeNo;

    /**
     * 业务返回码
     */
    private String subCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSubMsg() {
        return subMsg;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }
}
