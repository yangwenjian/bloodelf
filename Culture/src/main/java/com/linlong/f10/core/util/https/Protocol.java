package com.linlong.f10.core.util.https;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public abstract class Protocol {

	/**
	 * 登陆
	 * 
	 * @param username
	 * @param password
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public abstract String login(String username, String password, String url);

	/**
	 * 获取token
	 * 
	 * @param tokenId
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public abstract String validateToken(String tokenId, String url);

	protected String post(List<NameValuePair> param, String url) {
		String responseContent = null;
		HttpClient httpClient = getHttpClient();
		HttpPost post = new HttpPost(url);
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(param,
				Consts.UTF_8);
		post.setEntity(entity);
		HttpResponse response = null;
		try {
			response = httpClient.execute(post);
			HttpEntity response_entity = response.getEntity();
			responseContent = EntityUtils.toString(response_entity, ContentType
					.getOrDefault(response_entity).getCharset());
			EntityUtils.consume(response_entity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseContent;
	}

	protected String get(String url) {
		String responseContent = null;
		HttpClient httpClient = getHttpClient();
		HttpGet post = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(post);
			HttpEntity response_entity = response.getEntity();
			responseContent = EntityUtils.toString(response_entity, ContentType
					.getOrDefault(response_entity).getCharset());
			EntityUtils.consume(response_entity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseContent;
	}

	private HttpClient getHttpClient() {
		HttpClient httpClient = null;
		SSLContext context;
		X509TrustManager tm = new MyX509TrustManager();
		try {
			context = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);
			context.init(null, new X509TrustManager[] { tm }, null);
			ConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
					context);
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
					.<ConnectionSocketFactory> create()
					.register("http", PlainConnectionSocketFactory.INSTANCE)
					.register("https", socketFactory).build();
			// ConnectionManager Connection
			PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(
					socketFactoryRegistry);
			httpClient = HttpClients.custom()
					.setConnectionManager(connectionManager)
					.setDefaultRequestConfig(RequestConfig.DEFAULT).build();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
		return httpClient;
	}
}
