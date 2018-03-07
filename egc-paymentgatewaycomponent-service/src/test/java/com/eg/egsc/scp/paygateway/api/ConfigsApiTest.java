/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.api;

import com.eg.egsc.framework.client.dto.RequestDto;
import com.eg.egsc.scp.paygateway.PaymentGatewayServiceApplication;
import com.eg.egsc.scp.paygateway.dto.ConfigsDelDto;
import com.eg.egsc.scp.paygateway.dto.ConfigsDto;
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
public class ConfigsApiTest extends BaseApiTest {

    /**
     * 测试新增配置表信息
     */
    @Test
    public void insertConfigsTest() {
        String url = "/api/configs/insertConfig";
        RequestDto<ConfigsDto> requestDto = new RequestDto();
        ConfigsDto configsDto = new ConfigsDto();
        configsDto.setRemark("insertConfigsTest");
        requestDto.setData(configsDto);
        getHead(url, requestDto);
    }

    /**
     * 测试删除配置表信息
     */
    @Test
    public void deleteConfigsTest() {
        String url = "/api/configs/deleteConfig";
        RequestDto<ConfigsDelDto> requestDto = new RequestDto();
        ConfigsDelDto configsDelDto = new ConfigsDelDto();
        List<String> configsUuids = new ArrayList<>();
        configsDelDto.setConfigsUuids(configsUuids);
        configsUuids.add("535939a6192643329986ca63bd583e13");
        requestDto.setData(configsDelDto);
        getHead(url, requestDto);
    }

    /**
     * 测试修改配置表信息
     */
    @Test
    public void updateConfigsTest() {
        String url = "/api/configs/updateConfig";
        RequestDto<ConfigsDto> requestDto = new RequestDto();
        ConfigsDto configsDto = new ConfigsDto();
        configsDto.setRemark("updateConfigsTest");
        configsDto.setUuid("626c80fa17d54570a6f303649d8fc989");
        requestDto.setData(configsDto);
        getHead(url, requestDto);
    }

    /**
     * 测试查询配置表信息
     */
    @Test
    public void getConfigsByUuid() {
        String url = "/api/configs/getConfig";
        RequestDto<ConfigsDto> requestDto = new RequestDto();
        ConfigsDto configsDto = new ConfigsDto();
        configsDto.setUuid("4cac476759ae4c5b9bcbf0defa9d3502");
        requestDto.setData(configsDto);
        getHead(url, requestDto);
    }

    /**
     * 测试配置表信息分页查询
     */
    @Test
    public void getConfigsList() {
        String url = "/api/configs/listConfigs";
        RequestDto<PageQueryDto> requestDto = new RequestDto();
        PageQueryDto pageQueryDto = new PageQueryDto();
        pageQueryDto.setPageNo(1);
        pageQueryDto.setPageSize(2);
        requestDto.setData(pageQueryDto);
        getHead(url, requestDto);
    }

}