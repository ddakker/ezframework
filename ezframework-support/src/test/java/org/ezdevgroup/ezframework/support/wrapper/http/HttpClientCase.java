package org.ezdevgroup.ezframework.support.wrapper.http;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.ezdevgroup.ezframework.support.wrapper.http.security.Encryption;
import org.ezdevgroup.ezframework.support.wrapper.http.security.impl.Base64Encryption;

public class HttpClientCase {
	public void test_utf8() throws Exception {
		Encryption en = new Base64Encryption(false, true);

        DefaultHttpClient httpclient = new DefaultHttpClient();

        HttpGet httpget = new HttpGet("http://localhost:8080/test?test2=한글&test22=en");

        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(response.getEntity());

        System.out.println("Login form get: " + response.getStatusLine());
        System.out.println("Login form post: " + result);
        System.out.println("Login form get: " + en.decode(result));
        if (entity != null) {
            entity.consumeContent();
        }

        HttpPost httpost = new HttpPost("http://localhost:8080/test?test2=한글&test22=en");
        
        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
        nvps.add(new BasicNameValuePair("test1", "한글1"));
        nvps.add(new BasicNameValuePair("test11", "en"));

        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        
        HttpRequestBase httpRequestBase = httpost;

        response = httpclient.execute(httpRequestBase);
        entity = response.getEntity();
        result = EntityUtils.toString(response.getEntity());
        		
        System.out.println("Login form post: " + response.getStatusLine());
        System.out.println("Login form post: " + result);
        System.out.println("Login form post: " + en.decode(result));
        if (entity != null) {
            entity.consumeContent();
        }
    }
	
	public void test_euckr() throws Exception {
		Encryption en = new Base64Encryption(false, true, "euc-kr");

        DefaultHttpClient httpclient = new DefaultHttpClient();

        HttpGet httpget = new HttpGet("http://ez1.local.ezdevgroup.org/common/auth/userSessionTest.ez?test2=한글&test22=en");

        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(response.getEntity());

        System.out.println("Login form get: " + response.getStatusLine());
        System.out.println("Login form post: " + result);
        System.out.println("Login form get: " + en.decode(result));
        if (entity != null) {
            entity.consumeContent();
        }

        HttpPost httpost = new HttpPost("http://ez1.local.ezdevgroup.org/common/auth/userSessionTest.ez?test2=한글&test22=en");

        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
        nvps.add(new BasicNameValuePair("test1", "한글"));
        nvps.add(new BasicNameValuePair("test11", "en"));

        httpost.setEntity(new UrlEncodedFormEntity(nvps, "euc-kr"));

        response = httpclient.execute(httpost);
        entity = response.getEntity();
        result = EntityUtils.toString(response.getEntity());
        		
        System.out.println("Login form post: " + response.getStatusLine());
        System.out.println("Login form post: " + result);
        System.out.println("Login form post: " + en.decode(result));
        if (entity != null) {
            entity.consumeContent();
        }
    }
}
