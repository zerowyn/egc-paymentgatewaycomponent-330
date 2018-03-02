/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.dto;

import com.eg.egsc.framework.client.dto.BaseBusinessDto;

import java.util.Date;

/**
 * @author gucunyang
 * @since 2018-02-11
 */
public class PaymentBusinessDto extends BaseBusinessDto {

    private static final long serialVersionUID = -4295449118054562789L;

    /**
     * 表主键
     */
    private String uuid;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修订人
     */
    private String updateUser;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
}
