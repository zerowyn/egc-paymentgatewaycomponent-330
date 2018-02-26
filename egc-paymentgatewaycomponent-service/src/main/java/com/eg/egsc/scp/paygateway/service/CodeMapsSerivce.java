/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.service;

import com.eg.egsc.scp.paygateway.dto.CodeMapsDto;
import com.eg.egsc.scp.paygateway.dto.PageQueryDto;

import java.util.List;

/**
 * 代码转换表
 *
 * @author gucunyang
 * @since 2018-02-11
 */
public interface CodeMapsSerivce {

    /**
     * 新增代码转换表信息
     *
     * @param codeMapsDto
     */
    void insertCodeMaps(CodeMapsDto codeMapsDto);

    /**
     * 删除代码转换表信息
     *
     * @param codeMapsUuids
     */
    void deleteCodeMaps(List<String> codeMapsUuids);

    /**
     * 修改代码转换表信息
     *
     * @param codeMapsDto
     */
    void updateCodeMaps(CodeMapsDto codeMapsDto);

    /**
     * 查询代码转换表信息
     *
     * @param uuid
     * @return
     */
    CodeMapsDto getCodeMapsByUuid(String uuid);

    /**
     * 代码转换表信息分页查询
     *
     * @param pageQueryDto
     * @return
     */
    List<CodeMapsDto> getCodeMapsList(PageQueryDto pageQueryDto);

}
