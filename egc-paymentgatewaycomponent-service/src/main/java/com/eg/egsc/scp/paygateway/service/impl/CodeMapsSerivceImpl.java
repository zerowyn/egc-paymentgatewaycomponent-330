/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.service.impl;

import com.eg.egsc.framework.paging.PageUtils;
import com.eg.egsc.scp.paygateway.dto.CodeMapsDto;
import com.eg.egsc.scp.paygateway.dto.PageQueryDto;
import com.eg.egsc.scp.paygateway.mapper.CodeMapTypesMapper;
import com.eg.egsc.scp.paygateway.mapper.CodeMapsMapper;
import com.eg.egsc.scp.paygateway.mapper.entity.*;
import com.eg.egsc.scp.paygateway.service.CodeMapsSerivce;
import com.eg.egsc.scp.paygateway.util.CollectionUtil;
import com.eg.egsc.scp.paygateway.util.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author gucunyang
 * @since 2018-02-24
 */
@Service
public class CodeMapsSerivceImpl implements CodeMapsSerivce {

    private static final Logger logger = LoggerFactory.getLogger(CodeMapsSerivceImpl.class);

    @Autowired
    private CodeMapsMapper codeMapsMapper;

    @Autowired
    private CodeMapTypesMapper codeMapTypesMapper;

    /**
     * 新增代码转换表信息
     *
     * @param codeMapsDto 代码转换表数据对象
     */
    @Override
    public void insertCodeMaps(CodeMapsDto codeMapsDto) {
        if (codeMapsDto == null) {
            logger.error("Param codeMapsDto is null!");
            return;
        }
        logger.info("save CodeMaps start");
        String uuid = StringUtils.generateUuid();
        codeMapsDto.setUuid(uuid);
        CodeMaps codeMaps = new CodeMaps();
        codeMapsDtoConvertToCodeMaps(codeMapsDto, codeMaps);
        codeMapsMapper.insert(codeMaps);
        logger.info("save CodeMaps successful");
    }

    /**
     * 删除代码转换表信息
     *
     * @param codeMapsUuids 代码转换表主键集合
     */
    @Override
    public void deleteCodeMaps(List<String> codeMapsUuids) {
        if (CollectionUtil.isEmpty(codeMapsUuids)) {
            logger.error("Param codeMapsUuids is empty!");
            return;
        }
        logger.info("delete CodeMaps start");
        CodeMapsCriteria codeMapsCriteria = new CodeMapsCriteria();
        codeMapsCriteria.createCriteria().andUuidIn(codeMapsUuids);
        CodeMaps codeMaps = new CodeMaps();
        codeMaps.setDeleteFlag((short) 0);
        codeMapsMapper.updateByExampleSelective(codeMaps, codeMapsCriteria);
        logger.info("delete CodeMaps successful");
    }

    /**
     * 修改代码转换表信息
     *
     * @param codeMapsDto 代码转换表数据对象
     */
    @Override
    public void updateCodeMaps(CodeMapsDto codeMapsDto) {
        if (codeMapsDto == null) {
            logger.error("Param codeMapsDto is null!");
            return;
        }
        logger.info("update CodeMaps start");
        CodeMaps codeMaps = new CodeMaps();
        codeMapsDtoConvertToCodeMaps(codeMapsDto, codeMaps);
        codeMapsMapper.updateByPrimaryKeySelective(codeMaps);
        logger.info("update CodeMaps successful");
    }

    /**
     * 查询代码转换表信息
     *
     * @param uuid 代码转换表主键
     * @return 代码转换表数据对象
     */
    @Override
    public CodeMapsDto getCodeMapsByUuid(String uuid) {
        if (StringUtils.isEmpty(uuid)) {
            logger.error("Param uuids is empty!");
            return null;
        }
        logger.info("getCodeMapsByUuid start");
        CodeMapsCriteria codeMapsCriteria = new CodeMapsCriteria();
        codeMapsCriteria.createCriteria().andUuidEqualTo(uuid).andDeleteFlagEqualTo((short) 1);
        List<CodeMaps> codeMapsList = codeMapsMapper.selectByExample(codeMapsCriteria);
        CodeMaps codeMaps = getUpdateCodeMaps(codeMapsList);
        if (codeMaps == null) {
            return null;
        }
        CodeMapsDto codeMapsDto = new CodeMapsDto();
        codeMapsConvertToCodeMapsDto(codeMaps, codeMapsDto);
        logger.info("getCodeMapsByUuid successful");
        return codeMapsDto;
    }

    /**
     * 代码转换表信息分页查询
     *
     * @param pageQueryDto 页面请求对象
     * @return 代码转换表数据对象列表
     */
    @Override
    public List<CodeMapsDto> getCodeMapsList(PageQueryDto pageQueryDto) {
        List<CodeMapsDto> codeMapsDtoList = new ArrayList<>();
        if (pageQueryDto == null) {
            logger.error("Param pageQueryDto is null!");
            return codeMapsDtoList;
        }
        logger.info("getCodeMapsList start");
        CodeMapsCriteria codeMapsCriteria = new CodeMapsCriteria();
        codeMapsCriteria.createCriteria().andDeleteFlagEqualTo((short) 1);
        RowBounds rowBounds = new RowBounds(PageUtils.getOffset(pageQueryDto.getPageNo(), pageQueryDto.getPageSize()),
                pageQueryDto.getPageSize());
        List<CodeMaps> codeMapsList = codeMapsMapper.selectByExampleWithRowbounds(codeMapsCriteria, rowBounds);
        codeMapsDtoList = codeMapsListConvertToCodeMapsDtoList(codeMapsList);
        logger.info("getCodeMapsList successful");
        return codeMapsDtoList;
    }

    /**
     * 平台代码转换
     *
     * @param platform 支付平台
     * @param method   消息方法
     * @param exField  外部代码字段
     * @param exCode   外部代码值
     * @param exMsg    外部消息
     * @return 数据集合
     */
    @Override
    public Map excodeConvertToIncode(String platform, String method, String exField, String exCode, String exMsg) {

        if (StringUtils.isEmpty(platform) || StringUtils.isEmpty(exField)) {
            logger.error("Param is null!");
            return null;
        }
        List<CodeMapTypes> codeMapTypesList = new ArrayList<>();
        if (!StringUtils.isEmpty(method)) {
            CodeMapTypesCriteria codeMapTypesCriteria = new CodeMapTypesCriteria();
            codeMapTypesCriteria.createCriteria().andPlatformEqualTo(platform).andMethodEqualTo(method)
                    .andExFieldEqualTo(exField).andDeleteFlagEqualTo((short) 1);
            codeMapTypesList = codeMapTypesMapper.selectByExample(codeMapTypesCriteria);
        }
        if (CollectionUtil.isEmpty(codeMapTypesList) || StringUtils.isEmpty(method)) {
            codeMapTypesList = getListWithoutMethod(platform, exField);
        }
        return getResultMap(exCode, exMsg, codeMapTypesList);
    }

    /**
     * 平台代码转换
     *
     * @param platform 支付平台
     * @param method   消息方法
     * @param exField  外部代码字段
     * @param exCode   外部代码值
     * @return 数据集合
     */
    @Override
    public Map excodeConvertToIncode(String platform, String method, String exField, String exCode) {
        return excodeConvertToIncode(platform, method, exField, exCode, "");
    }


    /**
     * 获取返回结果集合
     *
     * @param exCode           外部代码值
     * @param exMsg            外部消息
     * @param codeMapTypesList 代码转换类型对象集合
     * @return 结果集合
     */
    private Map getResultMap(String exCode, String exMsg, List<CodeMapTypes> codeMapTypesList) {
        List<CodeMaps> codeMapsList = new ArrayList<>();
        for (CodeMapTypes codeMapTypes : codeMapTypesList) {
            String typeId = codeMapTypes.getUuid();
            if (StringUtils.isEmpty(typeId) || StringUtils.isEmpty(exCode)) {
                logger.error("Param is null!");
                return null;
            }
            CodeMapsCriteria codeMapsCriteria = new CodeMapsCriteria();
            codeMapsCriteria.createCriteria().andTypeIdEqualTo(typeId).andExCodeEqualTo(exCode)
                    .andDeleteFlagEqualTo((short) 1);
            List<CodeMaps> codeMapsListVal = codeMapsMapper.selectByExample(codeMapsCriteria);
            codeMapsList.addAll(codeMapsListVal);
        }
        CodeMaps codeMaps = getUpdateCodeMaps(codeMapsList);
        Map resultMap = new HashMap();
        if (codeMaps == null) {
            return resultMap;
        }
        CodeMapTypes codeMapTypes = codeMapTypesMapper.selectByPrimaryKey(codeMaps.getTypeId());
        resultMap.put(codeMapTypes.getInField(), codeMaps.getInCode());
        if (codeMapTypes.getMsgOverwrite() == 0 && !StringUtils.isEmpty(exMsg) && exMsg.equals(codeMaps.getExMsg())) {
            resultMap.put(codeMapTypes.getInMsgField(), codeMaps.getInMsg());
        }
        return resultMap;
    }

    /**
     * 获取代码转换对象集合
     *
     * @param codeMapsList 代码转换对象集合
     * @return 代码转换对象
     */
    private CodeMaps getUpdateCodeMaps(List<CodeMaps> codeMapsList) {
        CodeMaps codeMaps = null;
        if (codeMapsList.size() == 1) {
            codeMaps = codeMapsList.get(0);
        }
        if (codeMapsList.size() > 1) {
            Date createTime = codeMapsList.get(0).getCreateTime();
            codeMaps = codeMapsList.get(0);
            for (CodeMaps codeMapsVal : codeMapsList) {
                if (codeMapsVal.getCreateTime().getTime() > createTime.getTime()) {
                    codeMaps = codeMapsVal;
                }
            }
        }
        return codeMaps;
    }


    private List<CodeMapTypes> getListWithoutMethod(String platform, String exField) {
        CodeMapTypesCriteria codeMapTypesCriteria = new CodeMapTypesCriteria();
        codeMapTypesCriteria.createCriteria().andPlatformEqualTo(platform).andMethodIsNull()
                .andExFieldEqualTo(exField).andDeleteFlagEqualTo((short) 1);
        return codeMapTypesMapper.selectByExample(codeMapTypesCriteria);
    }

    /**
     * codeMapsDtoConvertToCodeMaps
     *
     * @param codeMapsDto
     * @param codeMaps
     */
    private void codeMapsDtoConvertToCodeMaps(CodeMapsDto codeMapsDto, CodeMaps codeMaps) {
        codeMaps.setUuid(codeMapsDto.getUuid());
        codeMaps.setTypeId(codeMapsDto.getTypeId());
        codeMaps.setCodeType(codeMapsDto.getCodeType());
        codeMaps.setExCode(codeMapsDto.getExCode());
        codeMaps.setInCode(codeMapsDto.getInCode());
        codeMaps.setExMsg(codeMapsDto.getExMsg());
        codeMaps.setInMsg(codeMapsDto.getInMsg());
        Short deleteFlag = codeMapsDto.getDeleteFlag();
        if (deleteFlag == null) {
            deleteFlag = 1;
        }
        codeMaps.setDeleteFlag(deleteFlag);
        codeMaps.setCreateTime(codeMapsDto.getCreateTime());
        codeMaps.setUpdateTime(codeMapsDto.getUpdateTime());
        codeMaps.setCreateUser(codeMapsDto.getCreateUser());
        codeMaps.setUpdateUser(codeMapsDto.getUpdateUser());
    }

    /**
     * codeMapsConvertToCodeMapsDto
     *
     * @param codeMapsDto
     * @param codeMaps
     */
    private void codeMapsConvertToCodeMapsDto(CodeMaps codeMaps, CodeMapsDto codeMapsDto) {
        codeMapsDto.setUuid(codeMaps.getUuid());
        codeMapsDto.setCodeType(codeMaps.getCodeType());
        codeMapsDto.setTypeId(codeMaps.getTypeId());
        codeMapsDto.setExCode(codeMaps.getExCode());
        codeMapsDto.setInCode(codeMaps.getInCode());
        codeMapsDto.setExMsg(codeMaps.getExMsg());
        codeMapsDto.setInMsg(codeMaps.getInMsg());
        codeMapsDto.setDeleteFlag(codeMaps.getDeleteFlag());
        codeMapsDto.setCreateTime(codeMaps.getCreateTime());
        codeMapsDto.setUpdateTime(codeMaps.getUpdateTime());
        codeMapsDto.setCreateUser(codeMaps.getCreateUser());
        codeMapsDto.setUpdateUser(codeMaps.getUpdateUser());
    }

    /**
     * codeMapsListConvertToCodeMapsDtoList
     *
     * @param codeMapsList
     * @return
     */
    private List<CodeMapsDto> codeMapsListConvertToCodeMapsDtoList(List<CodeMaps> codeMapsList) {
        List<CodeMapsDto> codeMapsDtoList = new ArrayList<>();
        for (CodeMaps codeMaps : codeMapsList) {
            CodeMapsDto codeMapsDto = new CodeMapsDto();
            codeMapsConvertToCodeMapsDto(codeMaps, codeMapsDto);
            codeMapsDtoList.add(codeMapsDto);
        }
        return codeMapsDtoList;
    }
}
