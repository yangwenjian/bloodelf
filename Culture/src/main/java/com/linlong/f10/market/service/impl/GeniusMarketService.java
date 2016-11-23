package com.linlong.f10.market.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.linlong.f10.dto.market.CategoryStockCode;
import com.linlong.f10.dto.market.Market;
import com.linlong.f10.market.geniusdao.MarketDAO;
import com.linlong.f10.market.service.ContentType;
import com.linlong.f10.market.service.FileService;
import com.linlong.f10.market.service.MarketService;

@Service("geniusService")
public class GeniusMarketService implements MarketService {

	@Autowired
	private MarketDAO marketDAO;

	@Value("${server.domain}")
	private String domain;

	@Autowired
	private FileService fileService;

	private static final String SHTradeMarket = "上海证券交易所";
	private static final String SZTradeMarket = "深圳证券交易所";

	private List<Map<String, Object>> SHShareResult;
	private List<Map<String, Object>> SZShareResult;
	private List<Market> SHMarket = new ArrayList<Market>();
	private List<Market> SZMarket = new ArrayList<Market>();

	private List<String> proList;
	private List<CategoryStockCode> proStCodes;

	private List<String> industryList;
	private List<CategoryStockCode> induStCodes;

	private static Logger LOGGER = Logger.getLogger(GeniusMarketService.class);

	// @PostConstruct
	public void preReadData() {
		// 预读股本信息到内存中
		queryFloatShare();
		// 预读省会数据到内存中
		queryProvince();
		queryProvinceStockCode();
		// 预读行业分来数据到内存中
		queryIndustry();
		queryIndustryStockCode();
	}

	// @PostConstruct
	public void writeDatatoFile() {
		fileService.writetoFile(SHMarket, FileService.shFloatFileName);
		fileService.writetoFile(SZMarket, FileService.szFloatFileName);
		fileService.writetoFile(proStCodes, FileService.proStCodeFileName);
		fileService.writetoFile(induStCodes, FileService.induStCodeFileName);
	}

	private void queryFloatShare() {
		SHShareResult = marketDAO.selectFloatShare(SHTradeMarket);
		SZShareResult = marketDAO.selectFloatShare(SZTradeMarket);
		Market market;
		for (Map<String, Object> map : SHShareResult) {
			market = new Market();
			String code = (String) map.get("STOCKCODE");
			market.setCode(code);
			BigDecimal floatShare = (BigDecimal) map.get("FL_SHR");
			if (floatShare != null) {
				market.setFloatShare(floatShare);
			}
			SHMarket.add(market);
		}
		for (Map<String, Object> map : SZShareResult) {
			market = new Market();
			String code = (String) map.get("STOCKCODE");
			market.setCode(code);
			BigDecimal floatShare = (BigDecimal) map.get("FL_SHR");
			if (floatShare != null) {
				market.setFloatShare(floatShare);
			}
			SZMarket.add(market);
		}
	}

	public String queryFloatShareFile(List<String> codes) {
		String tagStr = fileService.getMd5Base64(codes.toString());
		String fileName = "market_" + tagStr + ".txt";
		String lockFileName = fileService.getLockFileName(tagStr);
		File lockFile = new File(lockFileName);
		// 如果对应的锁文件存在，则直接返回相应的url，
		if (lockFile.exists()) {
			return fileName;
		}
		List<Market> marketList = null;
		marketList = this.queryFloatShare(codes);
		if (CollectionUtils.isEmpty(marketList)) {
			return "没有相应股票的股本信息，请重新输入股票代码！";
		}
		fileService.writetoFile(marketList, fileName);
		fileService.createLockFile(tagStr);

		return fileName;
	}

	public List<Market> queryFloatShare(List<String> codes) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("codes input: " + codes);
		}
		List<Market> marketList = new ArrayList<Market>();
		Market market = null;

		for (String code : codes) {
			if (code.startsWith("SH")) {
				market = new Market();
				String stockCode = code.substring(2);
				BigDecimal floatShare = findFloatShare(stockCode, SHShareResult);
				if (floatShare != null) {
					market.setCode(code);
					market.setFloatShare(floatShare);
					marketList.add(market);
				}
			} else if (code.startsWith("SZ")) {
				market = new Market();
				String stockCode = code.substring(2);
				BigDecimal floatShare = findFloatShare(stockCode, SZShareResult);
				if (floatShare != null) {
					market.setCode(code);
					market.setFloatShare(floatShare);
					marketList.add(market);
				}
			}
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("market list: " + marketList);
		}
		return marketList;
	}

	private BigDecimal findFloatShare(String stockCode,
			List<Map<String, Object>> flShareSHResult) {
		for (Map<String, Object> map : flShareSHResult) {
			if (stockCode.equals(map.get("STOCKCODE"))) {
				return (BigDecimal) map.get("FL_SHR");
			}
		}
		return null;
	}

	private void queryProvince() {
		proList = marketDAO.selectProvince();
		// for(String area : proList){
		// System.out.println("(5, \"" + area.substring(9) +
		// "\", 1, 1, \"ywj\", \"ywj\"), " );
		// }
		proList.add(0, "0*000000&地区");

	}

	public void queryProvinceStockCode() {
		proStCodes = new ArrayList<CategoryStockCode>();
		List<Map<String, String>> provinceStockCodeMaps = marketDAO
				.selectProvinceStockCode();
		if (CollectionUtils.isEmpty(provinceStockCodeMaps)) {
			return;
		}
		Map<String, String> firstElement = provinceStockCodeMaps.get(0);
		CategoryStockCode code = new CategoryStockCode();
		code.setClassifyCode(firstElement.get("DISTCODE"));
		code.getCodes().add(firstElement.get("STOCKCODE"));
		proStCodes.add(code);
		for (Map<String, String> provinceStockCodeMap : provinceStockCodeMaps) {
			code = proStCodes.get(proStCodes.size() - 1);
			if (code.getClassifyCode().equals(
					provinceStockCodeMap.get("DISTCODE"))) {
				code.getCodes().add(provinceStockCodeMap.get("STOCKCODE"));
			} else {
				code = new CategoryStockCode();
				code.setClassifyCode(provinceStockCodeMap.get("DISTCODE"));
				code.getCodes().add(provinceStockCodeMap.get("STOCKCODE"));
				proStCodes.add(code);
			}
		}
	}

	public void queryIndustry() {
		industryList = new ArrayList<String>();
		industryList.add("0*0&行业");
		List<Map<String, Object>> indul1Result = marketDAO.selectIndustry(1);
		List<Map<String, Object>> indul2Result = marketDAO.selectIndustry(2);
		for (Map<String, Object> mapl1 : indul1Result) {
			Integer innerCode = (Integer) mapl1.get("INNER_CODE");
			String industryl1 = "1*" + mapl1.get("INDU_CODE") + "&"
					+ mapl1.get("INDU_NAME");
			industryList.add(industryl1);
			for (Map<String, Object> mapl2 : indul2Result) {
				Integer parent = (Integer) mapl2.get("PARENT_CODE");
				if (innerCode.equals(parent)) {
					String industryl2 = "2*" + mapl2.get("INDU_CODE") + "&"
							+ mapl2.get("INDU_NAME");
					industryList.add(industryl2);
				}
			}
		}
		// for(int i = 0; i < industryList.size(); i++){
		// String indu = industryList.get(i);
		// if(indu.startsWith("2")){
		// System.out.println("(7, \"" + indu.substring(indu.indexOf("&")+1) +
		// "\", " + i + ", 1, 1, \"ywj\", \"ywj\"), " );
		// }else{
		//
		// System.out.println("(7, \"" + indu.substring(indu.indexOf("&")+1) +
		// "\", " + i + ", 0, 1, \"ywj\", \"ywj\"), " );
		// }
		// }
		System.out.println("");
	}

	public void queryIndustryStockCode() {
		induStCodes = new ArrayList<CategoryStockCode>();
		List<Map<String, String>> induStCodeResult = marketDAO
				.selectIndustryStockCode();
		if (CollectionUtils.isEmpty(induStCodeResult)) {
			return;
		}
		CategoryStockCode code = new CategoryStockCode();
		Map<String, String> firstResult = induStCodeResult.get(0);
		code.setClassifyCode(firstResult.get("INDUCODEL2"));
		code.getCodes().add(firstResult.get("STOCKCODE"));
		induStCodes.add(code);
		int size = induStCodeResult.size();
		for (int i = 1; i < size; i++) {
			Map<String, String> map = induStCodeResult.get(i);
			CategoryStockCode classifyStCode = induStCodes.get(induStCodes
					.size() - 1);
			String induCodel2 = map.get("INDUCODEL2");
			if (induCodel2.equals(classifyStCode.getClassifyCode())) {
				classifyStCode.getCodes().add(map.get("STOCKCODE"));
			} else {
				classifyStCode = new CategoryStockCode();
				classifyStCode.setClassifyCode(induCodel2);
				classifyStCode.getCodes().add(map.get("STOCKCODE"));
				induStCodes.add(classifyStCode);
			}
		}
	}

	@Override
	public String queryMarketFile(ContentType fileType) {
		String fileName = null;
		switch (fileType) {
		case FLOATSHARE_SH:
			fileName = FileService.shFloatFileName;
			break;
		case FLOATSHARE_SZ:
			fileName = FileService.szFloatFileName;
			break;
		case PROVINCESTCODE:
			fileName = FileService.proStCodeFileName;
			break;
		case CATEGORY:
			fileName = FileService.categoryFileName;
			break;
		case INDUSTRYSTCODE:
			fileName = FileService.induStCodeFileName;
			break;
		default:
			break;
		}
		return fileName;
	}
}
