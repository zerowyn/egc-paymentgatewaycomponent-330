/**
 * @Probject Name: scp-pay-gateway-app-dao
 * @Path: com.eg.egsc.scp.paygateway.daoCreateOrderRequestForWeiXin.java
 * @Create By lihui
 * @Create In 2018年2月25日 上午11:15
 */
package com.eg.egsc.scp.paygateway.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Class Name CreateOrderRequestForWeiXin
 * @Author lihui
 * @Create In 2018年2月25日
 */
@Component
@XmlRootElement(name = "xml")
public class CreateOrderRequestForWeiXin {

    /**
     * 转发给微信创建订单接口的消息格式-微信开放平台审核通过的应用APPID
     */
    @NotBlank(message = "scp.pay.gateway.app.pay.request.appid.notblank")
    private String appid;

    /**
     * 转发给微信订单查询接口的消息格式-微信支付分配的商户号
     */
    @NotBlank(message = "scp.pay.gateway.app.pay.request.mch_id.notblank")
    @JsonProperty(value = "mch_id")
    private String mchId;

    /**
     * 设备号
     * 写入默认值“WEB”（从配置表读取）
     */
    @JsonProperty("device_info")
    private String deviceInfo;

    /**
     * 转发给微信订单查询接口的消息格式-随机字符串，不长于32位。推荐随机数生成算法
     */
    @JsonProperty("nonce_str")
    private String nonceStr;

    /**
     * 转发给微信订单查询接口的消息格式-签名
     */
    private String sign;

    /**
     * 签名类型
     * 写入默认值“MD5”（从配置表读取）
     */
    @JsonProperty(value = "sign_type")
    private String signType;

    /**
     * 商品描述
     */
    private String body;

    /**
     * 商品详情
     */
    private String detail;

    /**
     * 附加数据
     */
    private String attach;

    /**
     * 转发给微信订单查询接口的消息格式-缴费后台内部订单号，
     * 当没提供transaction_id时需要传这个。
     * 要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     */
    @JsonProperty(value = "out_trade_no")
    private String outTradeNo;

    /**
     * 货币类型
     */
    @JsonProperty(value = "fee_type")
    private String feeType;

    /**
     * 总金额
     */
    @JsonProperty(value = "total_fee")
    private String totalFee;

    /**
     * 终端IP
     */
    @JsonProperty(value = "spbill_create_ip")
    private String spbillCreateIp;

    /**
     * 交易起始时间
     */
    @JsonProperty(value = "time_start")
    private String timeStart;

    /**
     * 交易结束时间
     */
    @JsonProperty(value = "time_expire")
    private String timeExpire;

    /**
     * 订单优惠标记
     */
    @JsonProperty(value = "goods_tag")
    private String goodsTag;

    /**
     * 通知地址
     */
    @JsonProperty(value = "notify_url")
    private String notifyUrl;

    /**
     * 交易类型
     */
    @JsonProperty(value = "trade_type")
    private String tradeType;

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 指定支付方式
     */
    @JsonProperty(value = "limit_pay")
    private String limitPay;

    /**
     * openid
     */
    private String openid;

    /**
     * 场景信息
     */
    @JsonProperty(value = "scene_info")
    private String sceneInfo;

    /**
     * 获取openid返回的错误信息
     */
    private String errorMsg;

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

    /**
     * @Return the String sign
     */
    public String getSign() {
        return sign;
    }

    /**
     * @Param String sign to set
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getLimitPay() {
        return limitPay;
    }

    public void setLimitPay(String limitPay) {
        this.limitPay = limitPay;
    }

    public String getSceneInfo() {
        return sceneInfo;
    }

    public void setSceneInfo(String sceneInfo) {
        this.sceneInfo = sceneInfo;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
