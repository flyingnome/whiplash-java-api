package com.grandst.whiplash.api;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.grandst.whiplash.util.JsonCleaner;
import com.grandst.whiplash.util.WhiplashReturn;

public class ItemService {
	
	public static WhiplashReturn getItems(Whiplash w) throws ClientProtocolException, ParseException, IOException, URISyntaxException{
		return parseItemListJson(API.get("/items.json/", w));
	}
	public static WhiplashReturn getItemsBySku(Whiplash w, String sku) throws ClientProtocolException, ParseException, IOException, URISyntaxException{
		return parseItemListJson(API.get("/items/sku/"+sku, w));
	}
	public static WhiplashReturn getItemsWithParams(Whiplash w, Map<String,Object> params) throws ClientProtocolException, ParseException, IOException, URISyntaxException{
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<String, Object> entry : params.entrySet()){
			sb.append(entry.getKey());
			sb.append("=");
			sb.append(entry.getValue());
			sb.append("&");
		}
		return parseItemListJson(API.get("/items.json", w, sb.toString()));
	}
	public static WhiplashReturn getItemsWithPage(Whiplash w, long page) throws ClientProtocolException, ParseException, IOException, URISyntaxException{
		StringBuilder query = new StringBuilder();
		query
			.append("page=")
			.append(page)
			.append("&")
			.append("limit=")
			.append("150");
		return parseItemListJson(API.get("/items.json", w,query.toString()));
	}
	public static WhiplashReturn getItemById(Whiplash w, long itemId) throws ClientProtocolException, ParseException, IOException, URISyntaxException{
		return parseItemJson(API.get("/items/"+itemId, w));
	}
	public static WhiplashReturn getItemByOriginatorId(Whiplash w, String originatorId) throws ClientProtocolException, ParseException, IOException, URISyntaxException{
		return parseItemJson(API.get("/items/originator/"+originatorId, w));
	}
	public static WhiplashReturn deleteItem(Whiplash w, long itemId) throws ClientProtocolException, ParseException, IOException, URISyntaxException{
		return parseItemJson(API.delete("/items/"+itemId, w, 3000, 3000));
	}
	public static WhiplashReturn createItem(Whiplash w, Item i) throws ClientProtocolException, ParseException, IOException, URISyntaxException{
		List<NameValuePair> postData = new ArrayList<NameValuePair>();
    	postData.add(new BasicNameValuePair("sku",i.getSku()));
    	postData.add(new BasicNameValuePair("title",i.getTitle()));
    	postData.add(new BasicNameValuePair("description",i.getDescription()));
    	postData.add(new BasicNameValuePair("originator_id",""+i.getOriginatorId()));
		return parseItemJson(API.post("/items", w, postData, 3000,30000));
	}
	public static WhiplashReturn updateItem(Whiplash w, Item i) throws ClientProtocolException, ParseException, IOException, URISyntaxException{
		List<NameValuePair> postData = new ArrayList<NameValuePair>();
    	postData.add(new BasicNameValuePair("sku",i.getSku()));
    	postData.add(new BasicNameValuePair("title",i.getTitle()));
    	postData.add(new BasicNameValuePair("description",i.getDescription()));
    	postData.add(new BasicNameValuePair("originator_id",""+i.getOriginatorId()));
		return parseItemJson(API.put("/items/"+i.getId(), w,postData , 3000, 3000));
	}
	
	private static WhiplashReturn parseItemListJson(String apiJson) throws  ParseException{
		WhiplashReturn retObj = new WhiplashReturn();
		if(retObj.tryParseError(apiJson))
			return retObj;
		if(retObj.tryParseStatus(apiJson))
			return retObj;
		apiJson = JsonCleaner.cleanDateFormat(apiJson); // ugh! only Java 7+ supports date formats with Timezone X eg. yyyy-MM-dd'T'HH:mm:ssX so we need to change the format to yyyy-MM-dd'T'HH:mm:ssZ
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
		retObj.setReturnObj(retList);
		return retObj;	
	}
	
	private static WhiplashReturn parseItemJson(String apiJson) throws  ParseException{
		WhiplashReturn retObj = new WhiplashReturn();
		if(retObj.tryParseError(apiJson))
			return retObj;
		if(retObj.tryParseStatus(apiJson))
			return retObj;
		apiJson = JsonCleaner.cleanDateFormat(apiJson); // ugh! only Java 7+ supports date formats with Timezone X eg. yyyy-MM-dd'T'HH:mm:ssX so we need to change the format to yyyy-MM-dd'T'HH:mm:ssZ
		JsonParser parser = new JsonParser();
		GsonBuilder gb = new GsonBuilder()
			.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		Gson gson = gb.create();
		JsonObject itemObj = parser.parse(apiJson).getAsJsonObject();
		retObj.setReturnObj( gson.fromJson(itemObj, Item.class));
		return retObj;
	}


}
