/**   
 * @Title: ViewMetadataService.java
 * @Package com.linlong.f10.view.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年8月31日 上午8:58:34
 * @version V1.0   
 */
package com.linlong.f10.view.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linlong.f10.view.f10dao.ViewMetaDAO;

/**
 * @ClassName: ViewMetadataService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年8月31日 上午8:58:34
 * 
 */
@Service
public class ViewMetadataService {
	@Autowired
	private ViewMetaDAO viewMetaDAO;
	
	private Map<Integer, String> buffettComments = new HashMap<Integer, String>();
	private Map<Integer, Integer> buffettScores = new HashMap<Integer, Integer>();

	private Map<Integer, String> professorComments = new HashMap<Integer, String>();
	private Map<Integer, Integer> professorScores = new HashMap<Integer, Integer>();

	private Map<Integer, String> commonComments = new HashMap<Integer, String>();
	private Map<Integer, Integer> commonScores = new HashMap<Integer, Integer>();

	private Map<String, String> metadata = new HashMap<String, String>();
	private double gdbRatio = 0;
	private double interest = 0;

	public static final String buffett1 = "buffett1";
	public static final String buffett2 = "buffett2";
	public static final String buffett3 = "buffett3";
	public static final String buffett4 = "buffett4";
	public static final String buffett5 = "buffett5";
	public static final String professor1 = "professor1";
	public static final String professor2 = "professor2";
	public static final String professor3 = "professor3";
	public static final String professor4 = "professor4";
	public static final String professor5 = "professor5";
	public static final String common1 = "common1";
	public static final String common2 = "common2";
	public static final String common3 = "common3";
	public static final String common4 = "common4";
	public static final String common5 = "common5";

	@PostConstruct
	public void synchronizeMetadata() {
		List<Map<String, String>> metadatas = viewMetaDAO.selectMetadata();
		List<Map<String, Object>> comments = viewMetaDAO.selectComments();
		for (Map<String, String> map : metadatas) {
			metadata.put(map.get("METAKEY"), map.get("METAVALUE"));
		}
		for (Map<String, Object> map : comments) {
			if ("buffett".equals(map.get("VIEWTYPE"))) {
				buffettScores.put((Integer) map.get("MATCHPOINT"), (Integer) map.get("SCORE"));
				buffettComments.put((Integer) map.get("MATCHPOINT"), (String) map.get("COMMENT"));
			} else if ("professor".equals(map.get("VIEWTYPE"))) {
				professorScores.put((Integer) map.get("MATCHPOINT"), (Integer) map.get("SCORE"));
				professorComments.put((Integer) map.get("MATCHPOINT"), (String) map.get("COMMENT"));
			} else {
				commonScores.put((Integer) map.get("MATCHPOINT"), (Integer) map.get("SCORE"));
				commonComments.put((Integer) map.get("MATCHPOINT"), (String) map.get("COMMENT"));
			}
		}
		gdbRatio = Double.parseDouble(metadata.get("gdpratio"));
		interest = Double.parseDouble(metadata.get("interest"));
	}

	public Map<Integer, String> getBuffettComments() {
		return buffettComments;
	}

	public void setBuffettComments(Map<Integer, String> buffettComments) {
		this.buffettComments = buffettComments;
	}

	public Map<Integer, Integer> getBuffettScores() {
		return buffettScores;
	}

	public void setBuffettScores(Map<Integer, Integer> buffettScores) {
		this.buffettScores = buffettScores;
	}

	public Map<Integer, String> getProfessorComments() {
		return professorComments;
	}

	public void setProfessorComments(Map<Integer, String> professorComments) {
		this.professorComments = professorComments;
	}

	public Map<Integer, Integer> getProfessorScores() {
		return professorScores;
	}

	public void setProfessorScores(Map<Integer, Integer> professorScores) {
		this.professorScores = professorScores;
	}

	public Map<Integer, String> getCommonComments() {
		return commonComments;
	}

	public void setCommonComments(Map<Integer, String> commonComments) {
		this.commonComments = commonComments;
	}

	public Map<Integer, Integer> getCommonScores() {
		return commonScores;
	}

	public void setCommonScores(Map<Integer, Integer> commonScores) {
		this.commonScores = commonScores;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}

	public double getGdbRatio() {
		return gdbRatio;
	}

	public void setGdbRatio(double gdbRatio) {
		this.gdbRatio = gdbRatio;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

}
