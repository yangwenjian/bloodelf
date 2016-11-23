/**   
* @Title: ViewService.java
* @Package com.linlong.f10.view.service
* @Description: TODO(用一句话描述该文件做什么)
* @author  yangwenjian   
* @date 2016年8月26日 上午6:54:10
* @version V1.0   
*/
package com.linlong.f10.view.service;

import java.util.List;

import com.linlong.f10.dto.market.View;

/**
 * @ClassName: ViewService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年8月26日 上午6:54:10
 * 
 */
public interface ViewService {

	/**
	* @Title: getViewPoint
	* @Description: 返回大师观点对象
	* @param 
	* @return View    返回类型
	*/
	public List<View> getViews(String stockcode, double price);
	
	public View getViewsTest(Integer code, ViewType viewType);

}
