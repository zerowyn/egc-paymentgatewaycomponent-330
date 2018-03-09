/**
 * @Probject Name: scp-pay-gateway-app-dao
 * @Path: com.eg.egsc.scp.paygateway.extmapper.entityOrderQueryResponseForBackendSystem.java
 * @Create By caiqinli
 * @Create In 2018年2月5日 下午5:30:57
 */
package com.eg.egsc.scp.paygateway.dto;

import org.springframework.stereotype.Component;

/**
 * @Class Name OrderQueryResponseForBackendSystem
 * @Author caiqinli
 * @Create In 2018年2月5日
 */
@Component
public class OrderQueryResponseForBackendDto {
  
  /**
   * 指定请求的目标平台-‘WEIXIN’或‘ALIPAY’，必填
   * 
   */
  private String platform;  
  
  /**
   * SUCCESS/FAIL - 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
   * 
   */
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
  private String returnMsg;
  
  /**
   * 调用接口提交的应用ID
   * 
   */
  private String appId;
  
  /**
   * 调用接口提交的商户号
   * 
   */
   private String mchId;
  
  /**
   * 调用接口提交的终端设备号
   * 
   */
  private String deviceInfo;
  
  
  /**
   * 业务结果
   * 微信：SUCCESS/FAIL
                 支付宝到本接口的代码转换(存入代码转换表)：
     TRADE_CLOSED->FAIL  未付款交易超时关闭，或支付完成后全额退款
     TRADE_SUCCESS->SUCCESS  交易支付成功
   * 
   */
  private String resultCode;
  
  /**
   * 错误代码
   * 
   */
  private String errCode;
  
  /**
   * 错误代码描述
   * 
   */
  private String errCodeDes;
  
  /**
   * 用户在商户appid下的唯一标识
   * 
   */
  private String openId;
  
  /**
   * 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
   * 
   */
  private String isSubscribe;
  
  /**
   * 调用接口提交的交易类型
   * 
   */
  private String tradeType;
  
  /**
   * 交易状态
   * 
   */
  private String tradeState;
  
  /**
   * 银行类型，采用字符串类型的银行标识
   * 
   */
  private String bankType;
  
  /**
   * 订单总金额，单位为分
   * 
   */
  private Double totalFee;
  
  
  /**
   * 订单总金额类型
   * 
   */
  private String feeType;
  
  /**
   * 现金支付金额订单现金支付金额
   * 
   */
  private Double cashFee;
  
  /**
   * 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
   * 
   */
  private String cashFeeType;
  
  /**
   * 应结订单金额： 当订单使用了免充值型优惠券后返回该参数，应结订单金额=订单金额-免充值优惠券金额。
   * 
   */
  private Double settlementTotalFee;
  
  /**
   * 代金券金额：“代金券或立减优惠”金额<=订单总金额，订单总金额-“代金券或立减优惠”金额=现金支付金额
   * 
   */
  private Double couponFee;
  
  /**
   * 代金券或立减优惠使用数量
   * 
   */
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
  private String couponListJsonString;
  
  /**
   * 转发给微信订单查询接口的消息格式-微信的订单号，优先使用
   * 
   */
  private String transactionId;
  
  /**
   * 转发给微信订单查询接口的消息格式-缴费后台内部订单号，
   * 当没提供transaction_id时需要传这个。
   * 要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
   * 
   */
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
  private String timeEnd;
  
  /**
   * 交易状态描述： 对当前查询订单状态的描述和下一步操作的指引
   * 
   */
  private String tradeStateDesc;

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
   * @Return the String appId
   */
  public String getAppId() {
    return appId;
  }

  /**
   * @Param String appId to set
   */
  public void setAppId(String appId) {
    this.appId = appId;
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
   * @Return the String openId
   */
  public String getOpenId() {
    return openId;
  }

  /**
   * @Param String openId to set
   */
  public void setOpenId(String openId) {
    this.openId = openId;
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




}
