/**
 * @Probject Name: scp-pay-gateway-app-dao
 * @Path: com.eg.egsc.scp.paygateway.daoOrderQueryResponseForWeiXin.java
 * @Create By caiqinli
 * @Create In 2018年2月5日 下午5:15:55
 * TODO
 */
package com.eg.egsc.scp.paygateway.service.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Class Name OrderQueryResponseForWeiXin
 * @Author caiqinli
 * @Create In 2018年2月5日
 */
@Component
@XmlRootElement(name="xml")
public class OrderQueryResponseForWeiXin {
  
  
  /**
   * SUCCESS/FAIL - 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
   * 
   */
  @JsonProperty(value = "return_code")
  private String returnCode;
  
  /**
   * 返回信息，如非空，为错误原因 
   * 以下为来自微信后台，由支付网关透传的错误信息：
   * 签名失败 
   * 参数格式校验错误
   * 以下为支付网关可能返回的错误：
   * 支付网关参数格式校验错误
   * 未收到返回消息
   * 
   */ 
  @JsonProperty(value = "return_msg")
  private String returnMsg;
  
  /**
   * 调用接口提交的应用ID
   * 
   */
  private String appid;
  
  /**
   * 调用接口提交的商户号
   * 
   */
  @JsonProperty(value = "mch_id")
   private String mchId;
  
  /**
   * 调用接口提交的终端设备号
   * 
   */
  @JsonProperty(value = "device_info")
  private String deviceInfo;
  
  
  /**
   * 业务结果
   * 微信：SUCCESS/FAIL
                 支付宝到本接口的代码转换(存入代码转换表)：
     TRADE_CLOSED->FAIL  未付款交易超时关闭，或支付完成后全额退款
     TRADE_SUCCESS->SUCCESS  交易支付成功
   * 
   */
  @JsonProperty(value = "result_code")
  private String resultCode;
  
  /**
   * 错误代码
   * 
   */
  @JsonProperty(value = "err_code")
  private String errCode;
  
  /**
   * 错误代码描述
   * 
   */
  @JsonProperty(value = "err_code_des")
  private String errCodeDes;
  
  /**
   * 用户在商户appid下的唯一标识
   * 
   */
  private String openid;
  
  /**
   * 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
   * 
   */
  @JsonProperty(value = "is_subscribe")
  private String isSubscribe;
  
  /**
   * 调用接口提交的交易类型
   * 
   */
  @JsonProperty(value = "trade_type")
  private String tradeType;
  
  /**
   * 交易状态
   * 
   */
  @JsonProperty(value = "trade_state")
  private String tradeState;
  
  /**
   * 银行类型，采用字符串类型的银行标识
   * 
   */
  @JsonProperty(value = "bank_type")
  private String bankType;
  
  /**
   * 订单总金额，单位为分
   * 
   */
  @JsonProperty(value = "total_fee")
  private Double totalFee;
  
  /**
   * 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
   * 
   */
  @JsonProperty(value = "fee_type")
  private String feeType;
  
  /**
   * 现金支付金额订单现金支付金额
   * 
   */
  @JsonProperty(value = "cash_fee")
  private Double cashFee;
  
  /**
   * 应结订单金额： 当订单使用了免充值型优惠券后返回该参数，应结订单金额=订单金额-免充值优惠券金额。
   * 
   */
  @JsonProperty(value = "settlement_total_fee")
  private Double settlementTotalFee;
  
  /**
   * 代金券金额：“代金券或立减优惠”金额<=订单总金额，订单总金额-“代金券或立减优惠”金额=现金支付金额
   * 
   */
  @JsonProperty(value = "coupon_fee")
  private Double couponFee;
  
  /**
   * 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
   * 
   */
  @JsonProperty(value = "cash_fee_type")
  private String cashFeeType;
  

  
  /**
   * 代金券或立减优惠使用数量
   * 
   */
  @JsonProperty(value = "coupon_count")
  private String couponCount;
  
  /**
   * 代金券类别：
   * CASH 微信充值代金券
     NO CASH 微信非充值优惠券
     COUPON 支付宝红包
     POINT 支付宝集分宝
     DISCOUNT 支付宝折扣券
     MCARD 支付宝商家储值卡
     MDISCOUNT 支付宝商户优惠券
     MCOUPON 商户红包
   * 
   */
  @JsonProperty(value = "coupon_list_json_string")
  private String couponListJsonString;
  
  /**
   * 转发给微信订单查询接口的消息格式-微信的订单号，优先使用
   * 
   */
  @JsonProperty(value = "transaction_id")
  private String transactionId;
  
  /**
   * 转发给微信订单查询接口的消息格式-缴费后台内部订单号，
   * 当没提供transaction_id时需要传这个。
   * 要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
   * 
   */
  @JsonProperty(value = "out_trade_no")
  private String outTradeNo;
  
  /**
   * 附加数据，原样返回
   * 
   */
  private String attach;
  
  /**
   * 订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。
   * 
   */
  @JsonProperty(value = "time_end")
  private String timeEnd;
  
  /**
   * 交易状态描述： 对当前查询订单状态的描述和下一步操作的指引
   * 
   */
  @JsonProperty(value = "trade_state_desc")
  private String tradeStateDesc;
  
  /**
   * 转发给微信订单查询接口的消息格式-随机字符串，不长于32位。推荐随机数生成算法
   * 
   */
  @JsonProperty(value = "nonce_str")
  private String nonceStr;
  
  /**
   * 转发给微信订单查询接口的消息格式-签名
   * 
   */
  private String sign;

  /**
   * @Return the String returnCode
   */
  public String getReturnCode() {
    return returnCode;
  }

  /**
   * @Param String returnCode to set
   */
  public void setReturnCode(String returnCode) {
    this.returnCode = returnCode;
  }

  /**
   * @Return the String returnMsg
   */
  public String getReturnMsg() {
    return returnMsg;
  }

  /**
   * @Param String returnMsg to set
   */
  public void setReturnMsg(String returnMsg) {
    this.returnMsg = returnMsg;
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

  /**
   * @Return the String mchId
   */
  public String getMchId() {
    return mchId;
  }

  /**
   * @Param String mchId to set
   */
  public void setMchId(String mchId) {
    this.mchId = mchId;
  }

  /**
   * @Return the String deviceInfo
   */
  public String getDeviceInfo() {
    return deviceInfo;
  }

  /**
   * @Param String deviceInfo to set
   */
  public void setDeviceInfo(String deviceInfo) {
    this.deviceInfo = deviceInfo;
  }

  /**
   * @Return the String resultCode
   */
  public String getResultCode() {
    return resultCode;
  }

  /**
   * @Param String resultCode to set
   */
  public void setResultCode(String resultCode) {
    this.resultCode = resultCode;
  }

  /**
   * @Return the String errCode
   */
  public String getErrCode() {
    return errCode;
  }

  /**
   * @Param String errCode to set
   */
  public void setErrCode(String errCode) {
    this.errCode = errCode;
  }

  /**
   * @Return the String errCodeDes
   */
  public String getErrCodeDes() {
    return errCodeDes;
  }

  /**
   * @Param String errCodeDes to set
   */
  public void setErrCodeDes(String errCodeDes) {
    this.errCodeDes = errCodeDes;
  }

  /**
   * @Return the String openid
   */
  public String getOpenid() {
    return openid;
  }

  /**
   * @Param String openid to set
   */
  public void setOpenid(String openid) {
    this.openid = openid;
  }

  /**
   * @Return the String isSubscribe
   */
  public String getIsSubscribe() {
    return isSubscribe;
  }

  /**
   * @Param String isSubscribe to set
   */
  public void setIsSubscribe(String isSubscribe) {
    this.isSubscribe = isSubscribe;
  }

  /**
   * @Return the String tradeType
   */
  public String getTradeType() {
    return tradeType;
  }

  /**
   * @Param String tradeType to set
   */
  public void setTradeType(String tradeType) {
    this.tradeType = tradeType;
  }

  /**
   * @Return the String tradeState
   */
  public String getTradeState() {
    return tradeState;
  }

  /**
   * @Param String tradeState to set
   */
  public void setTradeState(String tradeState) {
    this.tradeState = tradeState;
  }

  /**
   * @Return the String bankType
   */
  public String getBankType() {
    return bankType;
  }

  /**
   * @Param String bankType to set
   */
  public void setBankType(String bankType) {
    this.bankType = bankType;
  }

  /**
   * @Return the Double totalFee
   */
  public Double getTotalFee() {
    return totalFee;
  }

  /**
   * @Param Double totalFee to set
   */
  public void setTotalFee(Double totalFee) {
    this.totalFee = totalFee;
  }

  /**
   * @Return the String feeType
   */
  public String getFeeType() {
    return feeType;
  }

  /**
   * @Param String feeType to set
   */
  public void setFeeType(String feeType) {
    this.feeType = feeType;
  }

  /**
   * @Return the Double cashFee
   */
  public Double getCashFee() {
    return cashFee;
  }

  /**
   * @Param Double cashFee to set
   */
  public void setCashFee(Double cashFee) {
    this.cashFee = cashFee;
  }

  /**
   * @Return the Double settlementTotalFee
   */
  public Double getSettlementTotalFee() {
    return settlementTotalFee;
  }

  /**
   * @Param Double settlementTotalFee to set
   */
  public void setSettlementTotalFee(Double settlementTotalFee) {
    this.settlementTotalFee = settlementTotalFee;
  }

  /**
   * @Return the Double couponFee
   */
  public Double getCouponFee() {
    return couponFee;
  }

  /**
   * @Param Double couponFee to set
   */
  public void setCouponFee(Double couponFee) {
    this.couponFee = couponFee;
  }

  /**
   * @Return the String cashFeeType
   */
  public String getCashFeeType() {
    return cashFeeType;
  }

  /**
   * @Param String cashFeeType to set
   */
  public void setCashFeeType(String cashFeeType) {
    this.cashFeeType = cashFeeType;
  }

  /**
   * @Return the String couponCount
   */
  public String getCouponCount() {
    return couponCount;
  }

  /**
   * @Param String couponCount to set
   */
  public void setCouponCount(String couponCount) {
    this.couponCount = couponCount;
  }

  /**
   * @Return the String couponListJsonString
   */
  public String getCouponListJsonString() {
    return couponListJsonString;
  }

  /**
   * @Param String couponListJsonString to set
   */
  public void setCouponListJsonString(String couponListJsonString) {
    this.couponListJsonString = couponListJsonString;
  }

  /**
   * @Return the String transactionId
   */
  public String getTransactionId() {
    return transactionId;
  }

  /**
   * @Param String transactionId to set
   */
  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  /**
   * @Return the String outTradeNo
   */
  public String getOutTradeNo() {
    return outTradeNo;
  }

  /**
   * @Param String outTradeNo to set
   */
  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  /**
   * @Return the String attach
   */
  public String getAttach() {
    return attach;
  }

  /**
   * @Param String attach to set
   */
  public void setAttach(String attach) {
    this.attach = attach;
  }

  /**
   * @Return the String timeEnd
   */
  public String getTimeEnd() {
    return timeEnd;
  }

  /**
   * @Param String timeEnd to set
   */
  public void setTimeEnd(String timeEnd) {
    this.timeEnd = timeEnd;
  }

  /**
   * @Return the String tradeStateDesc
   */
  public String getTradeStateDesc() {
    return tradeStateDesc;
  }

  /**
   * @Param String tradeStateDesc to set
   */
  public void setTradeStateDesc(String tradeStateDesc) {
    this.tradeStateDesc = tradeStateDesc;
  }

  /**
   * @Return the String nonceStr
   */
  public String getNonceStr() {
    return nonceStr;
  }

  /**
   * @Param String nonceStr to set
   */
  public void setNonceStr(String nonceStr) {
    this.nonceStr = nonceStr;
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



}
