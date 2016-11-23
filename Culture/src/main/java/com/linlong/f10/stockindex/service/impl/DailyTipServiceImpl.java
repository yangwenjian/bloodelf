package com.linlong.f10.stockindex.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.linlong.f10.stockindex.geniusdao.DailyTipDAO;
import com.linlong.f10.stockindex.service.DailyTipService;

@Service
public class DailyTipServiceImpl implements DailyTipService {

	@Autowired
	private DailyTipDAO dailyTipsDAO;
	
	@Override
	@Cacheable(value="publicCache")
	public Map<String, Object> getSHTips() {
		return dailyTipsDAO.getTips("001008");
	}

	@Override
	@Cacheable(value="publicCache")
	public Map<String, Object> getSZTips() {
		return dailyTipsDAO.getTips("001007");
	}

	@Override
	public Map<String, Object> getAllotmentTips() {
		return dailyTipsDAO.getAllotmentTips();
	}

}
