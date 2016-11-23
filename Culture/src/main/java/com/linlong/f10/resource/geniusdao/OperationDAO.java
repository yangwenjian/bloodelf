/**   
 * @Title: OperationDAO.java
 * @Package com.linlong.f10.resource.geniusdao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月22日 上午8:26:02
 * @version V1.0   
 */
package com.linlong.f10.resource.geniusdao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: OperationDAO
 * @Description:
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月22日 上午8:26:02
 * 
 */
public interface OperationDAO {

	public Map<String, Object> selectBusiness(String stockcode);

	public List<Date> selectBusPerioddate(String stockcode);

	public List<Map<String, Object>> selectMainBusiness(
			@Param("stockcode") String stockcode, @Param("enddate") Date enddate);

	public List<Date> selectSecPerioddate(String stockcode);

	public List<Map<String, Object>> selectSecInfo(
			@Param("stockcode") String stockcode, @Param("enddate") Date enddate);

	public List<Map<String, Object>> selectRaiseInfo(String stockcode);

	public List<Map<String, Object>> selectProjectsrc(String stockcode);

	public List<Map<String, Object>> selectProjectList(
			@Param("stockcode") String stockcode, @Param("seq") Integer seq);

	public List<Map<String, Object>> selectProjectDetail(
			@Param("stockcode") String stockcode,
			@Param("project") String project);

	public Map<String, Object> selectOperation(String stockcode);
}
