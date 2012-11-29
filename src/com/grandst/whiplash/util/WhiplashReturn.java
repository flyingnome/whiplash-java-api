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
		if(apiJson!=null && (apiJson.equals("{}") || apiJson.equals("{ }")))
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
	
	
}
