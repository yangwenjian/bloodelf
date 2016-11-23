/**   
 * @Title: F10AuthenticationService.java
 * @Package com.linlong.f10.core.authentication
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月13日 上午11:18:54
 * @version V1.0   
 */
package com.linlong.f10.core.authentication.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.linlong.f10.core.authentication.service.AuthService;
import com.linlong.f10.core.authentication.service.TokenManager;
import com.linlong.f10.core.util.https.Protocol;
import com.linlong.f10.core.util.https.ProtocolEnum;
import com.linlong.f10.core.util.https.ProtocolFactory;

/**
 * @ClassName: F10AuthenticationService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月13日 上午11:18:54
 * 
 */
@Service("f10AuthService")
public class F10AuthService implements AuthService {

	@Value("${authentication.pc.url}")
	private String pcvalidateUrl;

	@Value("${authentication.ios.url}")
	private String iosvalidateUrl;

	@Value("${authentication.android.url}")
	private String androidvalidateUrl;

	@Resource(name = "cacheTokenManager")
	private TokenManager tokenManager;

	@Resource(name = "httsProtocol")
	private Protocol protocol;

	private static Logger LOGGER = Logger.getLogger(F10AuthService.class);

	@Override
	public boolean validateToken(String token) {
		// 如果缓存中有token
		if (tokenManager.containsToken(token)) {
			return true;
		}
		try {
			Protocol protocol = ProtocolFactory.createProtocol(ProtocolEnum.HTTPS);
			String validatedToken = protocol.validateToken(token, pcvalidateUrl);
			if (StringUtils.isNotBlank(validatedToken)) {
				tokenManager.addToken(validatedToken);
				return true;
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.core.authentication.AuthService#unregister(java.lang.
	 * String)
	 */
	@Override
	public void expire(List<String> tokenIds) {
		for (String tokenId : tokenIds) {
			tokenManager.removeToken(tokenId);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.core.authentication.AuthService#validateToken(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public boolean validateToken(String device, String token) {
		// 如果缓存中有token
		if (tokenManager.containsToken(token)) {
			return true;
		}
		try {
			// Protocol protocol =
			// ProtocolFactory.createProtocol(ProtocolEnum.HTTPS);
			String validatedToken;
			if (StringUtils.isBlank(device)) {
				validatedToken = protocol.validateToken(token, pcvalidateUrl);
			} else {
				switch (device) {
				case "ios":
					validatedToken = protocol.validateToken(token, iosvalidateUrl);
					break;
				case "android":
					validatedToken = protocol.validateToken(token, androidvalidateUrl);
					break;
				case "pc":
					validatedToken = protocol.validateToken(token, pcvalidateUrl);
					break;
				default:
					validatedToken = null;
				}
			}
			if (StringUtils.isNotBlank(validatedToken)) {
				tokenManager.addToken(validatedToken);
				return true;
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return false;
	}
}
