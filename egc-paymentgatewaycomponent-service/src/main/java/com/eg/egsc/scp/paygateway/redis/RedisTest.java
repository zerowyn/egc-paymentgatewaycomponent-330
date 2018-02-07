/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eg.egsc.common.component.redis.RedisUtils;

/**
 * redis工具类，把数据库中所有数据存放在内存中
 * @author wanghongben
 * @since 2018年1月11日
 */
@Component
public class RedisTest{
  private static final String REDISSET1 = "redisSet1"; 
  private static final String REDISSET2 = "redisSet2"; 
  @Autowired
  private RedisUtils redisUtils;
  
  /**
   * redis set方法
   */
  public void testSet() {
    redisUtils.set(REDISSET1, "你好");
    redisUtils.set(REDISSET2, "我的");
  }

  /**
   * redis testGet方法
   */
  public void testGet() {
    print();
    redisUtils.del(REDISSET1, REDISSET2, "redisSet3");
    print();
  }

  public void print() {
    redisUtils.get(REDISSET1);
    redisUtils.get(REDISSET2);
  }
  

}
