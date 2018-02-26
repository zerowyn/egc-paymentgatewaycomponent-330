/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.service.impl;

import com.eg.egsc.framework.paging.PageUtils;
import com.eg.egsc.scp.paygateway.dto.CodeMapsDto;
import com.eg.egsc.scp.paygateway.dto.PageQueryDto;
import com.eg.egsc.scp.paygateway.mapper.CodeMapsMapper;
import com.eg.egsc.scp.paygateway.mapper.entity.CodeMaps;
import com.eg.egsc.scp.paygateway.mapper.entity.CodeMapsCriteria;
import com.eg.egsc.scp.paygateway.service.CodeMapsSerivce;
import com.eg.egsc.scp.paygateway.util.CollectionUtil;
import com.eg.egsc.scp.paygateway.util.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gucunyang
 * @since 2018-02-24
 */
@Service
public class CodeMapsSerivceImpl implements CodeMapsSerivce {

    private static final Logger logger = LoggerFactory.getLogger(CodeMapsSerivceImpl.class);

    @Autowired
    private CodeMapsMapper codeMapsMapper;

    /**
     * 新增代码转换表信息
     *
     * @param codeMapsDto
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
     * @param codeMapsUuids
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
        codeMapsMapper.deleteByExample(codeMapsCriteria);
        logger.info("delete CodeMaps successful");
    }

    /**
     * 修改代码转换表信息
     *
     * @param codeMapsDto
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
     * @param uuid
     * @return
     */
    @Override
    public CodeMapsDto getCodeMapsByUuid(String uuid) {
        if (StringUtils.isEmpty(uuid)) {
            logger.error("Param uuids is empty!");
            return null;
        }
        logger.info("getCodeMapsByUuid start");
        CodeMaps codeMaps = codeMapsMapper.selectByPrimaryKey(uuid);
        CodeMapsDto codeMapsDto = new CodeMapsDto();
        codeMapsConvertToCodeMapsDto(codeMaps, codeMapsDto);
        logger.info("getCodeMapsByUuid successful");
        return codeMapsDto;
    }

    /**
     * 代码转换表信息分页查询
     *
     * @param pageQueryDto
     * @return
     */
    @Override
    public List<CodeMapsDto> getCodeMapsList(PageQueryDto pageQueryDto) {
        if (pageQueryDto == null) {
            logger.error("Param pageQueryDto is null!");
            return null;
        }
        logger.info("getCodeMapsList start");
        CodeMapsCriteria codeMapsCriteria = new CodeMapsCriteria();
        RowBounds rowBounds = new RowBounds(PageUtils.getOffset(pageQueryDto.getPageNo(), pageQueryDto.getPageSize()),
                pageQueryDto.getPageSize());
        List<CodeMaps> codeMapsList = codeMapsMapper.selectByExampleWithRowbounds(codeMapsCriteria, rowBounds);
        List<CodeMapsDto> codeMapsDtoList = codeMapsListConvertToCodeMapsDtoList(codeMapsList);
        logger.info("getCodeMapsList successful");
        return codeMapsDtoList;
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
        Short deleteFlag = codeMaps.getDeleteFlag();
        if (deleteFlag == null) {
            deleteFlag = 1;
        }
        codeMapsDto.setDeleteFlag(deleteFlag);
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
