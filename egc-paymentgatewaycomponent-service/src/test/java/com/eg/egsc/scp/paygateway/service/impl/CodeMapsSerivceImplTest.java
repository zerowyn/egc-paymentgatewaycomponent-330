/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.service.impl;

import com.eg.egsc.scp.paygateway.PaymentGatewayServiceApplication;
import com.eg.egsc.scp.paygateway.dto.CodeMapsDto;
import com.eg.egsc.scp.paygateway.dto.PageQueryDto;
import com.eg.egsc.scp.paygateway.service.CodeMapsSerivce;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author gucunyang
 * @since 2018-02-27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PaymentGatewayServiceApplication.class})
public class CodeMapsSerivceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(CodeMapsSerivceImplTest.class);

    @Autowired
    CodeMapsSerivce codeMapsSerivceImpl;

    /**
     * 测试新增代码转换表信息分支
     */
    @Test
    public void insertCodeMaps() {
        logger.info("test method insertCodeMaps: the branch that param is null");
        CodeMapsDto configsDto = null;
        codeMapsSerivceImpl.insertCodeMaps(configsDto);
    }

    /**
     * 测试删除代码转换表信息分支
     */
    @Test
    public void deleteCodeMaps() {
        logger.info("test method deleteCodeMaps: the branch that param is empty");
        List<String> codeMapsUuids = new ArrayList<>();
        codeMapsSerivceImpl.deleteCodeMaps(codeMapsUuids);
    }

    /**
     * 测试修改代码转换表信息分支
     */
    @Test
    public void updateCodeMaps() {
        logger.info("test method updateCodeMaps: the branch that param is null");
        CodeMapsDto codeMapsDto = null;
        codeMapsSerivceImpl.updateCodeMaps(codeMapsDto);
    }

    /**
     * 测试查询代码转换表信息分支
     */
    @Test
    public void getCodeMapsByUuid() {
        logger.info("test method getCodeMapsByUuid: the branch that param is empty");
        String uuid = "";
        codeMapsSerivceImpl.getCodeMapsByUuid(uuid);
        String uuidDev = "e06008b0030b4bf9bfb5fae2dafb6c8d";
        codeMapsSerivceImpl.getCodeMapsByUuid(uuidDev);

    }

    /**
     * 测试代码转换表信息分页查询分支
     */
    @Test
    public void getCodeMapsList() {
        logger.info("test method getCodeMapsList: the branch that param is null");
        PageQueryDto pageQueryDto = null;
        codeMapsSerivceImpl.getCodeMapsList(pageQueryDto);
    }

    /**
     * 测试平台代码转换
     */
    @Test
    public void excodeConvertToIncode() {
        logger.info("test method excodeConvertToIncode start");
        logger.info("test the branch that param is null");
        String platform = " ";
        String method = "";
        String exField = "trade_status";
        String exCode = "TRADE_SUCCESS";
        Map result = codeMapsSerivceImpl.excodeConvertToIncode(platform, method, exField, exCode);
        logger.info("the result of the branch that param is null: " + result);
        logger.info("test the branch of getListWithoutMethod");
        platform = "ALIPAY";
        result = codeMapsSerivceImpl.excodeConvertToIncode(platform, method, exField, exCode);
        logger.info("the result of the branch of getListWithoutMethod: " + result);
        logger.info("test the branch of getListWithMethod");
        method = "query";
        result = codeMapsSerivceImpl.excodeConvertToIncode(platform, method, exField, exCode);
        logger.info("the result of the branch of getListWithMethod: " + result);
        logger.info("test the branch of getResultMap");
        exCode = "";
        result = codeMapsSerivceImpl.excodeConvertToIncode(platform, method, exField, exCode);
        logger.info("the result of the branch of getResultMap: " + result);
    }

}