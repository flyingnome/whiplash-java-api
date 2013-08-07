package com.grandst.whiplash.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.grandst.whiplash.Whiplash;



public final class API {
	
	//VERB::GET
	public static String get(String apiCall, Whiplash w) throws ClientProtocolException, IOException, URISyntaxException {
		return get(apiCall,w,null);
	}
	
	public static String get(String apiCall, Whiplash w, String query) throws ClientProtocolException, IOException, URISyntaxException {
		HttpClient client = getNewHttpClient(null);
		HttpGet getReq = new HttpGet(
				new URI(
						w.getScheme()
						,w.getHost()
						,w.getApiPath()+apiCall
						,query
						,null
					).toASCIIString());
		setHeaders(getReq, w);
		final HttpResponse response = client.execute(getReq);
		if(response.getStatusLine().getStatusCode() != 200)
			return getStatusCodeJson(response.getStatusLine().getStatusCode());
		InputStream inputStream = response.getEntity().getContent();		
		return inputStreamToString(inputStream);
	}
	
	//VERB::POST
	public static String post(String apiCall, Whiplash w, List<NameValuePair> postData, int timeoutConnection,int timeoutSocket) throws ClientProtocolException, IOException, URISyntaxException {
		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		HttpClient client =  getNewHttpClient(httpParameters); 	
		HttpPost postReq = new HttpPost(
				new URI(
						w.getScheme()
						,w.getHost()
						,w.getApiPath()+apiCall
						,null
						,null
					).toASCIIString());
		if (postData != null) {
			HashMap<String,String> map = new HashMap<String,String>();
			for(NameValuePair nvp : postData){
				 map.put(nvp.getName(),nvp.getValue());
			}
			GsonBuilder gb = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
				.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
			postReq.setEntity(new StringEntity(gb.create().toJson(map)));
		}
		setHeaders(postReq,w);
		final HttpResponse response = client.execute(postReq);  
		if(response.getStatusLine().getStatusCode() != 200)
			return getStatusCodeJson(response.getStatusLine().getStatusCode());
		InputStream inputStream = response.getEntity().getContent();		
		return inputStreamToString(inputStream);
	}
	public static String post(String apiCall, Whiplash w, StringEntity jsonObj, int timeoutConnection,int timeoutSocket) throws ClientProtocolException, IOException, URISyntaxException {
		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		HttpClient client =  getNewHttpClient(httpParameters); 	 	
		HttpPost postReq = new HttpPost(
				new URI(
						w.getScheme()
						,w.getHost()
						,w.getApiPath()+apiCall
						,null
						,null
					).toASCIIString());
		if (jsonObj != null) {
			postReq.setEntity(jsonObj);
		}
		setHeaders(postReq,w);
		final HttpResponse response = client.execute(postReq);  
		InputStream inputStream;
		inputStream = response.getEntity().getContent();		
		return inputStreamToString(inputStream);
	}
	
	//VERB::PUT
	public static String put(String apiCall, Whiplash w, int timeoutConnection,int timeoutSocket) throws ClientProtocolException, IOException, URISyntaxException  {
		return put(apiCall,w,new StringEntity(""),timeoutConnection,timeoutSocket);
	}
	public static String put(String apiCall, Whiplash w, List<NameValuePair> putData, int timeoutConnection,int timeoutSocket) throws ClientProtocolException, IOException, URISyntaxException  {
		if (putData != null) {
			HashMap<String,String> map = new HashMap<String,String>();
			for(NameValuePair nvp : putData){
				 map.put(nvp.getName(),nvp.getValue());
			}
			GsonBuilder gb = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
				.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
			return put(apiCall,w,new StringEntity(gb.create().toJson(map)),timeoutConnection,timeoutSocket);
		}
		return null;
	}
	public static String put(String apiCall, Whiplash w, StringEntity jsonObj, int timeoutConnection,int timeoutSocket) throws ClientProtocolException, IOException, URISyntaxException  {
		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		HttpClient client =  getNewHttpClient(httpParameters); 	 	
		HttpPut putReq = new HttpPut(
				new URI(
						w.getScheme()
						,w.getHost()
						,w.getApiPath()+apiCall
						,null
						,null
					).toASCIIString());
		if (jsonObj != null) {
			putReq.setEntity(jsonObj);
		}
		setHeaders(putReq,w);
		final HttpResponse response = client.execute(putReq);
		//this block is a quick fix for the API returning codes
		if(response.getStatusLine().getStatusCode() != 200)
			return getStatusCodeJson(response.getStatusLine().getStatusCode());
		InputStream inputStream = response.getEntity().getContent();
		return inputStreamToString(inputStream);	 

	}
	
	//VERB::DELETE
	public static String delete(String apiCall, Whiplash w,int timeoutConnection,int timeoutSocket) throws ClientProtocolException, IOException, URISyntaxException  {
		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		HttpClient client =  getNewHttpClient(httpParameters); 	 
		HttpDelete delReq = new HttpDelete(
				new URI(
						w.getScheme()
						,w.getHost()
						,w.getApiPath()+apiCall
						,null
						,null
					).toASCIIString());
		setHeaders(delReq,w);
		final HttpResponse response = client.execute(delReq);
		if(response.getStatusLine().getStatusCode() != 200)
			return getStatusCodeJson(response.getStatusLine().getStatusCode());
		InputStream inputStream = response.getEntity().getContent();
		return inputStreamToString(inputStream);
	}
	
	
	//UTILS
	private static String inputStreamToString(InputStream is) throws IOException{
		byte[] data = new byte[512];
		int len = 0;
		StringBuffer buffer = new StringBuffer();
		while (-1 != (len = is.read(data)) )
		{
			buffer.append(new String(data, 0, len)); 
		}
		is.close();
		return buffer.toString();	
	}
	private static String getStatusCodeJson(int code) throws IOException{
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("httpstatus:");
		sb.append(code);
		sb.append("}");
		return 	sb.toString();
	}
	
	private static void setHeaders(HttpRequestBase req, Whiplash w){
		req.setHeader("X-API-KEY",w.getApiKey());
		//req.setHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
		req.setHeader("Content-Type", "application/json");
		req.setHeader("Accept","application/json");
		req.setHeader("X-API-VERSION","1");
		//use later
		//req.setHeader("X-API-VERSION",API_KEY);
	}
	
	public static HttpClient getNewHttpClient(HttpParams params) {
	    try {
	        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
	        trustStore.load(null, null);

	        SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
	        sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	        if (params == null)
	        	params = new BasicHttpParams();
	        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
	        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

	        SchemeRegistry registry = new SchemeRegistry();
	        registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
	        registry.register(new Scheme("https", sf, 443));

	        ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

	        return new DefaultHttpClient(ccm, params);
	    } catch (Exception e) {
	        return new DefaultHttpClient();
	    }
	}
	
	public static class MySSLSocketFactory extends SSLSocketFactory {
	    SSLContext sslContext = SSLContext.getInstance("TLS");

	    public MySSLSocketFactory(KeyStore truststore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
	        super(truststore);

	        TrustManager tm = new X509TrustManager() {
	            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	            }

	            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	            }

	            public X509Certificate[] getAcceptedIssuers() {
	                return null;
	            }
	        };

	        sslContext.init(null, new TrustManager[] { tm }, null);
	    }

	    @Override
	    public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
	        return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
	    }

	    @Override
	    public Socket createSocket() throws IOException {
	        return sslContext.getSocketFactory().createSocket();
	    }
	}

}
