package com.linlong.f10.stockindex.service;

import java.util.List;
import java.util.Map;

public interface PerformanceDynamicService {
	List<Map<String, Object>> getEarnings();

	List<Map<String, Object>> getSZEarningsPlus();

	List<Map<String, Object>> getSZEarningsSubtract();

	List<Map<String, Object>> getSHEarningsPlus();

	List<Map<String, Object>> getSHEarningsSubtract();
}
