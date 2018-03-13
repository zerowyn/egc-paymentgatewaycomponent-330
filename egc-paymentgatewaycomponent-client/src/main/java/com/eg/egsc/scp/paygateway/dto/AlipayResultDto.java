package com.eg.egsc.scp.paygateway.dto;

import java.util.Date;

public class AlipayResultDto {
    private Date notifyTime;   //Date  通知时间
    private String notifyType;   //String(64)  通知类型
    private String notifyId;   //String(128)  通知校验ID
    private String appId;   //String(32)  支付宝分配给开发者的应用Id
    private String charset;   //String(10)  编码格式
    private String version;   //String(3)  接口版本
    private String signType;   //String(10)  签名类型
    private String sign;   //String(256)  签名
    private String tradeNo;   //String(64)  支付宝交易号
    private String outTradeNo;   //String(64)  商户订单号
    private String outBizNo;   //String(64)  商户业务号
    private String buyerId;   //String(16)  买家支付宝用户号
    private String buyerLogonId;   //String(100)  买家支付宝账号
    private String sellerId;   //String(30)  卖家支付宝用户号
    private String sellerEmail;   //String(100)  卖家支付宝账号
    private String tradeStatus;   //String(32)  交易状态
    private Double totalAmount;   //Number(9,2)  订单金额
    private Double receiptAmount;   //Number(9,2)  实收金额
    private Double invoiceAmount;   //Number(9,2)  开票金额
    private Double buyerPayAmount;   //Number(9,2)  付款金额
    private Double pointAmount;   //Number(9,2)  集分宝金额
    private Double refundFee;   //Number(9,2)  总退款金额
    private String subject;   //String(256)  订单标题
    private String body;   //String(400)  商品描述
    private Date gmtCreate;   //Date  交易创建时间
    private Date gmtPayment;   //Date  交易付款时间
    private Date gmtRefund;   //Date  交易退款时间
    private Date gmtClose;   //Date  交易结束时间
    private String fundBillList;   //String(512)  支付金额信息
    private String fundChannel;   //String  支付渠道
    private String amount;   //String  支付金额
    private String sumaMount;   //  支付金额合计
    private String countFundBillList;   //  fund_bill_list实例合计
    private String passbackParams;   //String(512)  回传参数
    private String voucherDetailList;
    ;   //String 优惠券信息


    public Date getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
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

    public String getOutBizNo() {
        return outBizNo;
    }

    public void setOutBizNo(String outBizNo) {
        this.outBizNo = outBizNo;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerLogonId() {
        return buyerLogonId;
    }

    public void setBuyerLogonId(String buyerLogonId) {
        this.buyerLogonId = buyerLogonId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(Double receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public Double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Double getBuyerPayAmount() {
        return buyerPayAmount;
    }

    public void setBuyerPayAmount(Double buyerPayAmount) {
        this.buyerPayAmount = buyerPayAmount;
    }

    public Double getPointAmount() {
        return pointAmount;
    }

    public void setPointAmount(Double pointAmount) {
        this.pointAmount = pointAmount;
    }

    public Double getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Double refundFee) {
        this.refundFee = refundFee;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtPayment() {
        return gmtPayment;
    }

    public void setGmtPayment(Date gmtPayment) {
        this.gmtPayment = gmtPayment;
    }

    public Date getGmtRefund() {
        return gmtRefund;
    }

    public void setGmtRefund(Date gmtRefund) {
        this.gmtRefund = gmtRefund;
    }

    public Date getGmtClose() {
        return gmtClose;
    }

    public void setGmtClose(Date gmtClose) {
        this.gmtClose = gmtClose;
    }

    public String getFundBillList() {
        return fundBillList;
    }

    public void setFundBillList(String fundBillList) {
        this.fundBillList = fundBillList;
    }

    public String getFundChannel() {
        return fundChannel;
    }

    public void setFundChannel(String fundChannel) {
        this.fundChannel = fundChannel;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSumaMount() {
        return sumaMount;
    }

    public void setSumaMount(String sumaMount) {
        this.sumaMount = sumaMount;
    }

    public String getCountFundBillList() {
        return countFundBillList;
    }

    public void setCountFundBillList(String countFundBillList) {
        this.countFundBillList = countFundBillList;
    }

    public String getPassbackParams() {
        return passbackParams;
    }

    public void setPassbackParams(String passbackParams) {
        this.passbackParams = passbackParams;
    }

    public String getVoucherDetailList() {
        return voucherDetailList;
    }

    public void setVoucherDetailList(String voucherDetailList) {
        this.voucherDetailList = voucherDetailList;
    }
}
