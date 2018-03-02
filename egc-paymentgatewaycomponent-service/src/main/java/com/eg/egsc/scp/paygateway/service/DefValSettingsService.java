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
     * @param defValSettingsDto 默认设置表对象
     */
    void insertDefValSettings(DefValSettingsDto defValSettingsDto);

    /**
     * 删除默认设置表信息
     *
     * @param defValSettingsUuids 默认设置表主键集合
     */
    void deleteDefValSettings(List<String> defValSettingsUuids);

    /**
     * 修改默认设置表信息
     *
     * @param defValSettingsDto 默认设置表对象
     */
    void updateDefValSettings(DefValSettingsDto defValSettingsDto);

    /**
     * 查询默认设置表信息
     *
     * @param uuid 默认设置表主键
     * @return 默认设置表对象
     */
    DefValSettingsDto getDefValSettingsByUuid(String uuid);

    /**
     * 默认设置表信息分页查询
     *
     * @param pageQueryDto 页面请求对象
     * @return 默认设置表对象集合
     */
    List<DefValSettingsDto> getDefValSettingsList(PageQueryDto pageQueryDto);

    /**
     * 查询默认设置表信息的值
     *
     * @param platform  支付平台代码
     * @param method    消息方法的代码
     * @param fieldName 参数名
     * @return 参数的默认值
     */
    String getDefValSettingsValueByExample(String platform, String method, String fieldName);

    /**
     * 查询默认设置表信息的值
     *
     * @param platform  支付平台代码
     * @param fieldName 参数名
     * @return 参数的默认值
     */
    String getDefValSettingsValueByExample(String platform, String fieldName);

}
