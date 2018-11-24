package com.projeto.pi2018.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class ChamadaHttpBinaryData {
	public String chamada(String endPoint, String base64) throws URISyntaxException, IOException {
		
		byte[] bytes = Base64.decodeBase64(base64);
		
		String directory = "C:/teste/teste.png";

		File foto = new File(directory);
		 foto.createNewFile();
		FileOutputStream fos = new FileOutputStream(foto);
		 fos.write(bytes);
		 fos.close();
		FileEntity reqEntity = new FileEntity(foto, ContentType.APPLICATION_OCTET_STREAM);
		
		 HttpClient httpclient = new DefaultHttpClient();
		  URIBuilder builder = new URIBuilder(endPoint);
		  
		  URI uri = builder.build();
	       HttpPost request = new HttpPost(uri);
	       
	       request.setHeader("Content-Type", "application/octet-stream");
	       request.setHeader("Ocp-Apim-Subscription-Key", "3cc32ee03b6c4a29af2129038717f3ec");
	       request.setEntity(reqEntity);
	       
	       HttpResponse response = httpclient.execute(request);
	       response.getStatusLine().getStatusCode();
	       HttpEntity entity = response.getEntity();
	       
	       return  EntityUtils.toString(entity);
	       
		  
	}
}
