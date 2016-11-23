/**   
 * @Title: IPUtil.java
 * @Package com.linlong.f10.core.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author  yangwenjian   
 * @date 2016年9月29日 下午2:10:01
 * @version V1.0   
 */
package com.linlong.f10.core.util;

/**
 * @ClassName: IPUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年9月29日 下午2:10:01
 * 
 */
public class IPUtil {
	private IPUtil() {
	}

	public static IPUtil getInstance() {
		return IPUtil.SingletonHolder.instance;
	}

	public static long ipToLong(String strIp) {
		long[] ip = new long[4];
		int position1 = strIp.indexOf(".");
		int position2 = strIp.indexOf(".", position1 + 1);
		int position3 = strIp.indexOf(".", position2 + 1);
		ip[0] = Long.parseLong(strIp.substring(0, position1));
		ip[1] = Long.parseLong(strIp.substring(position1 + 1, position2));
		ip[2] = Long.parseLong(strIp.substring(position2 + 1, position3));
		ip[3] = Long.parseLong(strIp.substring(position3 + 1));
		return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
	}

	public static String longToIP(long longIp) {
		StringBuffer sb = new StringBuffer("");
		sb.append(String.valueOf(longIp >>> 24));
		sb.append(".");
		sb.append(String.valueOf((longIp & 16777215L) >>> 16));
		sb.append(".");
		sb.append(String.valueOf((longIp & 65535L) >>> 8));
		sb.append(".");
		sb.append(String.valueOf(longIp & 255L));
		return sb.toString();
	}
	private static class SingletonHolder {
        private static IPUtil instance = new IPUtil();

        private SingletonHolder() {
        }
    }
	public static void main(String[] args) {
		System.out.println(ipToLong("172.27.20.41"));
	}
}
