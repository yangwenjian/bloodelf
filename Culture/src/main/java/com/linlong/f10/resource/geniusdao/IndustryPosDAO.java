/**   
 * @Title: IndustryPosDAO.java
 * @Package com.linlong.f10.resource.geniusdao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月22日 上午8:23:30
 * @version V1.0   
 */
package com.linlong.f10.resource.geniusdao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: IndustryPosDAO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月22日 上午8:23:30
 * 
 */
public interface IndustryPosDAO {
	public Map<String, Object> selectIndu(String stockcode);

	public List<Map<String, Object>> selectInduPos(String stockcode);

	public List<Date> selectEnddate(String stockcode);

	public List<Map<String, Object>> selectInduCom(
			@Param("stockcode") String stockcode, @Param("enddate") Date enddate);

	public Map<String, Object> selectEpspRank(
			@Param("stockcode") String stockcode, @Param("enddate") Date enddate);

	public Map<String, Object> selectIncjRank(
			@Param("stockcode") String stockcode, @Param("enddate") Date enddate);

	public Map<String, Object> selectRoedRank(
			@Param("stockcode") String stockcode, @Param("enddate") Date enddate);

	public Map<String, Object> selectBpsnedRank(
			@Param("stockcode") String stockcode, @Param("enddate") Date enddate);

	public Map<String, Object> selectPsnetvalRank(
			@Param("stockcode") String stockcode, @Param("enddate") Date enddate);

	public Map<String, Object> selectInciRank(
			@Param("stockcode") String stockcode, @Param("enddate") Date enddate);

	public Map<String, Object> selectSelrintRank(
			@Param("stockcode") String stockcode, @Param("enddate") Date enddate);

	public Map<String, Object> selectBaloRank(
			@Param("stockcode") String stockcode, @Param("enddate") Date enddate);

	public Map<String, Object> selectCaplabRank(
			@Param("stockcode") String stockcode, @Param("enddate") Date enddate);

	public Map<String, Object> selectOpetaiRank(
			@Param("stockcode") String stockcode, @Param("enddate") Date enddate);

}
