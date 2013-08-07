package com.grandst.whiplash;

public class Whiplash {

	//TEST
	private static final String TEST_API_KEY = "Hc2BHTn3bcrwyPooyYTP";
	private static final String TEST_PROTOCOL = "http";
	private static final String TEST_DOMAIN = "testing";
	private static final String TEST_ENDPOINT = ".whiplashmerch.com";
	private static final String TEST_PATH = "/api";
	
	//PROD
	private static final String PROD_PROTOCOL = "https";
	private static final String PROD_DOMAIN = "www";
	private static final String PROD_ENDPOINT = ".whiplashmerch.com";
	private static final String PROD_PATH = "/api";
	
	private String apiKey;
	private Boolean test;
	
	public Whiplash(String apiKey, Boolean test){
		super();
		this.setApiKey(apiKey);
		this.setTest(test);
	}
	
	public Whiplash(String apiKey){
		super();
		this.setApiKey(apiKey);
		this.setTest(false);
	}

	public Boolean getTest() {
		return test;
	}

	public void setTest(Boolean test) {
		this.test = test;
	}

	public String getApiKey() {
		if(apiKey==null || apiKey.equals("") || apiKey.equals("test"))
			return TEST_API_KEY;
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public String getApiBaseUrl(){
		return getScheme() +"://"+ getHost() + getApiPath();
	}
	
	public String getScheme(){
		if(test)
			return TEST_PROTOCOL;
		return PROD_PROTOCOL;
	}
	
	public String getHost(){
		if(test)
			return TEST_DOMAIN+TEST_ENDPOINT;
		return PROD_DOMAIN+PROD_ENDPOINT;
	}
	
	public String getApiPath(){
		if(test)
			return TEST_PATH;
		return PROD_PATH;
	}
}
