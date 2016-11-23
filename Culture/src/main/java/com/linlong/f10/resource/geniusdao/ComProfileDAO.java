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
public interface ComProfileDAO {

	public String selectStockName(String stockcode);

	public List<String> selectStockCodes();

	public Map<String, Object> selectShareHolder(String stockcode);

	public String selectInvestKeyPoint(String stockcode);

	public Map<String, Object> selectPubOffer(String stockcode);

	public Map<String, Object> selectComInfo(String stockcode);

	public List<String> selectIndex(String stockcode);

	public List<Map<String, Object>> selectFormerName(String stockcode);

	public List<Map<String, Object>> selectRelStock(String stockcode);

	public List<Map<String, Object>> selectFirstShareHolder(String stockcode);

	public List<Map<String, Object>> selectEmplSitu(String stockcode);

	public Map<String, Object> selectEmplInfo(String stockcode);

}
