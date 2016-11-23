/**   
 * @Title: CategoryDAO.java
 * @Package com.f10.market.marketdao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月5日 上午9:00:09
 * @version V1.0   
 */
package com.linlong.f10.market.f10dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: CategoryDAO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月5日 上午9:00:09
 * 
 */
public interface CategoryDAO {

	public List<Map<String, Object>> selectCategory(
			@Param("isLeaf") Integer isLeaf, @Param("parentId") Long parentId);

	/**
	 * @Title: selectProvStCode
	 * @Description: 读取各个分类对应的股票
	 * @param
	 * @return List<Map<String,String>> 返回类型
	 */
	public List<Map<String, Object>> selectl1StCode(String categoryName);

	/**
	 * @Title: selectl2StCode
	 * @Description: 读取各个分类对应的股票
	 * @param
	 * @return List<Map<String,Object>> 返回类型
	 */
	public List<Map<String, Object>> selectl2StCode(String string);

	/**
	 * @Title: selectMenuId
	 * @Description: 根据menuName返回ID
	 * @param
	 * @return String 返回类型
	 */
	public Long selectMenuId(String menuName);

	/**
	 * @Title: updateStock
	 * @Description: 跟新股票的menuId
	 * @param
	 * @return void 返回类型
	 */
	public void updateStock(@Param("stockCode") String stockCode,
			@Param("menuId") Long menuId);

	/**
	 * @Title: insertStock
	 * @Description: 插入一条语句
	 * @param
	 * @return void 返回类型
	 */
	public void insertStock(@Param("stockCode") String stockCode,
			@Param("stockName") String stockName, @Param("menuId") Long menuId);


}
