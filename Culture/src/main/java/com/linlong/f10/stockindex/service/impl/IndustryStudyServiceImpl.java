package com.linlong.f10.stockindex.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linlong.f10.stockindex.geniusdao.IndustryStudyDAO;
import com.linlong.f10.stockindex.service.IndustryStudyService;

@Service
public class IndustryStudyServiceImpl implements IndustryStudyService {

	@Autowired
	private IndustryStudyDAO industryStudyDao;
	
	@Override
	public List<Map<String, Object>> getNews() {
		List<String> guids = industryStudyDao.getNewsList();
		if(guids == null || guids.isEmpty()) {
			return null;
		}
		return industryStudyDao.getNews(guids);
	}

}
