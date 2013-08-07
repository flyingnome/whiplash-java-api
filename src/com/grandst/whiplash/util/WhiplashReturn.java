package com.grandst.whiplash.util;

import org.apache.http.ParseException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WhiplashReturn {
	
	public WhiplashReturn(){}
	
	private Object returnObj;
	private String apiMsg;
	private String errorMsg;
	private int status;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
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
		if(apiJson!=null && (apiJson.equals("{}") || apiJson.equals("{ }") || !(apiJson.startsWith("{") || apiJson.startsWith("[")))) //break out if its not Json or its empty
			return true;
		try{
			JsonParser parser = new JsonParser();
			JsonObject jObj = parser.parse(apiJson).getAsJsonObject();
			if(jObj!=null && jObj.has("error") ){
				this.setErrorMsg(jObj.get("error").getAsString());
				return true;
			}
		}catch(IllegalStateException ex){
			try{
				JsonParser parser = new JsonParser();
				JsonArray jObj = parser.parse(apiJson).getAsJsonArray();
				if(jObj!=null && jObj.size()>0){
					if(jObj.get(0).getAsJsonObject()!=null && jObj.get(0).getAsJsonObject().has("error")){
						this.setErrorMsg(jObj.get(0).getAsJsonObject().get("error").getAsString());
						return true;
					}
				}
			}catch(ParseException ex2){
				return true;
			}
		}catch(ParseException ex){
			return true;
		}
		return false;
	}
	
	public Boolean tryParseStatus(String apiJson){
		if(apiJson!=null && (apiJson.equals("{}") || apiJson.equals("{ }") || !(apiJson.startsWith("{") || apiJson.startsWith("[")))) //break out if its not Json or its empty
			return true;
		try{
			JsonParser parser = new JsonParser();
			JsonObject jObj = parser.parse(apiJson).getAsJsonObject();
			if(jObj!=null && jObj.has("httpstatus") ){
				this.setStatus(jObj.get("httpstatus").getAsInt());
				return true;
			}
		}catch(IllegalStateException ex){
			try{
				JsonParser parser = new JsonParser();
				JsonArray jObj = parser.parse(apiJson).getAsJsonArray();
				if(jObj!=null && jObj.size()>0){
					if(jObj.get(0).getAsJsonObject()!=null && jObj.get(0).getAsJsonObject().has("httpstatus")){
						this.setStatus(jObj.get(0).getAsJsonObject().get("httpstatus").getAsInt());
						return true;
					}
				}
			}catch(ParseException ex2){
				return true;
			}
		}catch(ParseException ex){
			return true;
		}
		return false;
	}
	
	
}
