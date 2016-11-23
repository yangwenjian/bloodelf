package com.linlong.f10.stockindex.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linlong.f10.stockindex.geniusdao.NewsDAO;
import com.linlong.f10.stockindex.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService{

	@Autowired
	NewsDAO newsDao;
	
	@Override
	public Map<String, Object> getTodayNews() {
		return newsDao.getTodayNews();
	}

	@Override
	public Map<String, Object> getYestdayNews() {
		return newsDao.getYestdayNews();
	}

	@Override
	public List<Map<String, Object>> getTodayFocus() {
		return newsDao.getTodayFocus();
	}

}
