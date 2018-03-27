/**
 * @Probject Name: scp-pay-gateway-app-client
 * @Path: com.eg.egsc.scp.paygateway.dtoCreateOrderResponseForBackendDto.java
 * @Create By lihui
 * @Create In 2018年2月25日 上午：11:51
 */
package com.eg.egsc.scp.paygateway.dto;

import com.eg.egsc.framework.client.dto.BaseBusinessDto;
import org.springframework.stereotype.Component;

/**
 * @Class Name CreateOrderResponseForBackendDto
 * @Author lihui
 * @Create In 2018年2月25日
 */
@Component
public class CreateOrderResponseForBackendDto extends BaseBusinessDto {

    /**
     * @Field long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 指定请求的目标平台-‘WEIXIN’或‘ALIPAY’，必填
     */
    //@NotBlank(message = "scp.pay.gateway.app.backend.request.platform.notblank")
    private String platform;

    /**
     * 返回状态码
     * SUCCESS/FAIL
     */
    private String returnCode;

    /**
     * 返回信息
     */
    private String returnMsg;

    /**
     * 微信统一下单接口的消息格式-微信开放平台审核通过的应用APPID
     */
    private String appid;

    /**
     * 微信统一下单接口的消息格式-微信支付分配的商户号
     */
    private String partnerid;

    /**
     * 设备号
     * 调用接口提交的终端设备号，
     */
    private String deviceInfo;

    /**
     * 随机字符串
     * 微信返回的随机字符串（验签用，不向缴费后台传送）
     */
    private String noncestr;

    /**
     * 签名
     */
    private String sign;

    /**
     * 业务结果
     */
    private String resultCode;

    /**
     * 错误代码
     */
    private String errCode;

    /**
     * 错误代码描述
     */
    private String errCodeDes;

    /**
     * 微信统一下单接口的消息格式-微信的交易类型
     */
    private String tradeType;

    /**
     * 预支付交易会话标识
     */
    private String prepayid;

    /**
     * 支付跳转链接
     * mweb_url为拉起微信支付收银台的中间页面，可通过访问该url来拉起微信客户端，完成支付,mweb_url的有效期为5分钟。
     * 当trade_type为MWEB时返回。
     */
    private String mwebUrl;

    /**
     * 支付宝的订单字符串
     * 前端页面调用支付宝支付的支付串，无需做处理直接使用
     *
     * @return
     */
    private String orderStr;

    /**
     * 微信时间戳
     *
     * @return
     */
    private String timestamp;

    /**
     * 调起微信支付需要用到的参数
     *
     * @return
     */
    private String packageValue;

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @Return the String platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @Param String platform to set
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * @Return the String appid
     */
    public String getAppid() {
        return appid;
    }

    /**
     * @Param String appid to set
     */
    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getOrderStr() {
        return orderStr;
    }

    public void setOrderStr(String orderStr) {
        this.orderStr = orderStr;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getMwebUrl() {
        return mwebUrl;
    }

    public void setMwebUrl(String mwebUrl) {
        this.mwebUrl = mwebUrl;
    }
}
