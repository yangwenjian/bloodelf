/**   
 * @Title: View.java
 * @Package com.linlong.f10.dto
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年8月26日 上午1:48:35
 * @version V1.0   
 */
package com.linlong.f10.dto.market;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: View
 * @Description: 大师观点返回DTO
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年8月26日 上午1:48:35
 * 
 */
public class View implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4324378608120163456L;
	private Integer score;
	private String message;
	private List<Map<String, String>> details = new ArrayList<Map<String, String>>();

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Map<String, String>> getDetails() {
		return details;
	}

	public void setDetails(List<Map<String, String>> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "score: " + score + ", message: " + message + ", details: " + details.toString();
	}

}
