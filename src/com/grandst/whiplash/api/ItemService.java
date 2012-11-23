package com.grandst.whiplash.api;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.grandst.whiplash.Whiplash;
import com.grandst.whiplash.bean.Item;

public class ItemService {
	
	public static ArrayList<Item> getItems(Whiplash w) throws ClientProtocolException, ParseException, IOException{
		return parseItemListJson(API.get("/items.json/", w));
	}
	public static ArrayList<Item> getItemsBySku(Whiplash w, String sku) throws ClientProtocolException, ParseException, IOException{
		return parseItemListJson(API.get("/items/sku/"+sku, w));
	}
	public static Item getItemById(Whiplash w, long itemId) throws ClientProtocolException, ParseException, IOException{
		return parseItemJson(API.get("/items/"+itemId, w));
	}
	public static Item getItemByOriginatorId(Whiplash w, long originatorId) throws ClientProtocolException, ParseException, IOException{
		return parseItemJson(API.get("/items/originator/"+originatorId, w));
	}
	public static Item createItem(Whiplash w, Item i) throws ClientProtocolException, ParseException, IOException{
		List<NameValuePair> postData = new ArrayList<NameValuePair>();
    	postData.add(new BasicNameValuePair("sku",i.getSku()));
    	postData.add(new BasicNameValuePair("title",i.getTitle()));
    	postData.add(new BasicNameValuePair("description",i.getDescription()));
    	postData.add(new BasicNameValuePair("originator_id",""+i.getOriginatorId()));
		return parseItemJson(API.post("/items", w, postData, 3000,30000));
	}
	
	private static ArrayList<Item> parseItemListJson(String apiJson) throws  ParseException{
		apiJson = cleanDateFormat(apiJson); // ugh! only Java 7+ supports date formats with Timezone X eg. yyyy-MM-dd'T'HH:mm:ssX so we need to change the format to yyyy-MM-dd'T'HH:mm:ssZ
		ArrayList<Item> retList = new ArrayList<Item>();
		JsonParser parser = new JsonParser();
		GsonBuilder gb = new GsonBuilder()
			.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		Gson gson = gb.create();
		JsonArray itemArray = parser.parse(apiJson).getAsJsonArray();
		for(int i = 0; i < itemArray.size(); i++){
			Item o = new Item();
			o = gson.fromJson(itemArray.get(i).getAsJsonObject(), Item.class);
			retList.add(o);
		}
		return retList;	
	}
	
	private static Item parseItemJson(String apiJson) throws  ParseException{
		apiJson = cleanDateFormat(apiJson); // ugh! only Java 7+ supports date formats with Timezone X eg. yyyy-MM-dd'T'HH:mm:ssX so we need to change the format to yyyy-MM-dd'T'HH:mm:ssZ
		JsonParser parser = new JsonParser();
		GsonBuilder gb = new GsonBuilder()
			.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		Gson gson = gb.create();
		JsonObject itemObj = parser.parse(apiJson).getAsJsonObject();
		return  gson.fromJson(itemObj, Item.class);
	}
	
	private static String cleanDateFormat(String json){
		Pattern regex = Pattern.compile("\\d\\d:\\d\\d:\\d\\d[-\\+]\\d\\d:\\d\\d"); 
		Matcher regexMatcher = regex.matcher(json);
		StringBuffer buff = new StringBuffer();
		while(regexMatcher.find()){
			regexMatcher.appendReplacement(buff, getSubOfMatch(regexMatcher));
		}
		regexMatcher.appendTail(buff);
		return buff.toString();
	}
	
	private static String getSubOfMatch(Matcher matcher){
		StringBuilder sb = new StringBuilder(matcher.group(0));
		sb.deleteCharAt(sb.length()-3);
		return sb.toString();
	}

}
