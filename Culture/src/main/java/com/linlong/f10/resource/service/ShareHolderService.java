/**   
* @Title: ShareHolderService.java
* @Package com.linlong.f10.resource.service
* @Description: TODO(用一句话描述该文件做什么)
* @author  yangwenjian   
* @date 2016年7月21日 上午1:30:53
* @version V1.0   
*/
package com.linlong.f10.resource.service;

import java.util.List;
import java.util.Map;

import com.linlong.f10.dto.resource.ShareHolderInfo;

/**
 * @ClassName: ShareHolderService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月21日 上午1:30:53
 * 
 */
public interface ShareHolderService {
	public List<Map<String, Object>> queryHoldSitu(String stockcode);
	public ShareHolderInfo queryShareHolders(String stockcode);
	public List<ShareHolderInfo> queryHisShareHolders(String stockcode);
}
