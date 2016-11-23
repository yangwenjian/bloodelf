package com.linlong.f10.market.geniusdao;

import java.util.List;
import java.util.Map;

public interface MarketDAO {
	List<Map<String, Object>> selectFloatShare(String tradeMarket);

	List<String> selectProvince();

	List<Map<String, String>> selectProvinceStockCode();

	List<Map<String, Object>> selectIndustry(int level);

	List<Map<String, String>> selectIndustryStockCode();
}
