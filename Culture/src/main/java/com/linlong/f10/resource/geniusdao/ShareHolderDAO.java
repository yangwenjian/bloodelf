/**   
 * @Title: StockRequireDAO.java
 * @Package com.linlong.f10.resource.f10dao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月14日 上午2:31:21
 * @version V1.0   
 */
package com.linlong.f10.resource.geniusdao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: StockRequireDAO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月14日 上午2:31:21
 * 
 */
public interface ShareHolderDAO {

	public List<Map<String, Object>> selectHoldSitu(String stockcode);
	public Map<String, Object> selectTotHolders(String stockcode);
	public List<Map<String, Object>> selectNewHolders(String stockcode);
	public List<Map<String, Object>> selectFloatHolders(String stockcode);
	public List<Map<String, Object>> selectLimitHolders(String stockcode);
	public String selectTotalShare(String stockcode);
	public List<Date> selectHisDate(String stockcode);
	public Map<String, Object> selectHisTotHolders(@Param("stockcode")  String stockcode, @Param("changedate") Date changeDate);
	public List<Map<String, Object>> selectHisNewHolders(@Param("stockcode") String stockcode, @Param("changedate") Date changedate);
	public List<Map<String, Object>> selectHisFloatHolders(@Param("stockcode") String stockcode, @Param("changedate") Date changedate);
	public String selectHisTotalShare(@Param("stockcode") String stockcode, @Param("changedate") Date changedate);
	public List<Map<String, Object>> selectHisLimitHolders(@Param("stockcode") String stockcode, @Param("changedate") Date changedate);

}
