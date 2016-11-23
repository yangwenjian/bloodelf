/**   
 * @Title: GeniusMediaReportService.java
 * @Package com.linlong.f10.resource.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年7月22日 上午8:25:26
 * @version V1.0   
 */
package com.linlong.f10.resource.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.linlong.f10.resource.geniusdao.MediaReportDAO;
import com.linlong.f10.resource.service.MediaReportService;

/**
 * @ClassName: GeniusMediaReportService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年7月22日 上午8:25:26
 * 
 */
@Service
public class GeniusMediaReportService implements MediaReportService {
	@Autowired
	private MediaReportDAO mediaReportDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.MediaReportService#queryMeidaReports
	 * (java.lang.String)
	 */
	@Override
	@Cacheable(value = "listCache", key = "#stockcode.concat(\"queryMeidaReports\")")
	public List<Map<String, Object>> queryMeidaReports(String stockcode) {
		List<Map<String, Object>> reports = mediaReportDAO.selectMediaReports(stockcode);
		return reports;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.MediaReportService#queryMeidaReportTitles
	 * (java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> queryMeidaReportTitles(String stockcode) {
		List<Map<String, Object>> reports = mediaReportDAO.selectMediaReportTitles(stockcode);
		return reports;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.resource.service.MediaReportService#queryMeidaReportById
	 * (java.lang.String)
	 */
	@Override
	public Map<String, Object> queryMeidaReportById(String guid) {
		Map<String, Object> report = mediaReportDAO.selectMediaReportById(guid);
		return report;
	}

}
