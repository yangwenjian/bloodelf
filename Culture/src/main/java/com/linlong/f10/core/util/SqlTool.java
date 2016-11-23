package com.linlong.f10.core.util;

import org.springframework.util.StringUtils;

/**
 * 
 * @ClassName: SqlTool 
 * @Description: sql工具类 
 * @author nisicong
 * @date 2015年6月3日 上午11:15:15 
 *
 */
public class SqlTool {

	/**
	 * 
	 * @Title: transfer 
	 * @Description: 替换模糊查询的通配符 
	 * @author nisicong
	 *
	 * @param keyword
	 * @return String
	 */
	public static String transfer(String keyword) {
		if(!StringUtils.isEmpty(keyword) && (keyword.contains("%") || keyword.contains("_"))){  
			keyword = keyword.replaceAll("\\\\", "\\\\\\\\")  
							 .replaceAll("\\%", "\\\\%")  
							 .replaceAll("\\_", "\\\\_");
		} 
		return keyword;
	}
}
