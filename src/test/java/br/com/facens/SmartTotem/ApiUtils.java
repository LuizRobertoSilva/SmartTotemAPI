package br.com.facens.SmartTotem;


import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONParser;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

class ApiUtils {
private CloseableHttpClient httpClient = HttpClients.createDefault();
	String url = null;
	public ApiUtils(String url){
		this.url = url;
	}
	public HttpResponse apiPost(String requestUrl, String inputJson ) throws IOException {
		HttpPost httpPost  = new HttpPost(url + requestUrl);
		httpPost.addHeader("Content-Type", "application/json");
		StringEntity stringEntity = new StringEntity(inputJson);
		httpPost.setEntity(stringEntity);
		HttpResponse response = httpClient.execute(httpPost);
		return response;
	}

	public HttpResponse apiPut(String requestUrl, String inputJson ) throws IOException {
		HttpPut httpPut  = new HttpPut(url + requestUrl);
		httpPut.addHeader("Content-Type", "application/json");
		StringEntity stringEntity = new StringEntity(inputJson);
		httpPut.setEntity(stringEntity);
		HttpResponse response = httpClient.execute(httpPut);
		return response;
	}

	public HttpResponse apiGet(String requestUrl ) throws IOException {
		HttpGet  httpGet  = new HttpGet (url + requestUrl);


		CloseableHttpResponse response = httpClient.execute(httpGet);
		return response;

	}

	public String makeJsonDevice (int id, String name, double currentHumidity, double currentTemperature, int currentCO2, String latitude, String longitude, String regiao ){
		String inputJson = "{\n" +
				"  \"id\":" + id + ",\n" +
				"  \"name\":" + "\"" + name + "\"" + ",\n" +
				"  \"currentHumidity\":" + currentHumidity + ",\n" +
				"  \"currentTemperature\":" + currentTemperature + ",\n" +
				"  \"currentCO2\":" + currentCO2 + ",\n" +
				"  \"latitude\":" + "\"" + latitude + "\"" + ",\n" +
				"  \"longitude\":" + "\"" + longitude + "\"" + ",\n" +
				"  \"region\": {\n" +
				"  \"name\":" + "\"" +regiao + "\"" + "\n}" +
				"}";
		return inputJson;
	}

	public String makeWrongJsonDevice (int id, String name, double currentHumidity, double currentTemperature, int currentCO2, String latitude, String longitude, String regiao ){
		String inputJson = "{\n" +
				"  \"id\":" + id + ",\n" +
				"  \"name\":" + name + ",\n" +
				"  \"currentHumidity\":" + currentHumidity + ",\n" +
				"  \"currentTemperature\":" + currentTemperature + ",\n" +
				"  \"currentCO2\":" + currentCO2 + ",\n" +
				"  \"latitude\":" + "\"" + latitude + "\"" + ",\n" +
				"  \"longitude\":" + "\"" + longitude + "\"" + ",\n" +
				"  \"region\": {\n" +
				"  \"name\":" + "\"" +regiao + "\"" + "\n}" +
				"}";
		return inputJson;
	}


}
