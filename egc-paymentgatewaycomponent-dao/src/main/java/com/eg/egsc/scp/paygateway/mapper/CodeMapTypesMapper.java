/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.mapper;


import com.eg.egsc.scp.paygateway.mapper.entity.CodeMapTypes;
import com.eg.egsc.scp.paygateway.mapper.entity.CodeMapTypesCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CodeMapTypesMapper {
    int countByExample(CodeMapTypesCriteria example);

    int deleteByExample(CodeMapTypesCriteria example);

    int deleteByPrimaryKey(String uuid);

    int insert(CodeMapTypes record);

    int insertSelective(CodeMapTypes record);

    List<CodeMapTypes> selectByExampleWithRowbounds(CodeMapTypesCriteria example, RowBounds rowBounds);

    List<CodeMapTypes> selectByExample(CodeMapTypesCriteria example);

    CodeMapTypes selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") CodeMapTypes record, @Param("example") CodeMapTypesCriteria example);

    int updateByExample(@Param("record") CodeMapTypes record, @Param("example") CodeMapTypesCriteria example);

    int updateByPrimaryKeySelective(CodeMapTypes record);

    int updateByPrimaryKey(CodeMapTypes record);
}