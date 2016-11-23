/**   
 * @Title: ExecutiveDAO.java
 * @Package com.linlong.f10.resource.geniusdao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月22日 上午8:16:25
 * @version V1.0   
 */
package com.linlong.f10.resource.geniusdao;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ExecutiveDAO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月22日 上午8:16:25
 * 
 */
public interface ExecutiveDAO {
	public List<Map<String, Object>> selectExecChange(String stockcode);
	public List<Map<String, Object>> selectExecDetail(String stockcode);
	public List<Map<String, Object>> selectExecChReason(String stockcode);
	public List<Map<String, Object>> selectExecPT(String stockcode);
	public List<Map<String, Object>> selectStIncetive(String stockcode);
	public List<Map<String, Object>> selectExecIntro(String stockcode);
}
