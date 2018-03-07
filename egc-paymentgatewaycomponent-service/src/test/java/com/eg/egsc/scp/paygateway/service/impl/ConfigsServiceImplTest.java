/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.service.impl;

import com.eg.egsc.scp.paygateway.PaymentGatewayServiceApplication;
import com.eg.egsc.scp.paygateway.dto.ConfigsDto;
import com.eg.egsc.scp.paygateway.dto.PageQueryDto;
import com.eg.egsc.scp.paygateway.service.ConfigsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author gucunyang
 * @since 2018-02-27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PaymentGatewayServiceApplication.class})
public class ConfigsServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(ConfigsServiceImplTest.class);

    @Autowired
    ConfigsService configsServiceImpl;

    /**
     * 测试新增配置表信息分支
     */
    @Test
    public void insertConfigs() {
        logger.info("test method insertConfigs: the branch that param is null");
        ConfigsDto configsDto = null;
        configsServiceImpl.insertConfigs(configsDto);
    }

    /**
     * 测试删除配置表信息分支
     */
    @Test
    public void deleteConfigs() {
        logger.info("test method deleteConfigs: the branch that param is empty");
        List<String> configsUuids = new ArrayList<>();
        configsServiceImpl.deleteConfigs(configsUuids);
    }

    /**
     * 测试修改配置表信息分支
     */
    @Test
    public void updateConfigs() {
        logger.info("test method updateConfigs: the branch that param is null");
        ConfigsDto configsDto = null;
        configsServiceImpl.updateConfigs(configsDto);
    }

    /**
     * 测试查询配置表信息分支
     */
    @Test
    public void getConfigsByUuid() {
        logger.info("test method getConfigsByUuid: the branch that param is empty");
        String uuid = "";
        configsServiceImpl.getConfigsByUuid(uuid);
        uuid = "TEST";
        configsServiceImpl.getConfigsByUuid(uuid);
    }

    /**
     * 测试配置表信息分页查询分支
     */
    @Test
    public void getConfigsList() {
        logger.info("test method getConfigsList: the branch that param is null");
        PageQueryDto pageQueryDto = null;
        configsServiceImpl.getConfigsList(pageQueryDto);
    }

    /**
     * 测试查询配置信息的值分支
     */
    @Test
    public void getConfigsValueByExample() {
        logger.info("test method getConfigsValueByExample: the branch that param is null");
        String typeCode = "WEIXIN-URL";
        String configItem = "";
        configsServiceImpl.getConfigsValueByExample(typeCode, configItem);
    }

}