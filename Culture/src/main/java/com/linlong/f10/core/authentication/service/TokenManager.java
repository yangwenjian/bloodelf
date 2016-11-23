/**   
* @Title: TokenManger.java
* @Package com.linlong.f10.core.authentication
* @Description: TODO(用一句话描述该文件做什么)
* @author  yangwenjian   
* @date 2016年7月14日 上午5:39:09
* @version V1.0   
*/
package com.linlong.f10.core.authentication.service;

/**
 * @ClassName: TokenManger
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月14日 上午5:39:09
 * 
 */
public interface TokenManager {

	/**
	* @Title: containsToken
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param 
	* @return boolean    返回类型
	*/
	boolean containsToken(String token);

	/**
	* @Title: addToken
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param 
	* @return void    返回类型
	*/
	void addToken(String validatedToken);

	/**
	* @Title: removeToken
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param 
	* @return void    返回类型
	*/
	void removeToken(String tokenId);

}
