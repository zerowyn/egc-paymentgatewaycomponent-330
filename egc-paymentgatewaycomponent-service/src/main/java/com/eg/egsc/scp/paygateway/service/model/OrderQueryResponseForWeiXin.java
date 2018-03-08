/**
 * @Probject Name: scp-pay-gateway-app-dao
 * @Path: com.eg.egsc.scp.paygateway.daoOrderQueryResponseForWeiXin.java
 * @Create By caiqinli
 * @Create In 2018年2月5日 下午5:15:55
 * TODO
 */
package com.eg.egsc.scp.paygateway.service.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

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
  @NotBlank(message = "scp.pay.gateway.app.backend.response.return_code.notblank")
  private String return_code;
  
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
  private String return_msg;
  
  /**
   * 调用接口提交的应用ID
   * 
   */
  private String appid;
  
  /**
   * 调用接口提交的商户号
   * 
   */
   private String mch_id;
  
  /**
   * 调用接口提交的终端设备号
   * 
   */
  private String device_info;
  
  
  /**
   * 业务结果
   * 微信：SUCCESS/FAIL
                 支付宝到本接口的代码转换(存入代码转换表)：
     TRADE_CLOSED->FAIL  未付款交易超时关闭，或支付完成后全额退款
     TRADE_SUCCESS->SUCCESS  交易支付成功
   * 
   */
  private String result_code;
  
  /**
   * 错误代码
   * 
   */
  private String err_code;
  
  /**
   * 错误代码描述
   * 
   */
  private String err_code_des;
  
  /**
   * 用户在商户appid下的唯一标识
   * 
   */
  private String openid;
  
  /**
   * 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
   * 
   */
  private String is_subscribe;
  
  /**
   * 调用接口提交的交易类型
   * 
   */
  private String trade_type;
  
  /**
   * 交易状态
   * 
   */
  private String trade_state;
  
  /**
   * 银行类型，采用字符串类型的银行标识
   * 
   */
  private String bank_type;
  
  /**
   * 订单总金额，单位为分
   * 
   */
  private Double total_fee;
  
  /**
   * 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
   * 
   */
  private String fee_type;
  
  /**
   * 现金支付金额订单现金支付金额
   * 
   */
  private Double cash_fee;
  
  /**
   * 应结订单金额： 当订单使用了免充值型优惠券后返回该参数，应结订单金额=订单金额-免充值优惠券金额。
   * 
   */
  private Double settlement_total_fee;
  
  /**
   * 代金券金额：“代金券或立减优惠”金额<=订单总金额，订单总金额-“代金券或立减优惠”金额=现金支付金额
   * 
   */
  private Double coupon_fee;
  
  /**
   * 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
   * 
   */
  private String cash_fee_type;
  

  
  /**
   * 代金券或立减优惠使用数量
   * 
   */
  private String coupon_count;
  
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
  private String coupon_list_json_string;
  
  /**
   * 转发给微信订单查询接口的消息格式-微信的订单号，优先使用
   * 
   */
  private String transaction_id;
  
  /**
   * 转发给微信订单查询接口的消息格式-缴费后台内部订单号，
   * 当没提供transaction_id时需要传这个。
   * 要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
   * 
   */
  private String out_trade_no;
  
  /**
   * 附加数据，原样返回
   * 
   */
  private String attach;
  
  /**
   * 订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。
   * 
   */
  private String time_end;
  
  /**
   * 交易状态描述： 对当前查询订单状态的描述和下一步操作的指引
   * 
   */
  private String trade_state_desc;
  
  /**
   * 转发给微信订单查询接口的消息格式-随机字符串，不长于32位。推荐随机数生成算法
   * 
   */
  private String nonce_str;
  
  /**
   * 转发给微信订单查询接口的消息格式-签名
   * 
   */
  private String sign;

  /**
   * @Return the String return_code
   */
  public String getReturn_code() {
    return return_code;
  }

  /**
   * @Param String return_code to set
   */
  public void setReturn_code(String return_code) {
    this.return_code = return_code;
  }

  /**
   * @Return the String return_msg
   */
  public String getReturn_msg() {
    return return_msg;
  }

  /**
   * @Param String return_msg to set
   */
  public void setReturn_msg(String return_msg) {
    this.return_msg = return_msg;
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
   * @Return the String mch_id
   */
  public String getMch_id() {
    return mch_id;
  }

  /**
   * @Param String mch_id to set
   */
  public void setMch_id(String mch_id) {
    this.mch_id = mch_id;
  }

  /**
   * @Return the String device_info
   */
  public String getDevice_info() {
    return device_info;
  }

  /**
   * @Param String device_info to set
   */
  public void setDevice_info(String device_info) {
    this.device_info = device_info;
  }

  /**
   * @Return the String result_code
   */
  public String getResult_code() {
    return result_code;
  }

  /**
   * @Param String result_code to set
   */
  public void setResult_code(String result_code) {
    this.result_code = result_code;
  }

  /**
   * @Return the String err_code
   */
  public String getErr_code() {
    return err_code;
  }

  /**
   * @Param String err_code to set
   */
  public void setErr_code(String err_code) {
    this.err_code = err_code;
  }

  /**
   * @Return the String err_code_des
   */
  public String getErr_code_des() {
    return err_code_des;
  }

  /**
   * @Param String err_code_des to set
   */
  public void setErr_code_des(String err_code_des) {
    this.err_code_des = err_code_des;
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
   * @Return the String is_subscribe
   */
  public String getIs_subscribe() {
    return is_subscribe;
  }

  /**
   * @Param String is_subscribe to set
   */
  public void setIs_subscribe(String is_subscribe) {
    this.is_subscribe = is_subscribe;
  }

  /**
   * @Return the String trade_type
   */
  public String getTrade_type() {
    return trade_type;
  }

  /**
   * @Param String trade_type to set
   */
  public void setTrade_type(String trade_type) {
    this.trade_type = trade_type;
  }

  /**
   * @Return the String trade_state
   */
  public String getTrade_state() {
    return trade_state;
  }

  /**
   * @Param String trade_state to set
   */
  public void setTrade_state(String trade_state) {
    this.trade_state = trade_state;
  }

  /**
   * @Return the String bank_type
   */
  public String getBank_type() {
    return bank_type;
  }

  /**
   * @Param String bank_type to set
   */
  public void setBank_type(String bank_type) {
    this.bank_type = bank_type;
  }

  /**
   * @Return the String cash_fee_type
   */
  public String getCash_fee_type() {
    return cash_fee_type;
  }

  /**
   * @Param String cash_fee_type to set
   */
  public void setCash_fee_type(String cash_fee_type) {
    this.cash_fee_type = cash_fee_type;
  }



  /**
   * @Return the String coupon_count
   */
  public String getCoupon_count() {
    return coupon_count;
  }

  /**
   * @Param String coupon_count to set
   */
  public void setCoupon_count(String coupon_count) {
    this.coupon_count = coupon_count;
  }

  /**
   * @Return the String transaction_id
   */
  public String getTransaction_id() {
    return transaction_id;
  }

  /**
   * @Param String transaction_id to set
   */
  public void setTransaction_id(String transaction_id) {
    this.transaction_id = transaction_id;
  }

  /**
   * @Return the String out_trade_no
   */
  public String getOut_trade_no() {
    return out_trade_no;
  }

  /**
   * @Param String out_trade_no to set
   */
  public void setOut_trade_no(String out_trade_no) {
    this.out_trade_no = out_trade_no;
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
   * @Return the String time_end
   */
  public String getTime_end() {
    return time_end;
  }

  /**
   * @Param String time_end to set
   */
  public void setTime_end(String time_end) {
    this.time_end = time_end;
  }

  /**
   * @Return the String trade_state_desc
   */
  public String getTrade_state_desc() {
    return trade_state_desc;
  }

  /**
   * @Param String trade_state_desc to set
   */
  public void setTrade_state_desc(String trade_state_desc) {
    this.trade_state_desc = trade_state_desc;
  }

  /**
   * @Return the String nonce_str
   */
  public String getNonce_str() {
    return nonce_str;
  }

  /**
   * @Param String nonce_str to set
   */
  public void setNonce_str(String nonce_str) {
    this.nonce_str = nonce_str;
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

  /**
   * @Return the String fee_type
   */
  public String getFee_type() {
    return fee_type;
  }

  /**
   * @Param String fee_type to set
   */
  public void setFee_type(String fee_type) {
    this.fee_type = fee_type;
  }

  /**
   * @Return the String coupon_list_json_string
   */
  public String getCoupon_list_json_string() {
    return coupon_list_json_string;
  }

  /**
   * @Param String coupon_list_json_string to set
   */
  public void setCoupon_list_json_string(String coupon_list_json_string) {
    this.coupon_list_json_string = coupon_list_json_string;
  }

  /**
   * @Return the Double total_fee
   */
  public Double getTotal_fee() {
    return total_fee;
  }

  /**
   * @Param Double total_fee to set
   */
  public void setTotal_fee(Double total_fee) {
    this.total_fee = total_fee;
  }

  /**
   * @Return the Double cash_fee
   */
  public Double getCash_fee() {
    return cash_fee;
  }

  /**
   * @Param Double cash_fee to set
   */
  public void setCash_fee(Double cash_fee) {
    this.cash_fee = cash_fee;
  }

  /**
   * @Return the Double settlement_total_fee
   */
  public Double getSettlement_total_fee() {
    return settlement_total_fee;
  }

  /**
   * @Param Double settlement_total_fee to set
   */
  public void setSettlement_total_fee(Double settlement_total_fee) {
    this.settlement_total_fee = settlement_total_fee;
  }

  /**
   * @Return the Double coupon_fee
   */
  public Double getCoupon_fee() {
    return coupon_fee;
  }

  /**
   * @Param Double coupon_fee to set
   */
  public void setCoupon_fee(Double coupon_fee) {
    this.coupon_fee = coupon_fee;
  }


}
