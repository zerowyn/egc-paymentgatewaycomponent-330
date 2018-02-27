package com.eg.egsc.scp.paygateway.mapper;

import com.eg.egsc.scp.paygateway.mapper.entity.CodeMaps;
import com.eg.egsc.scp.paygateway.mapper.entity.CodeMapsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface CodeMapsMapper {
    int countByExample(CodeMapsCriteria example);

    int deleteByExample(CodeMapsCriteria example);

    int deleteByPrimaryKey(String uuid);

    int insert(CodeMaps record);

    int insertSelective(CodeMaps record);

    List<CodeMaps> selectByExampleWithRowbounds(CodeMapsCriteria example, RowBounds rowBounds);

    List<CodeMaps> selectByExample(CodeMapsCriteria example);

    CodeMaps selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") CodeMaps record, @Param("example") CodeMapsCriteria example);

    int updateByExample(@Param("record") CodeMaps record, @Param("example") CodeMapsCriteria example);

    int updateByPrimaryKeySelective(CodeMaps record);

    int updateByPrimaryKey(CodeMaps record);
}