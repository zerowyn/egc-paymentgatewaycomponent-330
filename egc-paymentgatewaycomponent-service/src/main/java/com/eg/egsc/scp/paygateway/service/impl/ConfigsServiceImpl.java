/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.service.impl;

import com.eg.egsc.framework.paging.PageUtils;
import com.eg.egsc.scp.paygateway.dto.ConfigsDto;
import com.eg.egsc.scp.paygateway.dto.PageQueryDto;
import com.eg.egsc.scp.paygateway.mapper.ConfigsMapper;
import com.eg.egsc.scp.paygateway.mapper.entity.Configs;
import com.eg.egsc.scp.paygateway.mapper.entity.ConfigsCriteria;
import com.eg.egsc.scp.paygateway.service.ConfigsService;
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
 * @since 2018-02-10
 */
@Service
public class ConfigsServiceImpl implements ConfigsService {

    private static final Logger logger = LoggerFactory.getLogger(ConfigsServiceImpl.class);

    @Autowired
    private ConfigsMapper configsMapper;

    /**
     * 新增配置表信息
     *
     * @param configsDto 配置表对象
     */
    @Override
    public void insertConfigs(ConfigsDto configsDto) {
        if (configsDto == null) {
            logger.error("Param configsDto is null!");
            return;
        }
        logger.info("save Configs start");
        String uuid = StringUtils.generateUuid();
        configsDto.setUuid(uuid);
        Configs configs = new Configs();
        configsDtoConvertToConfigs(configsDto, configs);
        configsMapper.insert(configs);
        logger.info("save Configs successful");
    }

    /**
     * 删除配置表信息
     *
     * @param configsUuids 配置表主键集合
     */
    @Override
    public void deleteConfigs(List<String> configsUuids) {
        if (CollectionUtil.isEmpty(configsUuids)) {
            logger.error("Param configsUuids is empty!");
            return;
        }
        logger.info("delete Configs start");
        ConfigsCriteria configsCriteria = new ConfigsCriteria();
        configsCriteria.createCriteria().andUuidIn(configsUuids);
        Configs configs = new Configs();
        configs.setDeleteFlag((short) 1);
        configsMapper.updateByExampleSelective(configs, configsCriteria);
        logger.info("delete Configs successful");
    }

    /**
     * 修改配置表信息
     *
     * @param configsDto 配置表对象
     */
    @Override
    public void updateConfigs(ConfigsDto configsDto) {
        if (configsDto == null) {
            logger.error("Param configsDto is null!");
            return;
        }
        logger.info("update Configs start");
        Configs configs = new Configs();
        configsDtoConvertToConfigs(configsDto, configs);
        configsMapper.updateByPrimaryKeySelective(configs);
        logger.info("update Configs successful");
    }

    /**
     * 查询配置表信息
     *
     * @param uuid 配置表主键
     * @return 配置表对象
     */
    @Override
    public ConfigsDto getConfigsByUuid(String uuid) {
        if (StringUtils.isEmpty(uuid)) {
            logger.error("Param uuids is empty!");
            return null;
        }
        logger.info("getConfigsByUuid start");
        ConfigsCriteria configsCriteria = new ConfigsCriteria();
        configsCriteria.createCriteria().andUuidEqualTo(uuid).andDeleteFlagEqualTo((short) 1);
        List<Configs> configsList = configsMapper.selectByExample(configsCriteria);
        Configs configs = getUpdateConfig(configsList);
        if (configs == null) {
            return null;
        }
        ConfigsDto configsDto = new ConfigsDto();
        configsConvertToConfigsDto(configs, configsDto);
        logger.info("getConfigsByUuid successful");
        return configsDto;
    }

    /**
     * 配置表信息分页查询
     *
     * @param pageQueryDto 页面请求对象
     * @return 配置表列表
     */
    @Override
    public List<ConfigsDto> getConfigsList(PageQueryDto pageQueryDto) {
        List<ConfigsDto> configsDtoList = new ArrayList<>();
        if (pageQueryDto == null) {
            logger.error("Param pageQueryDto is null!");
            return configsDtoList;
        }
        logger.info("getConfigsList start");
        ConfigsCriteria configsCriteria = new ConfigsCriteria();
        configsCriteria.createCriteria().andDeleteFlagEqualTo((short) 1);
        RowBounds rowBounds = new RowBounds(PageUtils.getOffset(pageQueryDto.getPageNo(), pageQueryDto.getPageSize()),
                pageQueryDto.getPageSize());
        List<Configs> configsList = configsMapper.selectByExampleWithRowbounds(configsCriteria, rowBounds);
        configsDtoList = configsListConvertToConfigsDtoList(configsList);
        logger.info("getConfigsList successful");
        return configsDtoList;
    }

    /**
     * 查询配置信息的值
     *
     * @param typeCode   配置项的类别代码
     * @param configItem 配置项的名称
     * @return 配置项的值
     */
    @Override
    public String getConfigsValueByExample(String typeCode, String configItem) {
        if (StringUtils.isEmpty(typeCode) || StringUtils.isEmpty(configItem)) {
            logger.error("Param is null!");
            return null;
        }
        ConfigsCriteria configsCriteria = new ConfigsCriteria();
        configsCriteria.createCriteria().andTypeCodeEqualTo(typeCode).andConfigItemEqualTo(configItem)
                .andDeleteFlagEqualTo((short) 1);
        List<Configs> configsList = configsMapper.selectByExample(configsCriteria);
        Configs configs = getUpdateConfig(configsList);
        return configs != null ? configs.getValue() : null;
    }

    /**
     * 获取配置表对象
     *
     * @param configsList 配置表对象集合
     * @return 配置表对象
     */
    private Configs getUpdateConfig(List<Configs> configsList) {
        Configs configs = null;
        if (configsList.size() == 1) {
            configs = configsList.get(0);
        }
        if (configsList.size() > 1) {
            Date createTime = configsList.get(0).getCreateTime();
            configs = configsList.get(0);
            for (Configs configsVal : configsList) {
                if (configsVal.getCreateTime().getTime() > createTime.getTime()) {
                    configs = configsVal;
                }
            }
        }
        return configs;
    }


    /**
     * configsDtoConvertToConfigs
     *
     * @param configsDto
     * @param configs
     */
    private void configsDtoConvertToConfigs(ConfigsDto configsDto, Configs configs) {
        configs.setUuid(configsDto.getUuid());
        configs.setTypeCode(configsDto.getTypeCode());
        configs.setConfigItem(configsDto.getConfigItem());
        configs.setValue(configsDto.getValue());
        configs.setRemark(configsDto.getRemark());
        Short deleteFlag = configsDto.getDeleteFlag();
        if (deleteFlag == null) {
            deleteFlag = 1;
        }
        configs.setDeleteFlag(deleteFlag);
        configs.setCreateTime(configsDto.getCreateTime());
        configs.setUpdateTime(configsDto.getUpdateTime());
        configs.setCreateUser(configsDto.getCreateUser());
        configs.setUpdateUser(configsDto.getUpdateUser());
        configs.setCourtUuid(configsDto.getCourtUuid());
    }

    /**
     * configsConvertToConfigsDto
     *
     * @param configs
     * @param configsDto
     */
    private void configsConvertToConfigsDto(Configs configs, ConfigsDto configsDto) {
        configsDto.setUuid(configs.getUuid());
        configsDto.setTypeCode(configs.getTypeCode());
        configsDto.setConfigItem(configs.getConfigItem());
        configsDto.setRemark(configs.getRemark());
        configsDto.setValue(configs.getValue());
        configsDto.setDeleteFlag(configs.getDeleteFlag());
        configsDto.setCreateTime(configs.getCreateTime());
        configsDto.setUpdateTime(configs.getUpdateTime());
        configsDto.setCreateUser(configs.getCreateUser());
        configsDto.setUpdateUser(configs.getUpdateUser());
        configsDto.setCourtUuid(configs.getCourtUuid());
    }

    /**
     * configsListConvertToConfigsDtoList
     *
     * @param configsList
     * @return
     */
    private List<ConfigsDto> configsListConvertToConfigsDtoList(List<Configs> configsList) {
        List<ConfigsDto> configsDtoList = new ArrayList<>();
        for (Configs configs : configsList) {
            ConfigsDto configsDto = new ConfigsDto();
            configsConvertToConfigsDto(configs, configsDto);
            configsDtoList.add(configsDto);
        }
        return configsDtoList;
    }
}
