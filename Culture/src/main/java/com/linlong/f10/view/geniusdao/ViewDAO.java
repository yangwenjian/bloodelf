/**   
 * @Title: ReviewDAO.java
 * @Package com.linlong.f10.resource.geniusdao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月22日 上午8:27:48
 * @version V1.0   
 */
package com.linlong.f10.view.geniusdao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: ReviewDAO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月22日 上午8:27:48
 * 
 */
public interface ViewDAO {

	/**
	 * @Title: selectROE
	 * @Description: 返回12个季度的股东权益比率
	 * @param
	 * @return Map<String,Object> 返回类型
	 */
	List<Map<String, Object>> selectEquity(@Param("stockcode") String stockcode);

	/**
	 * @Title: selectROE
	 * @Description: 返回12个季度的毛利率
	 * @param
	 * @return Map<String,Object> 返回类型
	 */
	List<Map<String, Object>> selectProfit(@Param("stockcode") String stockcode);

	/**
	 * @Title: selectROE
	 * @Description: 返回12个季度的现金流量比率
	 * @param
	 * @return Map<String,Object> 返回类型
	 */
	List<Map<String, Object>> selectCashFlow(@Param("stockcode") String stockcode);

	/**
	 * @Title: selectIsSeason
	 * @Description: 返回是否为季报
	 * @param
	 * @return Integer 返回类型
	 */
	Integer selectIsSeason(@Param("stockcode") String stockcode);

	/**
	 * @Title: selectEps
	 * @Description: 返回20个季度的eps
	 * @param
	 * @return List<Map<String,Object>> 返回类型
	 */
	List<Map<String, Object>> selectEpsSeason(@Param("stockcode") String stockcode);

	/**
	 * @Title: selectEps
	 * @Description: 返回20个季度的eps
	 * @param
	 * @return List<Map<String,Object>> 返回类型
	 */
	List<Map<String, Object>> selectEpsNoneSeason(@Param("stockcode") String stockcode);

	/**
	 * @Title: selectPB
	 * @Description: 返回最新的市净率pb
	 * @param
	 * @return Map<String,Object> 返回类型
	 */
	Map<String, Object> selectPB(@Param("stockcode") String stockcode);

	/**
	* @Title: selectInduInfo
	* @Description: 返回同行业相关信息
	* @param 
	* @return List<Map<String,BigDecimal>>    返回类型
	*/
	List<Map<String, BigDecimal>> selectInduInfo(@Param("stockcode") String stockcode);

	/**
	* @Title: selectInfo
	* @Description: 返回公司相公信息
	* @param 
	* @return Map<String,BigDecimal>    返回类型
	*/
	Map<String, BigDecimal> selectInfo(@Param("stockcode") String stockcode);

	/**
	* @Title: selectNetProfit
	* @Description: 返回3年的净利润
	* @param 
	* @return List<BigDecimal>    返回类型
	*/
	List<BigDecimal> selectNetProfit(@Param("stockcode") String stockcode);

	/**
	* @Title: selectDividend
	* @Description: 返回近3年的分红信息
	* @param 
	* @return List<Map<String,Object>>    返回类型
	*/
	List<Map<String, Object>> selectDividend(@Param("stockcode") String stockcode);

}
