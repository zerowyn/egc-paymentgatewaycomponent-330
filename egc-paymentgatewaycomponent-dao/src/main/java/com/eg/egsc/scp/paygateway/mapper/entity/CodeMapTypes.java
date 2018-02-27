package com.eg.egsc.scp.paygateway.mapper.entity;

import java.util.Date;

public class CodeMapTypes {
    private String uuid;

    private String platform;

    private String method;

    private String codeType;

    private String exField;

    private String inField;

    private Short msgOverwrite;

    private String exMsgField;

    private String inMsgField;

    private String remark;

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

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getExField() {
        return exField;
    }

    public void setExField(String exField) {
        this.exField = exField;
    }

    public String getInField() {
        return inField;
    }

    public void setInField(String inField) {
        this.inField = inField;
    }

    public Short getMsgOverwrite() {
        return msgOverwrite;
    }

    public void setMsgOverwrite(Short msgOverwrite) {
        this.msgOverwrite = msgOverwrite;
    }

    public String getExMsgField() {
        return exMsgField;
    }

    public void setExMsgField(String exMsgField) {
        this.exMsgField = exMsgField;
    }

    public String getInMsgField() {
        return inMsgField;
    }

    public void setInMsgField(String inMsgField) {
        this.inMsgField = inMsgField;
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
        CodeMapTypes other = (CodeMapTypes) that;
        return (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
            && (this.getPlatform() == null ? other.getPlatform() == null : this.getPlatform().equals(other.getPlatform()))
            && (this.getMethod() == null ? other.getMethod() == null : this.getMethod().equals(other.getMethod()))
            && (this.getCodeType() == null ? other.getCodeType() == null : this.getCodeType().equals(other.getCodeType()))
            && (this.getExField() == null ? other.getExField() == null : this.getExField().equals(other.getExField()))
            && (this.getInField() == null ? other.getInField() == null : this.getInField().equals(other.getInField()))
            && (this.getMsgOverwrite() == null ? other.getMsgOverwrite() == null : this.getMsgOverwrite().equals(other.getMsgOverwrite()))
            && (this.getExMsgField() == null ? other.getExMsgField() == null : this.getExMsgField().equals(other.getExMsgField()))
            && (this.getInMsgField() == null ? other.getInMsgField() == null : this.getInMsgField().equals(other.getInMsgField()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
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
        result = prime * result + ((getPlatform() == null) ? 0 : getPlatform().hashCode());
        result = prime * result + ((getMethod() == null) ? 0 : getMethod().hashCode());
        result = prime * result + ((getCodeType() == null) ? 0 : getCodeType().hashCode());
        result = prime * result + ((getExField() == null) ? 0 : getExField().hashCode());
        result = prime * result + ((getInField() == null) ? 0 : getInField().hashCode());
        result = prime * result + ((getMsgOverwrite() == null) ? 0 : getMsgOverwrite().hashCode());
        result = prime * result + ((getExMsgField() == null) ? 0 : getExMsgField().hashCode());
        result = prime * result + ((getInMsgField() == null) ? 0 : getInMsgField().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
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
        sb.append(", platform=").append(platform);
        sb.append(", method=").append(method);
        sb.append(", codeType=").append(codeType);
        sb.append(", exField=").append(exField);
        sb.append(", inField=").append(inField);
        sb.append(", msgOverwrite=").append(msgOverwrite);
        sb.append(", exMsgField=").append(exMsgField);
        sb.append(", inMsgField=").append(inMsgField);
        sb.append(", remark=").append(remark);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateUser=").append(updateUser);
        sb.append("]");
        return sb.toString();
    }
}