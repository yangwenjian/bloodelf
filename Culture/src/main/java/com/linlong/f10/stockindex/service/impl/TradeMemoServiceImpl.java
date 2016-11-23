package com.linlong.f10.stockindex.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linlong.f10.stockindex.geniusdao.TradeMemoDAO;
import com.linlong.f10.stockindex.service.TradeMemoService;

@Service
public class TradeMemoServiceImpl implements TradeMemoService {

	@Autowired
	TradeMemoDAO tradeMemoDao;
	
	@Override
	public Map<String, Object> getSHMemo() {
		return tradeMemoDao.getMemo("001013");
	}

	@Override
	public Map<String, Object> getSZMemo() {
		return tradeMemoDao.getMemo("001014");
	}

}
