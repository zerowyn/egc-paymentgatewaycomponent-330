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
public class ConfigsDelDto extends BaseBusinessDto {

    private static final long serialVersionUID = 3991875179109807845L;

    /**
     * 主键集合
     */
    private List<String> configsUuids;

    public List<String> getConfigsUuids() {
        return configsUuids;
    }

    public void setConfigsUuids(List<String> configsUuids) {
        this.configsUuids = configsUuids;
    }
}
