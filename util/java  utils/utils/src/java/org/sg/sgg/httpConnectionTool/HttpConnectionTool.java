package org.sg.sgg.httpConnectionTool;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpConnectionTool {
	public String invokeServiceMethod(String url,Map<String,String> params,Map<String, String> header) throws Exception{
		HttpClient httpclient=new DefaultHttpClient();
		 HttpResponse response = null;
	        HttpEntity entity = null;
	        httpclient.getParams().setParameter(
	                ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);  //����cookie�ļ�����
	        HttpPost httpost = new HttpPost(url);           //�����еĲ����ǣ�action�ĵ�ַ
	        if (header!=null&&header.size()>0) {
	        	for (String key : header.keySet()) {
	        		httpost.setHeader(key,header.get(key));
				}
			}
	        List <NameValuePair> nvps = new ArrayList <NameValuePair>();  
	        for (String keyItem : params.keySet()) {
	        	String key=keyItem;
	        	String value=params.get(keyItem);
	        	nvps.add(new BasicNameValuePair(key,value));  
			}
	        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));            //����������post������
	        response = httpclient.execute(httpost);                                               //ִ��
	        StatusLine stateLine=response.getStatusLine();
			int statusCode=stateLine.getStatusCode();
			if (statusCode!=200) {
				httpclient.getConnectionManager().shutdown();
				return "";
			}
	        entity = response.getEntity();                                                             //���ط�������Ӧ
	        try{
	            String responseString = null;
	            if (entity != null) {
	            responseString = EntityUtils.toString(entity);      
	            }
	            return responseString;
	        } finally {
	            if (httpclient!=null)                          
	            	httpclient.getConnectionManager().shutdown();                                                // release connection gracefully
	        }
	       
	}
	public String invokeServiceMethod(String url) throws Exception{
		HttpClient httpclient=new DefaultHttpClient();
		HttpResponse response = null;
		HttpEntity entity = null;
		httpclient.getParams().setParameter(
				ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);  //����cookie�ļ�����
		HttpGet httpGet = new HttpGet(url);           //�����еĲ����ǣ�action�ĵ�ַ
		response = httpclient.execute(httpGet);                                               //ִ��
		entity = response.getEntity();                                                             //���ط�������Ӧ
//		response.getStatusLine();
		try{
			String responseString = null;
			if (entity != null) {
				responseString = EntityUtils.toString(entity);      
			}
			return responseString;
		} finally {
			if (httpclient!=null)                          
				httpclient.getConnectionManager().shutdown();                                                // release connection gracefully
		}
		
	}
	public Boolean urlCheck(String url) throws ClientProtocolException, IOException{
		if (StringUtils.isBlank(url)) {
			return false;
		}
		HttpClient httpclient=new DefaultHttpClient();
		HttpResponse response = null;
		httpclient.getParams().setParameter(
				ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);  //����cookie�ļ�����
		HttpGet httpGet = new HttpGet(url);           //�����еĲ����ǣ�action�ĵ�ַ
		response = httpclient.execute(httpGet);                                               //ִ��
			StatusLine stateLine=response.getStatusLine();
			int statusCode=stateLine.getStatusCode();
			if (statusCode==200) {
				return true;
			}
			if (httpclient!=null)                          
				httpclient.getConnectionManager().shutdown();                                                // release connection gracefully
		return false;
		
	}
/*	public String invokeServiceMethod(String url,Map<String,String> params) throws Exception{
		HttpClient httpclient=new DefaultHttpClient();
		 HttpResponse response = null;
	        HttpEntity entity = null;
	        httpclient.getParams().setParameter(
	                ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);  //����cookie�ļ�����
	        HttpPost httpost = new HttpPost(url);           //�����еĲ����ǣ�action�ĵ�ַ
	        List <NameValuePair> nvps = new ArrayList <NameValuePair>();  
	        for (String keyItem : params.keySet()) {
	        	String key=keyItem;
	        	String value=params.get(keyItem);
	        	nvps.add(new BasicNameValuePair(key,value));  
			}
	        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));            //����������post������
	        response = httpclient.execute(httpost);                                               //ִ��
	        entity = response.getEntity();                                                             //���ط�������Ӧ
	        try{
	            String responseString = null;
	            if (entity != null) {
	            responseString = EntityUtils.toString(entity);      
	            }
	            return responseString;
	        } finally {
	            if (httpclient!=null)                          
	            	httpclient.getConnectionManager().shutdown();                                                // release connection gracefully
	        }
	       
	}*/
	public String invokeServiceMethod(String url,Map<String,String> params,Map<String, String> header,File[] files,String propName) throws Exception{
		HttpClient httpclient=new DefaultHttpClient();
		HttpResponse response = null;
		HttpEntity entity = null;
		httpclient.getParams().setParameter(
				ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);  //����cookie�ļ�����
		HttpPost httpost = new HttpPost(url);           //�����еĲ����ǣ�action�ĵ�ַ
        if (header!=null&&header.size()>0) {
        	for (String key : header.keySet()) {
        		httpost.setHeader(key,header.get(key));
			}
		}
		MultipartEntity multipartEntity=new MultipartEntity();
		for (String keyItem : params.keySet()) {
			multipartEntity.addPart(keyItem, new StringBody(params.get(keyItem), Charset.forName("UTF-8")));
		}
		if (files!=null) {
			for (File file : files) {
				multipartEntity.addPart(propName, new FileBody(file));
			}
		}
		httpost.setEntity(multipartEntity);            //����������post������
		response = httpclient.execute(httpost);                                               //ִ��
		entity = response.getEntity();                                                             //���ط�������Ӧ
		try{
			String responseString = null;
			if (entity != null) {
				responseString = EntityUtils.toString(entity);      
			}
			return responseString;
		} finally {
			if (httpclient!=null)                          
				httpclient.getConnectionManager().shutdown();                                                // release connection gracefully
		}
		
	}
	/**
	 * ִ��webservice�ӿ�
	 * @param url �ӿڵ�ַ
	 * @param params ���� 
	 * @param files ���ϴ����ļ�
	 * @param propName �ϴ��ļ���Ӧ��name��
	 * @return
	 * @throws Exception
	 */
	public String invokeServiceMethod(String url,List<Map<String, String>> params,File[] files,String propName) throws Exception{
		HttpClient httpclient=new DefaultHttpClient();
		HttpResponse response = null;
		HttpEntity entity = null;
		httpclient.getParams().setParameter(
				ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);  //����cookie�ļ�����
		HttpPost httpost = new HttpPost(url);           //�����еĲ����ǣ�action�ĵ�ַ
		MultipartEntity multipartEntity=new MultipartEntity();
		for (Map<String, String> map : params) {
			for (String keyItem : map.keySet()) {
				multipartEntity.addPart(keyItem, new StringBody(map.get(keyItem), Charset.forName("UTF-8")));
			}
		}
		for (File file : files) {
			multipartEntity.addPart(propName, new FileBody(file));
		}
		httpost.setEntity(multipartEntity);            //����������post������
		response = httpclient.execute(httpost);                                               //ִ��
		entity = response.getEntity();                                                             //���ط�������Ӧ
		try{
			String responseString = null;
			if (entity != null) {
				responseString = EntityUtils.toString(entity);      
			}
			return responseString;
		} finally {
			if (httpclient!=null)                          
				httpclient.getConnectionManager().shutdown();                                                // release connection gracefully
		}
		
	}
	public String invokeServiceMethod(String url, Map<String, String> params) throws Exception {
		return invokeServiceMethod(url, params, new HashMap<String, String>());
	}
	
	
	public String invokeServiceMethod(String url,Map<String,String> params,Map<String, String> header,HttpServletResponse httpServletResponse) throws Exception{
		HttpClient httpclient=new DefaultHttpClient();
		 HttpResponse response = null;
	        HttpEntity entity = null;
	        httpclient.getParams().setParameter(
	                ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);  //����cookie�ļ�����
	        HttpPost httpost = new HttpPost(url);           //�����еĲ����ǣ�action�ĵ�ַ
	        if (header!=null&&header.size()>0) {
	        	for (String key : header.keySet()) {
	        		httpost.setHeader(key,header.get(key));
				}
			}
	        List <NameValuePair> nvps = new ArrayList <NameValuePair>();  
	        for (String keyItem : params.keySet()) {
	        	String key=keyItem;
	        	String value=params.get(keyItem);
	        	nvps.add(new BasicNameValuePair(key,value));  
			}
	        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));            //����������post������
	        response = httpclient.execute(httpost);                                               //ִ��
	        StatusLine stateLine=response.getStatusLine();
			int statusCode=stateLine.getStatusCode();
			if (statusCode!=200) {
				httpclient.getConnectionManager().shutdown();
				return "����";
			}
	        entity = response.getEntity();                                                             //���ط�������Ӧ
	        try{
	            if (entity != null&&entity.isStreaming()) {
	            	//������Ӧͷ
	            	for (Header nameValuePair : response.getAllHeaders()) {
	            		httpServletResponse.setHeader(nameValuePair.getName(), nameValuePair.getValue());
					}
					byte[] temp = new byte[1024*10];
					int num=0;
					InputStream in = entity.getContent();      
					while ((num=in.read(temp))>0) {
						httpServletResponse.getOutputStream().write(temp,0,num);
					}
				}
	        } finally {
	            if (httpclient!=null)                          
	            	httpclient.getConnectionManager().shutdown();                                                // release connection gracefully
	        }
			return null;
	       
	}
}
