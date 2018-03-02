/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.service;

import com.eg.egsc.scp.paygateway.dto.CodeMapsDto;
import com.eg.egsc.scp.paygateway.dto.PageQueryDto;

import java.util.List;
import java.util.Map;

/**
 * 代码转换表
 *
 * @author gucunyang
 * @since 2018-02-11
 */
public interface CodeMapsSerivce {

    /**
     * 新增代码转换表信息
     *
     * @param codeMapsDto 代码转换表数据对象
     */
    void insertCodeMaps(CodeMapsDto codeMapsDto);

    /**
     * 删除代码转换表信息
     *
     * @param codeMapsUuids 代码转换表主键列表
     */
    void deleteCodeMaps(List<String> codeMapsUuids);

    /**
     * 修改代码转换表信息
     *
     * @param codeMapsDto 代码转换表数据对象
     */
    void updateCodeMaps(CodeMapsDto codeMapsDto);

    /**
     * 查询代码转换表信息
     *
     * @param uuid 代码转换表主键
     * @return 代码转换表数据对象
     */
    CodeMapsDto getCodeMapsByUuid(String uuid);

    /**
     * 代码转换表信息分页查询
     *
     * @param pageQueryDto 页面请求对象
     * @return 代码转换表数据对象列表
     */
    List<CodeMapsDto> getCodeMapsList(PageQueryDto pageQueryDto);

    /**
     * 平台代码转换
     *
     * @param platform 支付平台
     * @param method 消息方法
     * @param exField 外部代码字段
     * @param exCode 外部代码值
     * @param exMsg 外部消息
     * @return 数据集合
     */
    Map excodeConvertToIncode(String platform, String method, String exField, String exCode, String exMsg);

    /**
     * 平台代码转换
     *
     * @param platform 支付平台
     * @param method 消息方法
     * @param exField 外部代码字段
     * @param exCode 外部代码值
     * @return 数据集合
     */
    Map excodeConvertToIncode(String platform, String method, String exField, String exCode);


}
