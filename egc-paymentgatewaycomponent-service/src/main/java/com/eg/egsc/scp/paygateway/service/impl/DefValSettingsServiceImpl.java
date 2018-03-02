/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.service.impl;

import com.eg.egsc.framework.paging.PageUtils;
import com.eg.egsc.scp.paygateway.dto.DefValSettingsDto;
import com.eg.egsc.scp.paygateway.dto.PageQueryDto;
import com.eg.egsc.scp.paygateway.mapper.DefValSettingsMapper;
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
     * @param defValSettingsDto 默认设置表对象
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
     * @param defValSettingsUuids 默认设置表主键集合
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
        DefValSettings defValSettings = new DefValSettings();
        defValSettings.setDeleteFlag((short) 0);
        defValSettingsMapper.updateByExampleSelective(defValSettings, defValSettingsCriteria);
        logger.info("delete DefValSettings successful");
    }

    /**
     * 修改默认设置表信息
     *
     * @param defValSettingsDto 默认设置表对象
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
     * @param uuid 默认设置表主键
     * @return 默认设置表对象
     */
    @Override
    public DefValSettingsDto getDefValSettingsByUuid(String uuid) {
        if (StringUtils.isEmpty(uuid)) {
            logger.error("Param uuids is empty!");
            return null;
        }
        logger.info("getDefValSettingsByUuid start");
        DefValSettingsCriteria defValSettingsCriteria = new DefValSettingsCriteria();
        defValSettingsCriteria.createCriteria().andUuidEqualTo(uuid).andDeleteFlagEqualTo((short) 1);
        List<DefValSettings> defValSettingsList = defValSettingsMapper.selectByExample(defValSettingsCriteria);
        DefValSettings defValSettings = getUpdateDefValSetting(defValSettingsList);
        if (defValSettings == null) {
            return null;
        }
        DefValSettingsDto defValSettingsDto = new DefValSettingsDto();
        defValSettingsConvertToDefValSettingsDto(defValSettings, defValSettingsDto);
        logger.info("getDefValSettingsByUuid successful");
        return defValSettingsDto;
    }

    /**
     * 默认设置表信息分页查询
     *
     * @param pageQueryDto 页面请求对象
     * @return 默认设置表对象集合
     */
    @Override
    public List<DefValSettingsDto> getDefValSettingsList(PageQueryDto pageQueryDto) {
        if (pageQueryDto == null) {
            logger.error("Param pageQueryDto is null!");
            return null;
        }
        logger.info("getDefValSettingsList start");
        DefValSettingsCriteria defValSettingsCriteria = new DefValSettingsCriteria();
        defValSettingsCriteria.createCriteria().andDeleteFlagEqualTo((short) 1);
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
     * @param platform  支付平台代码
     * @param method    消息方法的代码
     * @param fieldName 参数名
     * @return 参数的默认值
     */
    @Override
    public String getDefValSettingsValueByExample(String platform, String method, String fieldName) {
        if (StringUtils.isEmpty(platform) || StringUtils.isEmpty(fieldName)) {
            logger.error("Param is null!");
            return null;
        }
        List<DefValSettings> defValSettingsList = new ArrayList<>();
        if (!StringUtils.isEmpty(method)) {
            DefValSettingsCriteria defValSettingsCriteria = new DefValSettingsCriteria();
            defValSettingsCriteria.createCriteria().andPlatformEqualTo(platform).andMethodEqualTo(method)
                    .andFieldNameEqualTo(fieldName).andDeleteFlagEqualTo((short) 1);
            defValSettingsList = defValSettingsMapper.selectByExample(defValSettingsCriteria);
        }
        if (CollectionUtil.isEmpty(defValSettingsList) || StringUtils.isEmpty(method)) {
            return getDefValSettingsValueByExample(platform, fieldName);
        }
        return getUpdateDefValSetting(defValSettingsList).getValue();
    }

    /**
     * 查询默认设置表信息的值
     *
     * @param platform  支付平台代码
     * @param fieldName 参数名
     * @return 参数的默认值
     */
    @Override
    public String getDefValSettingsValueByExample(String platform, String fieldName) {
        if (StringUtils.isEmpty(platform) || StringUtils.isEmpty(fieldName)) {
            logger.error("Param is null!");
            return null;
        }
        DefValSettingsCriteria defValSettingsCriteria = new DefValSettingsCriteria();
        defValSettingsCriteria.createCriteria().andPlatformEqualTo(platform).andMethodIsNull()
                .andFieldNameEqualTo(fieldName).andDeleteFlagEqualTo((short) 1);
        List<DefValSettings> defValSettingsList = defValSettingsMapper.selectByExample(defValSettingsCriteria);
        DefValSettings defValSettings = getUpdateDefValSetting(defValSettingsList);
        return defValSettings != null ? defValSettings.getValue() : null;
    }

    /**
     * 获取默认设置表对象
     *
     * @param defValSettingsList 默认设置表对象集合
     * @return 默认设置表对象
     */
    private DefValSettings getUpdateDefValSetting(List<DefValSettings> defValSettingsList) {
        DefValSettings defValSettings = null;
        if (defValSettingsList.size() == 1) {
            defValSettings = defValSettingsList.get(0);
        }
        if (defValSettingsList.size() > 1) {
            Date createTime = defValSettingsList.get(0).getCreateTime();
            defValSettings = defValSettingsList.get(0);
            for (DefValSettings defValSettingsVal : defValSettingsList) {
                if (defValSettingsVal.getCreateTime().getTime() > createTime.getTime()) {
                    defValSettings = defValSettingsVal;
                }
            }
        }
        return defValSettings;
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
        defValSettingsDto.setDeleteFlag(defValSettings.getDeleteFlag());
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
