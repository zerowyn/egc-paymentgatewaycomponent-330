/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.mapper1;

import com.eg.egsc.scp.paygateway.mapper1.entity.DemoUserRole;
import com.eg.egsc.scp.paygateway.mapper1.entity.DemoUserRoleCriteria;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface DemoUserRoleMapper {
    int countByExample(DemoUserRoleCriteria example);

    int deleteByExample(DemoUserRoleCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(DemoUserRole record);

    int insertSelective(DemoUserRole record);

    List<DemoUserRole> selectByExampleWithRowbounds(DemoUserRoleCriteria example, RowBounds rowBounds);

    List<DemoUserRole> selectByExample(DemoUserRoleCriteria example);

    DemoUserRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DemoUserRole record, @Param("example") DemoUserRoleCriteria example);

    int updateByExample(@Param("record") DemoUserRole record, @Param("example") DemoUserRoleCriteria example);

    int updateByPrimaryKeySelective(DemoUserRole record);

    int updateByPrimaryKey(DemoUserRole record);
}
