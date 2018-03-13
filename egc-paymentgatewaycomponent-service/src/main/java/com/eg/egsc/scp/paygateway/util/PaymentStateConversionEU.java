package com.eg.egsc.scp.paygateway.util;

public enum PaymentStateConversionEU {

    /**
     * TRADE_CLOSED
     * 透传给result_code
     * 代码转换(存入代码转换表)：
     * TRADE_CLOSED->FAIL	未付款交易超时关闭，或支付完成后全额退款
     * TRADE_SUCCESS->SUCCESS	交易支付成功
     * TRADE_FINISHED->FINISHED	交易结束，不可退款
     */
    TRADE_CLOSED("TRADE_CLOSED", "FAIL"),
    TRADE_SUCCESS("TRADE_SUCCESS", "SUCCESS"),
    TRADE_FINISHED("TRADE_FINISHED", "FINISHED");

    private String name;
    private String value;

    PaymentStateConversionEU(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static String getValue(String name) {
        for (PaymentStateConversionEU p : PaymentStateConversionEU.values()) {
            if (p.getName().equals(name)) {
                return p.value;
            }
        }
        return null;
    }

    public static String getName(String value) {
        for (PaymentStateConversionEU p : PaymentStateConversionEU.values()) {
            if (p.getValue().equals(value)) {
                return p.name;
            }
        }
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
