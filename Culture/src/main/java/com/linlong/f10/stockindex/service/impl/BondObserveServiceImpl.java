package com.linlong.f10.stockindex.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linlong.f10.stockindex.geniusdao.BondObserveDAO;
import com.linlong.f10.stockindex.service.BondObserveService;

@Service
public class BondObserveServiceImpl implements BondObserveService {

	@Autowired
	private BondObserveDAO bondObserveDao;
	
	@Override
	public List<Map<String, Object>> getNews() {
		List<String> guids = bondObserveDao.getNewsList();
		if(guids == null || guids.isEmpty()) {
			return null;
		}
		return bondObserveDao.getNews(guids);
	}

}
