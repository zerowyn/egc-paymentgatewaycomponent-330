/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.api;

import com.eg.egsc.framework.client.dto.RequestDto;
import com.eg.egsc.scp.paygateway.PaymentGatewayServiceApplication;
import com.eg.egsc.scp.paygateway.dto.CodeMapsDelDto;
import com.eg.egsc.scp.paygateway.dto.CodeMapsDto;
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
 * @since 2018-02-24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PaymentGatewayServiceApplication.class})
@Transactional
@Rollback
public class CodeMapsApiTest extends BaseApiTest {

    /**
     * 测试新增代码转换表信息
     */
    @Test
    public void insertCodeMapsTest() {
        String url = "/api/codeMaps/insertCodeMap";
        RequestDto<CodeMapsDto> requestDto = new RequestDto();
        CodeMapsDto codeMapsDto = new CodeMapsDto();
        codeMapsDto.setExMsg("insertCodeMapsTest");
        requestDto.setData(codeMapsDto);
        getHead(url, requestDto);
    }

    /**
     * 测试删除代码转换表信息
     */
    @Test
    public void deleteCodeMapsTest() {
        String url = "/api/codeMaps/deleteCodeMap";
        RequestDto<CodeMapsDelDto> requestDto = new RequestDto();
        CodeMapsDelDto codeMapsDelDto = new CodeMapsDelDto();
        List<String> codeMapsUuids = new ArrayList<>();
        codeMapsDelDto.setCodeMapsUuids(codeMapsUuids);
        codeMapsUuids.add("535939a6192643329986ca63bd583e13");
        requestDto.setData(codeMapsDelDto);
        getHead(url, requestDto);
    }

    /**
     * 测试修改代码转换表信息
     */
    @Test
    public void updateCodeMapsTest() {
        String url = "/api/codeMaps/updateCodeMap";
        RequestDto<CodeMapsDto> requestDto = new RequestDto();
        CodeMapsDto codeMapsDto = new CodeMapsDto();
        codeMapsDto.setExMsg("updateCodeMapsTest");
        codeMapsDto.setUuid("c178ea4581804bf29a11e04f55e9e835");
        requestDto.setData(codeMapsDto);
        getHead(url, requestDto);
    }

    /**
     * 测试查询代码转换表信息
     */
    @Test
    public void getCodeMapsByUuid() {
        String url = "/api/codeMaps/getCodeMap";
        RequestDto<CodeMapsDto> requestDto = new RequestDto();
        CodeMapsDto codeMapsDto = new CodeMapsDto();
        codeMapsDto.setUuid("623394d38b6e47e9b5d24054abca36e8");
        requestDto.setData(codeMapsDto);
        getHead(url, requestDto);
    }

    /**
     * 测试分页查询代码转换表信息
     */
    @Test
    public void getCodeMapsList() {
        String url = "/api/codeMaps/listCodeMaps";
        RequestDto<PageQueryDto> requestDto = new RequestDto();
        PageQueryDto pageQueryDto = new PageQueryDto();
        pageQueryDto.setPageNo(1);
        pageQueryDto.setPageSize(2);
        requestDto.setData(pageQueryDto);
        getHead(url, requestDto);
    }


}