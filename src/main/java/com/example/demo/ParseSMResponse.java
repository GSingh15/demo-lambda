package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;

import org.apache.http.impl.client.HttpClientBuilder;


@Service
public class ParseSMResponse<Twitter> {
	
	
	@Autowired
	private static final com.example.demo.Response response = new com.example.demo.Response();
	
	public static void parseTwitterResponse() {

		List<com.example.demo.Twitter> lstTwitter = new ArrayList<com.example.demo.Twitter>();

		String res = null;

		JSONArray arr = null;
		try {
			res = getSMResponse("https://takehome.io/twitter");
			arr = new JSONArray(res);
		} catch (Exception e) {
			com.example.demo.Twitter twt = new com.example.demo.Twitter();
			twt.setUsername("error");
			twt.setTweet(res);
			lstTwitter.add(twt);
			response.setTwitter(lstTwitter);
			return;
		}

		if (!arr.isEmpty() || arr != null) {
			for (int i = 0; i < arr.length(); i++) {
				com.example.demo.Twitter twt = new com.example.demo.Twitter();

				twt.setUsername(arr.getJSONObject(i).getString("username"));
				twt.setTweet(arr.getJSONObject(i).getString("tweet"));
				lstTwitter.add(twt);

			}

			response.setTwitter(lstTwitter);
		}
	}
	
	public static void parseFaceBookResponse() {

		List<com.example.demo.Facebook> lstFacebook = new ArrayList<com.example.demo.Facebook>();

		JSONArray arr = null;
		String res = null;
		try {
			res = getSMResponse("https://takehome.io/facebook");
			arr = new JSONArray(res);
		} catch (Exception e) {
			com.example.demo.Facebook fb = new com.example.demo.Facebook();
			fb.setName("error");
			fb.setStatus(res);
			lstFacebook.add(fb);
			response.setFacebook(lstFacebook);
			return;
		}
		if (!arr.isEmpty() || arr != null) {
			for (int i = 0; i < arr.length(); i++) {
				com.example.demo.Facebook fb = new com.example.demo.Facebook();

				fb.setName(arr.getJSONObject(i).getString("name"));
				fb.setStatus(arr.getJSONObject(i).getString("status"));
				lstFacebook.add(fb);

			}

			response.setFacebook(lstFacebook);
		}
	}
	
	public static void parseInstagramResponse() {

		List<com.example.demo.Instagram> lstInstagram = new ArrayList<com.example.demo.Instagram>();

		JSONArray arr = null;
		String res = null;
		try {
			res = getSMResponse("https://takehome.io/instagram");
			arr = new JSONArray(res);
		} catch (Exception e) {
			com.example.demo.Instagram insta = new com.example.demo.Instagram();
			insta.setUsername("error");
			insta.setPicture(res);
			lstInstagram.add(insta);
			response.setInstagram(lstInstagram);
			return;
		}
		if (!arr.isEmpty() || arr != null) {
			for (int i = 0; i < arr.length(); i++) {
				com.example.demo.Instagram insta = new com.example.demo.Instagram();

				insta.setUsername(arr.getJSONObject(i).getString("username"));
				insta.setPicture(arr.getJSONObject(i).getString("picture"));
				lstInstagram.add(insta);

			}

			response.setInstagram(lstInstagram);
		}
	}

	public static Response getAllParsedResponses() {

		parseTwitterResponse();
		parseFaceBookResponse();
		parseInstagramResponse();
		
		return response;

	}
	
	public static String getSMResponse(String url) throws Exception {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		String result = null;
		StringBuilder responseStrBuilder = new StringBuilder();

		// Get the response
		try {
			HttpResponse response = client.execute(request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			String line = "";
			while ((line = rd.readLine()) != null) {
				responseStrBuilder.append(line);
			}

			result = responseStrBuilder.toString();

		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}

		return result;
	}
	
	
}
