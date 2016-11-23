package com.linlong.f10.stockindex.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linlong.f10.stockindex.geniusdao.ManyDimensionsStatisticsDAO;
import com.linlong.f10.stockindex.service.ManyDimensionsStatisticsService;

@Service
public class ManyDimensionsStatisticsServiceImpl implements
		ManyDimensionsStatisticsService {

	@Autowired
	private ManyDimensionsStatisticsDAO manyDimensionsStatisticsDao;
	
	@Override
	public List<Map<String, Object>> getDividendScheme() {
		return manyDimensionsStatisticsDao.getDividendScheme();
	}

	@Override
	public List<Map<String, Object>> getManyDimensionsStatistics() {
		return manyDimensionsStatisticsDao.getManyDimensionsStatistics();
	}

}
