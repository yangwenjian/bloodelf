/**   
* @Title: FluctuationService.java
* @Package com.linlong.f10.resource.service
* @Description: TODO(用一句话描述该文件做什么)
* @author  yangwenjian   
* @date 2016年7月22日 上午8:30:15
* @version V1.0   
*/
package com.linlong.f10.resource.service;

import java.util.List;

import com.linlong.f10.dto.resource.Fluctuation;

/**
 * @ClassName: FluctuationService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月22日 上午8:30:15
 * 
 */
public interface FluctuationService {
	public List<Fluctuation> queryFluctuation(String stoccode);
}
