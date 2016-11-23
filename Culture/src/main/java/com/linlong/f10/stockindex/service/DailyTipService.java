package com.linlong.f10.stockindex.service;

import java.util.Map;

public interface DailyTipService {
	
	Map<String, Object> getSHTips();
	
	Map<String, Object> getSZTips();
	
	Map<String, Object> getAllotmentTips();
}
