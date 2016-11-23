/**   
 * @Title: SampleStatisticsService.java
 * @Package com.linlong.f10.stockindexdetail.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年8月1日 下午2:39:41
 * @version V1.0   
 */
package com.linlong.f10.stockindexdetail.service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SampleStatisticsService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年8月1日 下午2:39:41
 * 
 */
public interface SampleStatisticsService {
	public List<Map<String, Object>> querySampleStatistics(String indexcode);
}
