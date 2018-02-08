/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.mapper.entity;

import java.util.Date;

public class CodeMaps {
    private String uuid;

    private String typeId;

    private String codeType;

    private String exCode;

    private String inCode;

    private String exMsg;

    private String inMsg;

    private Short deleteFlag;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String updateUser;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CodeMaps other = (CodeMaps) that;
        return (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
            && (this.getTypeId() == null ? other.getTypeId() == null : this.getTypeId().equals(other.getTypeId()))
            && (this.getCodeType() == null ? other.getCodeType() == null : this.getCodeType().equals(other.getCodeType()))
            && (this.getExCode() == null ? other.getExCode() == null : this.getExCode().equals(other.getExCode()))
            && (this.getInCode() == null ? other.getInCode() == null : this.getInCode().equals(other.getInCode()))
            && (this.getExMsg() == null ? other.getExMsg() == null : this.getExMsg().equals(other.getExMsg()))
            && (this.getInMsg() == null ? other.getInMsg() == null : this.getInMsg().equals(other.getInMsg()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        result = prime * result + ((getTypeId() == null) ? 0 : getTypeId().hashCode());
        result = prime * result + ((getCodeType() == null) ? 0 : getCodeType().hashCode());
        result = prime * result + ((getExCode() == null) ? 0 : getExCode().hashCode());
        result = prime * result + ((getInCode() == null) ? 0 : getInCode().hashCode());
        result = prime * result + ((getExMsg() == null) ? 0 : getExMsg().hashCode());
        result = prime * result + ((getInMsg() == null) ? 0 : getInMsg().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uuid=").append(uuid);
        sb.append(", typeId=").append(typeId);
        sb.append(", codeType=").append(codeType);
        sb.append(", exCode=").append(exCode);
        sb.append(", inCode=").append(inCode);
        sb.append(", exMsg=").append(exMsg);
        sb.append(", inMsg=").append(inMsg);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateUser=").append(updateUser);
        sb.append("]");
        return sb.toString();
    }
}