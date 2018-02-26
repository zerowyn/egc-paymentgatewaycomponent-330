/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.service;

import com.eg.egsc.scp.paygateway.dto.ConfigsDto;
import com.eg.egsc.scp.paygateway.dto.PageQueryDto;

import java.util.List;

/**
 * 配置表
 *
 * @author gucunyang
 * @since 2018-02-10
 */
public interface ConfigsService {

    /**
     * 新增配置表信息
     *
     * @param configsDto
     */
    void insertConfigs(ConfigsDto configsDto);

    /**
     * 删除配置表信息
     *
     * @param configsUuids
     */
    void deleteConfigs(List<String> configsUuids);

    /**
     * 修改配置表信息
     *
     * @param configsDto
     */
    void updateConfigs(ConfigsDto configsDto);

    /**
     * 查询配置表信息
     *
     * @param uuid
     * @return
     */
    ConfigsDto getConfigsByUuid(String uuid);

    /**
     * 配置表信息分页查询
     *
     * @param pageQueryDto
     * @return
     */
    List<ConfigsDto> getConfigsList(PageQueryDto pageQueryDto);

    /**
     * 查询配置信息的值
     *
     * @param typeCode
     * @param configItem
     * @return
     */
    String getConfigsValueByExample(String typeCode, String configItem);

}
