/**   
 * @Title: GeniusViewService.java
 * @Package com.linlong.f10.view.service.impl
 * @author  yangwenjian   
 * @date 2016年8月26日 上午6:54:51
 * @version V1.0   
 */
package com.linlong.f10.view.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.linlong.f10.dto.market.View;
import com.linlong.f10.view.geniusdao.ViewDAO;
import com.linlong.f10.view.service.ViewMetadataService;
import com.linlong.f10.view.service.ViewService;
import com.linlong.f10.view.service.ViewType;

/**
 * @ClassName: GeniusViewService
 * @Description: 返回观点对象
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年8月26日 上午6:54:51
 * 
 */
@Service
public class GeniusViewService implements ViewService {

	@Autowired
	private ViewDAO viewDAO;
	@Autowired
	private ViewMetadataService viewMetadataService;

	private static final String viewpoint = "viewpoint";
	private static final String result = "result";

	private static final Logger LOGGER = LoggerFactory.getLogger(ViewService.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.view.service.ViewService#getViewPoint(java.lang.String,
	 * java.lang.String, java.lang.Double)
	 */
	@Override
	@Cacheable(value = "viewCache", key = "#stockcode.concat(#price)")
	public List<View> getViews(String stockcode, double price) {
		List<View> views = new ArrayList<View>(3);
		View buffett = buffettView(stockcode);
		views.add(buffett);
		View professor = professorView(stockcode);
		views.add(professor);
		View common = commonView(stockcode, price);
		views.add(common);
		LOGGER.info("Final Result: " + views);
		return views;
	}

	/**
	 * @Title: buffettView
	 * @Description: 返回巴菲特观点对象
	 * @param
	 * @return View 返回类型
	 */
	private View buffettView(String stockcode) {
		Integer value = 0;
		View buffettView = new View();
		boolean[] buffettViewDetails = queryBuffettDetail(stockcode);
		// 构造观点对象
		Map<String, String> meta = viewMetadataService.getMetadata();
		value = caculateValue(buffettViewDetails);
		buffettView.setScore(viewMetadataService.getBuffettScores().get(value));
		buffettView.setMessage(viewMetadataService.getBuffettComments().get(value));

		Map<String, String> buffett1 = new HashMap<String, String>();
		buffett1.put(viewpoint, meta.get(ViewMetadataService.buffett1));
		buffett1.put(result, String.valueOf(buffettViewDetails[0]));
		buffettView.getDetails().add(buffett1);
		Map<String, String> buffett2 = new HashMap<String, String>();
		buffett2.put(viewpoint, meta.get(ViewMetadataService.buffett2));
		buffett2.put(result, String.valueOf(buffettViewDetails[1]));
		buffettView.getDetails().add(buffett2);
		Map<String, String> buffett3 = new HashMap<String, String>();
		buffett3.put(viewpoint, meta.get(ViewMetadataService.buffett3));
		buffett3.put(result, String.valueOf(buffettViewDetails[2]));
		buffettView.getDetails().add(buffett3);
		Map<String, String> buffett4 = new HashMap<String, String>();
		buffett4.put(viewpoint, meta.get(ViewMetadataService.buffett4));
		buffett4.put(result, String.valueOf(buffettViewDetails[3]));
		buffettView.getDetails().add(buffett4);
		Map<String, String> buffett5 = new HashMap<String, String>();
		buffett5.put(viewpoint, meta.get(ViewMetadataService.buffett5));
		buffett5.put(result, String.valueOf(buffettViewDetails[4]));
		buffettView.getDetails().add(buffett5);
		return buffettView;
	}

	/**
	 * @Title: queryDetail
	 * @Description: 返回巴菲特观点的详细信息
	 * @param
	 * @return boolean [] 返回类型
	 */
	private boolean[] queryBuffettDetail(String stockcode) {
		boolean[] details = new boolean[5];
		// 股东权益报酬率一年及三年平均皆大于15%
		List<Map<String, Object>> resultMap1 = viewDAO.selectEquity(stockcode);
		double yearRoe = 0;
		double threeYearRoe = 0;
		int size = resultMap1.size();
		for (int i = 0; i < size; i++) {
			double temp = 0d;
			BigDecimal result = (BigDecimal) resultMap1.get(i).get("CAP_EQ");
			if (result != null) {
				temp = result.doubleValue();
			}
			if (i < 4) {
				yearRoe = yearRoe + temp;
			}
			threeYearRoe = threeYearRoe + temp;
		}
		yearRoe = yearRoe / 4;
		threeYearRoe = threeYearRoe / size;
		if (yearRoe > 15 && threeYearRoe > 15) {
			details[0] = true;
		}
		LOGGER.info("巴菲特观点1：一年股东权益报酬率【" + yearRoe + "】，三年平均股东权益报酬率【" + threeYearRoe + "】,结果：" + details[0]);
		// 毛利率一年及三年平均皆大于15%
		List<Map<String, Object>> resultMap2 = viewDAO.selectProfit(stockcode);
		double yearProfit = 0;
		double threeYearProfit = 0;
		size = resultMap2.size();
		for (int i = 0; i < size; i++) {
			double temp = 0d;
			BigDecimal result = (BigDecimal) resultMap2.get(i).get("PROFIT");
			if (result != null) {
				temp = result.doubleValue();
			}
			if (i < 4) {
				yearProfit = yearProfit + temp;
			}
			threeYearProfit = threeYearProfit + temp;
		}
		yearProfit = yearProfit / 4;
		threeYearProfit = threeYearProfit / size;
		LOGGER.info("巴菲特观点2：一年毛利率【" + yearProfit + "】，三年平均毛利率【" + threeYearProfit + "】");
		if (yearProfit > 15 && threeYearProfit > 15) {
			details[1] = true;
		}
		// 现金流量成长率一年及三年平均皆大于15%
		List<Map<String, Object>> resultMap3 = viewDAO.selectCashFlow(stockcode);
		double yearCashFlow = 0;
		double threeYearCashFlow = 0;
		size = resultMap3.size();
		for (int i = 0; i < size; i++) {
			double temp = 0d;
			BigDecimal result = (BigDecimal) resultMap3.get(i).get("CASH_FLOW");
			if (result != null) {
				temp = result.doubleValue();
			}
			if (i < 4) {
				yearCashFlow = yearCashFlow + temp;
			}
			threeYearCashFlow = threeYearCashFlow + temp;
		}
		yearCashFlow = yearCashFlow / 4;
		threeYearCashFlow = threeYearCashFlow / size;
		LOGGER.info("巴菲特观点3：现金流量成长率【" + yearCashFlow + "】，现金流量成长率【" + threeYearCashFlow + "】");
		if (yearCashFlow > 15 && threeYearCashFlow > 15) {
			details[2] = true;
		}
		// 5年来EPS从未有负值
		details[3] = true;
		Integer isSeason = viewDAO.selectIsSeason(stockcode);
		List<Map<String, Object>> resultMap4;
		if (isSeason == 1) {
			resultMap4 = viewDAO.selectEpsSeason(stockcode);
		} else {
			resultMap4 = viewDAO.selectEpsNoneSeason(stockcode);
		}
		size = resultMap4.size();
		LOGGER.info("巴菲特观点4： 五年内EPS值【" + resultMap4 + "】");
		for (int i = 0; i < size; i++) {
			BigDecimal eps = (BigDecimal) resultMap4.get(i).get("EPSNED");
			if (eps != null && eps.doubleValue() < 0) {
				details[3] = false;
				break;
			}
		}
		// 价格/净值比小于2
		Map<String, Object> resultMap5 = viewDAO.selectPB(stockcode);
		BigDecimal pb = (BigDecimal) resultMap5.get("PB");
		LOGGER.info("巴菲特观点5： 价格/净值比【" + pb + "】");
		if (pb != null && pb.doubleValue() < 2) {
			details[4] = true;
		}
		return details;
	}

	/**
	 * @Title: professorView
	 * @Description: 返回钱教授观点对象
	 * @param
	 * @return View 返回类型
	 */
	private View professorView(String stockcode) {
		Integer value = 0;
		View professorView = new View();
		boolean[] professorViewDetails = queryProfessorDetail(stockcode);
		// 构造观点对象
		Map<String, String> meta = viewMetadataService.getMetadata();
		value = caculateValue(professorViewDetails);
		professorView.setScore(viewMetadataService.getProfessorScores().get(value));
		professorView.setMessage(viewMetadataService.getProfessorComments().get(value));

		Map<String, String> professor1 = new HashMap<String, String>();
		professor1.put(viewpoint, meta.get(ViewMetadataService.professor1));
		professor1.put(result, String.valueOf(professorViewDetails[0]));
		professorView.getDetails().add(professor1);
		Map<String, String> professor2 = new HashMap<String, String>();
		professor2.put(viewpoint, meta.get(ViewMetadataService.professor2));
		professor2.put(result, String.valueOf(professorViewDetails[1]));
		professorView.getDetails().add(professor2);
		Map<String, String> professor3 = new HashMap<String, String>();
		professor3.put(viewpoint, meta.get(ViewMetadataService.professor3));
		professor3.put(result, String.valueOf(professorViewDetails[2]));
		professorView.getDetails().add(professor3);
		Map<String, String> professor4 = new HashMap<String, String>();
		professor4.put(viewpoint, meta.get(ViewMetadataService.professor4));
		professor4.put(result, String.valueOf(professorViewDetails[3]));
		professorView.getDetails().add(professor4);
		Map<String, String> professor5 = new HashMap<String, String>();
		professor5.put(viewpoint, meta.get(ViewMetadataService.professor5));
		professor5.put(result, String.valueOf(professorViewDetails[4]));
		professorView.getDetails().add(professor5);
		return professorView;
	}

	/**
	 * @Title: queryProfessorDetail
	 * @Description: 返回钱教授观点的详细信息
	 * @param
	 * @return boolean [] 返回类型
	 */
	private boolean[] queryProfessorDetail(String stockcode) {
		boolean[] details = new boolean[5];
		List<Map<String, BigDecimal>> induInfos = viewDAO.selectInduInfo(stockcode);
		Map<String, BigDecimal> info = viewDAO.selectInfo(stockcode);
		double induroe = 0;
		double induroa = 0;
		double induProfit = 0;
		double induTurnover = 0;
		int size = induInfos.size();
		for (Map<String, BigDecimal> induInfo : induInfos) {
			if (induInfo != null) {
				BigDecimal temproe = induInfo.get("ROE");
				if (temproe != null) {
					induroe = induroe + temproe.doubleValue();
				}
				BigDecimal temproa = induInfo.get("ROA");
				if (temproa != null) {
					induroa = induroa + temproa.doubleValue();
				}
				BigDecimal tempProfit = induInfo.get("RISOR");
				if (tempProfit != null) {
					induProfit = induProfit + tempProfit.doubleValue();
				}
				BigDecimal tempTurnover = induInfo.get("OPESTCI");
				if (tempTurnover != null) {
					induTurnover = induTurnover + tempTurnover.doubleValue();
				}
			}
		}
		// 年报ROE(摊薄)>行业平均ROE
		induroe = induroe / size;
		LOGGER.info("钱教授观点1：年报ROE(摊薄)【" + info.get("ROE") + "】行业平均ROE【" + induroe + "】");
		if (info.get("ROE") != null && info.get("ROE").doubleValue() > induroe) {
			details[0] = true;
		}
		// 年报ROA(摊薄)>行业平均ROA
		induroa = induroa / size;
		LOGGER.info("钱教授观点2：年报ROA(摊薄)【" + info.get("ROA") + "】行业平均ROE【" + induroa + "】");
		if (info.get("ROA") != null && info.get("ROA").doubleValue() > induroa) {
			details[1] = true;
		}
		induProfit = induProfit / size;
		LOGGER.info("钱教授观点3：年报营业利润同比增长率【" + info.get("RISOR") + "】行业平均增长率【" + induProfit + "】GDP增速【"
				+ viewMetadataService.getGdbRatio() + "】");
		if (info.get("RISOR") != null && info.get("RISOR").doubleValue() > induProfit
				&& info.get("RISOR").doubleValue() > viewMetadataService.getGdbRatio()) {
			details[2] = true;
		}
		// 速动比率>1
		LOGGER.info("钱教授观点4:速动比率【" + info.get("LABSLO") + "】");
		if (info.get("LABSLO") != null && info.get("LABSLO").doubleValue() > 1) {
			details[3] = true;
		}
		// 存货周转率>行业平均数
		induTurnover = induTurnover / size;
		LOGGER.info("钱教授观点5:存货周转率【" + info.get("OPESTCI") + "】，行业平均数【" + induTurnover + "】");
		if (info.get("OPESTCI") != null && info.get("OPESTCI").doubleValue() > induTurnover) {
			details[4] = true;
		}
		return details;
	}

	/**
	 * @Title: commonView
	 * @Description: 返回大妈观点对象
	 * @param
	 * @return View 返回类型
	 */
	private View commonView(String stockcode, double price) {
		Integer value = 0;
		View commonView = new View();
		boolean[] commonViewDetails = queryCommonDetail(stockcode, price);
		// 构造观点对象
		Map<String, String> meta = viewMetadataService.getMetadata();
		value = caculateValue(commonViewDetails);
		commonView.setScore(viewMetadataService.getCommonScores().get(value));
		commonView.setMessage(viewMetadataService.getCommonComments().get(value));

		Map<String, String> common1 = new HashMap<String, String>();
		common1.put(viewpoint, meta.get(ViewMetadataService.common1));
		common1.put(result, String.valueOf(commonViewDetails[0]));
		commonView.getDetails().add(common1);
		Map<String, String> common2 = new HashMap<String, String>();
		common2.put(viewpoint, meta.get(ViewMetadataService.common2));
		common2.put(result, String.valueOf(commonViewDetails[1]));
		commonView.getDetails().add(common2);
		Map<String, String> common3 = new HashMap<String, String>();
		common3.put(viewpoint, meta.get(ViewMetadataService.common3));
		common3.put(result, String.valueOf(commonViewDetails[2]));
		commonView.getDetails().add(common3);
		Map<String, String> common4 = new HashMap<String, String>();
		common4.put(viewpoint, meta.get(ViewMetadataService.common4));
		common4.put(result, String.valueOf(commonViewDetails[3]));
		commonView.getDetails().add(common4);
		Map<String, String> common5 = new HashMap<String, String>();
		common5.put(viewpoint, meta.get(ViewMetadataService.common5));
		common5.put(result, String.valueOf(commonViewDetails[4]));
		commonView.getDetails().add(common5);
		return commonView;
	}

	/**
	 * @Title: queryCommonDetail
	 * @Description: 返回大妈观点的详细信息
	 * @param
	 * @return boolean [] 返回类型
	 */
	private boolean[] queryCommonDetail(String stockcode, double price) {
		boolean[] details = new boolean[5];
		// 大妈一句话：饥荒大不大？(资产负债率<行业平均资产负债率*80%)
		List<Map<String, BigDecimal>> induInfos = viewDAO.selectInduInfo(stockcode);
		Map<String, BigDecimal> info = viewDAO.selectInfo(stockcode);
		double induLiability = 0;
		double induProfit = 0;
		for (Map<String, BigDecimal> induInfo : induInfos) {
			if (induInfo != null) {
				BigDecimal tempLiability = induInfo.get("CAPLAB");
				if (tempLiability != null) {
					induLiability = induLiability + tempLiability.doubleValue();
				}
				BigDecimal tempProfit = induInfo.get("RISOP");
				if (tempProfit != null) {
					induProfit = induProfit + tempProfit.doubleValue();
				}
			}
		}
		induLiability = induLiability / induInfos.size();
		LOGGER.info("大妈观点1:资产负债率【" + info.get("CAPLAB") + "】，行业平均资产负债率【" + induLiability + "】");
		if (info.get("CAPLAB") != null && info.get("CAPLAB").doubleValue() < induLiability * 0.8) {
			details[0] = true;
		}
		// 大妈一句话：挣钱不？(连续3年净利润增长)
		List<BigDecimal> netProfits = viewDAO.selectNetProfit(stockcode);
		LOGGER.info("大妈观点2:连续3年净利润【" + netProfits + "】");
		if (netProfits.size() == 3) {
			if (netProfits.get(0).doubleValue() > netProfits.get(1).doubleValue()
					&& netProfits.get(1).doubleValue() > netProfits.get(2).doubleValue()) {
				details[1] = true;
			}
		}
		// 大妈一句话：挣钱能力强不强？(净利润同比增长率>行业平均值*120%)
		induProfit = induProfit / induInfos.size();
		LOGGER.info("大妈观点3:净利润同比增长率【" + info.get("RISOP") + "】，行业平均值【" + induProfit + "】");
		if (info.get("RISOP") != null && info.get("RISOP").doubleValue() > induProfit * 1.2) {
			details[2] = true;
		}
		// 大妈一句话：发钱回馈股东不？(连续3年皆配息)
		List<Map<String, Object>> dividendInfos = viewDAO.selectDividend(stockcode);
		LOGGER.info("大妈观点4:连续3年配息信息【" + dividendInfos + "】");
		if (CollectionUtils.isNotEmpty(dividendInfos)) {
			details[3] = true;
			for (Map<String, Object> dividendInfo : dividendInfos) {
				String divInfo = (String) dividendInfo.get("DIVINFO");
				if (dividendInfo.get("CASHBT") == null && divInfo != null && divInfo.contains("不分配")) {
					details[3] = false;
				}
			}
			// 大妈一句话：收益比银行强不？(股息率>一年定期存款)
			BigDecimal divCash = (BigDecimal) dividendInfos.get(0).get("CASHBT");
			LOGGER.info("大妈观点5:每10股股息【" + divCash + "】，股价【" + price + "】，一年定期存款率【" + viewMetadataService.getInterest()
					+ "】");
			if (divCash != null) {
				if (divCash.doubleValue() / (10 * price) > viewMetadataService.getInterest()) {
					details[4] = true;
				}
			}
		}
		// else {
		// TODO 暂时以现金分红为主，暂时不计算股票转增
		// String divInfo = (String) dividendInfos.get(0).get("DIVINFO");
		// String regExp = "每10股[\u4e00-\u9fa5]+\\d+股";
		// }
		return details;
	}

	private Integer caculateValue(boolean... point) {
		Integer value = 0;
		for (int i = 0; i < point.length; i++) {
			value = value * 10;
			if (point[i] == true) {
				value = value + 1;
			}
		}
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.linlong.f10.view.service.ViewService#getViewsTest(java.lang.Integer)
	 */
	@Override
	public View getViewsTest(Integer code, ViewType viewType) {
		View buffettView = new View();
		switch (viewType) {
		case buffett:
			buffettView.setScore(viewMetadataService.getBuffettScores().get(code));
			buffettView.setMessage(viewMetadataService.getBuffettComments().get(code));
			break;
		case professor:
			buffettView.setScore(viewMetadataService.getProfessorScores().get(code));
			buffettView.setMessage(viewMetadataService.getProfessorComments().get(code));
			break;
		case common:
			buffettView.setScore(viewMetadataService.getCommonScores().get(code));
			buffettView.setMessage(viewMetadataService.getCommonComments().get(code));
			break;
		default:
			break;
		}
		return buffettView;
	}

}
