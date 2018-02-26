/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.service.impl;

import com.eg.egsc.framework.paging.PageUtils;
import com.eg.egsc.scp.paygateway.dto.DefValSettingsDto;
import com.eg.egsc.scp.paygateway.dto.PageQueryDto;
import com.eg.egsc.scp.paygateway.mapper.DefValSettingsMapper;
import com.eg.egsc.scp.paygateway.mapper.entity.Configs;
import com.eg.egsc.scp.paygateway.mapper.entity.ConfigsCriteria;
import com.eg.egsc.scp.paygateway.mapper.entity.DefValSettings;
import com.eg.egsc.scp.paygateway.mapper.entity.DefValSettingsCriteria;
import com.eg.egsc.scp.paygateway.service.DefValSettingsService;
import com.eg.egsc.scp.paygateway.util.CollectionUtil;
import com.eg.egsc.scp.paygateway.util.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author gucunyang
 * @since 2018-02-24
 */
@Service
public class DefValSettingsServiceImpl implements DefValSettingsService {

    private static final Logger logger = LoggerFactory.getLogger(DefValSettingsServiceImpl.class);

    @Autowired
    private DefValSettingsMapper defValSettingsMapper;

    /**
     * 新增默认设置表信息
     *
     * @param defValSettingsDto
     */
    @Override
    public void insertDefValSettings(DefValSettingsDto defValSettingsDto) {
        if (defValSettingsDto == null) {
            logger.error("Param defValSettingsDto is null!");
            return;
        }
        logger.info("save DefValSettings start");
        String uuid = StringUtils.generateUuid();
        defValSettingsDto.setUuid(uuid);
        DefValSettings defValSettings = new DefValSettings();
        defValSettingsDtoConvertToDefValSettings(defValSettingsDto, defValSettings);
        defValSettingsMapper.insert(defValSettings);
        logger.info("save DefValSettings successful");
    }

    /**
     * 删除默认设置表信息
     *
     * @param defValSettingsUuids
     */
    @Override
    public void deleteDefValSettings(List<String> defValSettingsUuids) {
        if (CollectionUtil.isEmpty(defValSettingsUuids)) {
            logger.error("Param defValSettingsUuids is empty!");
            return;
        }
        logger.info("delete DefValSettings start");
        DefValSettingsCriteria defValSettingsCriteria = new DefValSettingsCriteria();
        defValSettingsCriteria.createCriteria().andUuidIn(defValSettingsUuids);
        defValSettingsMapper.deleteByExample(defValSettingsCriteria);
        logger.info("delete DefValSettings successful");
    }

    /**
     * 修改默认设置表信息
     *
     * @param defValSettingsDto
     */
    @Override
    public void updateDefValSettings(DefValSettingsDto defValSettingsDto) {
        if (defValSettingsDto == null) {
            logger.error("Param defValSettingsDto is null!");
            return;
        }
        logger.info("update DefValSettings start");
        DefValSettings defValSettings = new DefValSettings();
        defValSettingsDtoConvertToDefValSettings(defValSettingsDto, defValSettings);
        defValSettingsMapper.updateByPrimaryKeySelective(defValSettings);
        logger.info("update DefValSettings successful");
    }

    /**
     * 查询默认设置表信息
     *
     * @param uuid
     * @return
     */
    @Override
    public DefValSettingsDto getDefValSettingsByUuid(String uuid) {
        if (StringUtils.isEmpty(uuid)) {
            logger.error("Param uuids is empty!");
            return null;
        }
        logger.info("getDefValSettingsByUuid start");
        DefValSettings defValSettings = defValSettingsMapper.selectByPrimaryKey(uuid);
        DefValSettingsDto defValSettingsDto = new DefValSettingsDto();
        defValSettingsConvertToDefValSettingsDto(defValSettings, defValSettingsDto);
        logger.info("getDefValSettingsByUuid successful");
        return defValSettingsDto;
    }

    /**
     * 默认设置表信息分页查询
     *
     * @param pageQueryDto
     * @return
     */
    @Override
    public List<DefValSettingsDto> getDefValSettingsList(PageQueryDto pageQueryDto) {
        if (pageQueryDto == null) {
            logger.error("Param pageQueryDto is null!");
            return null;
        }
        logger.info("getDefValSettingsList start");
        DefValSettingsCriteria defValSettingsCriteria = new DefValSettingsCriteria();
        RowBounds rowBounds = new RowBounds(PageUtils.getOffset(pageQueryDto.getPageNo(), pageQueryDto.getPageSize()),
                pageQueryDto.getPageSize());
        List<DefValSettings> defValSettingsList
                = defValSettingsMapper.selectByExampleWithRowbounds(defValSettingsCriteria, rowBounds);
        List<DefValSettingsDto> defValSettingsDtoList
                = defValSettingsListConvertToDefValSettingsDtoList(defValSettingsList);
        logger.info("getDefValSettingsList successful");
        return defValSettingsDtoList;
    }

    /**
     * 查询默认设置表信息的值
     *
     * @param platform
     * @param method
     * @param fieldName
     * @return
     */
    @Override
    public String getDefValSettingsValueByExample(String platform, String method, String fieldName) {
        if (StringUtils.isEmpty(platform) || StringUtils.isEmpty(method) || StringUtils.isEmpty(fieldName)) {
            logger.error("Param is null!");
            return null;
        }
        logger.info("getDefValSettingsValueByExample start");
        DefValSettingsCriteria defValSettingsCriteria = new DefValSettingsCriteria();
        defValSettingsCriteria.createCriteria().andPlatformEqualTo(platform).andMethodEqualTo(method)
                .andFieldNameEqualTo(fieldName);
        List<DefValSettings> defValSettingsList = defValSettingsMapper.selectByExample(defValSettingsCriteria);
        if (CollectionUtil.isEmpty(defValSettingsList)) {
            return getDefValSettingsValueByExample(platform, fieldName);
        }
        return getUpdateValue(defValSettingsList);
    }

    /**
     * 查询默认设置表信息的值
     *
     * @param platform
     * @param fieldName
     * @return
     */
    @Override
    public String getDefValSettingsValueByExample(String platform, String fieldName) {
        if (StringUtils.isEmpty(platform) || StringUtils.isEmpty(fieldName)) {
            logger.error("Param is null!");
            return null;
        }
        logger.info("getDefValSettingsValueByExample start");
        DefValSettingsCriteria defValSettingsCriteria = new DefValSettingsCriteria();
        defValSettingsCriteria.createCriteria().andPlatformEqualTo(platform).andFieldNameEqualTo(fieldName);
        List<DefValSettings> defValSettingsList = defValSettingsMapper.selectByExample(defValSettingsCriteria);
        return getUpdateValue(defValSettingsList);
    }

    /**
     * 获取最新默认设置表值
     *
     * @param defValSettingsList
     * @return
     */
    private String getUpdateValue(List<DefValSettings> defValSettingsList) {
        String value = null;
        if (defValSettingsList.size() == 1) {
            value = defValSettingsList.get(0).getValue();
        }
        if (defValSettingsList.size() > 1) {
            Date createTime = defValSettingsList.get(0).getCreateTime();
            value = defValSettingsList.get(0).getValue();
            for (DefValSettings defValSettings : defValSettingsList) {
                if (defValSettings.getCreateTime().getTime() > createTime.getTime()) {
                    value = defValSettings.getValue();
                }
            }
        }
        return value;
    }


    /**
     * defValSettingsDtoConvertToDefValSettings
     *
     * @param defValSettingsDto
     * @param defValSettings
     */
    private void defValSettingsDtoConvertToDefValSettings(DefValSettingsDto defValSettingsDto, DefValSettings defValSettings) {
        defValSettings.setUuid(defValSettingsDto.getUuid());
        defValSettings.setPlatform(defValSettingsDto.getPlatform());
        defValSettings.setMethod(defValSettingsDto.getMethod());
        defValSettings.setFieldName(defValSettingsDto.getFieldName());
        defValSettings.setValue(defValSettingsDto.getValue());
        defValSettings.setRemark(defValSettingsDto.getRemark());
        Short deleteFlag = defValSettingsDto.getDeleteFlag();
        if (deleteFlag == null) {
            deleteFlag = 1;
        }
        defValSettings.setDeleteFlag(deleteFlag);
        defValSettings.setCreateTime(defValSettingsDto.getCreateTime());
        defValSettings.setUpdateTime(defValSettingsDto.getUpdateTime());
        defValSettings.setCreateUser(defValSettingsDto.getCreateUser());
        defValSettings.setUpdateUser(defValSettingsDto.getUpdateUser());
    }

    /**
     * defValSettingsConvertToDefValSettingsDto
     *
     * @param defValSettings
     * @param defValSettingsDto
     */
    private void defValSettingsConvertToDefValSettingsDto(DefValSettings defValSettings, DefValSettingsDto defValSettingsDto) {
        defValSettingsDto.setUuid(defValSettings.getUuid());
        defValSettingsDto.setPlatform(defValSettings.getPlatform());
        defValSettingsDto.setMethod(defValSettings.getMethod());
        defValSettingsDto.setFieldName(defValSettings.getFieldName());
        defValSettingsDto.setRemark(defValSettings.getRemark());
        defValSettingsDto.setValue(defValSettings.getValue());
        Short deleteFlag = defValSettings.getDeleteFlag();
        if (deleteFlag == null) {
            deleteFlag = 1;
        }
        defValSettingsDto.setDeleteFlag(deleteFlag);
        defValSettingsDto.setCreateTime(defValSettings.getCreateTime());
        defValSettingsDto.setUpdateTime(defValSettings.getUpdateTime());
        defValSettingsDto.setCreateUser(defValSettings.getCreateUser());
        defValSettingsDto.setUpdateUser(defValSettings.getUpdateUser());
    }

    /**
     * defValSettingsListConvertToDefValSettingsDtoList
     *
     * @param defValSettingsList
     * @return
     */
    private List<DefValSettingsDto> defValSettingsListConvertToDefValSettingsDtoList(List<DefValSettings> defValSettingsList) {
        List<DefValSettingsDto> defValSettingsDtoList = new ArrayList<>();
        for (DefValSettings defValSettings : defValSettingsList) {
            DefValSettingsDto defValSettingsDto = new DefValSettingsDto();
            defValSettingsConvertToDefValSettingsDto(defValSettings, defValSettingsDto);
            defValSettingsDtoList.add(defValSettingsDto);
        }
        return defValSettingsDtoList;
    }
}
