/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.dto;

/**
 * @author gucunyang
 * @since 2018-02-24
 */
public class CodeMapsDto extends PaymentBusinessDto {

    private static final long serialVersionUID = 6116078228479397270L;

    /**
     * 代码转换类型表的uuid
     */
    private String typeId;

    /**
     * 代码的类别
     */
    private String codeType;

    /**
     * 外部代码
     */
    private String exCode;

    /**
     * 内部代码
     */
    private String inCode;

    /**
     * 外部信息的数据
     */
    private String exMsg;

    /**
     * 内部信息的数据
     */
    private String inMsg;

    /**
     * 逻辑删除标记：0-已删除 1-未删除（默认）
     */
    private Short deleteFlag;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getExCode() {
        return exCode;
    }

    public void setExCode(String exCode) {
        this.exCode = exCode;
    }

    public String getInCode() {
        return inCode;
    }

    public void setInCode(String inCode) {
        this.inCode = inCode;
    }

    public String getExMsg() {
        return exMsg;
    }

    public void setExMsg(String exMsg) {
        this.exMsg = exMsg;
    }

    public String getInMsg() {
        return inMsg;
    }

    public void setInMsg(String inMsg) {
        this.inMsg = inMsg;
    }

    public Short getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Short deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

}
