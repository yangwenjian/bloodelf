package com.linlong.f10.stockindex.geniusdao;

import java.util.List;
import java.util.Map;

public interface PerformanceDynamicDAO {
	List<Map<String, Object>> getEarnings();

	List<Map<String, Object>> getEarningsPlus(int type);

	List<Map<String, Object>> getEarningsSubtract(int type);
}
