/**   
 * @Title: StockRequireDAO.java
 * @Package com.linlong.f10.resource.f10dao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月14日 上午2:31:21
 * @version V1.0   
 */
package com.linlong.f10.resource.geniusdao;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: StockRequireDAO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月14日 上午2:31:21
 * 
 */
public interface StockRequireDAO {
	public List<Map<String, Object>> selectSpeSug(String stockcode);

	/**
	 * @Title: selectPerformance
	 * @Description: 查询业绩预批
	 * @param
	 * @return List<Map<String,Object>> 返回类型
	 */
	public List<Map<String, Object>> selectPerformance(String stockcode);

	public List<Map<String, Object>> selectIndiTitle(String stockcode);

	public Integer selectIsSeasonReport(String stockcode);

	public List<Map<String, Object>> selectNoneSeasonReport(String stockcode);

	public List<Map<String, Object>> selectSeasonReport(String stockcode);

	public Map<String, Object> selectShare(String stockcode);

	public List<Map<String, Object>> selectReview(String stockcode);

	public List<Map<String, Object>> selectDividend(String stockcode);

	public List<Map<String, Object>> selectAllotment(String stockcode);

	public List<Map<String, Object>> selectShareChange(String stockcode);

	public List<Map<String, Object>> selectAnnouncement(String stockcode);

}
