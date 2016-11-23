package com.linlong.f10.stockindex.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linlong.f10.stockindex.geniusdao.InternationalNewsDAO;
import com.linlong.f10.stockindex.service.InternationalNewsService;

@Service
public class InternationalNewsServiceImpl implements InternationalNewsService {

	@Autowired
	private InternationalNewsDAO internationalNewsDao;
	
	@Override
	public Map<String, Object> getGlobalIndex() {
		return internationalNewsDao.getGlobalIndex();
	}

	@Override
	public List<Map<String, Object>> getInternationalNews() {
		List<String> guids = internationalNewsDao.getNewsGUID();
		if(guids == null || guids.isEmpty()) {
			return null;
		}
		return internationalNewsDao.getInternationalNews(guids);
	}

}
