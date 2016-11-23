package com.linlong.f10.core.util.https;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

@Service("httsProtocol")
public class HTTPSProtocol extends Protocol {
	@Value("${serverPort}")
	private int serverPort;

	/**
	 * 登陆
	 * 
	 * @param username
	 * @param password
	 * @param url
	 * @return
	 * @throws IOException
	 */
	@Override
	public String login(final String username, final String password, String url) {
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new NameValuePair() {

			public String getValue() {
				return username;
			}

			public String getName() {
				return "username";
			}
		});
		param.add(new NameValuePair() {
			public String getValue() {
				return password;
			}

			public String getName() {
				return "password";
			}
		});

		String json = post(param, url);
		return json;
	}

	@Override
	public String validateToken(String tokenId, String url) {
		String responseStr = null;
		NameValuePair pair1 = new BasicNameValuePair("tokenId", tokenId);// new
		NameValuePair pair2 = new BasicNameValuePair("port", String.valueOf(serverPort));
		List<NameValuePair> pairList = new ArrayList<NameValuePair>();
		pairList.add(pair1);
		pairList.add(pair2);
		System.out.println("url:" + url);
		System.out.println("params:" + pairList);
		responseStr = post(pairList, url);
		System.out.println(responseStr);
		JSONObject jsonObject = JSONObject.parseObject(responseStr);
		if (StringUtils.isNotBlank(tokenId) && jsonObject.get("content") != null
				&& tokenId.equals(((JSONObject) jsonObject.get("content")).get("tokenId"))) {
			return tokenId;
		}
		return null;
	}

	public static void main(String[] args) {
		String mb = new HTTPSProtocol().login("21133114", "9xRIVtXjypWWvXrCaOf9ow==", "https://59.46.52.54:9001/login");
		System.out.println(mb);
		String mb2 = new HTTPSProtocol().validateToken("177898361029558566", "https://172.27.20.138/obtainToken");
		System.out.println(mb2);
	}

	/**
	 * @Title: logout
	 * @Description: 
	 * @param
	 * @return String 返回类型
	 */
	public String logout(final String tokenId, String url) {
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new NameValuePair() {

			public String getValue() {
				return tokenId;
			}

			public String getName() {
				return "tokenId";
			}
		});

		String json = post(param, url);
		return json;
	}
}
