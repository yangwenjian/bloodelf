/**   
 * @Title: RedisTokenmanager.java
 * @Package com.linlong.f10.core.authentication.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年10月24日 下午4:16:52
 * @version V1.0   
 */
package com.linlong.f10.core.authentication.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.linlong.f10.core.authentication.service.TokenManager;

/**
 * @ClassName: RedisTokenmanager
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年10月24日 下午4:16:52
 * 
 */
@Service
public class RedisTokenManager implements TokenManager {

	@Autowired
	private RedisTemplate<String, List<String>> redisTemplate;
	private List<String> tokenList;
	private static final String KEY = "token";
	private boolean init = false;

	public void init() {
		tokenList = new ArrayList<String>();
		redisTemplate.boundValueOps(KEY).set(tokenList);
		init = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.core.authentication.service.TokenManager#containsToken
	 * (java.lang.String)
	 */
	@Override
	public boolean containsToken(String token) {
		if (init == false) {
			init();
		}
		tokenList = redisTemplate.boundValueOps(KEY).get();
		return tokenList.contains(token);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.core.authentication.service.TokenManager#addToken(java
	 * .lang.String)
	 */
	@Override
	public void addToken(String validatedToken) {
		if (init == false) {
			init();
		}
		tokenList = redisTemplate.boundValueOps(KEY).get();
		tokenList.add(validatedToken);
		redisTemplate.boundValueOps(KEY).set(tokenList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.core.authentication.service.TokenManager#removeToken(
	 * java.lang.String)
	 */
	@Override
	public void removeToken(String tokenId) {
		if (init == false) {
			init();
		}
		tokenList = redisTemplate.boundValueOps(KEY).get();
		System.out.println("@knight removeToken: " + tokenId);
		tokenList.remove(tokenId);
		redisTemplate.boundValueOps(KEY).set(tokenList);
	}

}
