package com.grandst.whiplash.api;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.StringEntity;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.grandst.whiplash.Whiplash;
import com.grandst.whiplash.bean.OrderItem;
import com.grandst.whiplash.util.JsonCleaner;
import com.grandst.whiplash.util.WhiplashReturn;

public class OrderItemService {
	
	public static WhiplashReturn getOrderItems(Whiplash w) throws ClientProtocolException, ParseException, IOException{
		return parseOrderItemListJson(API.get("/order_items.json/", w));
	}
	public static WhiplashReturn getOrderItemById(Whiplash w, long orderItemId) throws ClientProtocolException, ParseException, IOException{
		return parseOrderItemJson(API.get("/order_items/"+orderItemId, w));
	}
	public static WhiplashReturn createOrderItem(Whiplash w, StringEntity jsonObj) throws ClientProtocolException, ParseException, IOException{
		return parseOrderItemJson(API.post("/order_items", w, jsonObj, 3000,30000));
	}
	
	private static WhiplashReturn parseOrderItemListJson(String apiJson) throws  ParseException{
		WhiplashReturn retObj = new WhiplashReturn();
		if(retObj.tryParseError(apiJson))
			return retObj;
		apiJson = JsonCleaner.cleanDateFormat(apiJson); // ugh! only Java 7+ supports date formats with Timezone X eg. yyyy-MM-dd'T'HH:mm:ssX so we need to change the format to yyyy-MM-dd'T'HH:mm:ssZ
		ArrayList<OrderItem> retList = new ArrayList<OrderItem>();
		JsonParser parser = new JsonParser();
		GsonBuilder gb = new GsonBuilder()
			.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		Gson gson = gb.create();
		JsonArray itemArray = parser.parse(apiJson).getAsJsonArray();
		for(int i = 0; i < itemArray.size(); i++){
			OrderItem o = new OrderItem();
			o = gson.fromJson(itemArray.get(i).getAsJsonObject(), OrderItem.class);
			retList.add(o);
		}
		retObj.setReturnObj(retList);
		return retObj;	
	}
	
	private static WhiplashReturn parseOrderItemJson(String apiJson) throws  ParseException{
		WhiplashReturn retObj = new WhiplashReturn();
		if(retObj.tryParseError(apiJson))
			return retObj;
		apiJson = JsonCleaner.cleanDateFormat(apiJson); // ugh! only Java 7+ supports date formats with Timezone X eg. yyyy-MM-dd'T'HH:mm:ssX so we need to change the format to yyyy-MM-dd'T'HH:mm:ssZ
		JsonParser parser = new JsonParser();
		GsonBuilder gb = new GsonBuilder()
			.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		Gson gson = gb.create();
		JsonObject itemObj = parser.parse(apiJson).getAsJsonObject();
		retObj.setReturnObj( gson.fromJson(itemObj, OrderItem.class));
		return retObj;
	}
	
	

}
