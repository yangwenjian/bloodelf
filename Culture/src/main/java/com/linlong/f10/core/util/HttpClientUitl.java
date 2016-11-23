package com.linlong.f10.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * [HttpClient工具类]
 * 
 * @version: 1.0
 */
public class HttpClientUitl {

	private static final String cert = "jssecacerts";
	private static final String phrase = "jssecacerts";

	private static final char[] HEXDIGITS = "0123456789abcdef".toCharArray();

	/**
	 * 模拟post请求，进行远程访问并获取返回值
	 * 
	 * @param mapInfo
	 * @param URL
	 * @return
	 */
	public static String javahttpPost(Map<String, Object> mapInfo, String URL) {
		HttpClient client = HttpClientBuilder.create().build();
		RequestConfig defaultRequestConfig = RequestConfig.custom()
				.setSocketTimeout(5000).setConnectTimeout(5000)
				.setConnectionRequestTimeout(5000).build();
		try {
			String getStr = "";
			HttpPost httpost = new HttpPost(URL);
			httpost.setConfig(defaultRequestConfig);
			// 参数设置
			// 遍历map
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			if (mapInfo != null) {
				Iterator<String> iter = mapInfo.keySet().iterator();
				while (iter.hasNext()) {
					String key = iter.next();
					Object value = mapInfo.get(key);
					nvps.add(new BasicNameValuePair(key, value.toString()));
				}
				httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
				HttpResponse response = client.execute(httpost);
				int status = response.getStatusLine().getStatusCode();
				if (status == HttpStatus.SC_OK) {
					getStr = EntityUtils
							.toString(response.getEntity(), "UTF-8");
				}
			}
			httpost.releaseConnection();
			return getStr;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			HttpClientUtils.closeQuietly(client);
		}
	}

	/**
	 * 模拟get请求
	 * 
	 * @param URL
	 * @return
	 */
	public static String javahttpGet(String URL) {
		HttpClient client = HttpClientBuilder.create().build();
		RequestConfig defaultRequestConfig = RequestConfig.custom()
				.setSocketTimeout(5000).setConnectTimeout(5000)
				.setConnectionRequestTimeout(5000).build();
		try {
			HttpGet httpget = new HttpGet(URL + "&t="
					+ System.currentTimeMillis());
			httpget.setConfig(defaultRequestConfig);
			httpget.addHeader("Content-type", "text/html; charset=utf-8");
			HttpResponse response = client.execute(httpget);
			String getStr = "";
			int status = response.getStatusLine().getStatusCode();
			if (status == HttpStatus.SC_OK) {
				getStr = EntityUtils.toString(response.getEntity(), "UTF-8");
			} else {
				System.out.println(EntityUtils.toString(response.getEntity(),
						"UTF-8"));
			}
			httpget.releaseConnection();
			return getStr;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			HttpClientUtils.closeQuietly(client);
		}
	}

	/**
	 * 通过url抓取html页面内容
	 * 
	 * @param URL
	 * @return
	 */
	public static String getHtmlContentByUrl(String URL) {
		HttpClient client = HttpClientBuilder.create().build();
		RequestConfig defaultRequestConfig = RequestConfig.custom()
				.setSocketTimeout(5000).setConnectTimeout(5000)
				.setConnectionRequestTimeout(5000).build();
		try {
			HttpGet httpget = new HttpGet(URL);
			httpget.setConfig(defaultRequestConfig);
			HttpResponse response = client.execute(httpget);
			String getStr = "";
			int status = response.getStatusLine().getStatusCode();
			if (status == HttpStatus.SC_OK) {
				getStr = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
			httpget.releaseConnection();
			return getStr;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			HttpClientUtils.closeQuietly(client);
		}
	}

	public static String sslHttpGet(String url) throws Exception {
		char[] passphrase = phrase.toCharArray();
		String host = url.substring(0, 12);
		File file = new File(cert);
		if (file.isFile() == false) {
			char SEP = File.separatorChar;
			File dir = new File(System.getProperty("java.home") + SEP + "lib"
					+ SEP + "security");
			file = new File(dir, cert);
			if (file.isFile() == false) {
				file = new File(dir, "cacerts");
			}
		}
		System.out.println("Loading KeyStore " + file + "...");
		InputStream in = new FileInputStream(file);
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		ks.load(in, passphrase);
		in.close();

		SSLContext context = SSLContext.getInstance("TLS");
		TrustManagerFactory tmf = TrustManagerFactory
				.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		tmf.init(ks);
		X509TrustManager defaultTrustManager = (X509TrustManager) tmf
				.getTrustManagers()[0];
		SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
		context.init(null, new TrustManager[] { tm }, null);
		SSLSocketFactory factory = context.getSocketFactory();

		SSLSocket socket = (SSLSocket) factory.createSocket(host, 443);
		socket.setSoTimeout(10000);
		try {
			System.out.println("Starting SSL handshake...");
			socket.startHandshake();
			socket.close();
			System.out.println();
			System.out.println("No errors, certificate is already trusted");
		} catch (SSLException e) {
			System.out.println();
			e.printStackTrace(System.out);
		}

		X509Certificate[] chain = tm.chain;
		if (chain == null) {
			System.out.println("Could not obtain server certificate chain");
			return null;
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		System.out.println();
		System.out.println("Server sent " + chain.length + " certificate(s):");
		System.out.println();
		MessageDigest sha1 = MessageDigest.getInstance("SHA1");
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		for (int i = 0; i < chain.length; i++) {
			X509Certificate cert = chain[i];
			System.out.println(" " + (i + 1) + " Subject "
					+ cert.getSubjectDN());
			System.out.println("   Issuer  " + cert.getIssuerDN());
			sha1.update(cert.getEncoded());
			System.out.println("   sha1    " + toHexString(sha1.digest()));
			md5.update(cert.getEncoded());
			System.out.println("   md5     " + toHexString(md5.digest()));
			System.out.println();
		}

		System.out
				.println("Enter certificate to add to trusted keystore or 'q' to quit: [1]");
		String line = reader.readLine().trim();
		int k;
		try {
			k = (line.length() == 0) ? 0 : Integer.parseInt(line) - 1;
		} catch (NumberFormatException e) {
			System.out.println("KeyStore not changed");
			return null;
		}
		X509Certificate cert = chain[k];
		String alias = host + "-" + (k + 1);
		ks.setCertificateEntry(alias, cert);

		OutputStream out = new FileOutputStream("jssecacerts");
		ks.store(out, passphrase);
		out.close();

		System.out.println();
		System.out.println(cert);
		System.out.println();
		System.out
				.println("Added certificate to keystore 'jssecacerts' using alias '"
						+ alias + "'");
		return null;
	}

	private static String toHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * 3);
		for (int b : bytes) {
			b &= 0xff;
			sb.append(HEXDIGITS[b >> 4]);
			sb.append(HEXDIGITS[b & 15]);
			sb.append(' ');
		}
		return sb.toString();
	}

	private static class SavingTrustManager implements X509TrustManager {

		private final X509TrustManager tm;
		private X509Certificate[] chain;

		SavingTrustManager(X509TrustManager tm) {
			this.tm = tm;
		}

		public X509Certificate[] getAcceptedIssuers() {
			throw new UnsupportedOperationException();
		}

		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			throw new UnsupportedOperationException();
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			this.chain = chain;
			tm.checkServerTrusted(chain, authType);
		}
	}
}
