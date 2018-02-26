/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.service;

import com.eg.egsc.scp.paygateway.dto.DefValSettingsDto;
import com.eg.egsc.scp.paygateway.dto.PageQueryDto;

import java.util.List;

/**
 * 默认设置表
 *
 * @author gucunyang
 * @since 2018-02-10
 */
public interface DefValSettingsService {

    /**
     * 新增默认设置表信息
     *
     * @param defValSettingsDto
     */
    void insertDefValSettings(DefValSettingsDto defValSettingsDto);

    /**
     * 删除默认设置表信息
     *
     * @param defValSettingsUuids
     */
    void deleteDefValSettings(List<String> defValSettingsUuids);

    /**
     * 修改默认设置表信息
     *
     * @param defValSettingsDto
     */
    void updateDefValSettings(DefValSettingsDto defValSettingsDto);

    /**
     * 查询默认设置表信息
     *
     * @param uuid
     * @return
     */
    DefValSettingsDto getDefValSettingsByUuid(String uuid);

    /**
     * 默认设置表信息分页查询
     *
     * @param pageQueryDto
     * @return
     */
    List<DefValSettingsDto> getDefValSettingsList(PageQueryDto pageQueryDto);

    /**
     * 查询默认设置表信息的值
     *
     * @param platform
     * @param method
     * @param fieldName
     * @return
     */
    String getDefValSettingsValueByExample(String platform, String method, String fieldName);

    /**
     * 查询默认设置表信息的值
     *
     * @param platform
     * @param fieldName
     * @return
     */
    String getDefValSettingsValueByExample(String platform, String fieldName);

}
