package com.linlong.f10.stockindex.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linlong.f10.stockindex.geniusdao.PerformanceDynamicDAO;
import com.linlong.f10.stockindex.service.PerformanceDynamicService;

@Service
public class PerformanceDynamicServiceImpl implements PerformanceDynamicService {

	@Autowired
	private PerformanceDynamicDAO performanceDynamicDao;
	
	@Override
	public List<Map<String, Object>> getEarnings() {
		return performanceDynamicDao.getEarnings();
	}

	@Override
	public List<Map<String, Object>> getSZEarningsPlus() {
		return performanceDynamicDao.getEarningsPlus(1);
	}

	@Override
	public List<Map<String, Object>> getSZEarningsSubtract() {
		return performanceDynamicDao.getEarningsSubtract(1);
	}

	@Override
	public List<Map<String, Object>> getSHEarningsPlus() {
		return performanceDynamicDao.getEarningsPlus(2);
	}

	@Override
	public List<Map<String, Object>> getSHEarningsSubtract() {
		return performanceDynamicDao.getEarningsSubtract(2);
	}

}
