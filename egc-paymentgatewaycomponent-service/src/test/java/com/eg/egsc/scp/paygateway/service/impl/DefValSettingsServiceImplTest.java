/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.service.impl;

import com.eg.egsc.scp.paygateway.PaymentGatewayServiceApplication;
import com.eg.egsc.scp.paygateway.dto.DefValSettingsDto;
import com.eg.egsc.scp.paygateway.dto.PageQueryDto;
import com.eg.egsc.scp.paygateway.service.DefValSettingsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gucunyang
 * @since 2018-02-27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PaymentGatewayServiceApplication.class})
public class DefValSettingsServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(DefValSettingsServiceImplTest.class);

    @Autowired
    DefValSettingsService defValSettingsServiceImpl;

    /**
     * 测试新增默认设置表信息分支
     */
    @Test
    public void insertDefValSettings() {
        logger.info("test method insertDefValSettings: the branch that param is null");
        DefValSettingsDto defValSettingsDto = null;
        defValSettingsServiceImpl.insertDefValSettings(defValSettingsDto);
    }

    /**
     * 测试删除默认设置表信息分支
     */
    @Test
    public void deleteDefValSettings() {
        logger.info("test method deleteDefValSettings: the branch that param is empty");
        List<String> defValSettingsUuids = new ArrayList<>();
        defValSettingsServiceImpl.deleteDefValSettings(defValSettingsUuids);
    }

    /**
     * 测试修改默认设置表信息分支
     */
    @Test
    public void updateDefValSettings() {
        logger.info("test method updateDefValSettings: the branch that param is null");
        DefValSettingsDto defValSettingsDto = null;
        defValSettingsServiceImpl.updateDefValSettings(defValSettingsDto);
    }

    /**
     * 测试查询默认设置表信息分支
     */
    @Test
    public void getDefValSettingsByUuid() {
        logger.info("test method getDefValSettingsByUuid: the branch that param is empty");
        String uuid = "";
        defValSettingsServiceImpl.getDefValSettingsByUuid(uuid);
        uuid = "TEST";
        defValSettingsServiceImpl.getDefValSettingsByUuid(uuid);
    }

    /**
     * 测试默认设置表信息分页查询分支
     */
    @Test
    public void getDefValSettingsList() {
        logger.info("test method getDefValSettingsList: the branch that param is null");
        PageQueryDto pageQueryDto = null;
        defValSettingsServiceImpl.getDefValSettingsList(pageQueryDto);
    }

    /**
     * 测试查询默认设置表信息的值
     */
    @Test
    public void getDefValSettingsValueByExample() {
        logger.info("test method getDefValSettingsValueByExample: the branch that param is null");
        String platform = "";
        String method = "query";
        String fieldName = "method";
        defValSettingsServiceImpl.getDefValSettingsValueByExample(platform, method, fieldName);
        platform = "ALIPAY";
        defValSettingsServiceImpl.getDefValSettingsValueByExample(platform, method, fieldName);
        method = "";
        defValSettingsServiceImpl.getDefValSettingsValueByExample(platform, method, fieldName);


    }

    /**
     * 测试查询默认设置表信息的值重载方法
     */
    @Test
    public void getDefValSettingsValueByExampleWithoutMethod() {
        logger.info("test method getDefValSettingsValueByExampleWithoutMethod: the branch that param is null");
        String platform = "ALIPAY";
        String fieldName = "sign_type";
        String value = defValSettingsServiceImpl.getDefValSettingsValueByExample(platform, fieldName);
        logger.info("the value is:" + value);
    }

}