/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.api;

import com.eg.egsc.framework.client.dto.RequestDto;
import com.eg.egsc.scp.paygateway.PaymentGatewayServiceApplication;
import com.eg.egsc.scp.paygateway.dto.DefValSettingsDelDto;
import com.eg.egsc.scp.paygateway.dto.DefValSettingsDto;
import com.eg.egsc.scp.paygateway.dto.PageQueryDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gucunyang
 * @since 2018-02-11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PaymentGatewayServiceApplication.class})
@Transactional
@Rollback
public class DefValSettingsApiTest extends BaseApiTest {
    /**
     * 测试新增默认设置表信息
     */
    @Test
    public void insertDefValSettingsTest() {
        String url = "/api/defValSettings/insertDefValSetting";
        RequestDto<DefValSettingsDto> requestDto = new RequestDto();
        DefValSettingsDto defValSettingsDto = new DefValSettingsDto();
        defValSettingsDto.setRemark("insertDefValSettingsTest");
        requestDto.setData(defValSettingsDto);
        getHead(url, requestDto);
    }

    /**
     * 测试删除默认设置表信息
     */
    @Test
    public void deleteDefValSettingsTest() {
        String url = "/api/defValSettings/deleteDefValSetting";
        RequestDto<DefValSettingsDelDto> requestDto = new RequestDto();
        DefValSettingsDelDto defValSettingsDelDto = new DefValSettingsDelDto();
        List<String> defValSettingsUuids = new ArrayList<>();
        defValSettingsDelDto.setDefValSettingsUuids(defValSettingsUuids);
        defValSettingsUuids.add("da802c6ae8b944c197f2e136e2454b4c");
        requestDto.setData(defValSettingsDelDto);
        getHead(url, requestDto);
    }

    /**
     * 测试修改默认设置表信息
     */
    @Test
    public void updateDefValSettingsTest() {
        String url = "/api/defValSettings/updateDefValSetting";
        RequestDto<DefValSettingsDto> requestDto = new RequestDto();
        DefValSettingsDto defValSettingsDto = new DefValSettingsDto();
        defValSettingsDto.setRemark("updateDefValSettingsTest");
        defValSettingsDto.setUuid("6beb736f69f140c69d322c6b58cb49e3");
        requestDto.setData(defValSettingsDto);
        getHead(url, requestDto);
    }

    /**
     * 测试查询默认设置表信息
     */
    @Test
    public void getDefValSettingsByUuid() {
        String url = "/api/defValSettings/getDefValSetting";
        RequestDto<DefValSettingsDto> requestDto = new RequestDto();
        DefValSettingsDto defValSettingsDto = new DefValSettingsDto();
        defValSettingsDto.setUuid("184d1da7a0e040408511d5de58ce90d8");
        requestDto.setData(defValSettingsDto);
        getHead(url, requestDto);
    }

    /**
     * 测试默认设置表信息分页查询
     */
    @Test
    public void getDefValSettingsList() {
        String url = "/api/defValSettings/listDefValSettings";
        RequestDto<PageQueryDto> requestDto = new RequestDto();
        PageQueryDto pageQueryDto = new PageQueryDto();
        pageQueryDto.setPageNo(1);
        pageQueryDto.setPageSize(2);
        requestDto.setData(pageQueryDto);
        getHead(url, requestDto);
    }
}