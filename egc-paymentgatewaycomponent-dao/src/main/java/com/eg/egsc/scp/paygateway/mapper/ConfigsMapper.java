/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.mapper;

import com.eg.egsc.scp.demo.mapper.entity.Configs;
import com.eg.egsc.scp.demo.mapper.entity.ConfigsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ConfigsMapper {
    int countByExample(ConfigsCriteria example);

    int deleteByExample(ConfigsCriteria example);

    int deleteByPrimaryKey(String uuid);

    int insert(Configs record);

    int insertSelective(Configs record);

    List<Configs> selectByExampleWithRowbounds(ConfigsCriteria example, RowBounds rowBounds);

    List<Configs> selectByExample(ConfigsCriteria example);

    Configs selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") Configs record, @Param("example") ConfigsCriteria example);

    int updateByExample(@Param("record") Configs record, @Param("example") ConfigsCriteria example);

    int updateByPrimaryKeySelective(Configs record);

    int updateByPrimaryKey(Configs record);
}