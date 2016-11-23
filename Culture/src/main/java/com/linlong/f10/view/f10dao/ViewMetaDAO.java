/**   
* @Title: ViewMetaDAO.java
* @Package com.linlong.f10.view.f10dao
* @Description: TODO(用一句话描述该文件做什么)
* @author  yangwenjian   
* @date 2016年8月31日 上午8:52:09
* @version V1.0   
*/
package com.linlong.f10.view.f10dao;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ViewMetaDAO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年8月31日 上午8:52:09
 * 
 */
public interface ViewMetaDAO {

	/**
	* @Title: selectMetadata
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param 
	* @return List<Map<String,String>>    返回类型
	*/
	List<Map<String, String>> selectMetadata();

	/**
	* @Title: selectComments
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param 
	* @return List<Map<String,Object>>    返回类型
	*/
	List<Map<String, Object>> selectComments();
	
}
