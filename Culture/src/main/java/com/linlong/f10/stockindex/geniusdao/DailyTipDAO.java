package com.linlong.f10.stockindex.geniusdao;

import java.util.Map;

public interface DailyTipDAO {
	Map<String, Object> getTips(String indexCode);
	
	Map<String, Object> getAllotmentTips();
}
