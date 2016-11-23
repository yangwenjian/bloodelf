package com.linlong.f10.stockindex.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linlong.f10.stockindex.geniusdao.TapeRemarkDAO;
import com.linlong.f10.stockindex.service.TapeRemarkService;

@Service
public class TapeRemarkServiceImpl implements TapeRemarkService {

	@Autowired
	private TapeRemarkDAO tapeRemarkDao;
	
	@Override
	public List<Map<String, Object>> getNews() {
		List<String> guids = tapeRemarkDao.getNewsList();
		if(guids == null || guids.isEmpty()) {
			return null;
		}
		return tapeRemarkDao.getNews(guids);
	}

}
