/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.mapper;

import com.eg.egsc.scp.paygateway.mapper.entity.DefValSettings;
import com.eg.egsc.scp.paygateway.mapper.entity.DefValSettingsCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface DefValSettingsMapper {
    int countByExample(DefValSettingsCriteria example);

    int deleteByExample(DefValSettingsCriteria example);

    int deleteByPrimaryKey(String uuid);

    int insert(DefValSettings record);

    int insertSelective(DefValSettings record);

    List<DefValSettings> selectByExampleWithRowbounds(DefValSettingsCriteria example, RowBounds rowBounds);

    List<DefValSettings> selectByExample(DefValSettingsCriteria example);

    DefValSettings selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") DefValSettings record, @Param("example") DefValSettingsCriteria example);

    int updateByExample(@Param("record") DefValSettings record, @Param("example") DefValSettingsCriteria example);

    int updateByPrimaryKeySelective(DefValSettings record);

    int updateByPrimaryKey(DefValSettings record);
}