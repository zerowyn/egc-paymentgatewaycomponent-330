/**
 * @Probject Name: egc-paymentgatewaycomponent-service
 * @Path: com.eg.egsc.scp.paygateway.utilPaymentBusinessConstant.java
 * @Create By caiqinli
 * @Create In 2018年2月7日 下午3:06:20
 * TODO
 */
package com.eg.egsc.scp.paygateway.util;

/**
 * @Class Name PaymentBusinessConstant
 * @Author caiqinli
 * @Create In 2018年2月7日
 */
public class PaymentBusinessConstant {

    private PaymentBusinessConstant() {

    }

    public static final String WEI_XIN = "WEIXIN";

    public static final String ALI_PAY = "ALIPAY";

    public static final String KEY = "KEY";

    public static final String SIGN_TYPE_MD5 = "MD5";

    public static final String SIGN_TYPE_HMAC = "HmacSHA256";

    public static final String SIGN_TYPE_RSA = "RSA";

    public static final String SIGN_TYPE_RSA2 = "RSA2";

    public static final String RSA_INSTANCE_NAME = "SHA1WithRSA";

    public static final String RSA2_INSTANCE_NAME = "SHA256WithRSA";

    public static final String CHARSET_UTF8 = "utf-8";

    public static final String CHARSET_GBK = "GBK";

    public static final String CHARSET = "charset";

    public static final String SIGN_TYPE = "sign_type";

    public static final String SIGN = "sign";

    public static final String SUCCESS_CODE = "200";

    public static final String FAIL_MESSAGE = "500:支付网关：创建订单出现异常";

    public static final String PRODUCT_CODE = "QUICK_MSECURITY_PAY";

    public static final String SUCCESS_MESSAGE = "SUCCESS";

    public static final String PACKAGE="Sign=WXPay";

    public static final String MESSAGE_UNSUPPORT_SIGN_TYPE = "Unsupported Signature Type: ";
    
    public static final String QUERY = "query";
    
    public static final String TRADE_STATUS = "trade_status";
    
    public static final String TRADE_STATE = "trade_state";
    
    public static final String ACQ_TRADE_HAS_SUCCESS = "ACQ.TRADE_HAS_SUCCESS";
    
    public static final String NUM_ZERO = "0.00";
    
    public static final String OUT_TRADE_NO = "out_trade_no";
    
    public static final String TRADE_NO = "trade_no";
    
    public static final String COUPON_ID = "coupon_id_";
    
    public static final String COUPON_TYPE = "coupon_type_";
    
    public static final String COUPON_FEE = "coupon_fee_";
    
    public static final String RETURN_CODE_ERROR = "FAIL";    
 
    public static final String ALIPAY_RETURN_CODE_SUCCESS = "10000";
    
    public static final String WEIXIN_URL = "WEIXIN-URL";

    public static final String ALIPAY_URL = "ALIPAY-URL";    

    public static final String ALIPAY_APP_PRIVATE = "ALIPAY-APP-PRIVATE";
    
    public static final String ALIPAY_PUBLIC = "ALIPAY-PUBLIC";
    
    public static final String METHOD = "method";
    
    public static final String FORMAT = "format";
    
    public static final String VERSION = "version";
    
    public static final String JSONDEL =  "\":\"";

    public static final String CREATE_METHOD="create";

}
