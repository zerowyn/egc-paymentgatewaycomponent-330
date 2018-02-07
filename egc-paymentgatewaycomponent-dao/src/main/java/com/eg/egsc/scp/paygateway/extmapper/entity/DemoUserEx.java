/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.extmapper.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.eg.egsc.scp.paygateway.mapper.entity.DemoRole;
import com.eg.egsc.scp.paygateway.mapper.entity.DemoUser;

/**
 * 扩展的用户对象
 * @author wanghongben
 * @since 2018年1月11日
 */
public class DemoUserEx extends DemoUser {
    @NotBlank(message = "demo.usermgnt.user.id.notblank")
    private String id;

    private String userCode;

    private String userName;

    private String userDesc;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String updateUser;

    private String tenantId;
    
    private List<DemoRole> roles;

    public List<DemoRole> getRoles() {
		return roles;
	}

	public void setRoles(List<DemoRole> roles) {
		this.roles = roles;
	}

	@Override
  public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getUserCode() {
        return userCode;
    }

    @Override
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getUserDesc() {
        return userDesc;
    }

    @Override
    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String getCreateUser() {
        return createUser;
    }

    @Override
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public String getUpdateUser() {
        return updateUser;
    }

    @Override
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
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
        DemoUserEx other = (DemoUserEx) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserCode() == null ? other.getUserCode() == null : this.getUserCode().equals(other.getUserCode()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getUserDesc() == null ? other.getUserDesc() == null : this.getUserDesc().equals(other.getUserDesc()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserCode() == null) ? 0 : getUserCode().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getUserDesc() == null) ? 0 : getUserDesc().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userCode=").append(userCode);
        sb.append(", userName=").append(userName);
        sb.append(", userDesc=").append(userDesc);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", tenantId=").append(tenantId);
        sb.append("]");
        return sb.toString();
    }
}
