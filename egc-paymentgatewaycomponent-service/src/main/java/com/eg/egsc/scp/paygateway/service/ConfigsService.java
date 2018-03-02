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
     * @param configsDto 配置表对象
     */
    void insertConfigs(ConfigsDto configsDto);

    /**
     * 删除配置表信息
     *
     * @param configsUuids 配置表主键集合
     */
    void deleteConfigs(List<String> configsUuids);

    /**
     * 修改配置表信息
     *
     * @param configsDto 配置表对象
     */
    void updateConfigs(ConfigsDto configsDto);

    /**
     * 查询配置表信息
     *
     * @param uuid 配置表主键
     * @return 配置表对象
     */
    ConfigsDto getConfigsByUuid(String uuid);

    /**
     * 配置表信息分页查询
     *
     * @param pageQueryDto 页面请求对象
     * @return 配置表列表
     */
    List<ConfigsDto> getConfigsList(PageQueryDto pageQueryDto);

    /**
     * 查询配置信息的值
     *
     * @param typeCode   配置项的类别代码
     * @param configItem 配置项的名称
     * @return 配置项的值
     */
    String getConfigsValueByExample(String typeCode, String configItem);

}
