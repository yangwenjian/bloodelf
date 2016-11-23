package com.linlong.f10.core.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created on   2015年7月22日
 * Title:       麟龙大平台_[公共]_[加密]
 * Description: [DES（Data Encryption Standard）加密标准实现]
 * Copyright:   Copyright (c) 2015
 * Company:     麟龙科技股份有限公司
 * Department:  研发部
 * @author:     suliang
 * @version:    1.0
*/
public class DesUtil {

	/**
	 * 加密密钥，长度必须为8位，任意字符
	 */
	public static final String KEY = "LinLongC";

	/**
	 * Created on   2015年7月22日
	 * Discription: [解密字符串]
	 * @param message
	 * @param urlDecodeFlag
	 * @return
	 * @author:     suliang
	 * @update:     2015年7月22日 下午5:48:33
	 */
	public static String decrypt(String message, boolean urlDecodeFlag) {
		String rtnStr = "";
		try {
			if (null != message && !"".equals(message)) {
				if (urlDecodeFlag) {
					rtnStr = URLDecoder.decode(message, "utf-8");
				}
				byte[] bytesrc = convertHexString(rtnStr);
				Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
				DESKeySpec desKeySpec = new DESKeySpec(KEY.getBytes("UTF-8"));
				SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
				SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
				IvParameterSpec iv = new IvParameterSpec(KEY.getBytes("UTF-8"));
				cipher.init(2, secretKey, iv);
				byte[] retByte = cipher.doFinal(bytesrc);
				rtnStr = new String(retByte,"utf-8");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return rtnStr;
	}

	/**
	 * Created on   2015年7月22日
	 * Discription: [先对给定的字符串进行url解码，再解密字符串]
	 * @param message
	 * @return
	 * @author:     suliang
	 * @update:     2015年7月22日 下午3:35:34
	 */
	public static String decrypt(String message) {
		return decrypt(message, true);
	}

	/**
	 * Created on   2015年7月22日
	 * Discription: [对给定的字符串进行加密]
	 * @param message
	 * @param urlEncodeFlag
	 * @return
	 * @author:     suliang
	 * @update:     2015年7月22日 下午3:35:07
	 */
	public static String encrypt(String message, boolean urlEncodeFlag) {
		String rtnStr = null;
		try {
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			DESKeySpec desKeySpec = new DESKeySpec(KEY.getBytes("UTF-8"));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			IvParameterSpec iv = new IvParameterSpec(KEY.getBytes("UTF-8"));
			cipher.init(1, secretKey, iv);
			rtnStr = toHexString(cipher.doFinal(message.getBytes("UTF-8")));
			if (urlEncodeFlag) {
				rtnStr = URLEncoder.encode(rtnStr, "utf-8");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return rtnStr;
	}

	/**
	 * 
	 * Created on   2015年9月15日
	 * Discription: [字符串md5加密]
	 * @param str
	 * @return String
	 * @author:     lch
	 * @update:     2015年9月15日 下午5:01:40
	 */
	public static String getMd5(String str) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			return "";
		}
		char[] charArray = str.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];

		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		
		return hexValue.toString();
	}

	/**
	 * Created on   2015年7月22日
	 * Discription: [对给定的字符串进行加密,并对加密后的字符串进行URLEncode]
	 * @param message
	 * @return
	 * @author:     suliang
	 * @update:     2015年7月22日 下午5:33:48
	 */
	public static String encrypt(String message) {
		return encrypt(message, true);
	}

	/**
	 * Created on   2015年7月22日
	 * Discription: [转十六进制]
	 * @param ss
	 * @return byte[]
	 * @author:     suliang
	 * @update:     2015年7月22日 下午5:47:12
	 */
	private static byte[] convertHexString(String ss) {
		byte[] digest = new byte[ss.length() / 2];
		for (int i = 0; i < digest.length; i++) {
			String byteString = ss.substring(2 * i, 2 * i + 2);
			int byteValue = Integer.parseInt(byteString, 16);
			digest[i] = ((byte) byteValue);
		}
		return digest;
	}

	/**
	 * Created on   2015年7月22日
	 * Discription: [转十六进制]
	 * @param b
	 * @return String
	 * @author:     suliang
	 * @update:     2015年7月22日 下午5:46:47
	 */
	private static String toHexString(byte[] b) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String plainText = Integer.toHexString(0xFF & b[i]);
			if (plainText.length() < 2)
				plainText = "0" + plainText;
			hexString.append(plainText);
		}
		return hexString.toString();
	}
}