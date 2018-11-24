package com.projeto.pi2018.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


public class ChamadaHttp {
	public HttpEntity chamada(String endPoint, String body) throws URISyntaxException, IOException {
		 HttpClient httpclient = new DefaultHttpClient();
		  URIBuilder builder = new URIBuilder(endPoint);
		  
		  URI uri = builder.build();
	       HttpPost request = new HttpPost(uri);
	       
	       request.setHeader("Content-Type", "application/json");
	       request.setHeader("Ocp-Apim-Subscription-Key", "3cc32ee03b6c4a29af2129038717f3ec");
	       
	       StringEntity reqEntity = new StringEntity(body);
	       request.setEntity(reqEntity);
	       
	       
	       HttpResponse response = httpclient.execute(request);
	       response.getStatusLine().getStatusCode();
	       HttpEntity entity = response.getEntity();
		  
		  return entity;
	}
}
