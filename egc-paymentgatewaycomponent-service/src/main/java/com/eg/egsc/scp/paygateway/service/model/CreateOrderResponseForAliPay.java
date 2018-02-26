/**
 * @Probject Name: scp-pay-gateway-app-dao
 * @Path: com.eg.egsc.scp.paygateway.daoCreateOrderResponseForAliPay.java
 * @Create By caiqinli
 * @Create In 2018年2月5日 下午5:17:03
 * TODO
 */
package com.eg.egsc.scp.paygateway.service.model;

/**
 * @Class Name CreateOrderResponseForAliPay
 * @Author lihui
 * @Create In 2018年2月25日
 */
public class CreateOrderResponseForAliPay {

    /**
     * 网关返回码
     */
    private String code;

    /**
     *网关返回码描述
     */
    private String msg;

    /**
     * 业务返回码
     */
    private String sub_code;

    /**
     * 业务返回码描述
     */
    private String sub_msg;

    /**
     * 签名
     */
    private String sign;

    /**
     * 支付宝交易号
     */
    private String trade_no;

    /**
     * 上家订单号
     */
    private String out_trade_no;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSub_code() {
        return sub_code;
    }

    public void setSub_code(String sub_code) {
        this.sub_code = sub_code;
    }

    public String getSub_msg() {
        return sub_msg;
    }

    public void setSub_msg(String sub_msg) {
        this.sub_msg = sub_msg;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
}
