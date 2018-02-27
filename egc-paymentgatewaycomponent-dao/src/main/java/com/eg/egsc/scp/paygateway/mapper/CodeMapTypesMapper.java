package com.eg.egsc.scp.paygateway.mapper;

import com.eg.egsc.scp.paygateway.mapper.entity.CodeMapTypes;
import com.eg.egsc.scp.paygateway.mapper.entity.CodeMapTypesCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

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