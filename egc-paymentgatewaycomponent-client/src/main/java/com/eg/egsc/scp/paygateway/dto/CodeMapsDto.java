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

    private String typeId;

    private String codeType;

    private String exCode;

    private String inCode;

    private String exMsg;

    private String inMsg;

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
