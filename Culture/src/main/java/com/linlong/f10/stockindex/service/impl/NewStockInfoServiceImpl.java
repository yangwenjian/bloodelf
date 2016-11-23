package com.linlong.f10.stockindex.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linlong.f10.stockindex.geniusdao.NewStockInfoDAO;
import com.linlong.f10.stockindex.service.NewStockInfoService;

@Service
public class NewStockInfoServiceImpl implements NewStockInfoService {

	@Autowired
	NewStockInfoDAO newStockInfoDao;
	
	@Override
	public Map<String, Object> getTodayNewStockInfo() {
		return newStockInfoDao.getNewStockInfo("今日新股提示");
	}

	@Override
	public Map<String, Object> getWaitIssueStockInfo() {
		return newStockInfoDao.getNewStockInfo("等待发行新股提示");
	}

	@Override
	public Map<String, Object> getWaitListing() {
		return newStockInfoDao.getWaitListing();
	}

	@Override
	public Map<String, Object> getLatestStock() {
		return newStockInfoDao.getLatestStock();
	}

	@Override
	public Map<String, Object> getNewStockLocation() {
		return newStockInfoDao.getNewStockLocation();
	}

}
