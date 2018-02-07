/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.mapper;

import com.eg.egsc.scp.paygateway.mapper.entity.DemoRole;
import com.eg.egsc.scp.paygateway.mapper.entity.DemoRoleCriteria;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface DemoRoleMapper {
    int countByExample(DemoRoleCriteria example);

    int deleteByExample(DemoRoleCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(DemoRole record);

    int insertSelective(DemoRole record);

    List<DemoRole> selectByExampleWithRowbounds(DemoRoleCriteria example, RowBounds rowBounds);

    List<DemoRole> selectByExample(DemoRoleCriteria example);

    DemoRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DemoRole record, @Param("example") DemoRoleCriteria example);

    int updateByExample(@Param("record") DemoRole record, @Param("example") DemoRoleCriteria example);

    int updateByPrimaryKeySelective(DemoRole record);

    int updateByPrimaryKey(DemoRole record);
}
