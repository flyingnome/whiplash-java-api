package com.grandst.whiplash.util;

import org.apache.http.ParseException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WhiplashReturn {
	
	public WhiplashReturn(){}
	
	private Object returnObj;
	private String apiMsg;
	private String errorMsg;
	
	public Object getReturnObj() {
		return returnObj;
	}
	public void setReturnObj(Object returnObj) {
		this.returnObj = returnObj;
	}
	public String getApiMsg() {
		return apiMsg;
	}
	public void setApiMsg(String apiMsg) {
		this.apiMsg = apiMsg;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public Boolean tryParseError(String apiJson){
		try{
			JsonParser parser = new JsonParser();
			JsonObject jObj = parser.parse(apiJson).getAsJsonObject();
			if(jObj!=null && jObj.has("error")){
				this.setErrorMsg(jObj.get("error").getAsString());
				return true;
			}
		}catch(ParseException ex){
			return true;
		}
		return false;
	}
}
