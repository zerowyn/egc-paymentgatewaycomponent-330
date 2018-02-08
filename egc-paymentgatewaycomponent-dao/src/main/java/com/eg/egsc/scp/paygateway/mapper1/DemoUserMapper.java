/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.mapper1;

import com.eg.egsc.scp.paygateway.mapper1.entity.DemoUser;
import com.eg.egsc.scp.paygateway.mapper1.entity.DemoUserCriteria;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface DemoUserMapper {
    int countByExample(DemoUserCriteria example);

    int deleteByExample(DemoUserCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(DemoUser record);

    int insertSelective(DemoUser record);

    List<DemoUser> selectByExampleWithRowbounds(DemoUserCriteria example, RowBounds rowBounds);

    List<DemoUser> selectByExample(DemoUserCriteria example);

    DemoUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DemoUser record, @Param("example") DemoUserCriteria example);

    int updateByExample(@Param("record") DemoUser record, @Param("example") DemoUserCriteria example);

    int updateByPrimaryKeySelective(DemoUser record);

    int updateByPrimaryKey(DemoUser record);
    
    void insertByBatch(List<DemoUser> demoUsers);
}
