/**   
* @Title: CacheTokenManager.java
* @Package com.linlong.f10.core.authentication
* @Description: TODO(用一句话描述该文件做什么)
* @author  yangwenjian   
* @date 2016年7月14日 上午5:41:43
* @version V1.0   
*/
package com.linlong.f10.core.authentication.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.linlong.f10.core.authentication.service.TokenManager;

/**
 * @ClassName: CacheTokenManager
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月14日 上午5:41:43
 * 
 */
@Service
public class CacheTokenManager implements TokenManager{
	private List<String> tokenList = new ArrayList<String>();
	/* (non-Javadoc)
	 * @see com.linlong.f10.core.authentication.TokenManager#containsToken(java.lang.String)
	 */
	@Override
	public synchronized boolean containsToken(String token) {
		return tokenList.contains(token);
	}

	/* (non-Javadoc)
	 * @see com.linlong.f10.core.authentication.TokenManager#addToken(java.lang.String)
	 */
	@Override
	public synchronized void addToken(String token) {
		tokenList.add(token);
	}

	/* (non-Javadoc)
	 * @see com.linlong.f10.core.authentication.TokenManager#removeToken(java.lang.String)
	 */
	@Override
	public synchronized void removeToken(String token) {
		//System.out.println("@knight removeToken: " + token);
		tokenList.remove(token);
	}

}
