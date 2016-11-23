/**   
 * @Title: StockRequireService.java
 * @Package com.linlong.f10.resource.service
 * @author  yangwenjian   
 * @date 2016年7月14日 上午2:35:29
 * @version V1.0   
 */
package com.linlong.f10.resource.service;

import java.util.List;
import java.util.Map;

import com.linlong.f10.dto.resource.ShareChange;
import com.linlong.f10.dto.resource.ShareInfo;
import com.linlong.f10.dto.resource.StockIndicator;

/**
 * @ClassName: StockRequireService
 * @Description: 提供f10操盘必读信息
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月14日 上午2:35:29
 * 
 */
public interface StockRequireService {
	public List<String> querySpeSug(String stockcode);

	public List<String> queryPerformance(String stockcode);

	public String queryIndiTitle(String stockcode);

	public List<StockIndicator> queryIndicator(String stockcode);

	public ShareInfo queryShare(String stockcode);

	public List<String> queryReview(String stockcode);

	public List<String> queryAllotment(String stockcode);

	public List<ShareChange> queryShareChange(String stockcode);

	public List<Map<String, String>> queryAnnouncement(String stockcode);
}
