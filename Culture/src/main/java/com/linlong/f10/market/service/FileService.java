package com.linlong.f10.market.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.linlong.f10.dto.market.Writeable;

@Component
public class FileService {

	private static Logger LOGGER = Logger.getLogger(MarketService.class);
	public static final String dividendFileName = "dividend.txt";
	public static final String dividendIncFileName = "dividendInc.txt";
	private Map<String, ReentrantReadWriteLock> fileLocks = new HashMap<String, ReentrantReadWriteLock>();
	public static final String szFloatFileName = "market_sz.txt";
	public static final String shFloatFileName = "market_sh.txt";

	public static final String proStCodeFileName = "provinceStCode.txt";

	public static final String categoryFileName = "category.txt";
	public static final String induStCodeFileName = "industryStCode.txt";

	public static final String ROOTKEY = "f10.root";
	public static final String DOWNLOADDIR = "download";
	public static final String dir = System.getProperty(ROOTKEY) + System.getProperty("file.separator") + DOWNLOADDIR
			+ System.getProperty("file.separator");

	/**
	 * 
	 * @Title: persitenceProvince @Description: 将list结果集进行持久化 @param @param
	 *         marketList 结果 @param @param fileName 设定文件名称 @return void 返回类型 @throws
	 */
	public <T extends Writeable> void writetoFile(List<T> list, String fileName) {
		ReentrantReadWriteLock lock = retriveLock(fileName);
		BufferedWriter writer = null;
		String filePath = System.getProperty(ROOTKEY) + System.getProperty("file.separator") + DOWNLOADDIR
				+ System.getProperty("file.separator") + fileName;
		File file = new File(filePath);  
		if (!file.getParentFile().exists()) {
			boolean createDirectory = file.getParentFile().mkdirs();
			if (createDirectory == false) {
				LOGGER.error("error creating direcotry: " + file.getParentFile());
				return;
			}
		}
		lock.writeLock().lock();
		try {
			writer = new BufferedWriter(new FileWriter(file));
			for (Writeable object : list) {
				writer.write(object.writeObject());
				writer.newLine();
			}
		} catch (IOException e) {
			LOGGER.info(e.getMessage(), e);
		} finally {
			lock.writeLock().unlock();
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				LOGGER.info(e.getMessage(), e);
				throw new RuntimeException(e);
			}
		}
	}

	public void writeStrtoFile(List<String> list, String fileName) {
		ReentrantReadWriteLock lock = retriveLock(fileName);
		BufferedWriter writer = null;
		String filePath = System.getProperty(ROOTKEY) + System.getProperty("file.separator") + DOWNLOADDIR
				+ System.getProperty("file.separator") + fileName;
		File file = new File(filePath);
		if (!file.getParentFile().exists()) {
			boolean createDirectory = file.getParentFile().mkdirs();
			if (createDirectory == false) {
				LOGGER.error("error creating direcotry: " + file.getParentFile());
				return;
			}
		}
		lock.writeLock().lock();
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			for (String str : list) {
				if (str.equals("\n")) {
					writer.newLine();
				} else {
					writer.write(str + ",");
				}
			}
		} catch (IOException e) {
			LOGGER.info(e.getMessage(), e);
		} finally {
			lock.writeLock().unlock();
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				LOGGER.info(e.getMessage(), e);
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * @Title: retriveLock
	 * @Description: 获取锁
	 * @param
	 * @return ReentrantReadWriteLock 返回类型
	 */
	private synchronized ReentrantReadWriteLock retriveLock(String fileName) {
		ReentrantReadWriteLock rwlock = fileLocks.get(fileName);
		if (rwlock == null) {
			rwlock = new ReentrantReadWriteLock();
			fileLocks.put(fileName, rwlock);
		}
		return rwlock;
	}

	/**
	 * @Title: getMd5Base64 @Description: 更具String串生成MD5加密并用Base64进行编码 @param @param
	 *         string @param @return 设定文件 @return String 返回类型 @throws
	 */
	public String getMd5Base64(String inputStr) {
		String encodeStr = null;
		try {
			byte[] utfBytes = inputStr.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(utfBytes);
			byte[] md5Bytes = mdTemp.digest();
			encodeStr = new String(Base64.encodeBase64(md5Bytes), "utf-8");
			encodeStr = encodeStr.replaceAll("/", "+");
		} catch (NoSuchAlgorithmException e) {
			LOGGER.info(e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			LOGGER.info(e.getMessage(), e);
		}
		return encodeStr;
	}

	/**
	 * @Title: getLockFileName @Description: 根据特征值获取lock文件名称 @param @param
	 *         md5Str @param @return 设定文件 @return String 返回类型 @throws
	 */
	public String getLockFileName(String tagStr) {
		String lockFileName;
		SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd");
		lockFileName = System.getProperty(ROOTKEY) + "WEB-INF" + System.getProperty("file.separator")
				+ format.format(new Date()) + "_" + tagStr + ".lock";
		return lockFileName;
	}

	public void createLockFile(String md5Str) {
		String lockFileName = getLockFileName(md5Str);
		File lockFile = new File(lockFileName);
		if (!lockFile.exists()) {
			try {
				lockFile.createNewFile();
			} catch (IOException e) {
				LOGGER.info(e.getMessage(), e);
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 
	 * @Title: removeOldLockFile
	 * @Description: 删除所有WEN-INF下的lock文件
	 * @param
	 * @return void 返回类型
	 */
	public void removeOldLockFile() {
		File dirFile = new File(System.getProperty(ROOTKEY) + "WEB-INF" + System.getProperty("file.separator"));
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile() && files[i].exists() && files[i].getName().endsWith(".lock")) {
				files[i].delete();
			}
		}
	}

}
