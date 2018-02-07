/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.redis;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.eg.egsc.common.component.redis.RedisUtils;
import com.eg.egsc.config.AbstractUnitTestSupport;

/**
 * 测试Redis的功能类
 * 
 * @author wanghongben
 * @since 2018年1月11日
 */

public class TestRedis extends AbstractUnitTestSupport {
  protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RedisUtils redisUtils;
	private final String key = "test.set";
	private final String lockkey = "test.lock";
	private final String delkey = "test.del";
	private final String originkey = "origin.set";
	private final String deloriginkey = "origin.del";
	
	@Test
	public void getseq() {
		for (int i = 0; i < 10; i++) {
		  logger.info("---------------- generate [abc] = " + redisUtils.generate("abc"));
		}
	}

	@Test
	public void testSet() {
		String value = "redis测试";
		logger.info("---------------- set key [" + key + "], value = " + value);
		redisUtils.set(key, value);
	}

	@Test
	public void testGet() {
		get(key);
	}

	@Test
	public void testLock() {
	  logger.info("---------------- 1 lock [" + lockkey + "] = "
				+ redisUtils.lock(lockkey));
		try {
			Thread.sleep(1000);
			logger.info("---------------- 2 lock [" + lockkey + "] = "
					+ redisUtils.lock(lockkey));
			Thread.sleep(1000);
			logger.info("---------------- 3 lock [" + lockkey + "] = "
					+ redisUtils.lock(lockkey));
			Thread.sleep(1100);
			logger.info("---------------- 4 lock [" + lockkey + "] = "
					+ redisUtils.lock(lockkey));
		} catch (InterruptedException e) {
		  logger.error(e.getMessage());
			Thread.currentThread().interrupt();
		}
	}

	@Test
	public void testDel() {
		redisUtils.set(delkey, "redis删除测试");
		get(delkey);
		redisUtils.del(delkey);
		get(delkey);
	}

	@Test
	public void testSetOrigin() {
		String value = "redisOrigin测试";
		logger.info("---------------- set key [" + originkey + "], value = " + value);
		redisUtils.setOrigin(originkey, value);
	}
	
	@Test
	public void testGetOrigin() {
		getOrigin(originkey);
	}
	
	@Test
	public void testDelOrigin() {
		redisUtils.set(deloriginkey, "redisOrigin删除测试");
		get(deloriginkey);
		redisUtils.del(deloriginkey);
		get(deloriginkey);
	}
	
	public void get(String key) {
		Object value = redisUtils.get(key);
		if (null == value) {
		  logger.info("---------------- get [" + key + "] is null");
		} else {
		  logger.info("---------------- get [" + key + "] = " + value.toString());
		}
	}
	
	public void getOrigin(String key) {
		Object value = redisUtils.getOrigin(key);
		if (null == value) {
		  logger.info("---------------- getOrigin [" + key + "] is null");
		} else {
		  logger.info("---------------- getOrigin [" + key + "] = " + value.toString());
		}
	}

	
}
