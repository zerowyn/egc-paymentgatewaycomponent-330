/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.dto;


import com.eg.egsc.framework.client.dto.BaseBusinessDto;

/**
 * @author gucunyang
 * @since 2018-02-24
 */
public class PageQueryDto extends BaseBusinessDto {

    private static final long serialVersionUID = -3547507246888669929L;

    /**
     * 页数
     */
    private Integer pageNo = 1;

    /**
     * 页大小
     */
    private Integer pageSize = 10;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
