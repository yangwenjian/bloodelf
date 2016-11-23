/**   
 * @Title: AuthenticationService.java
 * @Package com.linlong.f10.core.authentication
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月13日 上午11:18:29
 * @version V1.0   
 */
package com.linlong.f10.core.authentication.service;

import java.util.List;

/**
 * @ClassName: AuthenticationService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月13日 上午11:18:29
 * 
 */
public interface AuthService {
	public boolean validateToken(String token);

	public boolean validateToken(String device, String token);

	public void expire(List<String> tokenId);
}
