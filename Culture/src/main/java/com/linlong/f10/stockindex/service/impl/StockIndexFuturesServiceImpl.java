package com.linlong.f10.stockindex.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linlong.f10.stockindex.geniusdao.StockIndexFuturesDAO;
import com.linlong.f10.stockindex.service.StockIndexFuturesService;

@Service
public class StockIndexFuturesServiceImpl implements StockIndexFuturesService {

	@Autowired
	private StockIndexFuturesDAO stockIndexFuturesDao;
	
	@Override
	public List<Map<String, Object>> getNews() {
		List<String> guids = stockIndexFuturesDao.getNewsList();
		if(guids == null || guids.isEmpty()) {
			return null;
		}
		return stockIndexFuturesDao.getNews(guids);
	}

}
