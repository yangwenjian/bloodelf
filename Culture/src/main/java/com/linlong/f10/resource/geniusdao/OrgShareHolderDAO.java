/**   
 * @Title: OrgShareHolderDAO.java
 * @Package com.linlong.f10.resource.geniusdao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月21日 上午8:47:21
 * @version V1.0   
 */
package com.linlong.f10.resource.geniusdao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: OrgShareHolderDAO
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月21日 上午8:47:21
 * 
 */
public interface OrgShareHolderDAO {
	public List<Date> selectNodeDate(String stockcode);

	public List<Map<String, Object>> selectOrgPosition(
			@Param("stockcode") String stockcode, @Param("enddate") Date enddate);

	public List<Map<String, Object>> selectFundPosition(String stockcode);

	public List<Date> selectOrgHolderDate(String stockcode);

	public List<Map<String, Object>> selectOrgHoler(
			@Param("stockcode") String stockcode, @Param("enddate") Date enddate);

	public List<Map<String, Object>> selectFundPositionBrief(@Param("stockcode") String stockcode);

	public List<Map<String, Object>> selectOrgHolerBrief(@Param("stockcode") String stockcode, @Param("enddate") Date enddate);
}
