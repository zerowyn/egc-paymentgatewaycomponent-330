/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.mapper.entity;

import java.util.Date;

public class DefValSettings {
    private String uuid;

    private String platform;

    private String method;

    private String fieldName;

    private String value;

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
        DefValSettings other = (DefValSettings) that;
        return (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
            && (this.getPlatform() == null ? other.getPlatform() == null : this.getPlatform().equals(other.getPlatform()))
            && (this.getMethod() == null ? other.getMethod() == null : this.getMethod().equals(other.getMethod()))
            && (this.getFieldName() == null ? other.getFieldName() == null : this.getFieldName().equals(other.getFieldName()))
            && (this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue()))
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
        result = prime * result + ((getFieldName() == null) ? 0 : getFieldName().hashCode());
        result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
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
        sb.append(", fieldName=").append(fieldName);
        sb.append(", value=").append(value);
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