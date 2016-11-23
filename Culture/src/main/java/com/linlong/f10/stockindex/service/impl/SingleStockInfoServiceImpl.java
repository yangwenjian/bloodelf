package com.linlong.f10.stockindex.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linlong.f10.stockindex.geniusdao.SingleStockInfoDAO;
import com.linlong.f10.stockindex.service.SingleStockInfoService;

@Service
public class SingleStockInfoServiceImpl implements SingleStockInfoService {

	@Autowired
	private SingleStockInfoDAO singleStockInfoDao;
	
	@Override
	public List<Map<String, Object>> getNews() {
		List<String> guids = singleStockInfoDao.getNewsList();
		if(guids == null || guids.isEmpty()) {
			return null;
		}
		return singleStockInfoDao.getNews(guids);
	}

}
