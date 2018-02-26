/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.dto;

import com.eg.egsc.framework.client.dto.BaseBusinessDto;

import java.util.List;

/**
 * @author gucunyang
 * @since 2018-02-24
 */
public class DefValSettingsDelDto extends BaseBusinessDto {

    private static final long serialVersionUID = -1641604924805454519L;

    /**
     * 主键集合
     */
    private List<String> defValSettingsUuids;

    public List<String> getDefValSettingsUuids() {
        return defValSettingsUuids;
    }

    public void setDefValSettingsUuids(List<String> defValSettingsUuids) {
        this.defValSettingsUuids = defValSettingsUuids;
    }
}
