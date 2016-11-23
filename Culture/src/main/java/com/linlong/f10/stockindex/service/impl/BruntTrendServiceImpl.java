package com.linlong.f10.stockindex.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linlong.f10.stockindex.geniusdao.BruntTrendDAO;
import com.linlong.f10.stockindex.service.BruntTrendService;

@Service
public class BruntTrendServiceImpl implements BruntTrendService {

	@Autowired
	private BruntTrendDAO bruntTrendDao;
	
	@Override
	public List<Map<String, Object>> getSHBruntTrend() {
		return bruntTrendDao.getBruntTrend("001019");
	}

	@Override
	public List<Map<String, Object>> getSZBruntTrend() {
		return bruntTrendDao.getBruntTrend("001020");
	}

}
