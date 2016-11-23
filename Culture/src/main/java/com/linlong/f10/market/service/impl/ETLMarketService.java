/**   
 * @Title: ETLMarketService.java
 * @Package com.f10.market.serviceImpl
 * @Description: 提供与巨灵数据同步，并查询菜单和股票代码
 * @author  yangwenjian   
 * @date 2016年7月4日 下午5:31:37
 * @version V1.0   
 */
package com.linlong.f10.market.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.linlong.f10.dto.market.CategoryStockCode;
import com.linlong.f10.dto.market.Dividend;
import com.linlong.f10.dto.market.DividendDTO;
import com.linlong.f10.dto.market.Market;
import com.linlong.f10.market.f10dao.CategoryDAO;
import com.linlong.f10.market.geniusdao.MarketDAO;
import com.linlong.f10.market.geniusdao.WarrantDAO;
import com.linlong.f10.market.service.ContentType;
import com.linlong.f10.market.service.FileService;
import com.linlong.f10.market.service.MarketService;

@Service("etlService")
public class ETLMarketService implements MarketService {
	@Autowired
	private MarketDAO marketDAO;

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private WarrantDAO warrantDAO;

	@Value("${server.domain}")
	private String domain;

	@Autowired
	private FileService fileService;
	private static final String SHTradeMarket = "上海证券交易所";
	private static final String SZTradeMarket = "深圳证券交易所";

	private List<Map<String, Object>> SHShareResult;
	private List<Map<String, Object>> SZShareResult;
	private List<Market> SHMarket = new ArrayList<Market>(100);
	private List<Market> SZMarket = new ArrayList<Market>(100);

	private List<CategoryStockCode> proStCodes = new ArrayList<CategoryStockCode>(100);

	private List<CategoryStockCode> induStCodes = new ArrayList<CategoryStockCode>(100);

	private List<String> categoryList = new ArrayList<String>(100);

	private List<DividendDTO> dividendList = new ArrayList<DividendDTO>(12000);
	private Map<String, Date> dividendLast = new HashMap<String, Date>();
	private List<DividendDTO> dividendIncList = new ArrayList<DividendDTO>();
	private boolean first = true;

	private static Logger LOGGER = Logger.getLogger(ETLMarketService.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.f10.market.service.MarketService#preReadData()
	 */
	@Override
	//@PostConstruct
	public void preReadData() {
		SHMarket.clear();
		SZMarket.clear();
		proStCodes.clear();
		induStCodes.clear();
		categoryList.clear();
		dividendList.clear();
		dividendLast.clear();
		dividendIncList.clear();
		// 预读股本信息到内存中
		queryFloatShare();
		// 预读分类信息到内存中
		queryCategory();
		// 将巨灵的数据库读出，并更新到F10库中
		queryAndUpdateProvSt();
		queryAndUpdateInduSt();
		// 预读省会数据到内存中
		queryProStock();
		// 预读行业分来数据到内存中
		queryInduStock();
		// 预读所有除权信息到内存中,如果是第二次查询（每日早晨定时任务），先读增量的除权信息，再全部读入所有除权信息，并更新时间戳
		if (first) {
			queryDividend();
		} else {
			queryDividendInc();
			queryDividend();
		}
		first = false;
		// 将结果写道文件中
		writeDatatoFile();

	}

	/**
	 * @Title: queryDividend
	 * @Description: 预读所有除权信息到内存中,并将每只股票的最后一条除权信息的时间存入dividendLast中
	 * @param
	 * @return void 返回类型
	 */
	private void queryDividend() {
		// 从数据库读出所有除权信息，按股票代码和时间为key，之后进行合并
		List<Dividend> dividents = warrantDAO.selectAllDividend();
		String tempStockcode = "";
		Date tempDate = new Date();
		DividendDTO dto = null;
		for (Dividend dividend : dividents) {
			String stockcode = dividend.getStockcode();
			// 结果集中新的stockcode，则存入dividendList中
			if (tempStockcode != null && !tempStockcode.equals(stockcode) && dividend.getExdividate() != null) {
				dto = new DividendDTO();
				dto.setStockcode(dividend.getStockcode());
				if ("上海证券交易所".equals(dividend.getMarket())) {
					dto.setMarket("SH");
				} else if ("深圳证券交易所".equals(dividend.getMarket())) {
					dto.setMarket("SZ");
				}else if("其他市场".equals(dividend.getMarket())){
					dto.setMarket("QT");
				}else{
					continue;
				}
				dto.getList().add(dividend);
				// 如果是新的股票加入，则把上一个股票的最后一个值和时间戳存入到dividendLast中，保存各个股票的时间戳
				if (!StringUtils.isEmpty(tempStockcode)) {
					dividendLast.put(tempStockcode, tempDate);
				}
				// 加入结果list中
				dividendList.add(dto);
				// 更新时间戳和上支股票的代码
				tempStockcode = stockcode;
				tempDate = dividend.getExdividate();

			}// 如果结果集中stockcode和上一个相同，则将上一个存入的dto取出来，将结果塞入进去。
			else if (tempStockcode != null && tempStockcode.equals(stockcode) && dividend.getExdividate() != null) {
				Dividend last = dto.getList().get(dto.getList().size() - 1);
				// 如果当前除权信息和上一条除权信息的时间相同，则将这条数据和上一条数据合并存储。
				if (tempDate != null && tempDate.equals(dividend.getExdividate())) {
					if (dividend.getAllot() != 0) {
						last.setAllot(dividend.getAllot());
					}
					if (dividend.getAllotPrice() != 0) {
						last.setAllotPrice(dividend.getAllotPrice());
					}
					if (dividend.getBonus() != 0) {
						last.setBonus(dividend.getBonus());
					}
					if (dividend.getCash() != 0) {
						last.setCash(dividend.getCash());
					}
				} // 如果时间戳不同，则直接放入当前股票的dto中
				else {
					dto.getList().add(dividend);
				}
				tempDate = dividend.getExdividate();
			}
		}
		// 最后一支股票的时间戳
		dividendLast.put(tempStockcode, tempDate);
		LOGGER.debug("除权信息更新完毕");
	}

	/**
	 * @Title: queryDividend
	 * @Description: 根据dividendLast里的时间信息，将新的数据存入dividendIncList中
	 * @param
	 * @return void 返回类型
	 */
	private void queryDividendInc() {
		List<Dividend> dividents = warrantDAO.selectAllDividend();
		String tempStockcode = "";
		Date tempDate = new Date();
		DividendDTO dto = null;
		for (Dividend dividend : dividents) {
			String stockcode = dividend.getStockcode();
			Date exdividate = dividend.getExdividate();
			Date lastdate = dividendLast.get(stockcode);
			// 和之前逻辑相同，但是多了一个条件就是当前除权信息dividend的时间在记录的时间戳后，才存储到dividendIncList中
			if (tempStockcode != null && !tempStockcode.equals(stockcode) && exdividate != null && lastdate != null
					&& exdividate.after(lastdate)) {
				dto = new DividendDTO();
				dto.setStockcode(dividend.getStockcode());
				if ("上海证券交易所".equals(dividend.getMarket())) {
					dto.setMarket("SH");
				} else if ("深圳证券交易所".equals(dividend.getMarket())) {
					dto.setMarket("SZ");
				}else if("其他市场".equals(dividend.getMarket())){
					dto.setMarket("QT");
				}else{
					continue;
				}
				dto.getList().add(dividend);
				dividendIncList.add(dto);
				tempStockcode = stockcode;
				tempDate = dividend.getExdividate();
			} // 如果和上支股票代码相同并且时间在记录的时间戳后，才进行合并存储
			else if (tempStockcode != null && tempStockcode.equals(stockcode) && exdividate != null && lastdate != null
					&& exdividate.after(lastdate)) {
				Dividend last = dto.getList().get(dto.getList().size() - 1);
				if (tempDate != null && tempDate.equals(dividend.getExdividate())) {
					if (dividend.getAllot() != 0) {
						last.setAllot(dividend.getAllot());
					}
					if (dividend.getAllotPrice() != 0) {
						last.setAllotPrice(dividend.getAllotPrice());
					}
					if (dividend.getBonus() != 0) {
						last.setBonus(dividend.getBonus());
					}
					if (dividend.getCash() != 0) {
						last.setCash(dividend.getCash());
					}
				} else {
					dto.getList().add(dividend);
				}
				tempDate = dividend.getExdividate();
			}
		}
		LOGGER.debug("除权信息更新完毕");
	}

	private void queryCategory() {
		int level = 1;
		List<Map<String, Object>> parentCates = categoryDAO.selectCategory(null, 0L);
		for (Map<String, Object> parentCate : parentCates) {
			Long id = (Long) parentCate.get("id");
			String cateStr = "0|" + id.toString() + "&" + (String) parentCate.get("catename");
			categoryList.add(cateStr);
			addAllLeaf(level, id, categoryList);
			categoryList.add("\n");
		}
		LOGGER.debug(categoryList);
	}

	/**
	 * @Title: addAllLeaf
	 * @Description: 将所有的节点按顺序加入到树中
	 * @param
	 * @return void 返回类型
	 */
	private void addAllLeaf(int level, Long id, List<String> categoryList) {
		List<Map<String, Object>> llevelCates = categoryDAO.selectCategory(null, id);
		for (Map<String, Object> llevelCate : llevelCates) {
			Long l1id = (Long) llevelCate.get("id");
			String l1cateStr = level + "|" + l1id.toString() + "&" + (String) llevelCate.get("catename");
			categoryList.add(l1cateStr);
			if ("0".equals(llevelCate.get("isleaf"))) {
				addAllLeaf(level + 1, l1id, categoryList);
			}
		}
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

	/**
	 * 
	 * @Title: queryProvinceStockCode
	 * @Description: 从巨灵库中读取股票与省市的对应关系，然后检索我们的库，如果没有则插入，如果不一致则更新
	 * @param
	 * @return void 返回类型
	 */
	public void queryAndUpdateProvSt() {
		List<Map<String, String>> provStMaps = marketDAO.selectProvinceStockCode();
		List<Map<String, Object>> etlProStMap = categoryDAO.selectl1StCode("地区");

		for (Map<String, String> map : provStMaps) {
			boolean newStock = true;
			String stockCode = map.get("stockcode");
			String provName = map.get("provname");
			for (Map<String, Object> etlmap : etlProStMap) {
				if (stockCode.equals(etlmap.get("stockcode"))) {
					if (!provName.equals(etlmap.get("menuname"))) {
						updateStock(stockCode, provName, map.get("stocksname"));
					}
					newStock = false;
					break;
				}
			}
			if (newStock == true) {
				insertStock(stockCode, provName, map.get("stocksname"));
			}
		}
	}

	/**
	 * @Title: updateStock
	 * @Description: 更新股票的地区信息
	 * @param
	 * @return void 返回类型
	 */
	private void updateStock(String stockCode, String menuName, String stockName) {
		Long menuId = categoryDAO.selectMenuId(menuName);
		if (menuId == null) {
			System.err.println("stockCode[" + stockCode + "],menuName[" + menuName + "],stockName[" + stockName
					+ "]没有相应menu");
			return;
		}
		categoryDAO.updateStock(stockCode, menuId);
	}

	/**
	 * @Title: insertStock
	 * @Description: 添加新的股票
	 * @param
	 * @return void 返回类型
	 */
	private void insertStock(String stockCode, String menuName, String stockName) {
		Long menuId = categoryDAO.selectMenuId(menuName);
		if (menuId == null) {
			System.err.println("stockCode[" + stockCode + "],menuName[" + menuName + "],stockName[" + stockName
					+ "]没有相应menu");
			return;
		}
		if (StringUtils.isEmpty(stockName)) {
			stockName = "";
		}
		categoryDAO.insertStock(stockCode, stockName, menuId);
		// categoryDAO.insertStockbyMenuName(stockCode, stockName, menuName);
	}

	/**
	 * @Title: queryProStock
	 * @Description: 将数据预读到内存中
	 * @param
	 * @return void 返回类型
	 */
	private void queryProStock() {
		List<Map<String, Object>> etlProStMap = categoryDAO.selectl1StCode("地区");
		if (CollectionUtils.isEmpty(etlProStMap)) {
			return;
		}
		Map<String, Object> firstElement = etlProStMap.get(0);
		CategoryStockCode code = new CategoryStockCode();
		code.setClassifyCode(firstElement.get("menuid").toString());
		code.getCodes().add((String) firstElement.get("stockcode"));
		proStCodes.add(code);
		int size = etlProStMap.size();
		for (int i = 1; i < size; i++) {
			Map<String, Object> etlprovSt = etlProStMap.get(i);
			code = proStCodes.get(proStCodes.size() - 1);
			if (code.getClassifyCode().equals(etlprovSt.get("menuid").toString())) {
				code.getCodes().add((String) etlprovSt.get("stockcode"));
			} else {
				code = new CategoryStockCode();
				code.setClassifyCode(etlprovSt.get("menuid").toString());
				code.getCodes().add((String) etlprovSt.get("stockcode"));
				proStCodes.add(code);
			}
		}
	}

	/**
	 * 
	 * @Title: queryIndustryStockCode
	 * @Description: 从巨灵库中读取股票与省市的对应关系，然后检索我们的库，如果没有则插入，如果不一致则更新
	 * @param
	 * @return void 返回类型
	 */
	public void queryAndUpdateInduSt() {
		List<Map<String, String>> induStCodeResult = marketDAO.selectIndustryStockCode();
		List<Map<String, Object>> etlInduStMap = categoryDAO.selectl2StCode("行业");
		for (Map<String, String> map : induStCodeResult) {
			boolean newStock = true;
			String stockCode = map.get("stockcode");
			String induName = map.get("induname");
			for (Map<String, Object> etlmap : etlInduStMap) {
				if (stockCode.equals(etlmap.get("stockcode"))) {
					if (!induName.equals(etlmap.get("menuname"))) {
						updateStock(stockCode, induName, map.get("stocksname"));
					}
					newStock = false;
					break;
				}
			}
			if (newStock == true) {
				insertStock(stockCode, induName, map.get("stocksname"));
			}
		}

	}

	/**
	 * @Title: queryInduStock
	 * @Description: 将数据预读到内存中
	 * @param
	 * @return void 返回类型
	 */
	private void queryInduStock() {
		List<Map<String, Object>> etlProStMap = categoryDAO.selectl2StCode("行业");
		if (CollectionUtils.isEmpty(etlProStMap)) {
			return;
		}
		CategoryStockCode code = new CategoryStockCode();
		Map<String, Object> firstResult = etlProStMap.get(0);
		code.setClassifyCode(firstResult.get("menuid").toString());
		code.getCodes().add((String) firstResult.get("stockcode"));
		induStCodes.add(code);
		int size = etlProStMap.size();
		for (int i = 1; i < size; i++) {
			Map<String, Object> map = etlProStMap.get(i);
			CategoryStockCode classifyStCode = induStCodes.get(induStCodes.size() - 1);
			String induCodel2 = map.get("menuid").toString();
			if (induCodel2.equals(classifyStCode.getClassifyCode())) {
				classifyStCode.getCodes().add((String) map.get("stockcode"));
				if (map.get("stockcode") == null) {
					System.out.println("error: " + code);
				}
			} else {
				classifyStCode = new CategoryStockCode();
				classifyStCode.setClassifyCode(induCodel2);
				classifyStCode.getCodes().add((String) map.get("stockcode"));
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
		case DIVIDEND:
			fileName = FileService.dividendFileName;
			break;
		case DIVIDENDINC:
			fileName = FileService.dividendIncFileName;
			break;
		default:
			break;
		}
		return fileName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.f10.market.service.MarketService#writeDatatoFile()
	 */
	@Override
	public void writeDatatoFile() {
		fileService.writetoFile(SHMarket, FileService.shFloatFileName);
		fileService.writetoFile(SZMarket, FileService.szFloatFileName);
		fileService.writetoFile(proStCodes, FileService.proStCodeFileName);
		fileService.writetoFile(induStCodes, FileService.induStCodeFileName);
		fileService.writeStrtoFile(categoryList, FileService.categoryFileName);
		fileService.writetoFile(dividendList, FileService.dividendFileName);
		fileService.writetoFile(dividendIncList, FileService.dividendIncFileName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.f10.market.service.MarketService#queryFloatShareFile(java.util.List)
	 */
	@Override
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

	private BigDecimal findFloatShare(String stockCode, List<Map<String, Object>> flShareSHResult) {
		for (Map<String, Object> map : flShareSHResult) {
			if (stockCode.equals(map.get("STOCKCODE"))) {
				return (BigDecimal) map.get("FL_SHR");
			}
		}
		return null;
	}

}
