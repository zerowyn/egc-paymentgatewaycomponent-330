/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.mapper;

import com.eg.egsc.scp.paygateway.mapper.entity.CodeTypes;
import com.eg.egsc.scp.paygateway.mapper.entity.CodeTypesCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CodeTypesMapper {
    int countByExample(CodeTypesCriteria example);

    int deleteByExample(CodeTypesCriteria example);

    int deleteByPrimaryKey(String uuid);

    int insert(CodeTypes record);

    int insertSelective(CodeTypes record);

    List<CodeTypes> selectByExampleWithRowbounds(CodeTypesCriteria example, RowBounds rowBounds);

    List<CodeTypes> selectByExample(CodeTypesCriteria example);

    CodeTypes selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") CodeTypes record, @Param("example") CodeTypesCriteria example);

    int updateByExample(@Param("record") CodeTypes record, @Param("example") CodeTypesCriteria example);

    int updateByPrimaryKeySelective(CodeTypes record);

    int updateByPrimaryKey(CodeTypes record);
}