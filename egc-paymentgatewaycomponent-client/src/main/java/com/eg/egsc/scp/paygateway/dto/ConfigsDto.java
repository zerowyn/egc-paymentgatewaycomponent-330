/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.dto;

/**
 * @author gucunyang
 * @since 2018-02-11
 */
public class ConfigsDto extends PaymentBusinessDto {

    private static final long serialVersionUID = -1009695950230041765L;

    /**
     * 配置项的类别代码，例如WEIXIN-URL或KEY。
     */
    private String typeCode;

    /**
     * 配置项的名称。对于外发URL类配置项，此处为方法名create，query
     * 对应KEY类配置项，此处为平台名称，如WEIXIN, ALIPAY-PUBLIC
     */
    private String configItem;

    /**
     * 配置项的值
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

    /**
     * 小区ID
     */
    private String courtUuid;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getConfigItem() {
        return configItem;
    }

    public void setConfigItem(String configItem) {
        this.configItem = configItem;
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

    public String getCourtUuid() {
        return courtUuid;
    }

    public void setCourtUuid(String courtUuid) {
        this.courtUuid = courtUuid;
    }

}
