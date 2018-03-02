/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.dto;

/**
 * @author gucunyang
 * @since 2018-02-11
 */
public class DefValSettingsDto extends PaymentBusinessDto {

    private static final long serialVersionUID = 7406861594956795559L;

    /**
     * 支付平台代码，如：WEIXIN、ALIPAY
     */
    private String platform;

    /**
     * 消息方法的代码
     */
    private String method;

    /**
     * 参数名
     */
    private String fieldName;

    /**
     * 参数的默认值
     */
    private String value;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 逻辑删除标记：0-已删除 1-未删除（默认）
     */
    private Short deleteFlag;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Short getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Short deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

}
