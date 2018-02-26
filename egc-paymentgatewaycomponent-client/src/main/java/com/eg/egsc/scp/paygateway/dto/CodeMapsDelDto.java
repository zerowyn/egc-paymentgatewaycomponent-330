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
public class CodeMapsDelDto extends BaseBusinessDto {

    private static final long serialVersionUID = -7078587201825281128L;

    /**
     * 主键集合
     */
    private List<String> codeMapsUuids;

    public List<String> getCodeMapsUuids() {
        return codeMapsUuids;
    }

    public void setCodeMapsUuids(List<String> codeMapsUuids) {
        this.codeMapsUuids = codeMapsUuids;
    }
}
