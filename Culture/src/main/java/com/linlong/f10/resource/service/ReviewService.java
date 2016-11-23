/**   
 * @Title: ReviewService.java
 * @Package com.linlong.f10.resource.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月22日 上午8:28:16
 * @version V1.0   
 */
package com.linlong.f10.resource.service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ReviewService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月22日 上午8:28:16
 * 
 */
public interface ReviewService {
	public List<Map<String, Object>> queryReviews(String stockcode);

	public List<Map<String, Object>> queryReviewTitles(String stockcode);

	public Map<String, Object> queryReviewById(String guid);
}
